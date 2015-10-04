package logic

import domain.User

object UserCalculator {
  def inTheMoneyUsers(allUsers: Set[User]) : Seq[User] = {
    val inTheMoneyUsers = allUsers.toSeq.sortBy(x => x.metaData.pointsEarned()).reverse.take(3)
    inTheMoneyUsers
  }
}
