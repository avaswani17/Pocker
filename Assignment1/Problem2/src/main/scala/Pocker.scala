import Card._

object Pocker extends App {

  var p1Rank = ""
  var p2Rank = ""
  var p1RankNumber = 1
  var p2RankNumber = 1
  var p1Score = 0
  var p2Score = 0

  val path = getClass.getResource("/poker.txt").getPath
  readFile(path)

  def calculateWinner(line: String): Unit = {
    p1Rank = ""
    p2Rank = ""
    p1RankNumber = 1
    p2RankNumber = 1
    p1Score = 0
    p2Score = 0
    val twoPlayers = line.split(" ").sliding(5, 5).toList
    //sorted according to the rank
    val p1 = twoPlayers(0)
    val p2 = twoPlayers(1)
    val p1suite = twoPlayers(0).map(_.charAt(1))
    val p2suite = twoPlayers(1).map(_.charAt(1))

    //grouping to group according to the card with number of occurrence
    val sortedP1 = p1.map(_.charAt(0)).groupMapReduce(identity)(_ => 1)(_ + _).toList.sortBy(a => cardRanks(a._1))
    val sortedP2 = p2.map(_.charAt(0)).groupMapReduce(identity)(_ => 1)(_ + _).toList.sortBy(a => cardRanks(a._1))

    //give score to every group based on the their repetition and highest values
    checkPairs(sortedP1, sortedP2)

    //check isConsecutive
    if (sortedP1.sliding(2).count(a => cardRanks(a(0)._1) + 1 == cardRanks(a(1)._1)) == 4) if (ranks(p1Rank) < ranks("strait")) p1Rank = "strait"
    if (sortedP2.sliding(2).count(a => cardRanks(a(0)._1) + 1 == cardRanks(a(1)._1)) == 4) if (ranks(p2Rank) < ranks("strait")) p2Rank = "strait"

    //check same suite
    if (p1suite.forall(_.equals(p1suite.head))) if (p1Rank == "strait") p1Rank = "straitflush" else if (ranks(p1Rank) < ranks("flush")) p1Rank = "flush"
    if (p2suite.forall(_.equals(p2suite.head))) if (p2Rank == "strait") p2Rank = "straitflush" else if (ranks(p2Rank) < ranks("flush")) p2Rank = "flush"

    //check royal flush
    if (p1Rank == "straitflush" && cardRanks(sortedP1.head._1) == 10) p1Rank = "royalflush"
    if (p2Rank == "straitflush" && cardRanks(sortedP2.head._1) == 10) p2Rank = "royalflush"

    //list to array conversion
    val arr1 = sortedP1.toArray
    val arr2 = sortedP2.toArray


    val winner = getWinner(arr1, arr2)
    if (winner == 1) {
      print("Winner:Player 1:")
      (p1.foreach(a => print(s" $a")))
    } else {
      print("Winner:Player 2:")
      p2.foreach(a => print(s" $a"))
    }
    println()
  }

  def getWinner(arr1: Array[(Char, Int)], arr2: Array[(Char, Int)]): Int = {
    val rank = 0
    if (p1Rank == p2Rank) {
      if (p1RankNumber == p2RankNumber) {
        for (i <- arr1.length - 1 to 0 by -1) {
          if (cardRanks(arr1(i)._1) != cardRanks(arr2(i)._1)) {
            if (cardRanks(arr1(i)._1) > cardRanks(arr2(i)._1)) {
              return 1
            } else {
              return 2
            }
          }
        }
      } else if (p1RankNumber > p2RankNumber) return 1 else return 2
    } else if (ranks(p1Rank) > ranks(p2Rank)) return 1 else return 2
    rank
  }

  def checkPairs(sortedP1: List[(Char, Int)], sortedP2: List[(Char, Int)]): Unit = {
    sortedP1.sortBy(_._2).foreach(s => {
      if (s._2 == 2) {
        p1Score += 1; p1RankNumber = cardRanks(s._1)
      }
      if (s._2 == 3) {
        p1Score += 3; p1RankNumber = cardRanks(s._1)
      }
      if (s._2 == 4) {
        p1Score += 8; p1RankNumber = cardRanks(s._1)
      }
    })
    sortedP2.sortBy(_._2).foreach(s => {
      if (s._2 == 2) {
        p2Score += 1; p2RankNumber = cardRanks(s._1)
      }
      if (s._2 == 3) {
        p2Score += 3; p2RankNumber = cardRanks(s._1)
      }
      if (s._2 == 4) {
        p2Score += 8; p2RankNumber = cardRanks(s._1)
      }
    })
    if (p1Score == 1) p1Rank = "onepair"
    if (p2Score == 1) p2Rank = "onepair"

    if (p1Score == 2) p1Rank = "twopair"
    if (p2Score == 2) p2Rank = "twopair"

    if (p1Score == 3) p1Rank = "tok"
    if (p2Score == 3) p2Rank = "tok"

    if (p1Score == 4) p1Rank = "fullhouse"
    if (p2Score == 4) p2Rank = "fullhouse"

    if (p1Score == 8) p1Rank = "fok"
    if (p2Score == 8) p2Rank = "fok"
  }

  def readFile(source: String): Unit = {
    val contents = scala.io.Source.fromFile(source).getLines()
    contents.foreach(calculateWinner(_))
  }

}
