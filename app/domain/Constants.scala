package domain

object Constants {
  // Connection Info
  val MongoDbServer = "ec2-52-1-233-182.compute-1.amazonaws.com"
  val MongoDbPort = 27017
  val DataStoreName = "CrowleyPool2015"

  // Collection Names
  val WeekCollectionName = "Week"
  val WeeklyMetaDataCollectionName = "WeeklyMetaData"
  val UserMetaDataCollectionName = "UserMetaData"
  val UserCollectionName = "User"
  val TeamCollectionName = "Team"
  val PickCollectionName = "Pick"
  val GameCollectionName = "Game"
  val ConfidenceCollectionName = "Confidence"
}
