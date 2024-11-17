package com.tcd.csharkey;

import java.io.File;
import java.io.IOException;
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

    private String fbisPath = "../data/fbis";
    private String frPath = "../data/fr94";
    private String ftPath = "../data/ft";
    private String latPath = "../data/latimes";
    String indexPath = "../index";
    
    public void FBISParser(String filePath, Analyzer analyzer) {
        File dir = new File(filePath);
        File[] fileList = dir.listFiles();

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
                    writeToIndex(documentsList, analyzer);
                    documentsList = new ArrayList<>();
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void FRParser(String filePath, Analyzer analyzer) {
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        ArrayList<String> fileList = new ArrayList<>();

        if (dirList != null) {

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
                writeToIndex(documentsList, analyzer);
                documentsList = new ArrayList<>();
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void FTParser(String filePath, Analyzer analyzer) {
        File dir = new File(filePath);
        File[] dirList = dir.listFiles();

        ArrayList<String> fileList = new ArrayList<>();

        if (dirList != null) {

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
                writeToIndex(documentsList, analyzer);
                documentsList = new ArrayList<>();
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void LATParser(String filePath, Analyzer analyzer) {
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
                    writeToIndex(docList, analyzer);
                    docList = new ArrayList<>();
                }   
            }
        }    
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToIndex(ArrayList<Document> docList, Analyzer analyzer) {
        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter iwriter = new IndexWriter(directory, config);

            iwriter.addDocuments(docList);

            iwriter.commit();
            iwriter.close(); 
            directory.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void CallParsers(String code, Analyzer analyzer) {
        if (code == "fbis") {
            FBISParser(fbisPath, analyzer);
            System.out.println("Added index: FBIS");
        }
        else if (code == "fr") {
            FRParser(frPath, analyzer);
            System.out.println("Added index: FR");
        }
        else if (code == "ft") {
            FTParser(ftPath, analyzer);
            System.out.println("Added index: FT");
        }
        else if (code == "lat") {
            LATParser(latPath, analyzer);
            System.out.println("Added index: LAT");
        }
    }
}
