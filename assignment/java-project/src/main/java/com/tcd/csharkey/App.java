package com.tcd.csharkey;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

// Chose to use StandardAnalyzer due to the number of effects it has
// I should consider creating a custom analyzer to pick and choose effects

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        IndexCran newIndex = new IndexCran();

        QueryCran newQueryIndex = new QueryCran();

    }
}
