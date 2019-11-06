package com.tutorialspoint.lucene;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.codehaus.jackson.JsonParseException;

import essai.extraction.tweet.Object;
import twitter4j.RateLimitStatus;
import twitter4j.StreamController.User;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterResponse;

//****************************************le 1 er 
public class Indexer {
	 private IndexWriter writer;
	 
static	Map<String,Long> t;
private IndexWriterConfig 	config;


	   public Indexer(String indexDirectoryPath) throws IOException {
	      //this directory will contain the indexes
		 
	      Directory indexDirectory = 
	         FSDirectory.open(new File(indexDirectoryPath));

	      //create the indexer
	      Analyzer poterAnalyser   = new StopAnalyser();
	      config = new IndexWriterConfig(Version.LUCENE_36, poterAnalyser);
	   
	      writer = new IndexWriter(indexDirectory, config);//creer l'index writter 
	   }
	 
	   public void close() throws CorruptIndexException, IOException {
		      writer.close();
		   }
	 
	   
	   
	          private Document getDocument( Object object) throws IOException, TwitterException  {
	     
		      Document document = new Document();
		      
		      Long id = object.getUser().getId();
		      
		      
		      try {
		         
		    	  
		    	  //Twitter twitter = new TwitterFactory().getInstance();
		     
		     
		    
			   
		      
		//  TwitterResponse response = twitter.showUser(id);// pour faire quoi ces 2 lignes !!!!!!!!!!!!!!!!!!!!!!!!!
		  //  RateLimitStatus status = response.getRateLimitStatus();
		    
		/*   
		     
	    
		     int ab = twitter.showUser(id).getFollowersCount();
		     int tw = twitter.showUser(id).getStatusesCount();
		     String sn = twitter.showUser(id).getScreenName();
		
		*/     
	            //regler realtime
		
		      
		      //index file contents
		      Field contentField = new Field(LuceneConstants.CONTENTS, object.getText(),Store.NO, Index.ANALYZED);
	   
		      //index file name .id tweet
		      Field id_tweet = new Field(LuceneConstants.ID_TWEET,
		         object.getId()+"",Store.YES,Field.Index.NOT_ANALYZED);
		      
		      //nom auteur
		      Field auth = new Field(LuceneConstants.AUTH,
		        object.getUser().getName(),Field.Store.YES,Field.Index.NOT_ANALYZED);
		     
		  
		      // l'id d'un user
		      Field id_auth = new Field(LuceneConstants.ID_AUTHEUR,
				        object.getUser().getId()+"",Field.Store.YES,Field.Index.NOT_ANALYZED);
		 
	  /* 
		    //recuperer le screen name d'un user
		      Field screename = new Field(LuceneConstants.SCREENAME,
				       sn+"",Field.Store.YES,Field.Index.NOT_ANALYZED);
		 
		   
		     // recuperer le nombre de tweets d'un utilisateur
		      Field Tweets = new Field(LuceneConstants.TWEETS,
				       tw+"",Field.Store.YES,Field.Index.NOT_ANALYZED);
		    
		      
		      //recuperer le nombre d'abonnés d'un user
		      Field abonnés = new Field(LuceneConstants.ABONNE,
				       ab+"",Field.Store.YES,Field.Index.NOT_ANALYZED);
		  
		      
		        */
		    
		     
             
		      document.add(contentField);
		      document.add(id_tweet);
		      document.add(auth);
		      document.add(id_auth);
		 /*
		      document.add(screename);
		      document.add(Tweets);
		
		      document.add(abonnés);*/
		      document.add(new Field("score", "42",  Field.Store.NO,  Field.Index.NOT_ANALYZED_NO_NORMS));
		        
		      }catch(Exception e){
		    	  
		    
		    	  e.printStackTrace();
					
				}
		      return document;
	    		     
		   } 
	   
	   
	   
	   public void indexFile(Object object) throws IOException, TwitterException {
		   System.out.println("Indexing "+object.getUser().getName());
		      Document document = getDocument(object);
		      writer.addDocument(document);
		   }
	   
	  
	
	   

}
