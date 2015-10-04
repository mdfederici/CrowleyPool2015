package domain.whoshouldirootfor

import domain.{Team, User, Game, Week}

case class WhoShouldIRootFor(week: Week, opponent: User, game: Game, rootFor: Team, diff: Option[Int] = None, reason: Option[String] = None) {
  override def toString: String = {
    s"You should root for ${rootFor.abbreviation} in ${game.shortString} from diff ${diff.get}."
  }
}