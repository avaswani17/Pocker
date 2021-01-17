package billofmaterial

trait Widget
case class Rectangle(position_x:Int,position_y:Int,width:Int,height:Int) extends Widget
case class Square(position_x:Int,position_y:Int,width:Int) extends Widget
case class Ellipse(position_x:Int,position_y:Int,horizontal_diameter:Int,vertical_diameter:Int) extends Widget
case class Circle(position_x:Int,position_y:Int,diameter:Int) extends Widget
case class Textbox(position_x:Int,position_y:Int,width:Int,height:Int,text:Option[String]=None) extends Widget
