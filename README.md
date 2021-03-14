# NVA data model

This is version 2 of the data model, as it entirely differs from the approach 
taken in [version 1](https://github.com/BIBSYSDEV/nva-datamodel-java), the 
decision was taken to separate the code entirely.

The data model comprises the following components:
-   Data Transfer Objects (DTOs) (TODO)

-   Data Access Objects (DAOs) (TODO)

-   Client APIs
    - Java client (TODO)
    - JavaScript client (TODO)

-   Ontological representations for:
    - DTOs (TODO)
    - DAOs (TODO)
    - JSON-LD contexts for the DTOs (TODO)

## Working with the data model

The main point-of-reference for users of the data model is the DTOs; the client
APIs simplify working with these.

The clients can be used via maven/gradle (Java) and npm (JavaScript):
- gradle: ```shell TODO```
- maven: ```shell TODO```
- npm: ```shell TODO```

The DTOs are objects exposed between services, the DAOs are used solely 
internally in NVA when accessing persisted objects.

The structure of the data model can be seen in the [ontology](TODO), which 
provides the necessary overview of what is intended by objects and fields.

## Developing the data model

### Quickstart

```shell
$ git clone <this repository>
$ cd nva-data-model
$ ./gradlew build
```
### Details

#### Java
We use [gradle](https://gradle.org/) as the build system, you should use 
the gradle wrapper to ensure consistent builds. 

The main code is the latest [LTS Java supported by AWS](https://docs.aws.amazon.com/lambda/latest/dg/lambda-runtimes.html), 
this is because the code is designed to be deployed in standard AWS-managed 
runtimes.

The code is separated into modules:
-   ontology
    - ontology
    - JSON-LD contexts

-   external (DTOs, Java)

-   internal (DAOs, Java)

-   client (Java client)

-   client-js (JavaScript)

Static analysis via [checkstyle](https://checkstyle.sourceforge.io/) and [PMD](https://pmd.github.io/) 
ensure that the Java code conforms with NVA expectations, the project 
will *not* build if this is not the case.

[Nebula.lint](https://github.com/nebula-plugins/gradle-lint-plugin) is used 
to ensure that all and only necessary dependencies are declared in the project. 
While the project should not build if dependency linting errors are present, 
it currently does.

Test coverage is set to 100%, the tests are at the API-level and reflect the 
intentions of the code rather than the implementation.

Tests are implemented with [JUnit 5](https://junit.org/junit5/docs/current/user-guide/), 
using both [Hamcrest](http://hamcrest.org/) and [Javers](https://javers.org/) 
for comparison (the latter is used to simplify development).