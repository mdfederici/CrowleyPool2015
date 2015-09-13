package administration

import domain.{User, UserContext}
import domain.data.Repository

class GlobalUserContext extends UserContext {
  var users: Option[Set[User]] = None

  def refreshUsers() = {
    users = Some(UserDefiner.defineUsers(new Repository()))
  }
}
