#!/bin/bash

echo "Purging for fresh run..."
rm -rf ../index/*
rm -rf ../results/*
echo "Finished purge"

echo "Executing build and run..."
mvn clean package
java -jar target/java-group-project-1.0.jar
echo "Finished execution"

echo "Calling trec_eval on query results..."
cd ../trec_eval-9.0.7/
./trec_eval -m runid -m map ../data/qrels.assignment2.part1 <insert query results file path here>
