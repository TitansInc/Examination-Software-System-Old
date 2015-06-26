/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examsoftgui;

/**
 *
 * @author Guru
 */
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ExamDetailsChck {
      String []arr=new String[5];
      ExamDetailsChck(){
      DataInputStream d = null;
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
                       
			d = new DataInputStream(new FileInputStream(path+".gnome2/eog/catch/ExamDetails.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String str;
		int f=0;
		try {
			while((str=d.readLine())!=null)
			{
				arr[f]=str;
				f++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	String[] getDetails(){
		return arr;
		
	}
}

