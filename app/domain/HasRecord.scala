package domain

trait HasRecord {
  def record(week: Option[Week] = None): String = s"${wins(week)} - ${losses(week)}"
  def wins(week: Option[Week] = None):Int
  def losses(week: Option[Week] = None):Int
}
