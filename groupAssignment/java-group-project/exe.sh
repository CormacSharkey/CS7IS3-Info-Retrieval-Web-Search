#!/bin/bash

echo "Executing build and run..."
mvn clean package
java -jar target/java-group-project-1.0.jar
echo "Finished execution"