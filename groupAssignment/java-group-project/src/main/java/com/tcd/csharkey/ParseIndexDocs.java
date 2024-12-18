package com.tcd.csharkey;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class ParseIndexDocs {

    // Specify the file paths to each document collection
    private String fbisPath = "../data/fbis";
    private String frPath = "../data/fr94";
    private String ftPath = "../data/ft";
    private String latPath = "../data/latimes";

    // Specify the file path to the index
    String indexPath = "../index";
    
    // Method to parse the FBIS document collection into an index - given the document collection file path, the analyzer and the index writer
    public void FBISParser(String filePath, Analyzer analyzer, IndexWriter iwriter) {
        // Get a list of all files in the root file collection directory
        File dir = new File(filePath);
        File[] fileList = dir.listFiles();

        // Declare some variables for parsing the document collection
        org.jsoup.nodes.Document document;
        Elements elements;
        String id;
        String title;
        String author;
        String body;
        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document doc = new Document();

        // For loop - loop through each file in the directory, if it starts with "fb", parse the contents into a document list and add it to the index
        try {
            for (File file: fileList) {
                if (file.getName().startsWith("fb")) {
                    document = Jsoup.parse(file,"ISO-8859-1");
                    elements = document.getElementsByTag("DOC");

                    for (Element el: elements) {
                        id = el.getElementsByTag("DOCNO").text();
                        doc.add(new TextField("id", id, Field.Store.YES));

                        title = el.getElementsByTag("TI").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("title", title, Field.Store.YES));

                        author = el.getElementsByTag("AU").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("author", author, Field.Store.YES));

                        body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("body", body, Field.Store.YES));

                        documentsList.add(doc);

                        doc = new Document();
                    }
                    iwriter.addDocuments(documentsList);
                    documentsList = new ArrayList<>();
                }
            }
        }
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in FBISParser: " + e);
        }
    }

    // Method to parse the FR document collection into an index - given the document collection file path, the analyzer and the index writer
    public void FRParser(String filePath, Analyzer analyzer, IndexWriter iwriter) {
        // Get a list of all folders in the root file collection directory
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        // Declare an array to store the file list for every subsequent directory in the root directory
        ArrayList<String> fileList = new ArrayList<>();

        // If there's files in the root directory, loop through each file, check it's a directory and add the file contents to a list
        if (dirList != null) {
            for (int i=0; i<dirList.length; i++) {
                if (dirList[i].isDirectory()) {
                    for (File f: dirList[i].listFiles()) {
                        fileList.add(f.getAbsolutePath());
                    }
                }
            }
        }

        // Declare some variables for parsing the document collection
        File file;
        org.jsoup.nodes.Document document;
        Elements elements;
        String id;
        String title;
        String author;
        String body;
        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document doc = new Document();

        // For loop - loop through each file in the list, parse the contents into a document list and add it to the index
        try {
            for (String name: fileList) {
                file = new File(name);
                document = Jsoup.parse(file,"ISO-8859-1");
                elements = document.getElementsByTag("DOC");
    
                for (Element el: elements) {
                    id = el.getElementsByTag("DOCNO").text();
                    doc.add(new TextField("id", id, Field.Store.YES));

                    title = el.getElementsByTag("DOCTITLE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("title", title, Field.Store.YES));

                    author = el.getElementsByTag("AUTHOR").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("author", author, Field.Store.YES));

                    body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();

                    doc.add(new TextField("body", body, Field.Store.YES));

                    documentsList.add(doc);

                    doc = new Document();
                }
                iwriter.addDocuments(documentsList);
                documentsList = new ArrayList<>();
            }
        }
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in FRParser: " + e);
        }
    }

    // Method to parse the FT document collection into an index - given the document collection file path, the analyzer and the index writer
    public void FTParser(String filePath, Analyzer analyzer, IndexWriter iwriter) {
        // Get a list of all files in the root file collection directory
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        // Declare an array to store the file list for every subsequent directory in the root directory
        ArrayList<String> fileList = new ArrayList<>();

        // If there's files in the root directory, loop through each file, check it's a directory and add the file contents to a list
        if (dirList != null) {
            for (int i=0; i<dirList.length; i++) {
                if (dirList[i].isDirectory()) {
                    for (File f: dirList[i].listFiles()) {
                        fileList.add(f.getAbsolutePath());
                    }
                }
            }
        }

        // Declare some variables for parsing the document collection
        File file;
        org.jsoup.nodes.Document document;
        Elements elements;
        String id;
        String title;
        String author;
        String body;
        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document doc = new Document();

        // For loop - loop through each file in the list, parse the contents into a document list and add it to the index
        try {
            for (String name: fileList) {
                file = new File(name);
                document = Jsoup.parse(file,"ISO-8859-1");
                elements = document.getElementsByTag("DOC");
    
                for (Element el: elements) {
                    id = el.getElementsByTag("DOCNO").text();
                    doc.add(new TextField("id", id, Field.Store.YES));

                    title = el.getElementsByTag("HEADLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("title", title, Field.Store.YES));

                    author = el.getElementsByTag("BYLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("author", author, Field.Store.YES));

                    body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                    doc.add(new TextField("body", body, Field.Store.YES));

                    documentsList.add(doc);

                    doc = new Document();
                }
                iwriter.addDocuments(documentsList);
                documentsList = new ArrayList<>();
            }
        }
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in FTParser: " + e);
        }
    }

    // Method to parse the LAT document collection into an index - given the document collection file path, the analyzer and the index writer
    public void LATParser(String filePath, Analyzer analyzer, IndexWriter iwriter) {
        // Get a list of all folders in the root file collection directory
        File dir = new File(latPath);
        File[] fileList = dir.listFiles();

        // Declare some variables for parsing the document collection
        ArrayList<Document> docList = new ArrayList<Document>();
        Document doc = new Document();
        String id;
        String title;
        String author;
        String body;
        org.jsoup.nodes.Document document;
        Elements elements;

        // For loop - loop through each file in the directory, if it starts with "la", parse the contents into a document list and add it to the index
        try {
            for (File file: fileList) {
                if (file.getName().startsWith("la")) {
                    document = Jsoup.parse(file,"ISO-8859-1");
                    elements = document.getElementsByTag("DOC");
        
                    for (Element el: elements) {
                        id = el.getElementsByTag("DOCNO").text();
                        doc.add(new TextField("id", id, Field.Store.YES));

                        title = el.getElementsByTag("HEADLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("title", title, Field.Store.YES));

                        author = el.getElementsByTag("BYLINE").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("author", author, Field.Store.YES));

                        body = el.getElementsByTag("TEXT").text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                        doc.add(new TextField("body", body, Field.Store.YES));

                        docList.add(doc);

                        doc = new Document();
                    }
                    iwriter.addDocuments(docList);
                    docList = new ArrayList<>();
                }   
            }
        }    
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in LATParser: " + e);
        }
    }

    // Method to call each parse method above in a thread to concurrently build the index
    public void CallParsers(Analyzer analyzer) {
        try {
            // Open the index directory with an index writer
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter iwriter = new IndexWriter(directory, config);

            // Create a separate thread for each parser method above
            Thread t1 = new Thread(new Runnable(){public void run(){FBISParser(fbisPath, analyzer, iwriter);
                System.out.println("Added index: FBIS");}});
            Thread t2 = new Thread(new Runnable(){public void run(){FRParser(frPath, analyzer, iwriter);
                System.out.println("Added index: FR");}});
            Thread t3 = new Thread(new Runnable(){public void run(){FTParser(ftPath, analyzer, iwriter);
                System.out.println("Added index: FT");}});
            Thread t4 = new Thread(new Runnable(){public void run(){LATParser(latPath, analyzer, iwriter);
                System.out.println("Added index: LAT");}});
            
            // Start every thread
            t1.start();
            t2.start();
            t3.start();
            t4.start();
            
            // Wait until every thread has terminated
            t1.join();
            t2.join();
            t3.join();
            t4.join();

            // Close up all open objects
            iwriter.commit();
            iwriter.close(); 
            directory.close();
        }
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in CallParsers: " + e);
        }
    }
}
