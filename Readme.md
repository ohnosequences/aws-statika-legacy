### aws-statika project

The part of [statika](https://github.com/ohnosequences/statika) library which contains all Amazon Web Services specific stuff

### Usage

To add a dependency in your sbt-project, add these lines:

```scala
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

libraryDependencies += "ohnosequences" %% "aws-statika" % "0.3.0"
```
