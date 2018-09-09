import com.malliina.sbt.GenericKeys
import com.malliina.sbt.GenericKeys.pkgHome
import com.malliina.sbt.win.WinKeys._
import com.malliina.sbt.win.WinPlugin

import scala.sys.process.Process
import scala.util.Try

lazy val native = project.in(file("."))
  .enablePlugins(PlayScala, SbtNativePackager, BuildInfoPlugin)

organization := "com.malliina"
version := "0.1.8"
scalaVersion := "2.12.6"
retrieveManaged := false
fork in Test := true
resolvers += Resolver.jcenterRepo
libraryDependencies ++= Seq(
  "com.lihaoyi" %% "scalatags" % "0.6.7"
)
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
  "-Ywarn-numeric-widen"
)
upgradeGuid := "5EC7F244-24F9-4E1C-B19D-591626C50F02"
GenericKeys.manufacturer := "Me"
WinPlugin.windowsSettings
forceStopOnUninstall := true
winSwExe in Windows := (pkgHome in Windows).value.resolve("WinSW.NET2.exe")
useTerminateProcess := true
buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, "gitHash" -> gitHash)
buildInfoPackage := "com.malliina.pn"

def gitHash: String =
  Try(Process("git rev-parse --short HEAD").lineStream.head).toOption.getOrElse("unknown")
