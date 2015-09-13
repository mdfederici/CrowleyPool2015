package domain

import java.time.LocalDate

import logic.DateCalculator

case class Week(number: Int,
                tuesdayStart: LocalDate) {

  lazy val lockDateTime = DateCalculator.calculateThursdayLockTime(this)
}

object Week {
  def getWeek(weekNumber: Int): Week = {
    Week.allWeeks.find(x => x.number == weekNumber).getOrElse(throw new Exception(s"Could not find week $weekNumber. Valid weeks are week 1 - week 17."))
  }

  val Week1: Week = Week(1, LocalDate.of(2015, 9, 8))
  val Week2: Week = Week(2, LocalDate.of(2015, 9, 15))
  val Week3: Week = Week(3, LocalDate.of(2015, 9, 22))
  val Week4: Week = Week(4, LocalDate.of(2015, 9, 29))
  val Week5: Week = Week(5, LocalDate.of(2015, 10, 6))
  val Week6: Week = Week(6, LocalDate.of(2015, 10, 13))
  val Week7: Week = Week(7, LocalDate.of(2015, 10, 20))
  val Week8: Week = Week(8, LocalDate.of(2015, 10, 27))
  val Week9: Week = Week(9, LocalDate.of(2015, 11, 3))
  val Week10: Week = Week(10, LocalDate.of(2015, 11, 10))
  val Week11: Week = Week(11, LocalDate.of(2015, 11, 17))
  val Week12: Week = Week(12, LocalDate.of(2015, 11, 24))
  val Week13: Week = Week(13, LocalDate.of(2015, 12, 1))
  val Week14: Week = Week(14, LocalDate.of(2015, 12, 8))
  val Week15: Week = Week(15, LocalDate.of(2015, 12, 15))
  val Week16: Week = Week(16, LocalDate.of(2015, 12, 22))
  val Week17: Week = Week(17, LocalDate.of(2015, 12, 29))

  val allWeeks: Seq[Week] = Seq(
    Week1,
    Week2,
    Week3,
    Week4,
    Week5,
    Week6,
    Week7,
    Week8,
    Week9,
    Week10,
    Week11,
    Week12,
    Week13,
    Week14,
    Week15,
    Week16,
    Week17
  )
}