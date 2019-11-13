import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class ParseWords {

	static Set<String> nounPhrases = new HashSet<>();
	public static void getNounPhrases(Parse p) {
		
	    if (p.getType().equals("NP")) { //NP=noun phrase
	         nounPhrases.add(p.getCoveredText());
	    }
	    for (Parse child : p.getChildren())
	         getNounPhrases(child);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 InputStream is = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-parser-chunking.bin");

		    ParserModel model = new ParserModel(is);

		    opennlp.tools.parser.Parser parser = ParserFactory.create(model);

		    String sentence = "George Washington was born in 1732 in Virginia.";
		   
		   
		    Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
		    System.out.println();
		    for (Parse p : topParses)
		    {
		      p.show();
		      getNounPhrases(p);
		    }
		    for (String s : nounPhrases)
			    System.out.println(s);
		    
		   
	}

}
