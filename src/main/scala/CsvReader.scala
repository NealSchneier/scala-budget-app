import scala.collection.mutable.ListBuffer

trait CsvReader {

  def parse(file: String): List[LineItem] = {
    val bufferedSource = io.Source.fromFile(file)
    val items : ListBuffer[LineItem] = ListBuffer[LineItem]()
    for (line <- bufferedSource.getLines()) {
      if (!line.contains("Date")) { // skip the header line
        val cols = line.split("\",\"").map(_.trim.replace("\"", ""))
        items += LineItem(Utils.convertStringToDate(cols(0)), cols(1), cols(2), BigDecimal(cols(3)), cols(4), cols(5), cols(6), cols(7), cols(8))
      }
    }
    items.toList
  }
}
