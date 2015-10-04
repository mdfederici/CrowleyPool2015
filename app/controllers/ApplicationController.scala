package controllers

import java.time.LocalDateTime
import javax.inject.Inject

import domain._
import domain.data.PickRepository
import logic.RootForCalculator
import play.api.mvc._

class ApplicationController @Inject() (userContext: UserContext, repository: PickRepository) extends Controller {

  if(userContext.users.isEmpty)
    userContext.refreshUsers()

  def index = Action { request =>
    standardAction(request.session, (currentUser) => {
      Redirect("/seasonSummary")
    })
  }

  def seasonSummary = Action { request =>
    standardAction(request.session, (currentUser) => {
      Ok(views.html.seasonsummary(userContext.users.get))
    })
  }

  def weekPicks(week: Int) = Action { request =>
    standardAction(request.session, (currentUser) => {
      val targetWeek = Week.getWeek(week)
      Ok(views.html.weekpicks(currentUser, targetWeek))
    })
  }

  def mobileWeekPicks() = Action { request =>
    standardAction(request.session, (currentUser) => {
      val currentWeek = Week.currentWeek
      implicit val ordering = new DateTimeOrdering()
      val thursdayGame = Game.getGames(currentWeek).sortBy(x => x.kickoff).head

      val weekAdjustment =
        CrowleyDateTime.now match {
          case x if thursdayGame.kickoff.isAfter(x) => 0
          case _ => 1
        }
      val weekNumber = Week.currentWeek.number + weekAdjustment
      Ok(views.html.mobileweekpicks(currentUser, Week.getWeek(weekNumber)))
    })
  }

  def weekSummaryRouter(week: Int) = Action { request =>
    standardAction(request.session, (currentUser) => {
      val targetWeek = Week.getWeek(week)

      currentUser.picks(targetWeek).exists(x => x.isLocked) match {
        case true => Ok(views.html.weeksummary(userContext.users.get, targetWeek, currentUser))
        case false => Ok(views.html.weekpicks(currentUser, targetWeek))
      }
    })
  }

  def weekSummary(week: Int) = Action { request =>
    standardAction(request.session, (currentUser) => {
      val targetWeek = Week.getWeek(week)
      Ok(views.html.weeksummary(userContext.users.get, targetWeek, currentUser))
    })
  }

  def updatePickWinner(gameShortString: String, week: Int, winner: String) = Action { request =>
    standardAction(request.session, (currentUser) => {
      val targetWeek = Week.getWeek(week)
      val game: Game = Game.getGame(targetWeek, gameShortString)
      val winningTeam = Team.allTeams.find(x => x.abbreviation == winner).get

      println(s"User $currentUser is asking to change the chosen winner of $game to $winner")
      val picks = currentUser.picks(targetWeek)
      val existingPick = picks.find(x => x.game.shortString == gameShortString).get
      repository.savePick(currentUser, new Pick(existingPick.game, winningTeam, existingPick.confidence))

      userContext.refreshUsers()
      Ok("ok")
    })
  }

  def updatePickConfidence(gameShortString: String, week: Int, confidence: Int) = Action { request =>
    standardAction(request.session, (currentUser) => {
      val targetWeek = Week.getWeek(week)
      val game: Game = Game.getGame(targetWeek, gameShortString)

      println(s"User $currentUser is asking to change confidence of $game to $confidence")
      val picks = currentUser.picks(targetWeek)
      val existingPick = picks.find(x => x.game.shortString == gameShortString).get
      repository.savePick(currentUser, new Pick(existingPick.game, existingPick.selectedTeam, Confidence(confidence)))

      val movingDown = existingPick.confidence.rating > confidence
      val (picksWhichNeedUpdating, modifier) =
        movingDown match {
          case true => (picks.filter(x => x.confidence.rating < existingPick.confidence.rating && x.confidence.rating >= confidence), 1)
          case false => (picks.filter(x => x.confidence.rating > existingPick.confidence.rating && x.confidence.rating <= confidence), -1)
        }

      picksWhichNeedUpdating.toSeq.sortBy(x => x.confidence.rating).foreach(p => {
        val newConfidence = p.confidence.rating + modifier
        repository.savePick(currentUser, new Pick(p.game, p.selectedTeam, Confidence(newConfidence)))
      })

      userContext.refreshUsers()
      Ok("ok")
    })
  }

  def whoShouldIRootFor(week: Int, username: Option[String]) = Action { request =>
    standardAction(request.session, (currentUser) => {
      val targetWeek = Week.getWeek(week)

      currentUser.picks(targetWeek).exists(x => x.isLocked) match {
        case true => {
          val vsUser =
            username match {
              case None => None
              case Some(u) => {
                val user = userContext.users.get.find(x => x.username.equalsIgnoreCase(u)).get
                val rootFor = Game.getGames(targetWeek).map(x => RootForCalculator.whoShouldIRootFor(x, currentUser, user))
                Some((user, rootFor.filter(x => x.game.winner.isEmpty)))
              }
            }

          val vsMoney =
            username match {
              case None => {
                val rootFor = Game.getGames(targetWeek).map(x => RootForCalculator.whoShouldIRootForForMoney(x, currentUser, userContext.users.get))
                Some(rootFor.filter(x => x.game.winner.isEmpty))
              }
              case Some(u) => None
            }

          Ok(views.html.whoshouldirootfor(userContext.users.get, currentUser, targetWeek, vsUser, vsMoney))
        }
        case false => Ok(views.html.weekpicks(currentUser, targetWeek))
      }
    })
  }

  def attemptLogin(username: String, password: String) = Action { request =>
    userContext.users.get.find(x => x.username.equalsIgnoreCase(username) && x.password == password) match {
      case Some(user) => {
        Redirect("/seasonSummary").withSession(
          "CurrentUser" -> user.username
        )
      }
      case None => Ok(views.html.login("Invalid username or password."))
    }
  }

  def standardAction(session: Session, actionFunction: User => Result): Result = {
    val currentUserOption: Option[User] = getCurrentUser(session)
    currentUserOption match {
      case Some(currentUser) => actionFunction(currentUser)
      case None => Ok(views.html.login(null)) //todo: figure out how to do this with option...
    }
  }

  private def getCurrentUser(session: Session): Option[User] = {
    session.get("CurrentUser") match {
      case Some(username) => userContext.users.get.find(x => x.username == username)
      case None => None
    }
  }
}

class DateTimeOrdering extends Ordering[LocalDateTime] {
  def compare(x: LocalDateTime, y: LocalDateTime): Int = {
    x.compareTo(y)
  }
}