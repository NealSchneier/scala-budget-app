

object BudgetProgram extends CsvReader {
  def main(args: Array[String]): Unit = {

   parse("transactions-jan-20.csv")
  }
}

