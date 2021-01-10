package worldline

import java.time.{DayOfWeek, LocalDate}

object CountSundays extends App {
  var count = 0
  for {
    year <- 1901 to 2000
    month <- 1 to 12
    if (LocalDate.of(year, month, 1).getDayOfWeek() == DayOfWeek.SUNDAY)
  } count += 1
  println(s"total number of sundays are = $count")
}
