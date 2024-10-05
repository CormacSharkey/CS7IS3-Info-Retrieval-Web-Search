package com.tcd.csharkey;

import java.io.IOException;

import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.similarities.SimilarityBase;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.DFISimilarity;

// Currently using StandardAnalyzer and BM25Similarity
// TODO:    Create a custom analyzer
// TODO:    Implement multiple scoring similarities (including at least the Vector Space Model and BM25)
//          Store results of similarity scoring in document for trec eval
//          Reformat qrels
// TODO:    Run trec eval on each document
// TODO:    Generate Mean Average Precision and Recall scores based upon the provided relevance judgements using TREC Eval
// TODO:    "Comment and optimize code"
// TODO:    Write report

public class App {

    public static void main(String[] args) throws IOException, ParseException {

        QuerySpecs standardBM25 = new QuerySpecs("Standard-BM25", new StandardAnalyzer(), new BM25Similarity());
        IndexCran indexSB = new IndexCran(standardBM25);
        QueryCran querySB = new QueryCran(standardBM25);

        QuerySpecs standardVSM = new QuerySpecs("Standard-VSM", new StandardAnalyzer(), new ClassicSimilarity());
        IndexCran indexSV = new IndexCran(standardVSM);
        QueryCran querySV = new QueryCran(standardVSM);



        QuerySpecs englishBM25 = new QuerySpecs("English-BM25", new EnglishAnalyzer(), new BM25Similarity());
        IndexCran indexEB = new IndexCran(englishBM25);
        QueryCran queryEB = new QueryCran(englishBM25);

        QuerySpecs englishVSM = new QuerySpecs("English-VSM", new EnglishAnalyzer(), new ClassicSimilarity());
        IndexCran indexEV = new IndexCran(englishVSM);
        QueryCran queryEV = new QueryCran(englishVSM);

    }

}
