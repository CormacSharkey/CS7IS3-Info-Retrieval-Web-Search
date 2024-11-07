package com.tcd.csharkey;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.SourceDataLine;

import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class ParserDocs {

    private String fbisPath = "../data/fbis";
    private String frPath = "../data/fr94";
    private String ftPath = "../data/ft";
    private String latPath = "../data/latimes";

    // // Might put FileExtractor in CallParsers() instead
    // private ArrayList<String> FileExtractor(String filePath, String code) {
    //     // Extract the specific files for the specific parser
    // }
    
    // private ArrayList<Document> FBISParser(String filePath) {
    //     // Parse the FBIS dataset

    // }

    // private ArrayList<Document> FRParser(String filePath) {
    //     // Parse the FR dataset

    // }

    // private ArrayList<Document> FTParser(String filePath) {
    //     File dir = new File(filePath);

    //     File[] dirList = dir.listFiles();

    //     for (int i=0; i<dirList.length; i++) {
    //         System.out.println(dirList[i].getName());
    //     }


    //     // org.jsoup.nodes.Document document = Jsoup.parse(file,"ISO-8859-1");
    //     // Elements elements = document.getElementsByTag("top");

    // }

    // private ArrayList<Document> LATParser(String filePath) {
    //     // Parse the LAT dataset
        
    // }

    // public ArrayList<Document> CallParsers() {
    //     ArrayList<Document> docList;

    //     docList.addAll(FBISParser(fbisPath));
    //     docList.addAll(FRParser(frPath));
    //     docList.addAll(FTParser(ftPath));
    //     docList.addAll(LATParser(latPath));

    //     return docList;

    //     // Call the parsers

    //     // Add the parsers' returned data together

    //     // Return combined data
    // }
}
