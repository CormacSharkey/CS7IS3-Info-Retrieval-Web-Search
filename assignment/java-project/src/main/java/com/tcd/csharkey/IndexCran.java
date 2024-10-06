package com.tcd.csharkey;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class IndexCran {

    private static String cranPath = "../cran/cran.all.1400";
    private static String indexPath = "../index";

    public IndexCran(QuerySpecs specs) throws IOException {

        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig config = new IndexWriterConfig(specs.getAnalyzer());

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter iwriter = new IndexWriter(directory, config);

        FileInputStream fstream = new FileInputStream(cranPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));


        // if field is an id (.I, .T, .A, .B, .W)
            // store past and current field
            // if past field not .I (who has already had their data added to the doc)
                // add data to doc using prev field
            // if curr field is .I
                // if not first .I
                    // remove "bibli" field
                    // add doc to doc list
                // create new doc
                // add .I data to it
        // else (must be text line)
            //if first line of text
                // add text with no whitespace
            // else
                //add text with whitespace

        String fileline;
        String currField = "id";
        String prevField = "";
        String data = "";
        Boolean dataFlag = true;
        Boolean firstFlag = true;

        ArrayList<Document> documentsList = new ArrayList<Document>();
        Document document = new Document();

        fileline = br.readLine();
        while ((fileline) != null) {
            if (returnField(fileline) != "X") {
                prevField = currField;
                currField = returnField(fileline);

                if (prevField != "id") {
                    document.add(new TextField(prevField, data, Field.Store.YES));
                }
                if (currField == "id") {
                    if (!firstFlag) {
                        document.removeField("bibli");
                        documentsList.add(document);
                    }
                    firstFlag = false;
                    document = new Document();
                    document.add(new TextField(currField, fileline.substring(3), Field.Store.YES));
                }
                dataFlag = true;
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
            
            fileline = br.readLine();
        }

        document.add(new TextField(currField, data, Field.Store.YES));
        documentsList.add(document);

        iwriter.addDocuments(documentsList);

        iwriter.close();
        directory.close();
        fstream.close();

    }

    // method to return the field mapping for a given code in cran.all.1400
    // returns "X" if no code present
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
        else if (str.startsWith(".W")) {
            return "body";
        }
        else if (str.startsWith(".B")) {
            return "bibli";
        }
        else {
            return "X";
        }
    }
}
