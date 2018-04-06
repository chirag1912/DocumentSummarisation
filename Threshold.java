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
    public static boolean applyThreshold(double sentenceLength,double sentencePosition,double cuePhrases,double titleresemblences,double sentenceScore,double noOfWords)
    {
        boolean flag=false;int c=0;
       /* try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/summarizer","root","root");
            PreparedStatement statement=connection.prepareStatement("Select avg(cue_phrases) from sentences");
            PreparedStatement update=connection.prepareStatement("update sentences set selected=true where id=?");
            ResultSet avg=statement.executeQuery();
            double avgCuePhrases=0;
            while (avg.next())
                avgCuePhrases=avg.getDouble(1);

            statement=connection.prepareStatement("Select * from sentences");
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int id=resultSet.getInt(1);
                double sentenceLength=resultSet.getDouble(3)/10;
                double sentencePositon=resultSet.getDouble(4);
                double cuePhrases=resultSet.getDouble(5);
//                double titleResemblance=resultSet.getDouble(6);
*/
                if((sentenceLength)>=15 && (sentenceLength)<=20)
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
                if((sentenceScore/noOfWords)>=0.1)
                {
                	System.out.println("Frequency");
                	flag=true;
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



