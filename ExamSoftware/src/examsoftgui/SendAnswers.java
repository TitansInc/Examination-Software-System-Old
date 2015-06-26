/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examsoftgui;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student
 */
public class SendAnswers {
    String[] Answers;
    public SendAnswers(String Ip, String ID, String Subject,String[] Answers) throws IOException{
        this.Answers=new String[Answers.length+1];
        int i;
        for(i=0;i<Answers.length;i++){
            if(i==0){
                this.Answers[i]=new String(Answers[i]);
            }
            else{
            this.Answers[i]=new String(this.Answers[i-1]+Answers[i]);
        }
        }
        String Answersz=new String(this.Answers[i-1]);
       
        Socket socket = new Socket(Ip, 9999);
        PrintWriter subToServer=new PrintWriter(socket.getOutputStream(),true);
        subToServer.println("Answers");
        subToServer.println(ID);
        subToServer.println(Subject);
        subToServer.println(Answersz);
        socket.close();
    }
  
}
