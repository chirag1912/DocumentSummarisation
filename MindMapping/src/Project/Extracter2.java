package Project;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

public class Extracter2 {
	
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//String sentence="Newton was born in 1643 in Lincolnshire.";

String sentence1 = "Newton was an English physicist and mathematician. he was born in 1643 in Lincolnshire. His father was a prosperous farmer. Newton went to University of Cambridge in 1661. He was interested in mathematics, optics, physics and astronomy. Newton published The Optics in 1704. Newton was elected as member of the parliament in 1689 for University of Cambridge.";

InputStream inputStream = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-sent.bin"); 
  SentenceModel sentmodel = new SentenceModel(inputStream);    
  //Instantiating the SentenceDetectorME class 
  SentenceDetectorME detector = new SentenceDetectorME(sentmodel); 
  
String sentences[] = detector.sentDetect(sentence1); 
for(String sentence : sentences)
{	
		FileInputStream tokenModelIn = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-token.bin");
        TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
        Tokenizer tokenizer = new TokenizerME(tokenModel);
        String tokens[] = tokenizer.tokenize(sentence);
       
        InputStream posModelIn = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-pos-maxent.bin");
        POSModel posModel = new POSModel(posModelIn);
        POSTaggerME posTagger = new POSTaggerME(posModel);
        String tags[] = posTagger.tag(tokens);

        InputStream ins = new FileInputStream("C:\\Users\\Abhijeet Ingale\\Desktop\\OpenNlp\\en-chunker.bin");
        ChunkerModel chunkerModel = new ChunkerModel(ins);
        ChunkerME chunker = new ChunkerME(chunkerModel);
        String[] chunks = chunker.chunk(tokens,tags);
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("outA.txt", true));
        
        for(int i=0;i< chunks.length;i++){
            System.out.println(tokens[i]+" | "+tags[i]+" | "+chunks[i]);
        }
        
        boolean flag=false;
       
        for(int i=0;i<chunks.length;i++){
        	 while(tags[i].contains("NN") && chunks[i].contains("NP"))
             {
        		writer.append(tokens[i]);
        		writer.append(' ');
             	System.out.print(tokens[i]+" ");
             	i++;
                
             }
        	 
        	 writer.newLine();
        	 writer.append("\t");
        	 System.out.println("\t");
        	 
        	while(chunks[i].contains("VP"))
        	{
        		writer.append(tokens[i]);
        		writer.append(' ');
        		System.out.println(tokens[i]);
        		i++;
        	}
        	 writer.newLine();
        	 writer.append("\t\t");
        	 System.out.println("\t\t");
        	 while(chunks[i].equals("IN") || (tags[i].contains("NN") && chunks[i].contains("NP") ))
             {
        		writer.append(tokens[i]);
        		writer.append(' ');
             	System.out.print(tokens[i]+" ");
             	i++;
                
             }
        }
		
		writer.close();
		
		

	}
}
}
