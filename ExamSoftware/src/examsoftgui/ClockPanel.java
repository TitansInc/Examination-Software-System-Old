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

import javax.swing.*;

public class ClockPanel extends JPanel{
	private ClockCanvas clockc=null;
	ClockPanel(int hours, int minutes, String ID, String[] answers,String SubjectName, JFrame Window,String Ip,Copycat copy){
			setSize(100,50);
			setLayout(new BorderLayout());
			add("Center",clockc=new ClockCanvas(hours,minutes,ID,answers,SubjectName,Window,Ip,copy));	
			clockc.setSize(100,50);
			
		}
	

}
