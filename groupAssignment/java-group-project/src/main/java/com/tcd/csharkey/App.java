package com.tcd.csharkey;

import java.io.File;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        // QueryTopics queryTopics = new QueryTopics();
        // queryTopics.CallQueries();

        // Start of parser
        String frPath = "../data/fr94";
        File dir = new File(frPath);
        File[] dirList = dir.listFiles();

        ArrayList<String> fileList = new ArrayList<>();

        if (dirList != null) {
            System.out.println(dirList.length);

            for (int i=0; i<dirList.length; i++) {
                if (dirList[i].isDirectory()) {
                    for (File f: dirList[i].listFiles()) {
                        fileList.add(f.getAbsolutePath());
                    }
                }
            }

            for (String name: fileList) {
                System.out.println(name);
            }
        }
    }
}
