package MindMapGraph;

	import java.io.BufferedWriter;
	import java.io.FileInputStream;
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

	public class Extracter5 {

		private static int noofsent;

		public static void main(String[] args) throws IOException {

			Scanner sc = new Scanner(System.in);
			String inputSent = sc.nextLine();

			InputStream inputStream = new FileInputStream("F:\\Project(Summarization)\\Summarization\\OpenNlp\\en-sent.bin");
			SentenceModel sentmodel = new SentenceModel(inputStream);
			// Instantiating the SentenceDetectorME class
			SentenceDetectorME detector = new SentenceDetectorME(sentmodel);

			BufferedWriter cleanup = new BufferedWriter(new FileWriter("outA.txt"));
			cleanup.append("");
			cleanup.close();

			BufferedWriter writer = new BufferedWriter(new FileWriter("outA.txt", true));

			int tab = 0;
			String sentences[] = detector.sentDetect(inputSent);
			String title = sentences[0];
			writer.append(title);
			writer.newLine();
			noofsent = 0;
			for (int s = 1; s < sentences.length; s++) {
				String sentence = sentences[s];
				tab = 0;
				// writer.append('\t');
			
				FileInputStream tokenModelIn = new FileInputStream("F:\\Project(Summarization)\\Summarization\\OpenNlp\\en-token.bin");
				TokenizerModel tokenModel = new TokenizerModel(tokenModelIn);
				Tokenizer tokenizer = new TokenizerME(tokenModel);
				String tokens[] = tokenizer.tokenize(sentence);

				InputStream posModelIn = new FileInputStream("F:\\Project(Summarization)\\Summarization\\OpenNlp\\en-pos-maxent.bin");
				POSModel posModel = new POSModel(posModelIn);
				POSTaggerME posTagger = new POSTaggerME(posModel);
				String tags[] = posTagger.tag(tokens);

				InputStream ins = new FileInputStream("F:\\Project(Summarization)\\Summarization\\OpenNlp\\en-chunker.bin");
				ChunkerModel chunkerModel = new ChunkerModel(ins);
				ChunkerME chunker = new ChunkerME(chunkerModel);
				String[] chunks = chunker.chunk(tokens, tags);

				boolean f1 = false, f2 = false, f3 = false;
				int count = 0;

				for (int i = 0; i < tags.length; i++) {
					System.out.println(tokens[i] + " | " + tags[i] + " | " + chunks[i]);
				}

				wordLoop: for (int i = 0; i < chunks.length;) {

					System.out.println("outside: " + tokens[i]);
					/*
					 * if (count > 0 && tags[i].contains("N")) writer.append('\t');
					 */
					if (chunks[i].contains("NP") || (chunks[i].contains("-ADJP") )) {

						for (int k = 0; k < tab; k++)
							writer.append('\t');

						// System.out.println("tab"+tab);
						tab++;

					}
					while (title.contains(tokens[i]))
						i++;

					while (chunks[i].contains("-ADJP") || chunks[i].contains("NP") || chunks[i].contains("PP")) {
						System.out.println(tokens[i]);

						
						if (tags[i].equals("PRP") && count == 0) { // skip first occurence of pronoun
							i++;
						}
						if (tags[i].equals("DT") || tags[i].equals("TO"))// skip a,an,the,to
							i++;
						if (chunks[i].contains("-VP"))
							break;

						writer.append(tokens[i]);
						writer.append(' ');

						if (i >= chunks.length - 1)
							break wordLoop;
						else
							i++;

						if (chunks[i].contains("-PP") && !(tokens[i].equals("of"))) { // remove for,in,other preposition and
																						// take only 'of'
							i++;

							writer.newLine();
							for (int k = 0; k < tab; k++)
								writer.append('\t');

							// tab++;
						}
						f1 = true;
						count++;
						if (tokens[i].contains(",") || tags[i].equals("CC") || tags[i].equals(".")
								|| chunks[i].contains("-ADVP")) // skip ,and
						{
							tab--;
							System.out.println("inner tab="+tab);
							break;
						}
					
					}

					if (f1 == true) {
						writer.newLine();
						/*
						 * tab++; if (tags[i].equals(",") || tags[i].equals("CC")) { for (int k = 0; k <
						 * tab; k++) writer.append('\t');
						 */
						// }
						f1 = false;
					}

					if (chunks[i].contains("-ADVP"))
						break wordLoop;
					if (chunks[i].contains("-VP")) {

						for (int k = 0; k < tab; k++)
							writer.append('\t');
						tab++;
					}
					while (chunks[i].contains("-VP") || tokens[i].toLowerCase().equals("not")) {
						System.out.println("VP:" + tokens[i]);

						writer.append(tokens[i]);
						writer.append(' ');
						i++;

						f2 = true;
					}

					if (f2 == true) {
						writer.newLine();
						// writer.append("\t\t");
						f2 = false;
					}

					if (chunks[i].contains("-PP") || chunks[i].contains("O") || tags[i].equals("PRP") || tags[i].equals(",") // exclude
																																// this
																																// part
																																// in
																																// mindmap
							|| tags[i].equals("CC") || tags[i].equals("IN") || tokens[i].toLowerCase().equals("then")
							|| tags[i].equals("RB") || tags[i].equals("TO"))
						i++;

				}

				sc.close();
			}
			writer.close();
			//drawGraph draw = new drawGraph();
			//draw.main(null);
		}
	}

