package com.tutorialspoint.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import essai.extraction.tweet.Object;
import twitter4j.TwitterException;
public class LuceneTester {

	
	
   //String indexDir = "D:\\Lucene\\index";
  // String dataDir = "D:\\Lucene\\data\\exp.json";
   Indexer indexer;
   //Searcher searcher;

   public static void main(String[] args) throws IOException, TwitterException {
	  
	    String line;
	      File f = new File("D:\\Lucene\\collection_reduite4\\");
	    //File f = new File("D:\\Lucene\\Data\\");
		    
		ObjectMapper objectMapper = new ObjectMapper();
		
	    objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    
	    BufferedReader reader;
	   Indexer indexer = new Indexer("D:\\Lucene\\Index");          // Index   : col2  sans twitter avec sc
	   int  i=0;													// Indext1  : col2  avec tweeter et field sc 
	   for(File file : f.listFiles())								
		{															
		   System.out.println(file.getName());
			reader = new BufferedReader( new FileReader(file));
			while((line = reader.readLine())!=null){
				Object object;
				try{
					
					 object=    objectMapper.readValue(line, Object.class);
				}catch(JsonParseException e){
					i++;
					continue;
				}
			
				indexer.indexFile(object);
				
	
	   
	 
	   
		//indexer.close();

	   
   }
			indexer.close();
}}}