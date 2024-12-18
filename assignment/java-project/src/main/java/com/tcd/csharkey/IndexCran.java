package com.tcd.csharkey;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;

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

    // Vars for the file paths of the cran content file and the index folder
    private static String cranPath = "../cran/cran.all.1400";
    private static String indexPath = "../index";

    // Constructor - takes a QuerySpecs object and indexes the cranfield collection to create an index
    public IndexCran(QuerySpecs specs) throws IOException {

        // Create a new index path string for the specific index folder
        String newIndexPath = indexPath + "/" + specs.getScoringApproach();
        Boolean dirStatus = new File(newIndexPath).mkdirs();

        Directory directory = FSDirectory.open(Paths.get(newIndexPath));
        // Create an IndexWriterConfig object using the given analyzer
        IndexWriterConfig config = new IndexWriterConfig(specs.getAnalyzer());

        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter iwriter = new IndexWriter(directory, config);

        // Create a reader for the cran content file to parse it
        FileInputStream fstream = new FileInputStream(cranPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String fileline;
        String prevField = "id";
        String data = "";
        Boolean dataFlag = true;
        Boolean firstFlag = true;

        // Create list of documents to store the parsed cran docs
        ArrayList<Document> documentsList = new ArrayList<Document>();

        Document document = new Document();

        // Cranfield collection parser - parses each field into a document object and adds a completed document object to the document list
        fileline = br.readLine();
        while ((fileline) != null) {
            if (returnField(fileline) != "X") {
                if (prevField != "id") {
                    document.add(new TextField(prevField, data, Field.Store.YES));
                }
                if (returnField(fileline) == "id") {
                    if (!firstFlag) {
                        document.removeField("bibli");
                        documentsList.add(document);
                    }
                    firstFlag = false;
                    document = new Document();
                    document.add(new TextField("id", fileline.substring(3), Field.Store.YES));
                }
                dataFlag = true;
                
                prevField = returnField(fileline);
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

        // Add the last document body to its document
        document.add(new TextField("body", data, Field.Store.YES));
        // Add the last document to the document list
        documentsList.add(document);

        // Write the documents to the index
        iwriter.addDocuments(documentsList);

        // Close all open objects
        iwriter.close();
        directory.close();
        fstream.close();

    }

    // returnField method - returns a field's designated name based on the passed field id, returns "X" if not a field line
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
