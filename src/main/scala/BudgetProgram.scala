

object BudgetProgram extends CsvReader {
  val neal = "Neal-"
  val lil = "Lil-"
  val nealPercentage = .61
  val lilPercentage = .39
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

    val nealSpending = personsItems(spending, neal)
    val nealIncome = personsItems(income, neal)
    val lilSpending =personsItems(spending, lil)
    val lilIncome = personsItems(income, lil)
    val totalIncome =income.map(x => x.amount).sum
    val totalSpending = spending.map(x => x.amount).sum
    println(s"Total Income ${totalIncome}")
    println(s"Neal Income ${nealIncome}")
    println(s"Lil Income ${lilIncome}")
    println(s"Total Spending ${totalSpending}")
    println(s"Neal Spending ${nealSpending}")
    println(s"Lil Spending ${lilSpending}")
    println(s"Bears Net ${totalIncome + totalSpending}")

    println(s"Lil Spending should be ${totalSpending * lilPercentage}")
    println(s"Neal Spending should be ${totalSpending * nealPercentage}")
    println(s"Lil Spending adjustment ${-(totalSpending * lilPercentage - lilSpending) }")
    println(s"Neal Spending adjustment  ${-(totalSpending * nealPercentage - nealSpending)}")

    println(spending.filter(x => x.accountName.startsWith("Lil-")).map(x => x.amount))
  }


  def personsItems(items: List[LineItem], person: String): BigDecimal = {
    items
      .filter(x => x.accountName.startsWith(person))
      .map(x => x.amount)
      .sum
  }
}


