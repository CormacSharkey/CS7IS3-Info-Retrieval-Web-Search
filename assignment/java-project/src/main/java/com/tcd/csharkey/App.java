package com.tcd.csharkey;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.Similarity;

// Currently using StandardAnalyzer and BM25Similarity
// TODO:    Create a custom analyzer
// TODO:    Implement multiple scoring similarities (including at least the Vector Space Model and BM25)
//          Store results of similarity scoring in document for trec eval
// TODO:    Reformat qrels
// TODO:    Run trec eval on each document
// TODO:    Generate Mean Average Precision and Recall scores based upon the provided relevance judgements using TREC Eval
// TODO:    "Comment and optimize code"
// TODO:    Write report

public class App {

    private static String cranPath = "/home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cran.all.1400";
    private static String indexPath = "../index";
    public static void main(String[] args) throws IOException, ParseException {

        QuerySpecs standardBM25 = new QuerySpecs("Standard-BM25", new StandardAnalyzer(), new BM25Similarity());
        IndexCran newIndexCran = new IndexCran(standardBM25);
        QueryCran newQueryCran = new QueryCran(standardBM25);

        QuerySpecs standardVSM = new QuerySpecs("Standard-VSM", new StandardAnalyzer(), new ClassicSimilarity());
        IndexCran newIndexCran = new IndexCran(standardVSM);
        QueryCran newQueryCran = new QueryCran(standardVSM);


        QuerySpecs englishBM25 = new QuerySpecs("English-BM25", new EnglishAnalyzer(), new BM25Similarity());
        IndexCran newIndexCran = new IndexCran(englishBM25);
        QueryCran newQueryCran = new QueryCran(englishBM25);

        QuerySpecs englishVSM = new QuerySpecs("English-VSM", new EnglishAnalyzer(), new ClassicSimilarity());
        IndexCran newIndexCran = new IndexCran(englishVSM);
        QueryCran newQueryCran = new QueryCran(englishVSM);

    }

}
