enablePlugins(ScalaJSPlugin)

name := "BackLogger"

version := "1.0"

scalaVersion := "2.12.2"

scalaJSModuleKind := ModuleKind.CommonJSModule

artifactPath in (Compile, fastOptJS) := (artifactPath in (Compile, fullOptJS)).value

