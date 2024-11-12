package com.tcd.csharkey;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;

public class App 
{
    public static void main( String[] args ) {

        Analyzer analyzer = new EnglishAnalyzer();
        Similarity score = new BM25Similarity();

        IndexDocs indexDocs = new IndexDocs();
        indexDocs.BuildIndex(analyzer);

        QueryTopics queryTopics = new QueryTopics();
        queryTopics.CallQueries(analyzer, score);

    }

}
