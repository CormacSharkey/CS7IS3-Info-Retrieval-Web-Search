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

    // Var for the max query results returned
    private static int MAX_RESULTS = 50;

    // Vars for the file paths of the cran query file, the created index folder, the query results folder
    private static String cranPath = "../cran/cran.qry";
    private static String indexPath = "../index";
    private static String resultsPath = "../query-results";

    // Constructor - takes a QuerySpecs object and creates a query results file based on the analyzer and similarity scorer
    public QueryCran(QuerySpecs specs) throws IOException, ParseException{

        Directory directory = FSDirectory.open(Paths.get(indexPath));

        DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

        // Set the similarity scorer for processing queries (same as used for creating the index)
        isearcher.setSimilarity(specs.getSimilarity());

        // Create a QueryParser object using the given analyzer (same as used for creating the index) and point it at the "body" field
        QueryParser queryParser = new QueryParser("body", specs.getAnalyzer());

        // Create list of strings to store the queries
        ArrayList<String> queryList = new ArrayList<String>();

        // Create a reader for the cran query file to parse it
        FileInputStream fstream = new FileInputStream(cranPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String fileline;
        String queryString = "";
        Boolean dataFlag = true;
        int counter = 0;

        // Cran query file parser - parse the body of the query and store it in the list, using the position in the array as the query id
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

        // Add the last query to the query list
        queryList.add(queryString);

        // Create a result file named after the scoring approach
        File resultsFile = new File(resultsPath + specs.getScoringApproach() + "-res.txt");
        // Create a file writer for the newly created file
        FileWriter myWriter = new FileWriter(resultsPath + specs.getScoringApproach() + "-res.txt");

        counter = 1;
        // Query processor - take each query and process it, then query the index and write results the query results file correctly
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

        // Close the file writer and report success 
        myWriter.close();
        System.out.println("Added file: " + specs.getScoringApproach() + "-res.txt");

        // Close all open objects
        ireader.close();
        directory.close();
        fstream.close();
    }
    
}
