package com.tcd.csharkey;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class IndexCran {

    private static String cranPath = "/home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cran.all.1400";
    private static String indexPath = "../index";

    public IndexCran() throws IOException {
        Analyzer analyzer = new StandardAnalyzer();

        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter iwriter = new IndexWriter(directory, config);

        FileInputStream fstream = new FileInputStream(cranPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));


        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document document = new Document();

        String fileline;
        String field = "id";
        String data = "";
        Boolean dataFlag = true;

        Boolean notFirstTime = false;
        
        // TODO: need to rewrite this to include previous field and ensure chain is correct (i->t->a->w->i->...)
        // *** CORRECT CHAIN IS IMPORTANT
        while ((fileline = br.readLine()) != null) {
            if (isField(fileline)) {
                if (fileline.startsWith(".I")) {
                    if (notFirstTime) {
                        documentsList.add(document);
                    }
                    notFirstTime = true;
                    document = new Document();

                    document.add(new TextField("id", fileline.substring(3), Field.Store.YES));
                }
                else if (field != "ignore") {
                    document.add(new TextField(field, data, Field.Store.YES));
                }

                dataFlag = true;

                field = returnField(fileline.substring(0, 2));
            }
            else {
                if (dataFlag) {
                    data = fileline;
                    dataFlag = false;
                }
                else {
                    data = data + "\n" + fileline;
                }

            }
        }

        document.add(new TextField("body", data, Field.Store.YES));
        documentsList.add(document);

        System.out.println(documentsList.size());
        
        iwriter.addDocuments(documentsList);

        iwriter.close();
        directory.close();
        fstream.close();
    }

    public boolean isField(String str) {
        if (str.startsWith(".I") || str.startsWith(".T") || str.startsWith(".A") || str.startsWith(".W") || str.startsWith(".B")) {
            return true;
        }
        return false;
    }

    public String returnField(String str) {
        if (str.startsWith(".I")) {
            return "id";
        }
        else if (str.startsWith(".T")) {
            return "title";
        }
        else if (str.startsWith(".A")) {
            return "author";
        }
        else if (str.startsWith(".B")) {
            return "ignore";
        }
        else {
            return "body";
        }
    }
}
