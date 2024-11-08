package com.tcd.csharkey;

import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ParserDocs {

    private String fbisPath = "../data/fbis";
    private String frPath = "../data/fr94";
    private String ftPath = "../data/ft";
    private String latPath = "../data/latimes";

    // Might put FileExtractor in CallParsers() instead
    private ArrayList<String> FileExtractor(String filePath, String code) {
        // Extract the specific files for the specific parser
    }
    
    private ArrayList<Document> FBISParser(String filePath) {
        // Parse the FBIS dataset

    }

    private ArrayList<Document> FRParser(String filePath) {
        // Parse the FR dataset

    }

    private ArrayList<Document> FTParser(String filePath) {
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        ArrayList<String> fileList = new ArrayList<>();

        if (dirList != null) {
            System.out.println(dirList.length);

            for (int i=0; i<dirList.length; i++) {
                if (dirList[i].isDirectory()) {
                    for (File f: dirList[i].listFiles()) {
                        fileList.add(f.getAbsolutePath());
                    }
                }
            }
        }

        File file;
        org.jsoup.nodes.Document document;
        Elements elements;
        
        String id;
        String title;
        String author;
        String body;

        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document doc = new Document();

        try {
            for (String name: fileList) {
                file = new File(name);
                document = Jsoup.parse(file,"ISO-8859-1");
                elements = document.getElementsByTag("DOC");
                System.out.println(name);
    
                for (Element el: elements) {
                    id = el.getElementsByTag("DOCNO").text();
                    doc.add(new TextField("id", id, Field.Store.YES));

                    // title = el.getElementsByTag("HEADLINE").text().split("/")[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    title = el.getElementsByTag("HEADLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("title", title, Field.Store.YES));

                    author = el.getElementsByTag("BYLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("author", author, Field.Store.YES));

                    System.out.println(author);

                    body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("body", body, Field.Store.YES));

                    documentsList.add(doc);

                    doc = new Document();
                }
    
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return documentsList;

    }

    private ArrayList<Document> LATParser(String filePath) {
        // Parse the LAT dataset
        
    }

    public ArrayList<Document> CallParsers() {
        ArrayList<Document> docList;

        docList.addAll(FBISParser(fbisPath));
        docList.addAll(FRParser(frPath));
        docList.addAll(FTParser(ftPath));
        docList.addAll(LATParser(latPath));

        return docList;
        
    }
}
