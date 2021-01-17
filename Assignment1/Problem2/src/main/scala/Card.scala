object Card{
  val cardRanks = Map[Char,Int](
    '2'->2,'3'->3,'4'->4,'5'->5,
    '6'->6,'7'->7,'8'->8,'9'->9,'T'->10,
    'J'->11,'Q'->12,'K'->13,'A'->14)
  val ranks = Map[String,Int](""->0,"onepair"->1,"twopair"->2,"tok"->3,"strait"->4,
    "flush"->5,"fullhouse"->6,"fok"->7,"straitflush"->8,"royalflush"->9)
}
