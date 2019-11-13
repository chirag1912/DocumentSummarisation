package tess4j;

import java.io.*;
import net.sourceforge.tess4j.*;

public class Test {
	
	public Test(String path) throws IOException {
		
		
		File imageFile = new File(path);
		
		ITesseract instance = new Tesseract();
		instance.setDatapath("F:\\Project(Summarization)\\Summarization\\tessdata");
		
		String result;
		try {
			result = instance.doOCR(imageFile);
			System.out.println(result);
			
			FileWriter fr =  new FileWriter(new File("Recovered.txt"));
			BufferedWriter br = new BufferedWriter(fr);
			br.write(result);
			br.close();
			fr.close();
			
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
}
