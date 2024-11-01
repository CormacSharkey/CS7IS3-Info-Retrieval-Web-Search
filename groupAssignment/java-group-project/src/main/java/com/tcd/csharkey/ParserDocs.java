package com.tcd.csharkey;

import java.util.ArrayList;

import org.apache.lucene.document.Document;

public class ParserDocs {

    private String fbisPath = ".../data/fbis";
    private String frPath = ".../data/fr94";
    private String ftPath = ".../data/ft";
    private String latPath = ".../data/latimes";

    private ArrayList<String> FileExtractor(String filePath) {
        // Extract the specific files for the specific parser
    }
    
    private ArrayList<Document> FBISParser(ArrayList<String> fileList) {
        // Parse the FBIS dataset

    }

    private ArrayList<Document> FRParser(ArrayList<String> fileList) {
        // Parse the FR dataset

    }

    private ArrayList<Document> FTParser(ArrayList<String> fileList) {
        // Parse the FT dataset

    }

    private ArrayList<Document> LATParser(ArrayList<String> fileList) {
        // Parse the LAT dataset
        
    }

    public ArrayList<Document> CallParsers() {
        ArrayList<String> fileList = FileExtractor();

        ArrayList<Document> docList;

        docList.addAll(FBISParser(fileList));
        docList.addAll(FRParser(fileList));
        docList.addAll(FTParser(fileList));
        docList.addAll(LATParser(fileList));

        // Call the parsers

        // Add the parsers' returned data together

        // Return combined data
    }
}
