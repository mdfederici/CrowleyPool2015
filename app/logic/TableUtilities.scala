package logic

object TableUtilities {
  def verticalDivider(index: Int, userCount: Int): String = {
    val result: String =
      index match {
        case 0 => "<td rowspan=\"" + userCount + "\" class=\"vertical-divider\"></td>"
        case _ => ""
      }
    result
  }

  //todo: highlight mathematically eliminated
  def inTheMoney(index: Int): String = {
    index match {
      case 0 => "inmoney-gold"
      case 1 => "inmoney-silver"
      case 2 => "inmoney-bronze"
      case _ => ""
    }
  }
}
