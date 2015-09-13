package domain

trait UserContext {
  var users: Option[Set[User]]
  def refreshUsers(): Unit
}
