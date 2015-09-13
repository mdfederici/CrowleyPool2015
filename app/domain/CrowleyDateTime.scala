package domain

import java.time.LocalDateTime

object CrowleyDateTime {
  def now: LocalDateTime = {
    LocalDateTime.now()
//    LocalDateTime.of(2015, 9, 17, 20, 19)
  }
}
