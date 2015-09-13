package modules

import administration.GlobalSimulationContext
import com.google.inject.AbstractModule
import domain.UserContext
import domain.data.{PickRepository, Repository}
import net.codingwell.scalaguice.ScalaModule

class CrowleyPoolModule extends AbstractModule with ScalaModule {
  def configure() {
    bind[UserContext].to[GlobalSimulationContext].asEagerSingleton()
    bind[PickRepository].to[Repository].asEagerSingleton()
  }
}
