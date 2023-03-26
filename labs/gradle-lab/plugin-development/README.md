# Gradle Plugin Development
This sample plugin is based on one of Gradle training sessions, `Plugin Development`, which is provided at https://gradle.com/training/

There are below 2 Gradle projects, `coveragelock` and `pluginmanualtest`.

## `coveragelock`
This Gradle project implements Gradle plugin, `CoverageLockInPlugin` with id `me.dwywdo.lab.gradle.coveragelock`.

- It defines an Gradle extension, `CoverageLockInExtension`, which is used to configure tasks from `Jacoco` plugin. As a result, we can configure this plugin's configuration as below.
  ```kotlin
  coverageLockIn {
    coverageFile.set(
        layout.projectDirectory.file("coverage_lock_in.txt")
    )
    onCi.set(true)
  }
  ```
  - This plugin configures 2 tasks from `Jacoco` plugin based on above information.
    1. `jacocoTestReport`
    2. `jacocoTestCoverageVerification`
  - If no configuration is declared, it will use default values that are set when initialized for `Jacoco` tasks configuration.
- It defines its own Gradle task, `CoverageLockInTask`, which locks in current code base's test coverage gain based on the report from Jacoco plugin as a text file. (`coverage_lock_in.txt`)
  - It checks current build conditions based on `CoverageLockInExtension` configuration.
  - It parses the XML reports created by `Jacoco` tasks, and creates / updates the text file.

This module also has integration tests with 3 test projects for different cases.
1. When this plugin is only applied in Gradle project.
2. When this plugin is applied and lock-in text file exists.
3. 2 + When the result of test coverage gain is higher than previous test coverage gain, which was locked in the text file.

## pluginmanualtest
This Gradle projects is a simple Java project, with `applicaton` plugin applied. 

You could play with `coveragelock` plugin here for you to understand how it works.