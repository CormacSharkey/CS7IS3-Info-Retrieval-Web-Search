package com.tcd.csharkey;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.document.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;

public class QueryTopics {

    private int MAX_RESULTS = 1000;
    private static String queryPath = "../data/topics";
    private static String indexPath = ".../index";
    private static String resultsPath = ".../results/";
    private ArrayList<String> queryList = new ArrayList<String>();
    private ArrayList<String> queryID = new ArrayList<String>();

    public void CallQueries() {
        BuildQueries();

        try {
            Analyzer analyzer = new EnglishAnalyzer();

            Directory directory = FSDirectory.open(Paths.get(indexPath));

            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);

            String queryFields[] = {"body"};

            QueryParser queryParser = new MultiFieldQueryParser(queryFields, analyzer);

            File resultsFile = new File(resultsPath + "results.txt");
            FileWriter myWriter = new FileWriter(resultsPath + "results.txt");

            for (String queryBase: queryList) {
                Query query = queryParser.parse(queryParser.escape(queryBase));

                ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;

                int counter = 0;
                for (int i = 1; i < hits.length+1; i++) {
                    Document hitDoc = isearcher.storedFields().document(hits[i-1].doc);
                    
                    myWriter.write(queryID.get(counter) + " Q0 " + hitDoc.get("id") + " 0 " + hits[i-1].score + " STANDARD" + "\n");
                    counter++;
                }
            }

            myWriter.close();
            System.out.println("Added file: results.txt");

            ireader.close();
            directory.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void BuildQueries() {

        File file = new File(queryPath);

        try {
            org.jsoup.nodes.Document document = Jsoup.parse(file,"ISO-8859-1");

            Elements elements = document.getElementsByTag("top");

            String query = "";

            for (Element el: elements){
                // DocumentQuery documentQuery = new DocumentQuery();
                Elements id = el.getElementsByTag("num");
                Elements title = el.getElementsByTag("title");
                Elements description = el.getElementsByTag("desc");
                Elements narrative = el.getElementsByTag("narr");

                // documentQuery.queryId = QueryId.text().split(" ")[1];
                queryID.add(id.text().split(" ")[1]);
                query += title.text().replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += description.text().split("Narrative")[0].replace("Description:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                query += narrative.text().replace("Narrative:","").replaceAll("[^a-zA-Z ]", "").toLowerCase();
                queryList.add(query);
            }   
        }
        catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    
}
