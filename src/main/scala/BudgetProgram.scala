

object BudgetProgram extends CsvReader {


  def main(args: Array[String]): Unit = {

    val lilPercent = BearsNumbers.percentage("Lil")
    val nealPercent = BearsNumbers.percentage("Neal")
    val janItems = parse("march-2021.csv")
    val income = janItems
      .filter( x => x.category.toLowerCase.contains("income")
        || x.category.toLowerCase.contains("paycheck")
        || x.category.toLowerCase.contains("credit card points")
        || x.category.toLowerCase.contains("reimbursement") //negate later
        || x.category.toLowerCase.contains("Neal") //Remove Gifts for Neal
        || x.category.toLowerCase.contains("Lil")) //Remove Gifts for Lil

    val spending = janItems
      .filter( x => !x.category.toLowerCase.contains("income"))
      .filter( x => !x.category.toLowerCase.contains("paycheck"))
      .filter( x => !x.category.toLowerCase.contains("hide from budgets & trends"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("transfer"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("reimbursement"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("investments"))//handle reimbursements
      .filter( x => !x.category.toLowerCase.contains("credit card points"))
      .filter( x => !x.category.toLowerCase.contains("Dividend & Cap Gains".toLowerCase())) //Dividend & Cap Gains
      .filter( x => !x.category.toLowerCase.contains("Credit Card Payment".toLowerCase))
      .filter( x => !x.category.toLowerCase.contains("Neal".toLowerCase))
      .filter( x => !x.category.toLowerCase.contains("Lil".toLowerCase))

    val nealSpending = personsItems(spending, BearsNumbers.neal) + Adjustments.nealAdjustments
    val nealIncome = personsItems(income, BearsNumbers.neal)
    val lilSpending =personsItems(spending, BearsNumbers.lil) + Adjustments.lilAdjustments
    val lilIncome = personsItems(income, BearsNumbers.lil)
    val bearsIncome = personsItems(income, BearsNumbers.bears)
    val totalIncome =income.map(x => x.amount).sum
    val totalSpending = spending.map(x => x.amount).sum
    println(s"Total Income ${totalIncome}")
    println(s"Neal Income ${nealIncome}")
    println(s"Lil Income ${lilIncome}")
    println(s"Bears Income ${bearsIncome}")
    println(s"Reimbursements Income ${income.filter(x => x.category.toLowerCase.contains("reimbursement")).map( x=> x.amount).sum}")
    println
    println(s"Total Spending ${totalSpending}")
    println(s"Neal Spending ${nealSpending}")
    println(s"Lil Spending ${lilSpending}")
    println
    println(s"Neal Adjustments ${Adjustments.nealAdjustments}")
    println(s"Lil Adjustments ${Adjustments.lilAdjustments}")
    println
    println(s"Bears Net ${totalIncome + totalSpending}")
    println
    println(s"Neal Percentage ${nealPercent.getOrElse(0)}")
    println(s"Lil Percentage ${lilPercent.getOrElse(0)}")
    println
    println(s"Lil Spending should be ${(totalSpending * lilPercent.getOrElse(0)).setScale(2, BigDecimal.RoundingMode.HALF_UP)}")
    println(s"Neal Spending should be ${(totalSpending * nealPercent.getOrElse(0)).setScale(2, BigDecimal.RoundingMode.HALF_UP)}")
    println
    println(s"Lil Spending adjustment ${(-(totalSpending * lilPercent.getOrElse(0) - lilSpending) ).setScale(2, BigDecimal.RoundingMode.HALF_UP)}")
    println(s"Neal Spending adjustment  ${(-(totalSpending * nealPercent.getOrElse(0) - nealSpending)).setScale(2, BigDecimal.RoundingMode.HALF_UP)}")
  }


  def personsItems(items: List[LineItem], person: String): BigDecimal = {
    items
      .filter(x => x.accountName.startsWith(person))
      .map(x => x.amount)
      .sum
  }
}


