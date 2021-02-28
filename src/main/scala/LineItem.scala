import java.util.Date

//"Date","Description","Original Description","Amount","Transaction Type","Category","Account Name","Labels","Notes"
case class LineItem(date: Date, description: String, originalDescription: String, amount: BigDecimal, transactionType: String, category: String,
                    accountName: String, labels: String, notes: String)
