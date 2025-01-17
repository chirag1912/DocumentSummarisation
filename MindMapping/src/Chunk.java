import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class Chunk {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String sentence="The little yellow dog barked at the dog.";
		
		FileInputStream tokenModelIn = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
        Tokenizer tokenizer = new TokenizerME(tokenModel);
        String tokens[] = tokenizer.tokenize(sentence);
        // Parts-Of-Speech Tagging
        // reading parts-of-speech model to a stream
        InputStream posModelIn = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-pos-maxent.bin");
        // loading the parts-of-speech model from stream
        POSModel posModel = new POSModel(posModelIn);
        // initializing the parts-of-speech tagger with model
        POSTaggerME posTagger = new POSTaggerME(posModel);
        // Tagger tagging the tokens
        String tags[] = posTagger.tag(tokens);

        // reading the chunker model
        InputStream ins = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-chunker.bin");
        // loading the chunker model
        ChunkerModel chunkerModel = new ChunkerModel(ins);
        // initializing chunker(maximum entropy) with chunker model
        ChunkerME chunker = new ChunkerME(chunkerModel);
        // chunking the given sentence : chunking requires sentence to be tokenized and pos tagged
        String[] chunks = chunker.chunk(tokens,tags);
        // printing the results
        for(int i=0;i< chunks.length;i++){
            System.out.println(tokens[i]+" | "+tags[i]+" | "+chunks[i]);
        }
        
        for(int i=0;i<chunks.length;i++){
        	if(chunks[i].contains("NP"))
        		System.out.println(tokens[i]);
        	
        }

	}

}
