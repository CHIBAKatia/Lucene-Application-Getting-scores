package com.tutorialspoint.lucene;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.FieldDoc;

import org.apache.lucene.search.TopDocs; 
import org.apache.lucene.search.Sort;

import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.function.FieldScoreQuery;
import org.apache.lucene.search.function.ValueSourceQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.lucene.search.function.ValueSourceQuery;

public class teters {
	 private Scorer scorer;
	 static IndexSearcher searcher;
	 static QueryParser queryParser;
	 Query query;
	 Query q;
	 Query ql;
	 Map<String, Float> influences = new HashMap<>(); 
	 static long [] querytime; 
	 static String [] queries;
	 
	 
	//main
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, ParseException {

		ReaderTopic_ChargeInfluence top_inf = new ReaderTopic_ChargeInfluence();
		top_inf.readertopic();
		querytime = top_inf.getQuerytime();
		
		
		Directory dir = FSDirectory.open(new File("D:\\Lucene\\Index"));
		queryParser = new QueryParser(Version.LUCENE_36,LuceneConstants.CONTENTS,new StandardAnalyzer(Version.LUCENE_36));
		//Query ql = new FieldScoreQuery("score", FieldScoreQuery.Type.BYTE);
		
		
		
		IndexReader r = IndexReader.open(dir);
		searcher = new IndexSearcher (r);
		searcher.setDefaultFieldSortScoring(true, true);
		
		/*
		 Query q = queryParser.parse("java in action");
		 Query q2 = new  InfluenceBoosting(q);
		 Sort sort = new Sort(new SortField[] {
		 SortField.FIELD_SCORE, new SortField("title2", SortField.STRING)});
		 TopDocs hits = searcher.search(q, null, 5, sort);
		*/
		
		
		
		
		
		int j= 0;
		queries=top_inf.getQueries();
		 
			for(String queryString : top_inf.getTopics()){
		
				System.out.println(j+" "+queryString);
				String qs = queryString;
				
				 Query q = queryParser.parse(qs);
				 Query q2 = new  InfluenceBoosting(q);
				 Sort sort = new Sort(new SortField[] { SortField.FIELD_SCORE, new SortField("********", SortField.STRING)});
				 
				 TopDocs hits = searcher.search(q, null, 1000, sort);//fixer les resultats à 100
					
				
				System.out.println("tttt");
				writeInFile(LuceneConstants.RESULTS,hits,queries[j]+"");
					
				
				//int agb = hits.scoreDocs.length
			for (int i = 0; i < hits.scoreDocs.length; i++) {
				
				
				
				
				Document doc = r.document(hits.scoreDocs[i].doc);
				System.out.println("alhamdou lilah");
				System.out.println((1+i) + ": " + doc.get(LuceneConstants.AUTH) +" score=" + hits.scoreDocs[i].score);
				System.out.println("**************************************************************");
					
				
			} j++;
			
			System.out.println("///////////////////////////////////////");
			dir.close();
			
			
			}
	}
	public static void writeInFile(String file, TopDocs hits,String req) throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
		int j =1 ;
		 String reqt=req.replace("MB0", "");
		 Long re=Long.valueOf(reqt);
		
	   		for (int i = 0; i < hits.scoreDocs.length; i++) {
			Document doc = searcher.doc(hits.scoreDocs[i].doc);
			//if(Long.valueOf(doc.get(LuceneConstants.ID_TWEET))<querytime[j])
			//{
				writer.write(re+"\t Q0 "+doc.get(LuceneConstants.ID_TWEET)+" \t"+j+" "+hits.scoreDocs[i].score+"\t STANDARD");
				writer.newLine();
				j++;
			//}
			if(j>999)break;
		}
		
		System.out.println(" j = "+j);
		writer.close();
	}
	
	

}

