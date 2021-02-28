

object BudgetProgram extends CsvReader {
  val neal = "Neal-"
  val lil = "Lil-"
  def main(args: Array[String]): Unit = {

    val janItems = parse("transactions-jan-20.csv")
    val income = janItems
      .filter( x => x.category.toLowerCase.contains("income")
        || x.category.toLowerCase.contains("paycheck")
        || x.category.toLowerCase.contains("reimbursement")) //negate later

    val spending = janItems
      .filter( x => !x.category.toLowerCase.contains("income"))
      .filter( x => !x.category.toLowerCase.contains("paycheck"))
      .filter( x => !x.category.toLowerCase.contains("hide from budgets & trends"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("transfer"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("reimbursement"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("investments"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("Dividend & Cap Gains".toLowerCase())) //Dividend & Cap Gains

    println(s"Total Income ${income.map(x => x.amount).sum}")
    println(s"Neal Income ${personsItems(income, neal)}")
    println(s"Lil Income ${personsItems(income, lil)}")
    println(s"Total Spending ${spending.map(x => x.amount).sum}")
    println(s"Neal Spending ${personsItems(spending, neal)}")
    println(s"Lil Spending ${personsItems(spending, lil)}")
  }


  def personsItems(items: List[LineItem], person: String): BigDecimal = {
    items
      .filter(x => x.accountName.startsWith(person))
      .map(x => x.amount)
      .sum
  }
}


