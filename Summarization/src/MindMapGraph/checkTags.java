package MindMapGraph;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class checkTags {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// String sentence="Newton was born in 1643 in Lincolnshire.";

		Scanner sc = new Scanner(System.in);
		String inputSent = sc.nextLine();
		InputStream inputStream = new FileInputStream("OpenNlp\\en-sent.bin");
		SentenceModel sentmodel = new SentenceModel(inputStream);
		// Instantiating the SentenceDetectorME class
		SentenceDetectorME detector = new SentenceDetectorME(sentmodel);

		String sentences[] = detector.sentDetect(inputSent);
		for (String sentence : sentences) {
			FileInputStream tokenModelIn = new FileInputStream("OpenNlp\\en-token.bin");
			TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
			Tokenizer tokenizer = new TokenizerME(tokenModel);
			String tokens[] = tokenizer.tokenize(sentence);

			InputStream posModelIn = new FileInputStream("OpenNlp\\en-pos-maxent.bin");
			POSModel posModel = new POSModel(posModelIn);
			POSTaggerME posTagger = new POSTaggerME(posModel);
			String tags[] = posTagger.tag(tokens);

			InputStream ins = new FileInputStream("OpenNlp\\en-chunker.bin");
			ChunkerModel chunkerModel = new ChunkerModel(ins);
			ChunkerME chunker = new ChunkerME(chunkerModel);
			String[] chunks = chunker.chunk(tokens, tags);

			
			for (int i = 0; i < chunks.length; i++) {
				System.out.println(tokens[i] + " | " + tags[i] + " | " + chunks[i]);
			}


		}
	}
}
