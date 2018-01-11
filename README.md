# number-desc
A simple demo application that produces a REST API for converting given integers into English word representations.

To build and run, this service requires:
* Java 1.8+
* Maven 3+

To build:
clone the repository
From the root of the repository run:

    mvn clean package

This should compile, run and pass all tests, and package the service as a jar file.

To run:

    java -jar target/numdesc-1.0-SNAPSHOT.jar

The service has a single, simple endpoint that accepts http GET requests and provides json responses.
Once the service is running locally, you can access it by putting this in your brower address bar:

    http://localhost:9000/numdesc/123

The 123 at the end of the URL is the parameter passed to the endpoint. The json response will contain an English text representation of the number:

    {"number":123,"description":"One hundred and twenty three"}

The endpoint handles any number that can be represented as a 32-bit signed integer. Decimal numbers, numbers that are too large, or other strings will return a 400 BAD REQUEST response.

