package com.csharkey.index;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.analysis.Analyzer;


public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        CreateIndex newIndex = new CreateIndex(new StandardAnalyzer());

    }
}
