package com.tcd.csharkey;

import java.io.File;
import java.util.ArrayList;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

public class App 
{
    public static void main( String[] args ) {

        IndexDocs indexDocs = new IndexDocs();
        indexDocs.BuildIndex();

        QueryTopics queryTopics = new QueryTopics();
        queryTopics.CallQueries();

    }

}
