name := "finagle-example"

version := "0.1"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % "6.27.0",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0",
  "com.escalatesoft.subcut" %% "subcut" % "2.1",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.pegdown" % "pegdown" % "1.5.0" % "test" // for scalatest html reports
)

parallelExecution in Test := false

testOptions in ThisBuild += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")
