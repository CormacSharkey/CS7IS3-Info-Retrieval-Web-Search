#!/bin/bash

echo "Purging for fresh run..."
sudo rm -rf ../query-results/*
sudo rm -rf ../index/*
echo "Finished purge"

echo "Executing build and run..."
mvn clean package
java -jar target/java-project-1.0.jar
echo "Finished execution"

echo "Calling trec_eval on query results..."
cd /home/csharkey/trec_eval/trec_eval-9.0.7/
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/English-BM25-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/English-VSM-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/English-Boolean-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Simple-BM25-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Simple-VSM-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Simple-Boolean-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Standard-BM25-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Standard-VSM-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/Standard-Boolean-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/WhiteSpace-BM25-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/WhiteSpace-VSM-res.txt
echo
./trec_eval -m runid -m map -m P.5 -m iprec_at_recall.00 /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cranqrel_trec_eval /home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/query-results/WhiteSpace-Boolean-res.txt
echo "Finished evaluation"