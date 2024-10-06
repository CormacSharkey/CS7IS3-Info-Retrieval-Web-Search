package com.tcd.csharkey;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;

public class QueryCran {

    private static int MAX_RESULTS = 50;

    private static String cranPath = "../cran/cran.qry";
    private static String indexPath = "../index";
    private static String resultsPath = "../query-results/";

    public QueryCran(QuerySpecs specs) throws IOException, ParseException{

        Directory directory = FSDirectory.open(Paths.get(indexPath));

        DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

        isearcher.setSimilarity(specs.getSimilarity());

        QueryParser queryParser = new QueryParser("body", specs.getAnalyzer());

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


        File resultsFile = new File(resultsPath + specs.getScoringApproach() + "-res.txt");
        FileWriter myWriter = new FileWriter(resultsPath + specs.getScoringApproach() + "-res.txt");

        counter = 1;
        for (String q : queryList) {
            q = q.trim();
            q = q.replace("?", "");

            Query query = queryParser.parse(q);

            ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

            for (int i = 1; i < hits.length+1; i++) {
                Document hitDoc = isearcher.storedFields().document(hits[i-1].doc);
                myWriter.write(counter + " Q0 " + hitDoc.get("id") + " " + i + " " + hits[i-1].score + " " + specs.getScoringApproach() + "\n");
            }

            counter += 1;
        }

        myWriter.close();
        System.out.println("Added file: " + specs.getScoringApproach() + "-res.txt");

        ireader.close();
        directory.close();
        fstream.close();
    }
    
}
