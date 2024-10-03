package com.tcd.csharkey;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;

// Currently using StandardAnalyzer and BM25Similarity
// TODO:    Create a custom analyzer
// TODO:    Implement multiple scoring similarities
// TODO:    Store results of similarity scoring in document for trec eval
// TODO:    Reformat qrels
// TODO:    Run trec eval on each document
// TODO:    "Comment and optimize code"
// TODO:    Write report

public class App {

    private static String cranPath = "/home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cran.all.1400";
    private static String indexPath = "../index";
    private static Analyzer analyzer = new StandardAnalyzer();
    private static Similarity similarity = new BM25Similarity();
    public static void main(String[] args) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        Similarity similarity = new BM25Similarity();

        IndexCran newIndexCran = new IndexCran(analyzer);
        QueryCran newQueryCran = new QueryCran(analyzer, similarity);

    }

}
