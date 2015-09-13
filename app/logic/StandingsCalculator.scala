package logic

import domain.User

object StandingsCalculator {
  def distanceFromLeader(target: User, allUsers: Set[User]): Int = {
    val leader = allUsers.toSeq.sortBy(x => x.metaData.pointsEarned()).reverse.head
    val distance = computeDistance(target, leader)
    distance
  }

  def distanceFromPredecessor(target: User, allUsers: Set[User]): Int = {
    val sortedUsers = allUsers.toSeq.sortBy(x => x.metaData.pointsEarned()).reverse
    val targetIndex = sortedUsers.indexOf(target)

    sortedUsers.isDefinedAt(targetIndex - 1) match {
      case true => computeDistance(target, sortedUsers(targetIndex - 1))
      case false => 0
    }
  }

  private def computeDistance(target: User, opponent: User): Int = {
    opponent.metaData.pointsEarned() - target.metaData.pointsEarned()
  }
}
