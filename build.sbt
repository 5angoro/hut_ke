name := """hutke"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.16"

libraryDependencies += guice
Compile / playEbeanModels := Seq("models.*")

playEbeanVersion := "8.2.0"
libraryDependencies += ehcache
libraryDependencies ++= Seq(
  jdbc,
  javaWs,
  javaJdbc,
  "com.mysql" % "mysql-connector-j" % "9.3.0"
)