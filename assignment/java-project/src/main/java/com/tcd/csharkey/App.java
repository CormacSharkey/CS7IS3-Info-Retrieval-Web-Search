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
// TODO:    Store results of similarity scoring in document for trec eval
// TODO:    Reformat qrels
// TODO:    Run trec eval on each document
// TODO:    Generate Mean Average Precision and Recall scores based upon the provided relevance judgements using TREC Eval
// TODO:    "Comment and optimize code"
// TODO:    Write report

public class App {

    private static String cranPath = "/home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cran.all.1400";
    private static String indexPath = "../index";
    public static void main(String[] args) throws IOException, ParseException {
        Analyzer analyzerStandard = new StandardAnalyzer();
        Analyzer analyzerEnglish = new EnglishAnalyzer();
        Similarity similarityBM25 = new BM25Similarity();
        Similarity similarityVSM = new ClassicSimilarity();

        IndexCran newIndexCran = new IndexCran(analyzerStandard);
        QueryCran newQueryCran = new QueryCran(analyzerStandard, similarityBM25);

    }

}
