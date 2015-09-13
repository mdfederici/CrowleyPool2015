package administration

import domain.{User, UserContext}
import domain.data.Repository

class GlobalSimulationContext extends UserContext {
  var users: Option[Set[User]] = None

  def refreshUsers() = {
    users = Some(DataSimulator.defineUsers(new Repository()))
  }
//  DataSimulator.simulateAFewWeeks(users.toSeq)
}
