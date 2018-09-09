scalaVersion := "2.12.6"
resolvers ++= Seq(
  Resolver.url("malliina bintray sbt", url("https://dl.bintray.com/malliina/sbt-plugins/"))(Resolver.ivyStylePatterns)
)
scalacOptions ++= Seq("-unchecked", "-deprecation")
Seq(
  "com.typesafe.play" % "sbt-plugin" % "2.6.18",
  "com.malliina" %% "sbt-packager" % "2.6.0",
  "com.eed3si9n" % "sbt-buildinfo" % "0.9.0"
) map addSbtPlugin
classpathTypes += "maven-plugin"
