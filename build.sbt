val stalactite = (project in file("stalactite-macro")).settings(
  MacroParadise,
  libraryDependencies ++= Seq(
    "org.scala-lang"       % "scala-compiler" % scalaVersion.value % "provided",
    "org.scala-lang"       % "scala-reflect"  % scalaVersion.value % "provided",
    "org.scalaz"           %% "scalaz-core"   % "7.2.16"           % "test",
    "com.chuusai"          %% "shapeless"     % "2.3.2"            % "test",
    "org.ensime"           %% "pcplod"        % "1.2.1"            % "test",
    "com.github.mpilquist" %% "simulacrum"    % "0.11.0"           % "test",
    "com.typesafe.play"    %% "play-json"     % "2.6.7"            % "test"
  )
)

val deriving = (project in file("scalaz-deriving")).settings(
  KindProjector,
  name := "scalaz-deriving",
  libraryDependencies ++= Seq(
    "io.frees"    %% "iotaz-core"  % "0.3.2",
    "com.chuusai" %% "shapeless"   % "2.3.2",
    "org.scalaz"  %% "scalaz-core" % "7.2.16"
  )
)

val xmlformat = (project in file("examples/xmlformat"))
  .dependsOn(stalactite)
  .settings(
    MacroParadise,
    scalacOptions -= "-Yno-imports",
    libraryDependencies ++= Seq(
      "org.scalaz"             %% "scalaz-core" % "7.2.16",
      "com.chuusai"            %% "shapeless"   % "2.3.2",
      "com.github.mpilquist"   %% "simulacrum"  % "0.11.0",
      "org.scala-lang.modules" %% "scala-xml"   % "1.0.6"
    )
  )

// root project
publishLocal := {}
publish := {}
