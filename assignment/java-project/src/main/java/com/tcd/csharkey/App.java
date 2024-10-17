package com.tcd.csharkey;

import java.io.IOException;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;

// TODO:    Create a custom analyzer
// TODO:    Generate Mean Average Precision and Recall scores based upon the provided relevance judgements using TREC Eval
// TODO:    Figure out what Mean Average Precision and Recall are calculating and what its value means
// TODO:    Write report

// * Questions to ask Professor
// Can I implement multiple analyzer and scoring combinations and compare them in report?
// Do I need to create a custom analyzer or can I stick with the pre-made ones?


// App class to run the main loop - creates multiple different indexes based on a combination of analyzers and similarity scores
// Scores tested with trec_eval using bash script "exe.sh"
public class App {

    public static void main(String[] args) throws IOException, ParseException {

        QuerySpecs englishBM25 = new QuerySpecs("English-BM25", new EnglishAnalyzer(), new BM25Similarity());
        IndexCran indexEngB = new IndexCran(englishBM25);
        QueryCran queryEngB = new QueryCran(englishBM25);

        QuerySpecs englishVSM = new QuerySpecs("English-VSM", new EnglishAnalyzer(), new ClassicSimilarity());
        IndexCran indexEngV = new IndexCran(englishVSM);
        QueryCran queryEngV = new QueryCran(englishVSM);

        QuerySpecs englishBoolean = new QuerySpecs("English-Boolean", new EnglishAnalyzer(), new BooleanSimilarity());
        IndexCran indexEngBo = new IndexCran(englishBoolean);
        QueryCran queryEngBo = new QueryCran(englishBoolean);



        QuerySpecs simpleBM25 = new QuerySpecs("Simple-BM25", new SimpleAnalyzer(), new BM25Similarity());
        IndexCran indexSimB = new IndexCran(simpleBM25);
        QueryCran querySimB = new QueryCran(simpleBM25);

        QuerySpecs simpleVSM = new QuerySpecs("Simple-VSM", new SimpleAnalyzer(), new ClassicSimilarity());
        IndexCran indexSimV = new IndexCran(simpleVSM);
        QueryCran querySimV = new QueryCran(simpleVSM);

        QuerySpecs simpleBoolean = new QuerySpecs("Simple-Boolean", new SimpleAnalyzer(), new BooleanSimilarity());
        IndexCran indexSimBo = new IndexCran(simpleBoolean);
        QueryCran querySimBo = new QueryCran(simpleBoolean);



        QuerySpecs standardBM25 = new QuerySpecs("Standard-BM25", new StandardAnalyzer(), new BM25Similarity());
        IndexCran indexStanB = new IndexCran(standardBM25);
        QueryCran queryStanB = new QueryCran(standardBM25);

        QuerySpecs standardVSM = new QuerySpecs("Standard-VSM", new StandardAnalyzer(), new ClassicSimilarity());
        IndexCran indexStanV = new IndexCran(standardVSM);
        QueryCran queryStanV = new QueryCran(standardVSM);

        QuerySpecs standardBoolean = new QuerySpecs("Standard-Boolean", new StandardAnalyzer(), new BooleanSimilarity());
        IndexCran indexStanBo = new IndexCran(standardBoolean);
        QueryCran queryStanBo = new QueryCran(standardBoolean);



        QuerySpecs whiteBM25 = new QuerySpecs("WhiteSpace-BM25", new WhitespaceAnalyzer(), new BM25Similarity());
        IndexCran indexWhiB = new IndexCran(whiteBM25);
        QueryCran queryWhiB = new QueryCran(whiteBM25);

        QuerySpecs whiteVSM = new QuerySpecs("WhiteSpace-VSM", new WhitespaceAnalyzer(), new ClassicSimilarity());
        IndexCran indexWhiV = new IndexCran(whiteVSM);
        QueryCran queryWhiV = new QueryCran(whiteVSM);

        QuerySpecs whiteBoolean = new QuerySpecs("WhiteSpace-Boolean", new WhitespaceAnalyzer(), new BooleanSimilarity());
        IndexCran indexWhiBo = new IndexCran(whiteBoolean);
        QueryCran queryWhiBo = new QueryCran(whiteBoolean);

    }

}
