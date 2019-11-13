package Sentence_Scoring;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.Scanner;
import java.util.Locale;


public class TitleResemblance {
	static String file;
	static String title;
	public TitleResemblance(String file,String title){
		this.file = file;
		this.title=title;
		
	}
	
	public static void Title_creation()throws IOException{
		
		//FileInputStream //f1;
		//File fp = new File("F:\\Project(Summarization)\\Title.txt");
		//f1.close();
		RandomAccessFile f1 = new RandomAccessFile(new File("F:\\Project(Summarization)\\Title.txt"), "rw");
		f1.setLength(0);
		
		f1.getFD().sync();
		String text;
		Locale locale = Locale.US;
		BreakIterator wordIterator = BreakIterator.getWordInstance(locale); 
		text = title;
		wordIterator.setText(text);
		int wordstart =wordIterator.first(); 
		for(int wordend = wordIterator.next();wordend!=BreakIterator.DONE;wordstart=wordend,wordend=wordIterator.next()){
			
			String value = Normalizer.normalize(text.substring(wordstart,wordend), Normalizer.Form.NFD);	//value =single word , normalizes unicode to suitable for sorting and searching
			
			if(!value.equalsIgnoreCase(" ")) 
			{f1.write(value.getBytes());
			f1.write(System.getProperty("line.separator").getBytes());
			}
		}
		f1.close();
	}
	public static double resemblence() throws IOException {
		
		
		double totalNumberOfResemblences = 0;
		RandomAccessFile f1 = new RandomAccessFile(file, "rw");
      try 
      {
      	RandomAccessFile f2 = new RandomAccessFile("F:\\Project(Summarization)\\Title.txt", "r");
          String sentence = f1.readLine();

          while (sentence != null)
          {
              String titlePhrase = f2.readLine();
              while (titlePhrase != null)
              {
                  if (sentence.toLowerCase().contains(titlePhrase.toLowerCase()) && !titlePhrase.equalsIgnoreCase(" "))
                  {
                      totalNumberOfResemblences++;
                  }
                  titlePhrase = f2.readLine();
              }
              f2.seek(0);
              sentence = f1.readLine();
          }
          f2.close();
          f1.close();
      }
      catch(Exception e){
      	
      }
     
      
     
      return totalNumberOfResemblences;
      
}

  public static double titlePhraseScore(String sentence,double totalNoOftitlePhrases) throws IOException
  {

      return titlePhrasesInSentence(sentence)/totalNoOftitlePhrases;
  }
  public static double titlePhrasesInSentence(String sentence) throws IOException
  {
      int titlePhraseInSentence = 0;
      RandomAccessFile f1 = new RandomAccessFile("F:\\Project(Summarization)\\Title.txt", "r");
          String titlePhrase = f1.readLine();
          while (titlePhrase != null) {
              if (sentence.toLowerCase().contains(titlePhrase.toLowerCase())&& !titlePhrase.equalsIgnoreCase(" ")) {
                  titlePhraseInSentence++;
                  
              }
              titlePhrase = f1.readLine();
          }
          f1.close();
          return titlePhraseInSentence;
          
      
  }

}
