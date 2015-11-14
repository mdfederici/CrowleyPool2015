package domain

/**
 * Created by Praveen on 11/14/2015.
 */
class SideBet(p: User,
              r: User,
              a: Int,
              w: Week,
              wi: User[Option],
              l: User[Option]
               ) {

  private var _proposer: User = p
  private var _recipient: User = r
  private var _amount: Int = a
  val _week: Week = w
  private var _winner: Option[User] = wi
  private var _loser: Option[User] =  l


  def proposer = _proposer
  def recipient = _recipient
  def amount = _amount
  def week = _week
  def winner = _winner
  def loser = _loser

  def proposer_(proposer:User): Unit = {
    isSamePerson match {
      case true => throw new Exception (s"$proposer, You cannot propose a sidebet to yourself.")
      case false =>
    }
    
    _proposer = proposer
  }

  def recipient_(recipient:User): Unit = {
    isSamePerson match {
      case true => throw new Exception (s"$recipient, You cannot receive a sidebet from yourself.")
      case false =>
    }

    _recipient = recipient
  }

  def amount_(amount:Int): Unit = {
    if (amount <= 0)
        throw new Exception (s"You cannot have a nonpositive sidebet of $amount.")

    _amount = amount
  }

  def winner_(winner:User): Unit = {
    _winner = Some(winner)
  }

  def loser_(loser:User): Unit = {
    _loser = Some(loser)
  }

  def isSamePerson:Boolean =
  {
    if (_proposer.equals(_recipient))
      return true
    else
      return false
  }

}