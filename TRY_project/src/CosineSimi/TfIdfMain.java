package CosineSimi;

import java.io.IOException;

import javax.annotation.processing.FilerException;


public class TfIdfMain {
	 /**
     * Main method
     * @param args
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void main(String args[]) throws FilerException, IOException
    {
        DocumentParser dp = new DocumentParser();
        dp.parseFiles("F:\\Project(Summarization)\\Sample.txt");
        dp.tfIdfCalculator(); //calculates tfidf
        dp.getCosineSimilarity(); //calculated cosine similarity   
    }
}
