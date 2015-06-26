/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serversidecomponent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class Evaluator extends Thread implements Runnable{
    
    Map<String, String> studMarks = new HashMap<String, String>();
    String Answers,MarksPattern,OutputLocation,InputLocation,Subject;
    int AdditionalMarks;
    Evaluator(String Subject,String Answers,String MarksPattern, String OutputLocation, String InputLocation,int additional){
        this.Answers=Answers;
        this.InputLocation=InputLocation;
        this.MarksPattern=MarksPattern;
        this.OutputLocation=OutputLocation;
        this.Subject=Subject;
        this.AdditionalMarks=additional;
        
        
    }
    public void run(){
        
        File folder = new File(InputLocation);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
        if (file.isFile()&&(file.getName().contains(Subject))&&(file.getName().contains("_"))) {
            DataInputStream d = null;
            try {
                
		d = new DataInputStream(new FileInputStream(InputLocation+"/"+file.getName()));
                 String ID=new String (file.getName().substring(0, file.getName().indexOf("_")));
                 
                 int count=1;
                 int Marks=0,j=0;
                 String  studans;
                  try {
                     studans=d.readLine();
                    
                     for(int i=0;i<studans.length();i++){
                             if(count%20==0){
                                 
                                  if(Answers.charAt(j)==studans.charAt(i)){
                                      
                                      
                                      Marks=Marks+Integer.parseInt(String.valueOf(MarksPattern.charAt(j)));
                                      
                                 }
                                 j++;
                                     
                             }  count++;
                         
                     }
                     studMarks.put(ID, String.valueOf(Marks+AdditionalMarks));
                         
                  } catch (IOException ex) {
                      Logger.getLogger(Evaluator.class.getName()).log(Level.SEVERE, null, ex);
                  }
               
            } catch (FileNotFoundException e1) {e1.printStackTrace();}
            try {
                d.close();
            } catch (IOException ex) {
                Logger.getLogger(Evaluator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
      }
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(OutputLocation+"/MarksList"));
            String newLine = System.getProperty("line.separator");
            for (Map.Entry<String, String> entry : studMarks.entrySet()) {
                try {
                    out.writeBytes(entry.getKey()+" "+entry.getValue()+newLine);
                } catch (IOException ex) {
                    Logger.getLogger(Evaluator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Evaluator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Evaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
    
   
}
    
    

