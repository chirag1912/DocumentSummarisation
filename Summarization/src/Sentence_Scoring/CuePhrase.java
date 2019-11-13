package Sentence_Scoring;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.Locale;
 
public class CuePhrase
{
	static String file;

   public CuePhrase(String file) {
        this.file = file;
    }
    	public void CuePhrases_creation(String CuePhrases)throws IOException{
		
		//FileInputStream //f1;
		//File fp = new File("F:\\Project(Summarization)\\Title.txt");
		//f1.close();
		RandomAccessFile f1 = new RandomAccessFile(new File("F:\\Project(Summarization)\\CuePhrases.txt"), "rw");
		f1.setLength(0);
		
	//	f1.getFD().sync();
		String text;
		Locale locale = Locale.US;
		BreakIterator wordIterator = BreakIterator.getWordInstance(locale); 
		text = CuePhrases;
		wordIterator.setText(text);
		int wordstart =wordIterator.first(); 
		for(int wordend = wordIterator.next();wordend!=BreakIterator.DONE;wordstart=wordend,wordend=wordIterator.next()){
			
			String value = Normalizer.normalize(text.substring(wordstart,wordend), Normalizer.Form.NFD);	//value =single word , normalizes unicode to suitable for sorting and searching
			
			if(!value.equalsIgnoreCase(" ")  ) 
			{f1.write(value.getBytes());
			 f1.write(System.getProperty("line.separator").getBytes());
			}
		}
		f1.close();
	}
    public static double totalCuePhrases() throws IOException {
        double totalNumberOfCuePhrases = 0;
        RandomAccessFile f1 = new RandomAccessFile(file, "rw");
        try 
        {
        	RandomAccessFile f2 = new RandomAccessFile("F:\\Project(Summarization)\\CuePhrases.txt", "r");
            String sentence = f1.readLine();

            while (sentence != null)
            {
                String cuePhrase = f2.readLine();
                while (cuePhrase != null)
                {
                    if (sentence.toLowerCase().contains(cuePhrase.toLowerCase()))
                    {
                        totalNumberOfCuePhrases++;
                    }
                    cuePhrase = f2.readLine();
                }
                f2.seek(0);
                sentence = f1.readLine();
            }
        }
        catch(Exception e){
        	
        }
     
        return totalNumberOfCuePhrases;
    }

    public static double cuePhraseScore(String sentence,double totalNoOfCuePhrases) throws IOException
    {

        return cuePhrasesInSentence(sentence)/totalNoOfCuePhrases;
    }
    public static double cuePhrasesInSentence(String sentence) throws IOException
    {
        int cuePhraseInSentence = 0;
        RandomAccessFile f1 = new RandomAccessFile("F:\\Project(Summarization)\\CuePhrases.txt", "r");
            String cuePhrase = f1.readLine();
            while (cuePhrase != null) {
                if (sentence.toLowerCase().contains(cuePhrase.toLowerCase())) {
                    cuePhraseInSentence++;
                   
                }
                cuePhrase = f1.readLine();
            }
        return cuePhraseInSentence;
    }
}

