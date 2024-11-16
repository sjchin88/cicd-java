[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/WBsLM5qE)

# Instructions to use

## Checkout repo

Prerequisite: git installed.

Navigate to the directory of your choice.

```shell
git clone https://github.com/CS6510-SEA-F24/hw3-sjchin88
```

## Working with code

This repo use gradle build system.

There should be a gradle/wrapper folder with gradle-wrapper.jar file,

and the following files in the project directory when cloned:

- gradlew
- gradlew.bat
- build.gradle
- settings.gradle

```shell

# To build the code only, output will be in build/lib folder
./gradlew build

# To run the tests with Jacoco coverage report
# build/reports/tests will contain the test report
# build/jacocoHtml will contain the Jacoco test coverage report
./gradlew test

# To run checkstyle (for Google Java coding style check and static code analysis)
# Reports will be at build/reports/checkstyle
./gradlew check

# To generate the java-doc file
# javadoc will be available at build/docs/javadoc
./gradlew javadoc

# To generate the jar package
# Output will be in build/lib folder
./gradlew jar
```

## Packaging for publishing

The build.gradle file use the gpg-agent plugin for signing. More details [here](https://docs.gradle.org/current/userguide/signing_plugin.html#sec:using_gpg_agent)

You need to add the following keys for GPG signing in the gradle.properties file.

Under .gradle folder inside the project root directory.

```shell
signing.gnupg.keyName=<YOUR_GPG_KEY_ID/last 8 char of Your GPG key>
signing.gnupg.passphrase=<YOUR_GPG_PASSPHRASE>
```

Then run the following command, the publishing package should be generated at

build/repos/releases directory.

```shell
./gradlew publish --warn --stacktrace
```

## Potential issues and fix

If exception thrown during packaging caused by
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':compileJava'.

which is caused by

java.lang.IllegalArgumentException: error: invalid source release: xx

where xx is the target java version,

Try change the source and target java version to 17.

## Link to Maven Central Repository

https://central.sonatype.com/artifact/io.github.sjchin88.cs6510.f24/binary-tree

## Note for GitHub Actions output

All artifacts will be upload to each workflow after workflow run.

Check workflow link: https://github.com/CS6510-SEA-F24/hw3-sjchin88/actions/workflows/prepare-production.yml
