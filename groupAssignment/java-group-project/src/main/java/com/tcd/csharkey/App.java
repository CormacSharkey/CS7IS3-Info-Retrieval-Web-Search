package com.tcd.csharkey;

import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
