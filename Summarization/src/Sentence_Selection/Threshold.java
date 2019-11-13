package Sentence_Selection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Threshold {
	/*public static void main(String []args)
    {
        applyThreshold();
    }*/
    public static boolean applyThreshold(double sentenceLength,double sentencePosition,double cuePhrases,double titleresemblences)
    {
        boolean flag=false;int c=0;
     
                if((sentenceLength)>=10 && (sentenceLength)<=15)
                {
                    System.out.println("Sentence length");
                    flag=true;c=1;
                }
                if(sentencePosition<0.2 || sentencePosition>0.8) {
                    System.out.println("Sentence Postion");
                    flag = true;c=2;
                }

                if(cuePhrases >= 0.25)
                {
                    System.out.println("Cue Phrases");
                    flag=true;c=3;
                }
                if(titleresemblences>=0.5){
                 System.out.println("Title Resemblence");
                     flag=true;c=4;
                }
                

                //else flag=false;
              return flag;
                
              /*  if(flag==true) 
                	return 1;
                else
                	return 0;
               *//* if(flag==true)
                {
                    update.setInt(1,id);
                    update.executeUpdate();
                }*/
            }
    
     //    connection.close()     //   }
        /*catch(Exception e)
        {
            e.printStackTrace();
        }*/

    }



