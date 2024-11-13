#!/bin/bash

echo "Purging for fresh run..."
rm -rf ../index/*
rm -rf ../results/*
echo "Finished purge"

echo "Executing build and run..."
mvn clean package
java -jar target/java-group-project-1.0.jar
echo "Finished execution"