package modules

import administration.GlobalUserContext
import com.google.inject.AbstractModule
import domain.UserContext
import domain.data.{PickRepository, Repository}
import net.codingwell.scalaguice.ScalaModule

class CrowleyPoolModule extends AbstractModule with ScalaModule {
  def configure() {
    bind[UserContext].to[GlobalUserContext].asEagerSingleton()
    bind[PickRepository].to[Repository].asEagerSingleton()
  }
}
