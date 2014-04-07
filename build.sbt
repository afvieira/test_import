name := "SAPUM"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  javaEbean,
  cache
)     

play.Project.playJavaSettings
