package preprocessor;

public class Sentence {
	double ID;
    String text;
    double sentenceLengthScore;
    double sentencePositionScore;
    double cuePhraseScore;
    double titleResemblanceScore;
   double sentenceScore;

    Sentence(double ID,String text,double sentenceLengthScore,double sentencePositionScore,double cuePhraseScore,double titleResemblanceScore)
    {
        this.ID=ID;
        this.text=text;
        this.sentenceLengthScore=sentenceLengthScore;
        this.sentencePositionScore=sentencePositionScore;
        this.cuePhraseScore=cuePhraseScore;
        this.titleResemblanceScore=titleResemblanceScore;
       
    }

    @Override
    public String toString() {
     return "["+ID+"] ["+text+"] ["+sentenceLengthScore+"] ["+sentencePositionScore+"] ["+cuePhraseScore+"] ["+titleResemblanceScore+"]";
    }
}
