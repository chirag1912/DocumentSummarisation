import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.parser.chunking.Parser;

public class WSD {
	 public static void main(String[] args) throws IOException {
		 
		 InputStream is = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-parser-chunking.bin");

		    ParserModel model = new ParserModel(is);

		    opennlp.tools.parser.Parser parser = ParserFactory.create(model);

		    String sentence = "Shakespeare is a great writer in the history of literature. He was born in 1564 and he had 3 children. He was earning his living from buying and selling the agricultural products. He lived in Stratford. He died in 1616”";
		    Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
		    
		    
		    
		    for (Parse p : topParses)
		        p.show();
		    is.close();

	 }
}

