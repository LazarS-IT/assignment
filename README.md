# Assignment

## Prerequisite

It is important to have your JVM set to 17+, otherwise build will fail.

## Building project

To generate .war file simply run `.\script.sh` file.

.war file will be generated in project root by the name `assignment.war`.
Once you have that file you can deploy it to your tomcat server.

## Endpoints
To test GraphQL endpoints you can navigate to `tomcatserver:8080/assignment/graphiql` where `tomcatserver` is url of your tomcat application.

Included in the project is postman collection that will show you all endpoint possibilities.

`/src/main/java/resources/postman/graphql.postman_collection.json`

disclaimer: do include `Basic Auth` when accessing users query with username `admin` and password `admin`
