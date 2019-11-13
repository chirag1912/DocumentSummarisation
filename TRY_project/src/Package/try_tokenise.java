package Package;

//package preprocessor;

//import Auto_Summary.main;
//import Sentence_Scoring.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Scanner;

//public class Tokeniser extends Tika_Extraction {
	
	/*public Tokeniser(String path){
		super(path);
	}*/
	
public class try_tokenise{
	
	public void tokenise() throws Exception{
		
		
		//extract(); //extract raw text apache tika
		Locale locale = Locale.US;
		BreakIterator wordIterator = BreakIterator.getWordInstance(locale); 			//it breaks the word first and last position of break(space)
		String temp = "";
		//System.out.println("hh");
		//Scanner scan =  new Scanner("F:\\Project(Summarization)\\Sample.txt");
		//FileInputStream in = new FileInputStream("F:\\Project(Summarization)\\Sample.txt");
		Scanner scan =  new Scanner(new File("F:\\Project(Summarization)\\Sample.txt"));
		while(scan.hasNext()){
			temp+=" "+scan.next();
			System.out.println(temp);
		}
		
		String text = "";
		wordIterator.setText(temp);
		int wordstart =wordIterator.first(); System.out.println("ws: "+wordstart);
		for(int wordend = wordIterator.next();wordend!=BreakIterator.DONE;wordstart=wordend,wordend=wordIterator.next()){
			System.out.println("we :"+wordend);
			String value = Normalizer.normalize(temp.substring(wordstart,wordend), Normalizer.Form.NFD);	//value =single word , normalizes unicode to suitable for sorting and searching
			System.out.println("ss : "+value);
			value =  value.replaceAll(" ","");			//replaces all string not in ASCII format to null
			System.out.println(value);
			text+=" "+value;
			text =  text.replaceAll("  "," ");
		}
		System.out.println(text);	temp = ""+text;		//lines followed by words
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US); 			//breaking sentences using english grammer
		iterator.setText(temp);			//pointing to temp for scanning
		
		int start = iterator.first();
		int index=0;
		//FileOutputStream out = new FileOutputStream(new File("F:\\Project(Summarization)\\Tokens.txt")) ;
		System.out.println(start);
		
		for(int end = iterator.next(); end!=BreakIterator.DONE;start=end,end=iterator.next()){
			int spaces =0 ;
			System.out.println("end : "+end);
			String finaltext = temp.substring(start,end).trim().replaceAll("		", " "); 
			System.out.println("final"+finaltext);
			
			for(int i =0 ;i<finaltext.length();i++){
				
				if(Character.isWhitespace(finaltext.charAt(i))) spaces++;
			}
			System.out.println("spaces : "+spaces);
			if((spaces+1)<3) continue;		//minimal 3 words in a line 
			
			index++;
		//	System.out.println("final"+finaltext);
			
		}
		System.out.println(index);
		//out.flush();
		//int noOfLines = index;
		//double totalNoOfCuePhrases = new CuePhrase("F:\\Project(Summarization)\\Tokens.txt");
		//scan = new Scanner(new File("F:\\Project(Summarization)\\Tokens.txt"));
		//index=0;
		
		/*while(scan.hasNext()){
			
			//this loop
			temp = scan.nextLine();
			temp =  temp.replaceAll("[^\\p{ASCII}]","");
			if(SentenceLength.noOfWords(temp)<2)	continue;
			
			
		}
		*/

}
	public static void main(String[] args) {
		System.out.println("hello");
		System.out.println("enter");
	
		try_tokenise t=new try_tokenise();
		try {
			t.tokenise();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

