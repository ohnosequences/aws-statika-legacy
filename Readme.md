## aws-statika project

This the part of [statika](https://github.com/ohnosequences/statika) project which contains all Amazon Web Services specific abstractions, such as

* [AWSCredentials](docs/code/package.md)
* [Metadata](docs/code/Metadata.md)
* [AbstractAMI](docs/code/AMI.md)
* [AWSDistribution](docs/code/AWSDistribution.md)

### Usage

To add a **direct** dependency in your sbt-project, add these lines:

```scala
resolvers += "Era7 maven releases" at "http://releases.era7.com.s3.amazonaws.com"

libraryDependencies += "ohnosequences" %% "aws-statika" % "0.5.0"
```

Normally, you should use [sbt-statika](https://github.com/ohnosequences/sbt-statika) plugin instead. It adds this dependency with the latest stable version and adds other things, which are kind of standardized configuration for statika bundles and distributions.

### General documentation

Besides documentation for the components of the library (see links in the beginning), This repo contains general documentation on statika usage related to AWS:

* [How to set up statika for a new Amazon account](docs/how-to-set-up-statika.md)
* [How to write a distribution](docs/how-to-write-a-distribution.md)
* [How to apply a bundle to an EC2 instance](docs/how-to-apply-a-bundle.md)

See also [statika core library](https://github.com/ohnosequences/statika) documentation, if you haven't yet, it describes general ideas and explains how to write bundles (and what they are, of course).
