name := "WordBox"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache
)     

//libraryDependencies += "com.typesafe.slick" %% "slick" % "1.0.1"

play.Project.playScalaSettings