val CreateReactClassVersion = "^15.5.1"
val HistoryVersion = "^4.6.1"
val ReactVersion = "^15.5.3"
val ReactReduxVersion = "^5.0.3"
val ReactRouterVersion = "^4.0.0"
val ReactRouterReduxVersion = "next"
val ReduxVersion = "^3.6.0"
val ReduxDevToolsVersion = "^2.13.0"

val StaticTagsVersion = "2.6.1"

val commonSettings = Seq(
  organization := "org.scommons.shogowada",

  crossScalaVersions := Seq("2.12.2", "2.13.1"),
  scalaVersion := "2.12.2",
  scalacOptions ++= Seq(
    "-deprecation", "-unchecked", "-feature", "-Xcheckinit", "-target:jvm-1.8", "-Xfatal-warnings"
  ),

  //resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",

  sonatypeProfileName := "org.scommons",
  publishArtifact := false,
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := sonatypePublishToBundle.value,
  pomExtra := {
    <url>https://github.com/scommons/scalajs-reactjs</url>
        <licenses>
          <license>
            <name>MIT</name>
            <url>https://opensource.org/licenses/MIT</url>
            <distribution>repo</distribution>
          </license>
        </licenses>
        <scm>
          <url>git@github.com:scommons/scalajs-reactjs.git</url>
          <connection>scm:git@github.com:scommons/scalajs-reactjs.git</connection>
        </scm>
        <developers>
          <developer>
            <id>shogowada</id>
            <name>Shogo Wada</name>
            <url>https://github.com/shogowada</url>
          </developer>
          <developer>
            <id>viktorp</id>
            <name>Viktor Podzigun</name>
            <url>https://github.com/viktor-podzigun</url>
          </developer>
        </developers>
  },
  pomIncludeRepository := {
    _ => false
  }
)

lazy val root = (project in file("."))
    .settings(commonSettings: _*)
    .settings(
      crossScalaVersions := Nil, //must be set to Nil on the aggregating project
      skip in publish := true,
      publish := ((): Unit),
      publishLocal := ((): Unit),
      publishM2 := ((): Unit)
    )
    .aggregate(
      core,
      history,
      router,
      routerDom,
      redux,
      reduxDevTools,
      routerRedux,
      exampleCustomVirtualDOM,
      exampleHelloWorld,
      exampleHelloWorldFunction,
      exampleInteractiveHelloWorld,
      exampleLifecycle,
      exampleReduxDevTools,
      exampleReduxMiddleware,
      exampleRouter,
      exampleRouterRedux,
      exampleStyle,
      exampleTodoApp,
      exampleTodoAppRedux,
      exampleTest
    )

