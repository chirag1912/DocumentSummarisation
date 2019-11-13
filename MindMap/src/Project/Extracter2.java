package Project;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.TransferHandler.TransferSupport;

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

String sentence1 = "Van Gogh is great well-known painter. He was born in 1853. He worked in an international firm of art in 1869 in Hague. He got jobs in London and then Paris. He was not interested in them. He was dismissed from them In 1876. He became an artist In 1880. He met many artists in 1886. They include Theo, Degas, Toulouse-Lautrec, Pissarro and Gauguin. He painted a large number of self-portraits. He painted his famous series of Sunflowers In 1888. He threatened Gauguin with a razor In 1888. He suffered from mental problems. He spent time in psychiatric hospitals. He committed suicide in 1890.";
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
        
        boolean f1=false,f2=false,f3=false;
        int count=0;
        for(int i=0;i<chunks.length;i++){
        	if(count>0 && tags[i].contains("N")) writer.append('\t');
        	 while( chunks[i].contains("NP") && (tags[i].equals("PRP")==false))
             {
        		 if(tags[i].equals(",") || tags[i].equals("CC")) break;
        		writer.append(tokens[i]);
        		writer.append(' ');
        		i++;
             	 if(chunks[i].contains("PP"))
             		{
             		if(tokens[i].toLowerCase().equals("in")) 
             			{
             			writer.newLine();
             			writer.append("\t\t");
             			}
             		writer.append(tokens[i]+ ' ');
             		i++;
             		}
             	f1=true;
             	count++;
             }
        		if(f1==true) 
    			{
    			writer.newLine();
    			writer.append('\t');
    			
    			f1=false;
    			}
    
        	while(chunks[i].contains("-VP") || tags.equals("RB"))
        	{
        		writer.append(tokens[i]);
        		writer.append(' ');
        		i++;
        		 if(tags[i].equals("NN") || tags[i].equals("NNS")) writer.append(tokens[i]); 
        		f3=true;	
        	}
        	 if(f3==true) 
    			{
    			writer.newLine();
    			writer.append("\t\t");
    			f3=false;
    			}
        }
		
		writer.close();
	}
}
}

