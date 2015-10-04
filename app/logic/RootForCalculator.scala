package logic

import domain.{WhoShouldIRootForInTheMoney, WhoShouldIRootFor, Game, User}

object RootForCalculator {
  def whoShouldIRootFor(game: Game, currentUser: User, opponent: User) : WhoShouldIRootFor = {
    val currentWeek = Game.getWeek(game)
    val myPick = currentUser.pick(currentWeek, game)
    val opponentPick = opponent.pick(currentWeek, game)

    val diff =
      myPick.selectedTeam match {
        case f if f == opponentPick.selectedTeam => myPick.confidence.rating - opponentPick.confidence.rating
        case _ => myPick.confidence.rating + opponentPick.confidence.rating
      }

    val choice =
      diff match {
        case x if x < 0 => myPick.selectedTeam match {
          case h if h == game.homeTeam => game.awayTeam
          case a if a == game.awayTeam => game.homeTeam
        }
        case _ => myPick.selectedTeam
      }

    WhoShouldIRootFor(currentWeek, opponent, game, choice, diff)
  }

  //todo: some duplicated code here
  def whoShouldIRootForForMoney(game: Game, currentUser: User, allUsers: Set[User]) : WhoShouldIRootForInTheMoney = {
    val currentWeek = Game.getWeek(game)
    val myPick = currentUser.pick(currentWeek, game)

    val inTheMoneyUsers = UserCalculator.inTheMoneyUsers(allUsers)
    val rootFors = inTheMoneyUsers.map(x => whoShouldIRootFor(game, currentUser, x))
    val totalDiff = rootFors.map(x => x.diff).sum

    val choice =
      totalDiff match {
        case x if x < 0 => myPick.selectedTeam  match {
          case h if h == game.homeTeam => game.awayTeam
          case a if a == game.awayTeam => game.homeTeam
        }
        case _ => myPick.selectedTeam
      }

    WhoShouldIRootForInTheMoney(currentWeek, inTheMoneyUsers, game, choice, totalDiff)
  }
}