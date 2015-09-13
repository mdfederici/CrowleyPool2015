package administration

import domain.Week
import domain.data.Repository

object Administrator extends App{

  val repository = new Repository()
  val users = DataSimulator.defineUsers(repository)
  users.foreach(user => {
    Week.allWeeks.foreach(week => {
      val picksForWeek = user.metaData.weeklyMetaData.find(x => x._1 == week).getOrElse(throw new Exception(s"Could not find picks for week $week."))
      val picks = picksForWeek._2.picks

      picks.foreach(pick => {
        repository.savePick(user, pick)
      })
    })
  })

  println("Completed setup")

//  DataSimulator.simulateAFewWeeks(users.toSeq)

  println("Done")
}
