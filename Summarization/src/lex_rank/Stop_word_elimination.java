package lex_rank;

import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.*; 
import java.util.regex.Pattern;
import java.io.*;



public class Stop_word_elimination {
	
	public int Eliminate(String SampleFileName) throws Exception {
		
    RandomAccessFile f1 = new RandomAccessFile(SampleFileName, "rw");
   
    RandomAccessFile fp = new RandomAccessFile(new File("F:\\Project(Summarization)\\StopWordOutput.txt"), "rw");
    
    	//fp.flush();
    	int count=0;
    	Boolean flag = false;
    	Locale locale = Locale.US;
		BreakIterator wordIterator = BreakIterator.getWordInstance(locale); 
		String text = f1.readLine();
		wordIterator.setText(text);
		int wordstart =wordIterator.first(); 
		for(int wordend = wordIterator.next();wordend!=BreakIterator.DONE;wordstart=wordend,wordend=wordIterator.next()){
			
			String value = Normalizer.normalize(text.substring(wordstart,wordend), Normalizer.Form.NFD);	//value =single word , normalizes unicode to suitable for sorting and searching
			RandomAccessFile f2 = new RandomAccessFile("F:\\Project(Summarization)\\StopWords.txt", "r");
			flag = false;
			String t = f2.readLine();
			while(t!=null){
				
			if(value.equalsIgnoreCase(" ") || value.equalsIgnoreCase(t)) 
			{
				flag =true;
				break;
			}
			
			
			t = f2.readLine();
			}
			if(!flag){
				fp.write(value.getBytes());
				fp.write(System.getProperty("line.separator").getBytes());
			}
			else count++;
			f2.seek(0);
		}
		//f1.close();
		return count;
		}

}
 
  
	
	
	

