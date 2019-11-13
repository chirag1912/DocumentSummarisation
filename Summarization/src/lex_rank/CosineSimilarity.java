package lex_rank;

public class CosineSimilarity {
	
	 public double calc_cosineSimilarity(double senVector1, double senVector2) {
	        double dotProduct = 0.0;
	        double magnitude1 = senVector1;
	        double magnitude2 = senVector2;
	        double cosineSimilarity = 0.0;

	        dotProduct = senVector1 * senVector2;  //a.b
	    
	      	cosineSimilarity =  Math.abs(magnitude1 - magnitude2);
	      
	        return cosineSimilarity;
	    }	
}
