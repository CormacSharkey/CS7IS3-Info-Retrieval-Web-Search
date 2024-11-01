package com.tcd.csharkey;

import java.nio.file.Paths;

import javax.swing.text.html.HTMLEditorKit.Parser;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexDocs {

    private static String indexPath = "../index";

    public void BuildIndex() {
        IndexWriterConfig config = new IndexWriterConfig();
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriter iwriter = new IndexWriter(directory, config);

        ParserDocs parserDocs = new ParserDocs();

        ArrayList<Document> = parserDocs.CallParsers();




    }
}
