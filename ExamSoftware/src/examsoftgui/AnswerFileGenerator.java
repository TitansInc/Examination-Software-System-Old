
package examsoftgui;
import java.util.*;
import java.util.regex.*;
import java.io.*;
public class AnswerFileGenerator 
{
	String[] answers;
	public AnswerFileGenerator(String[] answers,String ID, String Subject){
		this.answers=answers;
		DataOutputStream out = null;
      String path=this.getClass().getResource("/examsoftgui/").getPath().toString().trim();
      int count=0;
      int start=0,end=0;
      for(end=0;end<path.length();end++){
          if(path.charAt(end)=='/'&& count!=3){
              count++;
               if(count==1){
                    start=end;
                }
          }
          if(count==3){
              break;
          }
         
      }
      path=path.substring(start,end+1);
		try {
			out = new DataOutputStream(new FileOutputStream(path+"/Desktop/"+ID+"_"+Subject+".tst"));

                } catch (FileNotFoundException e) {e.printStackTrace();}
		
		for(int i=0;i<answers.length;i++)
		{
			try {
				for(int j=0;j<9;j++){
					out.writeChars(String.valueOf((int)(Math.random()*10)));
					
				}
				if(answers[i].equalsIgnoreCase("A")){out.writeChars(String.valueOf(0));}
				else if(answers[i].equalsIgnoreCase("B")){out.writeChars(String.valueOf(1));}
				else if(answers[i].equalsIgnoreCase("C")){out.writeChars(String.valueOf(2));}
				else if(answers[i].equalsIgnoreCase("D")){out.writeChars(String.valueOf(3));}
				else if(answers[i].equalsIgnoreCase("T")){out.writeChars(String.valueOf(4));}
				else if(answers[i].equalsIgnoreCase("F")){out.writeChars(String.valueOf(5));}
				else if(answers[i].equalsIgnoreCase("N")){out.writeChars(String.valueOf(6));}
				else if(answers[i].equalsIgnoreCase("U")){out.writeChars(String.valueOf(7));}
				
			} catch (IOException e) {e.printStackTrace();}
			
		}
		try {
			out.close();
		} catch (IOException e) {e.printStackTrace();}

	}
}
