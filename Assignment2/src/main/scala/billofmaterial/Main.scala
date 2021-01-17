package billofmaterial

object Main extends App {

  val bill = new BillOfMaterial

  //testing will valid input
  print(bill.generateBill(rectangle = Some(Rectangle( 10, 10,30,40)),
    square = Some(Square( 15,30,35)),
    ellipse = Some(Ellipse(100,150,300,200)),
    circle = Some(Circle(1,1,300)),
    textbox = Some(Textbox(5, 5,100,200,Some("sample text")))))

  //testing with invalid input
  println(bill.generateBill(rectangle = Some(Rectangle(- 10, 10,30,40))))
}
