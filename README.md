# TDD-Code-Test
You are required to write a program which filters application request log extracts by a number of different properties.

A log extract file contains a header line, followed by zero or more data lines, in comma-separated value format. The
first column is the Unix timestamp of the time the request was made, the second column is the country from which the
request originated, and the third column is the time in milliseconds which the request took to complete. The data lines
are not guaranteed to be in any particular order.

An example file is:

    REQUEST_TIMESTAMP,COUNTRY_CODE,RESPONSE_TIME
    1433190845,US,539
    1432917066,GB,37

The features which you must implement have been prototyped in the class uk.sky.DataFilterer. You must implement the
features in this class without changing the signatures of any methods or add any new dependencies. You must also provide
evidence that the features you have implemented work correctly.

# spring-elastic-search-dummies


## Build - gradle
dependencies used - spring-jpa, spring-web, spring-security, io.jsonwebtoken, mysql-connector

# How to run this application

## Machine Prerequisite
- Java 8 or later
- Elastic Search dependecy
- Gradle

## Local environment

In order to run the Integration tests a Postgres database is required.

### Running service for the first time
In order to force the download of dependencies outside of IntelliJ:

`./gradlew build --refresh-dependencies`

### Run any service

`./gradlew bootRun`

### Test and build

#### Run tests (also runs big tests)
`./gradlew clean test`

#### Build conditionally on tests
`./gradlew clean build`  

- 📫 Feel free to reach me: @ https://about.me/saurabhshcs

Reach out to me via [about.me](https://about.me/saurabhshcs)

![My github stats](https://github-readme-stats.vercel.app/api?username=saurabhshcs&show_icons=true)


![Profile views](https://komarev.com/ghpvc/?username=saurabhshcs)


Follow me on - [Medium](https://saurabhshcs.medium.com) | [Linkedin](https://www.linkedin.com/in/saurabhshcs/) | [YouTube](https://www.youtube.com/channel/UCSQqjPw7_tfx1Ie4yYHbcxQ?pbjreload=102) | [StackOverFlow](https://stackoverflow.com/users/10719720/saurabhshcs?tab=profile)
