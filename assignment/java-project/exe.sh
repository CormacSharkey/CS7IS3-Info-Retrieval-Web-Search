#!/bin/bash

echo "Purging for fresh run"

rm -rf ../query-results/*
rm -rf ../index/*

echo "Executing build and run"

mvn clean package

java -jar target/java-project-1.0.jar

echo "Finished execution"

cd /home/csharkey/trec_eval/trec_eval-9.0.7/

./trec_eval -m runid -m gm_map -m P.5 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Standard-BM25-res.txt