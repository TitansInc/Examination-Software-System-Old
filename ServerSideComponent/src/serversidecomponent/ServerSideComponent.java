/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serversidecomponent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author student
 */
public class ServerSideComponent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /*      try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }*/
        ServerComponent server=new ServerComponent();
        server.setVisible(true);
        server.pack();
        server.setResizable(false);
        
        
    }
    
}
