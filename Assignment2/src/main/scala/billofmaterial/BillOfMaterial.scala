package billofmaterial

import java.util.logging.Logger
import scala.util.{Failure, Success, Try}

class BillOfMaterial{

  val logger: Logger = Logger.getLogger(getClass.getSimpleName)

  def generateBillProcess(rectangle: Option[Rectangle] = None,
                          square: Option[Square] = None,
                          ellipse: Option[Ellipse] = None,
                          circle: Option[Circle] = None,
                          textbox: Option[Textbox] = None): Try[String] = Try {
    val str =
      s"""-------------------------------------------
         |Bill of Material
         |-------------------------------------------
         |${if (rectangle.isDefined && areValuesPositive(rectangle.get)) s"Rectangle (${rectangle.get.position_x},${rectangle.get.position_y}) width=${rectangle.get.width} height=${rectangle.get.height}" else ""}
         |${if(square.isDefined) s"Square (${square.get.position_x},${square.get.position_y}) size=${square.get.width}" else ""}
         |${if(ellipse.isDefined) s"Ellipse (${ellipse.get.position_x},${ellipse.get.position_y}) diameterH=${ellipse.get.horizontal_diameter} diameterV=${ellipse.get.vertical_diameter}" else ""}
         |${if(circle.isDefined) s"Circle (${circle.get.position_x},${circle.get.position_y}) size=${circle.get.diameter}" else ""}
         |${if(textbox.isDefined) s"Textbox (${textbox.get.position_x},${textbox.get.position_y}) width=${textbox.get.width} height=${textbox.get.height} ${if(textbox.get.text.isDefined) s"text=${textbox.get.text.get}" else ""}" else ""}
         |""".stripMargin
    str
  }

  def generateBill(rectangle: Option[Rectangle]=None,
                  square : Option[Square]=None,
                  ellipse : Option[Ellipse]=None,
                  circle : Option[Circle]=None,
                  textbox : Option [Textbox]=None) :String = {
    generateBillProcess( rectangle,square,ellipse,circle,textbox) match {
      case Success(s) => s
      case Failure(f) => "+++++Abort+++++"
    }
  }

  def areValuesPositive(widget: Widget): Boolean ={
    widget match { case Rectangle(x,y, w, h ) => if (x>0 && y>0 && w>0 && h>0) true else throw new Exception("")
      case Square(x, y, w ) => if (x>0 && y>0 && w>0) true else throw new Exception("")
      case Ellipse(x,y,h ,v ) => if ( x>0 && y>0 && h> 0 && v>0) true else throw new Exception("")
      case Circle(x,y,d ) => if(x> 0 && y> 0 && d>0) true else throw new Exception("")
      case Textbox( x, y,w,h,t) => if(x> 0 && y> 0 && w> 0 && h>0) true else throw new Exception("")
    }
  }
}
