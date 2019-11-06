package com.tutorialspoint.lucene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTopic_ChargeInfluence {
	

	String [] topics = new String[6]; // un tableau de string de taille 6
	String [] queries = new String[6];
	long [] querytime = new long[6];
	private BufferedReader reader;
	public void readertopic() throws IOException{
		
	/*	
		System.out.println(topics[1]);
		System.out.println(queries[1]);
		System.out.println(querytime[1]);
	
	*/	
		reader = new BufferedReader(new FileReader("D:\\Lucene\\req.txt"));
		String line, topic, time,query;
		int i=0;
	
		
		while((line = reader.readLine())!=null){
			if(line.contains("<title>")){
				topic = line.replace("<title>", "").replace("</title>", "").trim();
				topics[i] = topic;
				
			
			}
			if(line.contains("<querytweettime>")){
				time = line.replace("<querytweettime>", "").replace("</querytweettime>", "").trim();
				querytime[i] = 	Long.valueOf(time);
				i++;	
			}
			if(line.contains("<num> Number:")){
				query = line.replace("<num> Number:", "").replace("</num>", "").trim();
				queries[i] = query;
		
			}
		}
		
	/*	System.out.println(topics[0]);
		System.out.println(queries[i]);
		System.out.println(querytime[i]);
		System.out.println("****************************");
*/
			
	}
	public String afficheTopics(){
		for(String top : topics){
			System.out.println(top);
		}
		return null;
	}
	public String afficheQueries(){
		for(String top : queries){
			System.out.println(top);
		}
		return null;
	}
	public String [] getTopics(){
		return topics;
	}
	public String [] getQueries(){
		return queries;
	}
	
	
	
	public long[] getQuerytime() {
		return querytime;
	}

	

}
