import com.mle.sbt.win.WinKeys
import com.mle.sbt.win.WinPlugin
import com.mle.sbt.GenericPlugin
import com.mle.sbt.GenericKeys
import com.mle.sbt.azure.AzurePlugin
import com.mle.sbtplay.PlayProjects
import com.typesafe.sbt.SbtNativePackager
import sbt.Keys._
import sbt._

object PlayBuild extends Build {
  lazy val p = PlayProjects.plainPlayProject("p")
    .enablePlugins(SbtNativePackager).settings(commonSettings: _*)

  lazy val commonSettings = WinPlugin.windowsSettings ++ GenericPlugin.confSettings ++
    AzurePlugin.azureSettings ++ Seq(
    organization := "com.github.malliina",
    version := "0.0.3",
    scalaVersion := "2.11.6",
    retrieveManaged := false,
    fork in Test := true,
    updateOptions := updateOptions.value.withCachedResolution(true),
    resolvers += Resolver.jcenterRepo,
    libraryDependencies ++= Seq(
      "com.github.malliina" %% "play-base" % "0.5.0",
      "com.github.malliina" %% "util-rmi" % "1.9.0"
    ),
    mainClass := Some("com.mle.play.Starter"),
    exportJars := true,
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
    scalacOptions ++= Seq(
      "-target:jvm-1.8",
      "-deprecation",
      "-encoding", "UTF-8",
      "-unchecked",
      "-feature",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Xfatal-warnings",
      "-Xlint",
      "-Yno-adapted-args",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen"),
    WinKeys.upgradeGuid := "5EC7F244-24F9-4E1C-B19D-591626C50F02",
    GenericKeys.manufacturer := "Me"
  )
}
