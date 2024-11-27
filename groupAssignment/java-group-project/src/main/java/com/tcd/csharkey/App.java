package com.tcd.csharkey;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.search.similarities.LMJelinekMercerSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;

public class App 
{
    public static void main( String[] args ) throws IOException{
        final long startTime = System.currentTimeMillis();

        // Specify the analyzer
        Path resources = Paths.get("../data/");
        Analyzer analyzer = CustomAnalyzer.builder(resources)
            .withTokenizer("standard")
            .addTokenFilter("lowercase")
            .addTokenFilter("stop", "words", "stop_words.txt", "format", "wordset")
            .addTokenFilter("porterstem")
            .addTokenFilter("kStem")
            .build();

        // Specify the similarity scorer with a tweaked lambda value
        Similarity[] scores = {new LMJelinekMercerSimilarity(0.63f)};
        Similarity score = new MultiSimilarity(scores);

        // Create a ParseIndexDocs object
        ParseIndexDocs parserIndexer = new ParseIndexDocs();

        // Pass the document code and analzyer to the object to parse each document and add it to the index
        parserIndexer.CallParsers(analyzer);

        // Create a QueryTopics Object
        QueryTopics queryTopics = new QueryTopics();

        // Pass the analyzer and similarity scorer to the object to parse the queries and query the index
        queryTopics.CallQueries(analyzer, score);

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));   
    }
}
