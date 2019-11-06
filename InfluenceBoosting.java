package com.tutorialspoint.lucene;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//import searcher_custom.*;
import twitter4j.TwitterException;
import org.apache.lucene.search.function.ValueSourceQuery;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

import com.tutorialspoint.lucene.Indexer;

import org.apache.lucene.search.function.CustomScoreProvider;
import org.apache.lucene.search.function.CustomScoreQuery;

public class InfluenceBoosting extends CustomScoreQuery {
	private static final long serialVersionUID = -67195186917806375L;
	float influence;
	Map<String, Float> expertise;
	double mul;

	
	public  InfluenceBoosting(Query subQuery) {
		super(subQuery);
		
	}
	
	
	
	
	
	private class RecencyBooster extends CustomScoreProvider {
		final String[] values;
		final long [] id_tweet;
		final int[] id;
		final long [] abn;
		final long [] tws;
		 
	public RecencyBooster(IndexReader r) throws IOException  {
	
			 super(r);
				
			 values    = FieldCache.DEFAULT.getStrings(r, LuceneConstants.AUTH);
			 id_tweet  = FieldCache.DEFAULT.getLongs(r, LuceneConstants.ID_TWEET);
			 id        = FieldCache.DEFAULT.getInts(r, LuceneConstants.ID_AUTHEUR);
			 abn       = FieldCache.DEFAULT.getLongs(r, LuceneConstants.ABONNE);
			 tws       = FieldCache.DEFAULT.getLongs(r, LuceneConstants.TWEETS);
			 
			
		 }
		 
	public float customScore(int doc, float subQueryScore,float valSrcScore) {
			float score = 0;
			
				
		
			String auth= values[doc];//nom de l'auteur , du user
			int idu = id [doc];
			
			
			
			// with cache
			//Long followers = abn [doc];
			//Long tweets = tws [doc];
			
			
			
			
			
			
			
			
			
			// with twitter 
			long followers;
			long tweets;
			
			
			
			
		if(auth!=null){
			
			
				try {
					
					
				
				
				  
				  followers = (long) (TESTE.recupf(idu));
				  tweets =  (long) (TESTE.recup(idu));
				   
				 
				  score = (float) ( Math.log( tweets) + Math.log(followers));
				  
				  
				  
								
				
					
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				
				}
		
				//return  0.01f*subQueryScore + 0.99f*score;
				return  0.3f*subQueryScore + 0.7f*score;
				
		}else return	subQueryScore; 
		
			
			
		}		

			 
			
		 }
	 
	public CustomScoreProvider getCustomScoreProvider(IndexReader r) throws IOException {
		return new RecencyBooster(r);		 
	 }
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
