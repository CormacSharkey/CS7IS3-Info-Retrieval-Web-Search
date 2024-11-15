package com.tcd.csharkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.Similarity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App 
{
    public static void main( String[] args ) {

        Analyzer analyzer = new EnglishAnalyzer();
        // Similarity score = new BooleanSimilarity();
        Similarity score = new BM25Similarity();

        // IndexDocs indexDocs = new IndexDocs();
        // indexDocs.BuildIndex(analyzer, "fbis");
        // indexDocs.BuildIndex(analyzer, "fr");
        // indexDocs.BuildIndex(analyzer, "ft");
        // indexDocs.BuildIndex(analyzer, "lat");

        // QueryTopics queryTopics = new QueryTopics();
        // queryTopics.CallQueries(analyzer, score);

    }

}
