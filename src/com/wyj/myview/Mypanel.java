package com.wyj.myview;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import com.njue.mis.view.LoginFrame;

public class Mypanel extends JDesktopPane{
     @Override
	protected void paintComponent(Graphics g) {
		// TODO �Զ����ɵķ������
    	 setDoubleBuffered(true);
			g.drawImage(new ImageIcon(getClass().getResource("images/background.jpg"))
					.getImage(), 0, 0, null);
	}

	public Mypanel(){
    	super();	
     }
}
