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

    public void BuildIndex(Analyzer analyzer) {

        // Analyzer analyzer = new EnglishAnalyzer();

        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
    
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
    
            IndexWriter iwriter = new IndexWriter(directory, config);
    
            ParserDocs parserDocs = new ParserDocs();
            
            ArrayList<Document> documentsList = new ArrayList<>();

            documentsList = parserDocs.CallParsers("fbis");
            iwriter.addDocuments(documentsList);

            documentsList = parserDocs.CallParsers("fr");
            iwriter.addDocuments(documentsList);

            documentsList = parserDocs.CallParsers("ft");
            iwriter.addDocuments(documentsList);

            documentsList = parserDocs.CallParsers("lat");
            iwriter.addDocuments(documentsList);

            System.out.println("Added index");
    
            iwriter.close();
            directory.close();

        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
}
