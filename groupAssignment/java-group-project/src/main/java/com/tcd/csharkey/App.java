package com.tcd.csharkey;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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

        // Analyzer analyzer2 = new EnglishAnalyzer();
        // Analyzer analyzer3 = new SimpleAnalyzer();
        // Analyzer analyzer4 = new StandardAnalyzer();
        // Analyzer analyzer5 = new WhitespaceAnalyzer();

        // Analyzer analyzer;

        // if (args[0].equals("one")) {
        //     analyzer = analyzer1;
        //     System.out.println("Using Custom");
        // }
        // else if (args[0].equals("two")) {
        //     analyzer = analyzer2;
        //     System.out.println("Using English");
        // }
        // else if (args[0].equals("three")) {
        //     analyzer = analyzer3;
        //     System.out.println("Using Simple");
        // }
        // else if (args[0].equals("four")) {
        //     analyzer = analyzer4;
        //     System.out.println("Using Standard");
        // }
        // else {
        //     analyzer = analyzer5;
        //     System.out.println("Using Whitespace");
        // }

        // Specify the similarity scorer with a tweaked lambda value
        Similarity[] scores = {new LMJelinekMercerSimilarity(0.63f)};
        Similarity score = new MultiSimilarity(scores);

        // Create a ParseIndexDocs object
        ParseIndexDocs parserIndexer = new ParseIndexDocs();

        // Pass the document code and analzyer to the object to parse each document and add it to the index
        parserIndexer.CallParsers("fbis", analyzer);
        // parserIndexer.CallParsers("fr", analyzer);
        // parserIndexer.CallParsers("ft", analyzer);
        // parserIndexer.CallParsers("lat", analyzer);

        // Create a QueryTopics Object
        QueryTopics queryTopics = new QueryTopics();

        // Pass the analyzer and similarity scorer to the object to parse the queries and query the index
        queryTopics.CallQueries(analyzer, score);

        final long endTime = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTime - startTime));   
    }
}
