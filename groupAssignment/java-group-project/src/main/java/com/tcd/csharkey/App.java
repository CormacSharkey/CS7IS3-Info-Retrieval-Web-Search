package com.tcd.csharkey;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilterFactory;
import org.apache.lucene.analysis.TokenizerFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.search.similarities.LMJelinekMercerSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.PerFieldSimilarityWrapper;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;

public class App 
{
    public static void main( String[] args ) throws IOException{

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
        parserIndexer.CallParsers("fbis", analyzer);
        parserIndexer.CallParsers("fr", analyzer);
        parserIndexer.CallParsers("ft", analyzer);
        parserIndexer.CallParsers("lat", analyzer);

        // Create a QueryTopics Object
        QueryTopics queryTopics = new QueryTopics();

        // Pass the analyzer and similarity scorer to the object to parse the queries and query the index
        queryTopics.CallQueries(analyzer, score);

    }
}
