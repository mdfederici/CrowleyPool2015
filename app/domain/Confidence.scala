package domain

case class Confidence(rating: Int) {

  rating match {
    case x if x < 1 => throw new Exception("Confidence rating cannot be below 1.")
    case x if x > 16 => throw new Exception("Confidence rating cannot be above 16.")
    case _ => Unit
  }
}
