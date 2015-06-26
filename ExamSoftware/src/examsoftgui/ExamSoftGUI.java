/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examsoftgui;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
/**
 *
 * @author Guru
 */
public class ExamSoftGUI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      /*  try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }*/
       JFrame frame=new JFrame();
       PreLogin lgwin=new PreLogin(frame);
       frame.setUndecorated(true);
       frame.setBackground(new Color(0, 0, 0, 0));
       frame.setContentPane(new ShadowPane());
       frame.add(lgwin);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
      
       
       //frame.setShape(new RoundRectangle2D.Double(05,05, 350, 180, 40, 40));
       
                      
    }

    
}


class ShadowPane extends JPanel {

        public ShadowPane() {
            setLayout(new BorderLayout());
            setOpaque(false);
            setBackground(Color.BLACK);
            setBorder(new EmptyBorder(0, 0, 25, 25));
            
            
        }

        
        public Dimension getPreferredSize() {
            return new Dimension(310, 190);
        }

       
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
            
            g2d.fillRect(10, 10, getWidth(), getHeight());
            g2d.dispose();
        }
    }