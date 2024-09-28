package com.csharkey.index;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class CreateIndex {

    private String cranPath = "../cran/cran.all.1400";
    private String indexPath = "../index";

    public void createIndex(Analyzer analyzer) {
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        config .setOpenmode(IndexWriterConfig.OpenMode.CREATE);

        IndexWritier iwriter = new IndexWriter(directory, config);

        FileInputStream fstream = new FileInputStream(cranPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        Arrayist<document> documentsList = new ArrayList<Document>();
        Document document = new Document();

        String fileLine;
        String prevField = "";
        String currField = ".I";
        String data = "";
        String documentID = "";
        Boolean flag = false;
        Boolean newData = true;

        while ((fileLine = br.readLine()) != null) {
            if (isField(fileLine)) {
                prevField = currField;
                currField = returnField(fileLine);

                if (!prevField.equals(".I")) {
                    document.add(newTextField(prevField, data, Field.Store.YES));
                }
                if (fileLine.startsWith(".I")) {
                    documentID = fileLine.substring(3);
                    if (flag) {
                        documentsList.add(document);
                    }
                    flag = true;
                    document = new Document();
                    document.add(new StringField(currField, documentID, Field.Store.YES));
                }
                newData = true;
            }
            else {
                if (newData) {
                    data = fileLine;
                    newData = false;
                }
                else {
                    data = data + "\n" + fileLine;
                }
            }
        }

        document.add(new TextField(currField, data, Field.Store.YES));
        documentsList.add(document);

        iwriter.addDocuments(documentsList);
        iwriter.close();
        directory.close();
        fstream.close();
    }

    public boolean isField(String str) {
        if (str.startsWith(".I") || str.startsWith(".T") || str.startsWith(".A") || str.startsWith(".W")) {
            return true;
        }
        return false;
    }

    public String returnField(String str) {
        if (str.startsWith(".I")) {
            return ".I";
        }
        else if (str.startsWith(".T")) {
            return ".T";
        }
        else if (str.startsWith(".A")) {
            return ".A";
        }
        else {
            return ".W";
        }
    }
}
