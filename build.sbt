name := "scala-budget-app"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Settings.finatraDeps ++ Settings.loggingDeps ++ Settings.unitTestDeps

mainClass in (Compile, run) := Some("BudgetProgram")