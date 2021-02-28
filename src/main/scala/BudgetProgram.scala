

object BudgetProgram extends CsvReader {
  def main(args: Array[String]): Unit = {

    val janItems = parse("transactions-jan-20.csv")
    val income = janItems
      .filter( x => x.category.toLowerCase.contains("income") || x.category.toLowerCase.contains("Paycheck")) //handle reimbursements
    val spending = janItems
      .filter( x => !x.category.toLowerCase.contains("income"))
      .filter( x => !x.category.toLowerCase.contains("paycheck"))
      .filter( x => !x.category.toLowerCase.contains("hide from budgets & trends"))//handle reimbursements

  }
}

