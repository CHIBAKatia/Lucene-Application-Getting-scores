package com.tutorialspoint.lucene;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.LowerCaseTokenizer;
import org.apache.lucene.analysis.PorterStemFilter;
import org.apache.lucene.analysis.ReusableAnalyzerBase.TokenStreamComponents;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.util.Version;

public class StopAnalyser extends Analyzer {
	
	 private Set stopWords;
		//public static   Set<?> ENGLISH_STOP_WORDS_SET;
	 public StopAnalyser( ) {
		 stopWords = StopAnalyzer.ENGLISH_STOP_WORDS_SET;
	
	 }
	 public StopAnalyser(String[] stopWords) {
		 this.stopWords = StopFilter.makeStopSet(stopWords);
	 }

	public TokenStream tokenStream(String filename, Reader reader) {
		
		 return new StopFilter(true,
				new LowerCaseFilter(
						new LetterTokenizer(reader)),
						stopWords);
	}


}
