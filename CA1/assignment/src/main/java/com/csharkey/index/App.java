package com.csharkey.index;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.analysis.Analyzer;


public class App {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        CreateIndex newIndex = new CreateIndex(new StandardAnalyzer());

    }
}
