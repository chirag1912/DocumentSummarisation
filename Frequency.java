package lex_rank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.MathContext;
import java.io.File;
import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import Sentence_Selection.Threshold;

public class Frequency extends Threshold {
	static Map<String,Integer> allWords = new HashMap<String, Integer>();
	
	public HashMap<String, Integer> frequency_calculation() throws Exception{
		
		Map<String,Integer> freqWords = new HashMap<String, Integer>();
		RandomAccessFile f1 = new RandomAccessFile("F:\\Project(Summarization)\\StopWordOutput.txt", "rw");
		String text1 = f1.readLine();
		while(text1!=null){
			freqWords=getWordCounts(text1);
			text1=f1.readLine();
			//totalNoOfWords++;
			//System.out.println("totalNoOfWords"+totalNoOfWords);
		}
			HashMap<String,Integer> sorted  = 
			sortByFreqThenDropFreq(freqWords);
		
		return sorted;
	}
	
	public Map<String, Integer> getWordCounts(String text)
	{
		text = text.toLowerCase();
		int count = 1;
			if(allWords.containsKey(text))//do a check to see if a word already exists in the collection
			{
				allWords.put(text, (allWords.get(text)+1));
			}
			else
			{
				allWords.put(text, count++);
			}
			
		return allWords;
	}
			
	private HashMap<String, Integer> sortByFreqThenDropFreq(Map<String,Integer> wordFrequencies)				
	{
		Comparator<Entry<String, Integer>> valueComparator = new Comparator<Entry<String,Integer>>() { 
			@Override public int compare(Entry<String, Integer> e1, Entry<String,Integer> e2) { 
				Integer v1 = e1.getValue(); 
				Integer v2 = e2.getValue(); 
				return v1.compareTo(v2); 
				}
			};
			
			Set<Entry<String, Integer>> entries = wordFrequencies.entrySet();
			
			List<Entry<String, Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(entries); 
		
			Collections.sort(listOfEntries, valueComparator); 
			Collections.reverse(listOfEntries);
			
			HashMap<String, Integer> sortedByValue = new HashMap<String, Integer>(listOfEntries.size()); 
			for(Entry<String, Integer> entry : listOfEntries){ 
				sortedByValue.put(entry.getKey(), entry.getValue()); 
				} 
			
			Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet(); 
			
			return sortedByValue;
	}
	
	
	public double sentence_score(String text,int tcount) throws Exception{
		Frequency f = new Frequency();
		HashMap<String, Integer> freqWords =  f.frequency_calculation();
		//RandomAccessFile f1 = new RandomAccessFile("D:\\Project(Summarization)\\Sample.txt", "rw");
		
		Locale locale = Locale.US;
		BreakIterator wordIterator = BreakIterator.getSentenceInstance(locale); 
	
		wordIterator.setText(text);
		double sentence_score=0;
		
		sentence_score = search(text, freqWords,tcount) ;
		//System.out.println("sentence_score ;  "+sentence_score);
		
		return sentence_score;
	}
	
	
	private double search(String sentence,HashMap<String, Integer> freqWords,int totalNoOfWords)
	{
		Set<Entry<String, Integer>> entrySetSortedByValue = freqWords.entrySet(); 
		
		double totalfreq=0,sent_tfidf=0;
		
		//System.out.println("HASH MAP");
		for(Entry<String, Integer> mapping : entrySetSortedByValue){ 
			
				totalfreq+=mapping.getValue();
		//		System.out.println(mapping.getKey()+"  ---   "+mapping.getValue());
				
				
			}
		
		
		
		
		
		int i=0,j=0;String word="";
		int len=sentence.length();
		
		try{
		while(i<len)
		{	
			word="";
			while(sentence.charAt(j)!=' ')//||sentence.charAt(j)!='.')
			{
				word+=sentence.charAt(j++);
			}
			
			int count=0;
			double tf,idf,tfidf;
			for(Entry<String, Integer> mapping: entrySetSortedByValue){ 
				
				if(word.toLowerCase().equals(mapping.getKey())){
					//System.out.println("hi"+mapping.getKey()+mapping.getValue());
					count=mapping.getValue();
					//System.out.println("count:  "+count+"total : "+totalNoOfWords);
					//System.out.println("totalNoOfWords "+totalNoOfWords);
					tf=((double)count/totalNoOfWords);
					//tf = Math.round(tf);
					
					//System.out.println(total_distinct);
					idf=Math.log((double)allWords.size()/count);
					//idf = Math.round(idf);
					//System.out.println("tf = "+tf+"idf = "+idf);
					tfidf=tf*idf;
					
					sent_tfidf+=tfidf;
					
					break;
				}}
				j++;
				i=j;
				
				
				
		}
		}
		catch(Exception e){}
		
		
		return (sent_tfidf);
	}
	
	
	

}
