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



/**
 *
 * @author Guru
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


public class ClockCanvas extends Canvas implements Runnable{
	int x,y,z=0;
	private Thread timer=null;
	String ID,SubjectName;
	String answers[];
	JFrame Win;
        String Ip;
        Copycat copy;
	public ClockCanvas(int x,int y, String ID,String[] answers,String SubjectName,JFrame Win,String Ip,Copycat copy){
                this.x=x;
                this.y=y;
                this.ID=ID;
                this.Ip=Ip;
                this.SubjectName=SubjectName;
                this.answers=answers;
                this.Win=Win;
		timer=new Thread(this);
		timer.start();
                this.copy=copy;
	}
	public void run() {
		// TODO Auto-generated method stub
		 while(x!=0||y!=0||z!=0){
		     	try{
		     		timer.sleep(1000);
		     	}catch(InterruptedException e){}
		     	
		     	if(z==0 &&(x!=0||y!=0)){
		     		z=59;
		     		if(y==0 && x!=0){
		     			y=59;
		     			x=x-1;        			
		     		}
		     		else{
		     			y=y-1;
		     		}
		       	}
		 		else{
		 			z=z-1;
		 		}
		     	
		     	repaint();
		      
		      
			
		}
		 if(x==0&&y==0&&z==0){
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
      String[] files=f.list();
      for(int h=0;h<files.length;h++){
       
          File file=new File(path+".gnome2/eog/catch/"+files[h]);
          file.delete();
      }
      if(copy.isShowing()){
          System.exit(0);
      }                  
                try {
                         SendAnswers sender=new SendAnswers(Ip,ID,SubjectName,answers);
                     } catch (IOException ex) {
                         Logger.getLogger(ClockCanvas.class.getName()).log(Level.SEVERE, null, ex);
                     }
			 new AnswerFileGenerator(answers,ID,SubjectName); 
			 this.Win.setVisible(false);
			 System.exit(0);
		 }
	}
	public void paint(Graphics g){
		
	    g.setFont(new Font("TimesRoman",Font.BOLD,16));
	    if(z<10 && y>9){
			g.drawString("0"+x+":"+y+":0"+z,20,20);
		}
		else if(z>9 && y<10 ){
			g.drawString("0"+x+":0"+y+":"+z,20,20);
		}
		else if(z<10 && y<10){
			g.drawString("0"+x+":0"+y+":0"+z,20,20);
		}
		else if(z>9 && y>9){
		g.drawString("0"+x+":"+y+":"+z,20,20);
		}
	}
	

}

