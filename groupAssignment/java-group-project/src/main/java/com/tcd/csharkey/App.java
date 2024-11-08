package com.tcd.csharkey;

public class App 
{
    public static void main( String[] args ) {

        IndexDocs indexDocs = new IndexDocs();
        indexDocs.BuildIndex();

        QueryTopics queryTopics = new QueryTopics();
        queryTopics.CallQueries();

    }

}
