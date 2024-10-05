package com.tcd.csharkey;

import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.analysis.Analyzer;

public class QuerySpecs {
    private String scoringApproach;
    private Analyzer analyzer;
    private Similarity similarity;

    public QuerySpecs(String scoringApproach, Analyzer analyzer, Similarity similarity) {
        this.scoringApproach = scoringApproach;
        this.analyzer = analyzer;
        this.similarity = similarity;
    }

    public String getScoringApproach() {
        return scoringApproach;
    }

    public Analyzer getAnalyzer() {
        return analyzer;
    }

    public Similarity getSimilarity() {
        return similarity;
    }

}
