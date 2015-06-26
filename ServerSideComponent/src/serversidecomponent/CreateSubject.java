/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serversidecomponent;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class CreateSubject {
    CreateSubject(String Subject,String SubjectPass, String Noofque,String hours,String minutes,String locationofQues, String qtype) throws IOException{
      String newLine = System.getProperty("line.separator");
      DataOutputStream out = new DataOutputStream(new FileOutputStream(locationofQues+"/ExamDetails.txt"));
      DataOutputStream out2 = new DataOutputStream(new FileOutputStream(locationofQues+"/qtype.txt"));
      
      out.writeBytes(Subject+newLine);
      out.writeBytes(SubjectPass+newLine);
      out.writeBytes(Noofque+newLine);
      out.writeBytes(hours+newLine);
      out.writeBytes(minutes+newLine);
      out.flush();
      out.close();
      out2.writeBytes(qtype);
      out2.flush();
      out2.close();
      
    }
       
    
    }

