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
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.io.*;
public class FileToArray
{
	int arr2[];
	
	

	public FileToArray(int[] quetype){
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
		d = new DataInputStream(new FileInputStream(path+".gnome2/eog/catch/qtype.txt"));
                
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
           
		int c=0,f=0,z,k;
		int arr[]=new int[quetype.length];
		this.arr2=quetype;
		try {
			while((k=d.read())!=-1)
			{
				arr[f]=((int)k);
				f++;
			}
		} catch (IOException e) {}
		try {
		for(f=0;f<quetype.length;f++){
			arr2[f]=Integer.parseInt(String.valueOf((char)(arr[f])));			
		}
		
			d.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}
}