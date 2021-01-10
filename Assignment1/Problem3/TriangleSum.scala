package worldline

object TriangleSum extends App {
  def calculateSum(source:String): Int ={
    var sum = 0
    val contents = scala.io.Source.fromFile(source).getLines()
    var index = 0
    for{
      (line,idx)<-contents.zipWithIndex
    } if(idx==0) {
      index = 0
      sum += line.split(" ")(index).toInt
    }else {
      index = idx-1
      sum += line.split(" ")(index).toInt
    }
    sum
  }
  println(calculateSum("D:\\assignments\\worldline\\triangle1.txt"))
}
