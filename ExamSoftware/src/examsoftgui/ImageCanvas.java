/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examsoftgui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author zubaeyr
 */
public class ImageCanvas extends Canvas{
	
	private Image image=null;
	public ImageCanvas(){}
	public void showImage(Image image){
		this.image=image;
		repaint();
	}
	public void paint(Graphics g){
		if(image!=null){	
			g.drawImage(image,0,40,this);
		}
	}
	
}