lazy val core = project.in(file("core"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-reactjs",
      libraryDependencies ++= Seq(
        "org.scala-js" %%% "scalajs-dom" % "0.9.8",
        "org.scommons.shogowada" %%% "statictags" % StaticTagsVersion
      ),
      npmDependencies in Compile ++= Seq(
        "create-react-class" -> CreateReactClassVersion,
        "react" -> ReactVersion,
        "react-dom" -> ReactVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

lazy val history = project.in(file("history"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-history",
      npmDependencies in Compile ++= Seq(
        "history" -> HistoryVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

lazy val router = project.in(file("router"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-reactjs-router",
      npmDependencies in Compile ++= Seq(
        "react-router" -> ReactRouterVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, history)

lazy val routerDom = project.in(file("router-dom"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-reactjs-router-dom",
      npmDependencies in Compile ++= Seq(
        "react-router-dom" -> ReactRouterVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, router)

lazy val redux = project.in(file("redux"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-reactjs-redux",
      npmDependencies in Compile ++= Seq(
        "react-redux" -> ReactReduxVersion,
        "redux" -> ReduxVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val reduxDevTools = project.in(file("redux-devtools"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-reactjs-redux-devtools",
      npmDependencies in Compile ++= Seq(
        "redux-devtools-extension" -> ReduxDevToolsVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, redux)

lazy val routerRedux = project.in(file("router-redux"))
    .settings(commonSettings: _*)
    .settings(
      name := "scalajs-reactjs-router-redux",
      npmDependencies in Compile ++= Seq(
        "react-router-redux" -> ReactRouterReduxVersion
      ),
      (webpack in(Compile, fastOptJS)) := Seq(),
      (webpack in(Compile, fullOptJS)) := Seq(),
      publishArtifact := true
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, history, router, redux)

val exampleCommonSettings = commonSettings ++ Seq(
  name := "scalajs-reactjs-example",
  scalaJSUseMainModuleInitializer := true,
  (unmanagedResourceDirectories in Compile) += baseDirectory.value / "src" / "main" / "webapp"
)

lazy val exampleCustomVirtualDOM = project.in(file("example") / "custom-virtual-dom")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-custom-virtual-dom"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleHelloWorld = project.in(file("example") / "helloworld")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-helloworld"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleHelloWorldFunction = project.in(file("example") / "helloworld-function")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-helloworld-function"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleInteractiveHelloWorld = project.in(file("example") / "interactive-helloworld")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-interactive-helloworld"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleLifecycle = project.in(file("example") / "lifecycle")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-lifecycle"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleReduxDevTools = project.in(file("example") / "redux-devtools")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-redux-devtools"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, redux, reduxDevTools)

lazy val exampleReduxMiddleware = project.in(file("example") / "redux-middleware")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-redux-middleware"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, redux, reduxDevTools)

lazy val exampleRouter = project.in(file("example") / "router")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-router"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, routerDom)

lazy val exampleRouterRedux = project.in(file("example") / "router-redux")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-router-redux"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, redux, routerDom, routerRedux, reduxDevTools)

lazy val exampleStyle = project.in(file("example") / "style")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-style"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleTodoApp = project.in(file("example") / "todo-app")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-todo-app"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core)

lazy val exampleTodoAppRedux = project.in(file("example") / "todo-app-redux")
    .settings(exampleCommonSettings: _*)
    .settings(
      name += "-todo-app-redux"
    )
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .dependsOn(core, redux)

lazy val exampleTest = project.in(file("example") / "test")
    .configs(IntegrationTest)
    .settings(commonSettings: _*)
    .settings(Defaults.itSettings: _*)
    .settings(
      name := "scalajs-reactjs-example-test",
      libraryDependencies ++= Seq(
        "org.eclipse.jetty" % "jetty-server" % "9.+",

        "org.scalatestplus" %% "selenium-3-141" % "3.2.2.0",
        "org.scalatest" %% "scalatest" % "3.2.2"
      ).map(_ % "it,test"),

      parallelExecution in Test := false,
      parallelExecution in IntegrationTest := false,

      javaOptions ++= Seq(
        s"-Dtarget.path.custom-virtual-dom=${(crossTarget in exampleCustomVirtualDOM).value}",
        s"-Dtarget.path.helloworld=${(crossTarget in exampleHelloWorld).value}",
        s"-Dtarget.path.helloworld-function=${(crossTarget in exampleHelloWorldFunction).value}",
        s"-Dtarget.path.interactive-helloworld=${(crossTarget in exampleInteractiveHelloWorld).value}",
        s"-Dtarget.path.lifecycle=${(crossTarget in exampleLifecycle).value}",
        s"-Dtarget.path.redux-devtools=${(crossTarget in exampleReduxDevTools).value}",
        s"-Dtarget.path.redux-middleware=${(crossTarget in exampleReduxMiddleware).value}",
        s"-Dtarget.path.router=${(crossTarget in exampleRouter).value}",
        s"-Dtarget.path.router-redux=${(crossTarget in exampleRouterRedux).value}",
        s"-Dtarget.path.style=${(crossTarget in exampleStyle).value}",
        s"-Dtarget.path.todo-app=${(crossTarget in exampleTodoApp).value}",
        s"-Dtarget.path.todo-app-redux=${(crossTarget in exampleTodoAppRedux).value}"
      ),
      (test in IntegrationTest) := (test in IntegrationTest).dependsOn(
        webpack in fastOptJS in Compile in exampleCustomVirtualDOM,
        webpack in fastOptJS in Compile in exampleHelloWorld,
        webpack in fastOptJS in Compile in exampleHelloWorldFunction,
        webpack in fastOptJS in Compile in exampleInteractiveHelloWorld,
        webpack in fastOptJS in Compile in exampleLifecycle,
        webpack in fastOptJS in Compile in exampleReduxDevTools,
        webpack in fastOptJS in Compile in exampleReduxMiddleware,
        webpack in fastOptJS in Compile in exampleRouter,
        webpack in fastOptJS in Compile in exampleRouterRedux,
        webpack in fastOptJS in Compile in exampleStyle,
        webpack in fastOptJS in Compile in exampleTodoApp,
        webpack in fastOptJS in Compile in exampleTodoAppRedux
      ).value,
      fork := true
    )
