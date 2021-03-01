

object BearsNumbers {

  val neal = "Neal-"
  val lil = "Lil-"
  val bears = "Bears-"
  val nealYearly = 138400
  val nealBonus = 20000
  lazy val nealYearlyIncome : Int = nealYearly + nealBonus
  val lilYearly = 100000
  val lilBonus = 0
  lazy val lilYearlyIncome : Int = lilYearly + lilBonus
  lazy val yearlyIncome: Int = nealYearly + nealBonus + lilYearly + lilBonus

  def percentage(person: String) : Option[BigDecimal] = {
    person match {
      case "Neal" =>
        val percentage = BigDecimal(nealYearlyIncome) / BigDecimal(yearlyIncome)
        Option(percentage.setScale(3, BigDecimal.RoundingMode.HALF_UP))
      case "Lil" =>
        val percentage = BigDecimal(lilYearlyIncome) / BigDecimal(yearlyIncome)
        Option(percentage.setScale(3, BigDecimal.RoundingMode.HALF_UP))
      case  _ => None
    }
  }
}
