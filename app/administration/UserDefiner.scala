package administration

import domain.User
import domain.data.PickRepository
import play.api.Play

object UserDefiner {
  def defineUsers(repository: PickRepository): Set[User] = {
    val allPicks = repository.loadPicks()

    val users =
      Set(
        User("mfed", Play.current.configuration.getString("password.mfed").get, "Michael", "Federici", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks("mfed"))),
        User("ppam", Play.current.configuration.getString("password.ppam").get, "Praveen", "Pamidimukkala", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks( "ppam"))),
        User("RB2", Play.current.configuration.getString("password.RB2").get, "Robert", "Brown", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "RB2"))),
        User("byoungs", Play.current.configuration.getString("password.byoungs").get, "Brian", "Youngs", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "byoungs"))),
        User("Wbricker3", Play.current.configuration.getString("password.Wbricker3").get, "Will", "Bricker", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks( "Wbricker3"))),
        User("jamesflorio13", Play.current.configuration.getString("password.jamesflorio13").get, "James", "Florio", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "jamesflorio13"))),
        User("ADG", Play.current.configuration.getString("password.ADG").get, "Allan", "DGracia", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "ADG"))),
        User("dcrowley", Play.current.configuration.getString("password.dcrowley").get, "Daniel", "Crowley", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "dcrowley"))),
        User("Rmacnamara", Play.current.configuration.getString("password.Rmacnamara").get, "Ryan", "MacNamara", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "Rmacnamara"))),
        User("LeaveYourFriendsBehind", Play.current.configuration.getString("password.LeaveYourFriendsBehind").get, "Peter", "Bridgers", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks( "LeaveYourFriendsBehind"))),
        User("skatuska", Play.current.configuration.getString("password.skatuska").get, "Steve", "Katuska", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks( "skatuska"))),
        User("rcolonel", Play.current.configuration.getString("password.rcolonel").get, "Robert", "Colonel", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "rcolonel"))),
        User("verberkmoes", Play.current.configuration.getString("password.verberkmoes").get, "John", "Verberkmoes", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "verberkmoes"))),
        User("dcrosen", Play.current.configuration.getString("password.dcrosen").get, "David", "Rosen", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks( "dcrosen"))),
        User("joseph.woodlief", Play.current.configuration.getString("password.joseph.woodlief").get, "Joseph", "Woodlief", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "joseph.woodlief"))),
        User("dhobe1", Play.current.configuration.getString("password.dhobe1").get, "Dylan", "Hobe", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "dhobe1"))),
        User("ArashA", Play.current.configuration.getString("password.ArashA").get, "Arash", "Alidoust", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "ArashA"))),
        User("howardmaya", Play.current.configuration.getString("password.howardmaya").get, "Howie", "Maya", hasPaidEntry = true, DataGenerator.makeUserMetaData(allPicks( "howardmaya"))),
        User("ChrisWard", Play.current.configuration.getString("password.ChrisWard").get, "Chris", "Ward", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "ChrisWard"))),
        User("KeyurPatel", Play.current.configuration.getString("password.KeyurPatel").get, "Keyur", "Patel", hasPaidEntry = false, DataGenerator.makeUserMetaData(allPicks( "KeyurPatel")))
      )

    users
  }
}
