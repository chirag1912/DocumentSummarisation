import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class Sentence {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 InputStream is = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-parser-chunking.bin");

		    ParserModel model = new ParserModel(is);

		    opennlp.tools.parser.Parser parser = ParserFactory.create(model);

		    String sentence = "Newton published 'The Optics' in 1704.";
		   
		    InputStream inputStream = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-sent.bin"); 
		      SentenceModel sentmodel = new SentenceModel(inputStream); 
		       
		      //Instantiating the SentenceDetectorME class 
		      SentenceDetectorME detector = new SentenceDetectorME(sentmodel);  
		    
		      //Detecting the sentence
		      String sentences[] = detector.sentDetect(sentence); 
		     for(String sent : sentences)
		     {
		    Parse topParses[] = ParserTool.parseLine(sent, parser,2);
		    System.out.println();
		    for (Parse p : topParses)
		    	p.show();
		    }
		   
	}

}
