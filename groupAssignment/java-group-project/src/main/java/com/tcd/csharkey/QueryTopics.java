package com.tcd.csharkey;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParserBase;

public class QueryTopics {

    // Specify the max results returned for each query
    private int MAX_RESULTS = 1000;

    // Specify the various file paths required
    private static String queryPath = "../data/topics";
    private static String indexPath = "../index";
    private static String resultsPath = "../results/";

    // Declare two lists for the queries and the query IDs
    private ArrayList<String> queryList = new ArrayList<String>();
    private ArrayList<String> queryID = new ArrayList<String>();

    // Method to parse the query document and store each query in a list
    private void BuildQueries() {

        // Get the query document as a file object
        File file = new File(queryPath);

        // Declare some variables for parsing the query document
        String query = "";
        String id;
        String title;
        String description;
        String narrative;

        // For loop - parse each query in the document and create a query from it, and add it to the query list
        try {
            org.jsoup.nodes.Document document = Jsoup.parse(file,"ISO-8859-1");
            Elements elements = document.getElementsByTag("top");

            for (Element el: elements){
                id = el.getElementsByTag("num").text();
                title = el.getElementsByTag("title").text();
                description = el.getElementsByTag("desc").text().split("Narrative")[0];
                narrative = el.getElementsByTag("narr").text();

                queryID.add(id.split(" ")[1]);

                query += " ";
                query += title.replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += " ";
                query += title.replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += " ";
                query += title.replaceAll("[^a-zA-Z ]", "").toLowerCase();

                query += " ";
                query += description.replace("Description:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += " ";
                query += description.replace("Description:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                
                query += " ";
                query += narrative.replace("Narrative:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += " ";

                queryList.add(query);
                query = "";
            }   
        }
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in QueryTopics: " + e);
        }
    }

    // Method to query the index with the query list and create a results file - given the analyzer and the similarity scorer
    public void CallQueries(Analyzer analyzer, Similarity score) {

        // Call the above method to parse the queries and store them in a list
        BuildQueries();

        try {

            // Open the index directy with an index searcher
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);

            // Set the index searcher to have the specified similarity scorer
            isearcher.setSimilarity(score);

            // Specify the query fields
            String queryFields[] = {"body"};
            QueryParser queryParser = new MultiFieldQueryParser(queryFields, analyzer);
            
            // Create a results file
            File resultsFile = new File(resultsPath + "results.txt");
            FileWriter myWriter = new FileWriter(resultsPath + "results.txt");

            int counter = 0;
            
            // For loop - loop through every query in the query list, get results from the index and then write them into the results file
            for (String queryBase: queryList) {
                Query query = queryParser.parse(QueryParserBase.escape(queryBase));

                ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

                for (int i = 1; i < hits.length+1; i++) {
                    Document hitDoc = isearcher.storedFields().document(hits[i-1].doc);
                    
                    myWriter.write(queryID.get(counter) + " Q0 " + hitDoc.get("id") + " " + i + " " + hits[i-1].score + " CustomAnalyzerLMJM" + "\n");
                }
                counter++;
            }

            System.out.println("Added file: results.txt");

            // Close all open objects
            myWriter.close();
            ireader.close();
            directory.close();
        }
        // Catch any exceptions
        catch (Exception e) {
            System.out.println("Error in QueryTopics: " + e);
        }
    }    
}
