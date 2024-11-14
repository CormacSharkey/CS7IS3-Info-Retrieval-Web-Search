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
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;

public class QueryTopics {

    private int MAX_RESULTS = 1000;
    private static String queryPath = "../data/topics";
    private static String indexPath = "../index";
    private static String resultsPath = "../results/";
    private ArrayList<String> queryList = new ArrayList<String>();
    private ArrayList<String> queryID = new ArrayList<String>();

    private void BuildQueries() {

        File file = new File(queryPath);

        try {
            org.jsoup.nodes.Document document = Jsoup.parse(file,"ISO-8859-1");

            Elements elements = document.getElementsByTag("top");

            String query = "";
            
            String id;
            String title;
            String description;
            String narrative;

            for (Element el: elements){
                id = el.getElementsByTag("num").text();
                title = el.getElementsByTag("title").text();
                description = el.getElementsByTag("desc").text().split("Narrative")[0];
                narrative = el.getElementsByTag("narr").text();

                queryID.add(id.split(" ")[1]);
                query += title.replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += description.replace("Description:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                // query += narrative.replace("Narrative:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                queryList.add(query);
            }   
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void CallQueries(Analyzer analyzer, Similarity score) {
        BuildQueries();

        try {
            // Analyzer analyzer = new EnglishAnalyzer();
            // Similarity score = new BM25Similarity();

            Directory directory = FSDirectory.open(Paths.get(indexPath));

            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);

            isearcher.setSimilarity(score);

            // System.out.println(isearcher.getIndexReader().numDocs());

            String queryFields[] = {"title", "author", "body"};
            QueryParser queryParser = new MultiFieldQueryParser(queryFields, analyzer);

            File resultsFile = new File(resultsPath + "results.txt");
            FileWriter myWriter = new FileWriter(resultsPath + "results.txt");

            int counter = 0;
            
            for (String queryBase: queryList) {
                Query query = queryParser.parse(queryParser.escape(queryBase));

                ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

                // System.out.println(hits.length);
                // System.out.println(hits[50].doc);

                for (int i = 1; i < hits.length+1; i++) {
                    // System.out.println(hits.length);
                    // System.out.println(i-1);
                    // System.out.println("a");

                    // System.out.println(i);
                    Document hitDoc = isearcher.storedFields().document(hits[i-1].doc);
                    // System.out.println("b");
                    
                    myWriter.write(queryID.get(counter) + " Q0 " + hitDoc.get("id") + " " + i + " " + hits[i-1].score + " STANDARD" + "\n");
                    // System.out.println("c");
                    // counter++;
                }
                counter++;
            }

            myWriter.close();
            System.out.println("Added file: results.txt");

            ireader.close();
            directory.close();
        }
        catch (Exception e) {
            System.out.println("Error in QueryTopics: " + e);
        }
    }    
}
