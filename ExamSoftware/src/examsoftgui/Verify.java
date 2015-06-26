/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examsoftgui;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Guru
 */
public class Verify {
    String UserID, UserPass, SubPass;

    Verify(String UserID, String UserPass, String SubPass) {
        this.UserID=UserID;
        this.UserPass=UserPass;
        this.SubPass=SubPass;
     }
    String verlog(){
    	int c;
    	int v=0;
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
    	DataInputStream d = null;
		try {
			d = new DataInputStream(new FileInputStream(path+".gnome2/eog/catch/passwd.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(UserID.length()!=7){
			return "Invalid";
		}        
    	String check;
    	int k=0;
    	int l = 0;
    	try {
			while((check=d.readLine())!=null)
			{
				
				Pattern pat=Pattern.compile(UserID.substring(3, 7));
				Matcher mat=pat.matcher(check);
				Pattern pat1=Pattern.compile(UserPass);
				Matcher mat1=pat1.matcher(check);
				if(mat.find()&&mat1.find())
				{
					l=mat.start();	
					k=mat1.end();

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(l-k==8)
    	{
    		return "Valid";
    	}
    	else
    	{
    		return "Invalid";
    	}
    }
   }
  
