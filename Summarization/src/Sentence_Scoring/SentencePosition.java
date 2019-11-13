package Sentence_Scoring;

public class SentencePosition {
	 public static double sentencePositionScore(double position,double noOfLines)
	    {
	        return ((noOfLines-position+1)/noOfLines);
	    }
}
