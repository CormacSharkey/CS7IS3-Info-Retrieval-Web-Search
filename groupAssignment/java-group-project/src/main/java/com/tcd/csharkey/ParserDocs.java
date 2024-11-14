package com.tcd.csharkey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ParserDocs {

    private String fbisPath = "../data/fbis";
    private String frPath = "../data/fr94";
    private String ftPath = "../data/ft";
    private String latPath = "../data/latimes";
    
    private ArrayList<Document> FBISParser(String filePath) {
        File dir = new File(filePath);
        File[] fileList = dir.listFiles();

        // ArrayList<String> fileList = new ArrayList<>();

        // if (dirList != null) {
        //     System.out.println(dirList.length);

        //     for (int i=0; i<dirList.length; i++) {
        //         if (dirList[i].isDirectory()) {
        //             for (File f: dirList[i].listFiles()) {
        //                 fileList.add(f.getAbsolutePath());
        //             }
        //         }
        //     }
        // }

        // File file;
        org.jsoup.nodes.Document document;
        Elements elements;

        String id;
        String title;
        String author;
        String body;

        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document doc = new Document();

        try {
            for (File file: fileList) {
                if (file.getName().startsWith("fb")) {
                    // file = new File(name);
                    document = Jsoup.parse(file,"ISO-8859-1");
                    elements = document.getElementsByTag("DOC");
                    // System.out.println(file.getName());

                    for (Element el: elements) {
                        id = el.getElementsByTag("DOCNO").text();
                        doc.add(new TextField("id", id, Field.Store.YES));

                        // title = el.getElementsByTag("HEADLINE").text().split("/")[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        title = el.getElementsByTag("TI").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("title", title, Field.Store.YES));

                        author = el.getElementsByTag("AU").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("author", author, Field.Store.YES));

                        body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("body", body, Field.Store.YES));

                        // System.out.println(title);

                        documentsList.add(doc);

                        doc = new Document();
                    }
                }

            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return documentsList;
    }

    private ArrayList<Document> FRParser(String filePath) {
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        ArrayList<String> fileList = new ArrayList<>();

        if (dirList != null) {
            // System.out.println(dirList.length);

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
                // System.out.println(name);
    
                for (Element el: elements) {
                    id = el.getElementsByTag("DOCNO").text();
                    doc.add(new TextField("id", id, Field.Store.YES));

                    // title = el.getElementsByTag("HEADLINE").text().split("/")[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    title = el.getElementsByTag("DOCTITLE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("title", title, Field.Store.YES));

                    author = el.getElementsByTag("AUTHOR").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("author", author, Field.Store.YES));

                    // System.out.println(author);

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

    private ArrayList<Document> FTParser(String filePath) {
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        ArrayList<String> fileList = new ArrayList<>();

        if (dirList != null) {
            // System.out.println(dirList.length);

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
                // System.out.println(name);
    
                for (Element el: elements) {
                    id = el.getElementsByTag("DOCNO").text();
                    doc.add(new TextField("id", id, Field.Store.YES));

                    // title = el.getElementsByTag("HEADLINE").text().split("/")[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    title = el.getElementsByTag("HEADLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("title", title, Field.Store.YES));

                    author = el.getElementsByTag("BYLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("author", author, Field.Store.YES));

                    // System.out.println(author);

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
        File dir = new File(latPath);
        File[] fileList = dir.listFiles();

        ArrayList<Document> docList = new ArrayList<Document>();
        Document doc = new Document();

        String id;
        String title;
        String author;
        String body;

        org.jsoup.nodes.Document document;
        Elements elements;

        // Pattern docPattern;
        // Matcher docMatcher;
        // String docText;

        // Pattern patternDocNO;
        // Matcher matcherDocNO;

        // StringBuilder content = new StringBuilder();
        // String line;

        try {
            for (File file: fileList) {
                if (file.getName().startsWith("la")) {
                    // file = new File(name);
                    document = Jsoup.parse(file,"ISO-8859-1");
                    elements = document.getElementsByTag("DOC");
                    // System.out.println(name);
        
                    for (Element el: elements) {
                        id = el.getElementsByTag("DOCNO").text();
                        doc.add(new TextField("id", id, Field.Store.YES));

                        // title = el.getElementsByTag("HEADLINE").text().split("/")[1].replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        title = el.getElementsByTag("HEADLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("title", title, Field.Store.YES));

                        author = el.getElementsByTag("BYLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("author", author, Field.Store.YES));

                        // System.out.println(author);

                        body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("body", body, Field.Store.YES));

                        docList.add(doc);

                        doc = new Document();



                    // read file
        //             BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

        //             while ((line = reader.readLine()) != null) {
        //                 content.append(line).append("\n"); //"\n" = new line to preserve line breaks from article
        //             }
        //             reader.close();

        //             docPattern = Pattern.compile("<DOC>(.*?)</DOC>", Pattern.DOTALL);
        //             //Pattern = getting all the text / content that's between <DOC> and </DOC>
        //             docMatcher = docPattern.matcher(content.toString());
        //             //Matcher = searches for matches to the docPattern in the content string
        //             //content is a StringBuilder => convert it to string for matcher fct 
                    
        //             // loop through each <DOC> block => through each article found in the file
        //             while (docMatcher.find()) {
        //                 docText = docMatcher.group(1);
        //                 //docMatcher.group(1) returns whats inside the tags (without the tags themselves)

        //                 patternDocNO = Pattern.compile("<DOCNO>(.*?)</DOCNO>", Pattern.DOTALL);
        //                 matcherDocNO = patternDocNO.matcher(docText);
        //                 matcherDocNO.find();

        //                 docNO = matcherDocNO.group(1);
        //                 title = extractTagContent(docText, "HEADLINE").replaceAll("[^a-zA-Z ]", "").toLowerCase();
        //                 author = extractTagContent(docText, "BYLINE").replaceAll("[^a-zA-Z ]", "").toLowerCase();
        //                 body = extractTagContent(docText, "TEXT").replaceAll("[^a-zA-Z ]", "").toLowerCase();

        //                 // System.out.println(body);

        //                 //creating fields in the lucene docs
        //                 doc = new Document();
        //                 doc.add(new TextField("id", docNO, Field.Store.YES));
        //                 doc.add(new TextField("title", title, Field.Store.YES));  
        //                 doc.add(new TextField("author", author, Field.Store.YES)); 
        //                 doc.add(new TextField("body", body, Field.Store.YES));

        //                 docList.add(doc);
        //             }
                    }
                }   
            }
        }    
        catch (IOException e) {
            e.printStackTrace();
        }
        

        return docList;
        
    }

    // // since the formatting is 
    // // info abt the author : between <BYLINE> and </BYLINE>
    // // info abt title : between <HEADLINE> and </HEADLINE> 
    // // info abt text : between <TEXT> and </TEXT>
    // // we can use the same PATTERN method as previously to extract the info 
    // private static String extractTagContent(String docText, String tagName) {
    //     Pattern pattern = Pattern.compile("<" + tagName + ">(.*?)</" + tagName + ">", Pattern.DOTALL);
    //     Matcher matcher = pattern.matcher(docText);

    //     if (matcher.find()) {
    //         return matcher.group(1).trim();
    //     }
    //     return "";  // if the tag content is not found
    // }

    public ArrayList<Document> CallParsers(String code) {
        ArrayList<Document> docList = new ArrayList<Document>();
        
        if (code == "fbis") {
            docList.addAll(FBISParser(fbisPath));
            System.out.println("Finished FBIS");
        }
        else if (code == "fr") {
            docList.addAll(FRParser(frPath));
            System.out.println("Finished FR");
        }
        else if (code == "ft") {
            docList.addAll(FTParser(ftPath));
            System.out.println("Finished FT");
        }
        else if (code == "lat") {
            docList.addAll(LATParser(latPath));
            System.out.println("Finished LAT");
        }

        return docList;
        
    }
}
