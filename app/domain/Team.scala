package domain

case class Team(name: String,
                abbreviation: String,
                logoPath: String) {
  override def toString = name
}

object Team {
  val Dolphins: Team = Team("Miami Dolphins", "MIA", "images/logos/dolphins.svg")
  val Jets: Team = Team("New York Jets", "NYJ", "images/logos/jets.svg")
  val Bills: Team = Team("Buffalo Bills", "BUF", "images/logos/bills.svg")
  val Patriots: Team = Team("New England Patriots", "NE", "images/logos/patriots.svg")
  val Bengals: Team = Team("Cincinnati Bengals", "CIN", "images/logos/bengals.svg")
  val Steelers: Team = Team("Pittsburgh Steelers", "PIT", "images/logos/steelers.svg")
  val Browns: Team = Team("Cleveland Browns", "CLE", "images/logos/browns.svg")
  val Ravens: Team = Team("Baltimore Ravens", "BAL", "images/logos/ravens.svg")
  val Titans: Team = Team("Tennessee Titans", "TEN", "images/logos/titans.svg")
  val Texans: Team = Team("Houston Texans", "HOU", "images/logos/texans.svg")
  val Jaguars: Team = Team("Jacksonville Jaguars", "JAX", "images/logos/jaguars.svg")
  val Colts: Team = Team("Indianapolis Colts", "IND", "images/logos/colts.svg")
  val Broncos: Team = Team("Denver Broncos", "DEN", "images/logos/broncos.svg")
  val Chargers: Team = Team("San Diego Chargers", "SD", "images/logos/chargers.svg")
  val Raiders: Team = Team("Oakland Raiders", "OAK", "images/logos/raiders.svg")
  val Chiefs: Team = Team("Kansas City Chiefs", "KC", "images/logos/chiefs.svg")
  val Eagles: Team = Team("Philadelphia Eagles", "PHI", "images/logos/eagles.svg")
  val Redskins: Team = Team("Washington Redskins", "WAS", "images/logos/redskins.svg")
  val Cowboys: Team = Team("Dallas Cowboys", "DAL", "images/logos/cowboys.svg")
  val Giants: Team = Team("New York Giants", "NYG", "images/logos/giants.svg")
  val Vikings: Team = Team("Minnesota Vikings", "MIN", "images/logos/vikings.svg")
  val Lions: Team = Team("Detroit Lions", "DET", "images/logos/lions.svg")
  val Bears: Team = Team("Chicago Bears", "CHI", "images/logos/bears.svg")
  val Packers: Team = Team("Green Bay Packers", "GB", "images/logos/packers.svg")
  val Panthers: Team = Team("Carolina Panthers", "CAR", "images/logos/panthers.svg")
  val Falcons: Team = Team("Atlanta Falcons", "ATL", "images/logos/falcons.svg")
  val Saints: Team = Team("New Orleans Saints", "NO", "images/logos/saints.svg")
  val Buccaneers: Team = Team("Tampa Bay Buccaneers", "TB", "images/logos/buccaneers.svg")
  val Seahawks: Team = Team("Seattle Seahawks", "SEA", "images/logos/seahawks.svg")
  val FourtyNiners: Team = Team("San Francisco 49ers", "SF", "images/logos/49ers.svg")
  val Cardinals: Team = Team("Arizona Cardinals", "ARI", "images/logos/cardinals.svg")
  val Rams: Team = Team("St. Louis Rams", "STL", "images/logos/rams.svg")

  val allTeams: Set[Team] = Set(
    Dolphins,
    Jets,
    Bills,
    Patriots,
    Bengals,
    Steelers,
    Browns,
    Ravens,
    Titans,
    Texans,
    Jaguars,
    Colts,
    Broncos,
    Chargers,
    Raiders,
    Chiefs,
    Eagles,
    Redskins,
    Cowboys,
    Giants,
    Vikings,
    Lions,
    Bears,
    Packers,
    Panthers,
    Falcons,
    Saints,
    Buccaneers,
    Seahawks,
    FourtyNiners,
    Cardinals,
    Rams
  )
}