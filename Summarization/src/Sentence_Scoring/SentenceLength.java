package Sentence_Scoring;

public class SentenceLength {
public static double sentenceLengthScore(int length,double avgSentenceLenght)
{
  return length;
  
}
public static int noOfWords(String sentence)
{
	int count=1;
	
		 
	        for (int i = 0; i < sentence.length(); i++) {
	            if(Character.isWhitespace(sentence.charAt(i)))
	                count++;
	
	        
	        }
	        return count;
}
}