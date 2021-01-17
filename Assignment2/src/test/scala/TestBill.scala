import org.scalatest.flatspec.AnyFlatSpec
import java.util.logging.Logger
import billofmaterial._


class TestBill extends AnyFlatSpec {
  val bill = new BillOfMaterial

  val logger:Logger = Logger.getLogger(getClass.getSimpleName)
  "A Bill or Material for rectangle" should "generate" in {
    val output= bill.generateBill(rectangle = Some(Rectangle(10,10,30,40)))
    assert(output!="+++++Abort+++++")
  }

  "A Bill or Material for rectangle and circle" should "generate" in {
    val output= bill.generateBill(rectangle = Some(Rectangle(10,10,30,40)), circle = Some(Circle(1,1,300)))
    assert(output!="+++++Abort+++++")
  }

  "A Bill or Material for All Widget" should "generate" in {
    val output= bill.generateBill(rectangle = Some(Rectangle(10,10,30,40)),
      square = Some(Square(15,30,35)),
      ellipse = Some(Ellipse(100,150,300,200)),
      circle = Some(Circle(1,1,300)),
      textbox = Some(Textbox(5,5,100,200,Some("sample text"))))
    assert(output!="+++++Abort+++++")
  }

  "A Bill or Material for rectangle and circle" should "abort" in {
    val output= bill.generateBill(rectangle = Some(Rectangle(-10,10,30,40)), circle = Some(Circle(1,1,300)))
    assert(output=="+++++Abort+++++")
  }

}
