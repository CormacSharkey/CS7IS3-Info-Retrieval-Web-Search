#!/bin/bash

echo "Executing build and run"

mvn clean package

java -jar target/java-project-1.0.jar

echo "Finished execution"