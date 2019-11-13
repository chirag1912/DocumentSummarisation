package preprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Scanner;

import sun.security.util.Length;

public class PrerequisiteCalc {
	
	public int[] Calc(String SampleFileName) throws IOException {
	
		Scanner scan = new Scanner(new File(SampleFileName));
		int noOfSent=0, noOfWords =0;
		String line="",temp="";
		FileReader file = new FileReader(SampleFileName);
		BufferedReader br1 = new BufferedReader(file);
		//line=scan.nextLine(); 
		while (scan.hasNextLine()) {
			//temp = line;
			line=scan.nextLine();
			//noOfSent++;
			for(int i=0;i<line.length();i++) {
				if(line.charAt(i)==' ')
					noOfWords++;
				if(line.charAt(i)=='.') {
					noOfWords++;
					noOfSent++;
				}
			}
			
		}
		
		int ret[] = {noOfSent,noOfWords};
		return ret; 
	}

}
