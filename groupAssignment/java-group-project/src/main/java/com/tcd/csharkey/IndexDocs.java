package com.tcd.csharkey;

import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexDocs {

    private static String indexPath = "../index";

    public void BuildIndex(Analyzer analyzer, String code) {

        // Analyzer analyzer = new EnglishAnalyzer();

        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
    
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            if (code == "fbis") {
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            }
            else {
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            }
    
            IndexWriter iwriter = new IndexWriter(directory, config);
    
            ParserDocs parserDocs = new ParserDocs();
            
            ArrayList<Document> documentsList = new ArrayList<>();

            if (code == "fbis") {
                documentsList = parserDocs.CallParsers(code);
                iwriter.addDocuments(documentsList);
            }
            else if (code == "fr") {
                documentsList = parserDocs.CallParsers(code);
                iwriter.addDocuments(documentsList);
            }
            else if (code == "ft") {
                documentsList = parserDocs.CallParsers(code);
                iwriter.addDocuments(documentsList);
            }

            else if (code == "lat") {
                documentsList = parserDocs.CallParsers(code);
                iwriter.addDocuments(documentsList);
            }

            System.out.println("Added " + code + " index");
    
            // iwriter.optimize();
            iwriter.commit();
            iwriter.close(); 
            directory.close();

        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
