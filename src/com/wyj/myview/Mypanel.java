package com.wyj.myview;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import com.njue.mis.view.LoginFrame;

public class Mypanel extends JDesktopPane{
     @Override
	protected void paintComponent(Graphics g) {
		// TODO 自动生成的方法存根
    	 setDoubleBuffered(true);
			g.drawImage(new ImageIcon(getClass().getResource("images/background.jpg"))
					.getImage(), 0, 0, null);
	}

	public Mypanel(){
    	super();	
     }
}
