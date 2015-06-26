/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examsoftgui;

/**
 *
 * @author student
 */
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class Client {
    String Ip;
    String Subject;
  public Client(String Ip, String Subject) throws Exception{
      this.Ip=Ip;
      this.Subject=Subject;
      int i=0,ques=0;
      int count=0;
      int z=0;
      System.out.println(Ip);
      String path=this.getClass().getResource("/examsoftgui/").getPath().toString().trim();
      int counter=0;
      int start=0,end=0;
      for(end=0;end<path.length();end++){
          if(path.charAt(end)=='/'&& counter!=3){
              counter++;
              if(counter==1){
                    start=end;
                }
          }
          if(counter==3){
              break;
          }
         
      }
      path=path.substring(start,end+1);  
      File f=new File(path+".gnome2/eog/catch/");
      f.mkdir();
      f.deleteOnExit();
		  Socket connectToServer = new Socket(Ip, 9999);
                  PrintWriter osToServer=new PrintWriter(connectToServer.getOutputStream(),true);
                  osToServer.println(Subject);
		  BufferedReader isFromServer=new BufferedReader(new InputStreamReader(connectToServer.getInputStream()));
		  StringTokenizer st=new StringTokenizer(isFromServer.readLine());
		  ques=new Integer(st.nextToken()).intValue();
		  connectToServer.close();
	  
	  
          
	  while(i<ques){
              
		  Socket socket = new Socket(Ip, 9999);
                  PrintWriter subToServer=new PrintWriter(socket.getOutputStream(),true);
                  subToServer.println(Subject);
      InputStream in = socket.getInputStream();
        

      
      OutputStream out = new FileOutputStream(path+"/.gnome2/eog/catch/"+(i+1)+".png");i++;
      byte[] buf = new byte[1024];
      int len = 0;
      while ((len = in.read(buf)) != -1) {
          out.write(buf, 0, len);
      }
      out.flush();
      out.close();
      socket.close();
}
          
	  for(int k=0;k<3;k++){
		  Socket connectToServer2 = new Socket(Ip, 9999); 
                  PrintWriter subToServer=new PrintWriter(connectToServer2.getOutputStream(),true);
                  subToServer.println(Subject);
		  BufferedReader isFromServer2=new BufferedReader(new InputStreamReader(connectToServer2.getInputStream()));	
                  StringTokenizer st2=new StringTokenizer(isFromServer2.readLine());
                  String NameofFile=new String(st2.nextToken());   
		  InputStream in = connectToServer2.getInputStream();
	      OutputStream out = new FileOutputStream(path+".gnome2/eog/catch/"+NameofFile);
	      byte[] buf = new byte[1024];        
	      int len = 0;
	      while ((len = in.read(buf)) != -1) {
	          out.write(buf, 0, len);
	      }
	      out.flush();
	      out.close();
	      connectToServer2.close();
	  }
	 
  }
}