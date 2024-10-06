package com.tcd.csharkey;

import java.io.IOException;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.similarities.SimilarityBase;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;

// Currently using StandardAnalyzer and BM25Similarity
// TODO:    Create a custom analyzer
// TODO:    Implement multiple scoring similarities (including at least the Vector Space Model and BM25)
//          Store results of similarity scoring in document for trec eval
//          Reformat qrels
//          Run trec eval on each document
// TODO:    Generate Mean Average Precision and Recall scores based upon the provided relevance judgements using TREC Eval
// TODO:    "Comment and optimize code"
// TODO:    Write report

public class App {

    public static void main(String[] args) throws IOException, ParseException {

        QuerySpecs standardBM25 = new QuerySpecs("Standard-BM25", new StandardAnalyzer(), new BM25Similarity());
        IndexCran indexStanB = new IndexCran(standardBM25);
        QueryCran queryStanB = new QueryCran(standardBM25);

        QuerySpecs standardVSM = new QuerySpecs("Standard-VSM", new StandardAnalyzer(), new ClassicSimilarity());
        IndexCran indexStanV = new IndexCran(standardVSM);
        QueryCran queryStanV = new QueryCran(standardVSM);



        QuerySpecs englishBM25 = new QuerySpecs("English-BM25", new EnglishAnalyzer(), new BM25Similarity());
        IndexCran indexEngB = new IndexCran(englishBM25);
        QueryCran queryEngB = new QueryCran(englishBM25);

        QuerySpecs englishVSM = new QuerySpecs("English-VSM", new EnglishAnalyzer(), new ClassicSimilarity());
        IndexCran indexEngV = new IndexCran(englishVSM);
        QueryCran queryEngV = new QueryCran(englishVSM);



        QuerySpecs simpleBM25 = new QuerySpecs("Simple-BM25", new SimpleAnalyzer(), new BM25Similarity());
        IndexCran indexSimB = new IndexCran(simpleBM25);
        QueryCran querySimB = new QueryCran(simpleBM25);

        QuerySpecs simpleVSM = new QuerySpecs("Simple-VSM", new SimpleAnalyzer(), new ClassicSimilarity());
        IndexCran indexSimV = new IndexCran(simpleVSM);
        QueryCran querySimV = new QueryCran(simpleVSM);



        QuerySpecs whiteBM25 = new QuerySpecs("WhiteSpace-BM25", new WhitespaceAnalyzer(), new BM25Similarity());
        IndexCran indexWhiB = new IndexCran(whiteBM25);
        QueryCran queryWhiB = new QueryCran(whiteBM25);

        QuerySpecs whiteVSM = new QuerySpecs("WhiteSpace-VSM", new WhitespaceAnalyzer(), new ClassicSimilarity());
        IndexCran indexWhiV = new IndexCran(whiteVSM);
        QueryCran queryWhiV = new QueryCran(whiteVSM);

    }

}
