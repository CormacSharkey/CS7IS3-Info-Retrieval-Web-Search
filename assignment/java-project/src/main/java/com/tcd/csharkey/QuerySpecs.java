package com.tcd.csharkey;

import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.analysis.Analyzer;

// QuerySpecs class
public class QuerySpecs {
    // Scoring approach as a string
    private String scoringApproach;
    // Analyzer as an object
    private Analyzer analyzer;
    // Similarity scorer as an object
    private Similarity similarity;

    // Constructor - initializes a QuerySpecs object by setting its private vars as the params given
    public QuerySpecs(String scoringApproach, Analyzer analyzer, Similarity similarity) {
        this.scoringApproach = scoringApproach;
        this.analyzer = analyzer;
        this.similarity = similarity;
    }

    // Scoring approach getter
    public String getScoringApproach() {
        return scoringApproach;
    }

    // Analyzer getter
    public Analyzer getAnalyzer() {
        return analyzer;
    }

    // Similarity getter
    public Similarity getSimilarity() {
        return similarity;
    }

}
