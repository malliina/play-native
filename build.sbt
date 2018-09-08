import com.malliina.sbt.GenericKeys
import com.malliina.sbt.win.WinKeys

lazy val p = project.in(file("."))
  .enablePlugins(PlayScala, SbtNativePackager)

organization := "com.github.malliina"
version := "0.1.0"
scalaVersion := "2.12.6"
retrieveManaged := false
fork in Test := true
resolvers += Resolver.jcenterRepo
mainClass := Some("com.mle.play.Starter")
exportJars := true
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions ++= Seq(
  "-target:jvm-1.8",
  "-deprecation",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-numeric-widen")
WinKeys.upgradeGuid := "5EC7F244-24F9-4E1C-B19D-591626C50F02"
GenericKeys.manufacturer := "Me"
