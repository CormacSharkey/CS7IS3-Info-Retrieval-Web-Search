package com.tcd.csharkey;

import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.document.Document;

public class ParserDocs {

    private String fbisPath = ".../data/fbis";
    private String frPath = ".../data/fr94";
    private String ftPath = ".../data/ft";
    private String latPath = ".../data/latimes";

    // Might put FileExtractor in CallParsers() instead
    private ArrayList<String> FileExtractor(String filePath, String code) {
        // Extract the specific files for the specific parser
    }
    
    private ArrayList<Document> FBISParser(String filePath, String code) {
        ArrayList<String> fileList = FileExtractor(filePath, code);
        // Parse the FBIS dataset

    }

    private ArrayList<Document> FRParser(String filePath, String code) {
        ArrayList<String> fileList = FileExtractor(filePath, code);
        // Parse the FR dataset

    }

    private ArrayList<Document> FTParser(String filePath, String code) {
        ArrayList<String> fileList = FileExtractor(filePath, code);
        // Parse the FT dataset

    }

    private ArrayList<Document> LATParser(String filePath, String code) {
        ArrayList<String> fileList = FileExtractor(filePath, code);
        // Parse the LAT dataset
        
    }

    public ArrayList<Document> CallParsers() {
        ArrayList<Document> docList;

        docList.addAll(FBISParser(fileList));
        docList.addAll(FRParser(fileList));
        docList.addAll(FTParser(fileList));
        docList.addAll(LATParser(fileList));

        return docList;

        // Call the parsers

        // Add the parsers' returned data together

        // Return combined data
    }
}
