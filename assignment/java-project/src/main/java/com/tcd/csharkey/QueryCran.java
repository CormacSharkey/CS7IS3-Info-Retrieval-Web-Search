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
import org.apache.lucene.store.Directory;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.Similarity;

public class QueryCran {

    private static int MAX_RESULTS = 50;

    private static String cranPath = "/home/csharkey/InfoAssignments/CS7IS3-Info-Retrieval-Web-Search/assignment/cran/cran.qry";
    private static String INDEX_DIRECTORY = "../index";

    public QueryCran(Analyzer analyzer, Similarity similarity) throws IOException, ParseException{

        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));

        DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

        isearcher.setSimilarity(similarity);

        QueryParser queryParser = new QueryParser("body", analyzer);

        ArrayList<String> queryList = new ArrayList<String>();

        // process query file here
        FileInputStream fstream = new FileInputStream(cranPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String fileline;
        String queryString = "";
        Boolean dataFlag = true;
        int counter = 0;

        while ((fileline = br.readLine()) != null) {
            if (fileline.startsWith(".I")) {
                if (counter >= 1) {
                    queryList.add(queryString);
                }
                counter += 1;
            }
            else if (fileline.startsWith(".W")) {
                dataFlag = true;
            }
            else {
                 if (dataFlag) {
                    queryString = fileline;
                    dataFlag = false;
                 }
                 else {
                    queryString = queryString + "\n" + fileline;
                 }
            }
        }

        queryList.add(queryString);

        System.out.println(queryList.size());

        counter = 1;
        for (String q : queryList) {
            q = q.trim();
            q = q.replace("?", "");

            System.out.println(counter + ". " + q);

            Query query = queryParser.parse(q);

            ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

            System.out.println("Documents: " + hits.length);
            for (int i = 0; i < hits.length; i++) {
                Document hitDoc = isearcher.storedFields().document(hits[i].doc);
                System.out.println(i + ") " + hitDoc.get("id") + " " + hits[i].score);
            }

            System.out.println();
            counter += 1;
        }

        // System.out.println(queryString);

        // queryString = queryString.trim();

        // Query query = queryParser.parse(queryString);

        // ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

        // System.out.println("Documents: " + hits.length);
        // for (int i = 0; i < hits.length; i++) {
        //     Document hitDoc = isearcher.storedFields().document(hits[i].doc);
        //     System.out.println(i + ") " + hitDoc.get(".I") + " " + hits[i].score);
        // }

        // System.out.println();

        ireader.close();
        directory.close();
        fstream.close();
    }
    
}
