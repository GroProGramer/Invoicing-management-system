package com.njue.mis.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class MainFrame extends JFrame
{
	public static String power;
	public static String username;
	
	private JDesktopPane desktopPane;
	private static MainFrame mainFrame;
	/**
	 * ����������
	 */
	private MainFrame()
	{
		super("���������ϵͳ");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(screenSize.width/6,screenSize.height/6, screenSize.width*2/3,
				screenSize.height*2/3);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		desktopPane=new JDesktopPane(){
			public void paintComponent(Graphics g)
			{
				setDoubleBuffered(true);
				g.drawImage(new ImageIcon(getClass().getResource("images/bc.jpg"))
						.getImage(), 0, 0, null);
			}
		};
		
		desktopPane.setOpaque(true);

		this.setContentPane(desktopPane);
		this.setJMenuBar(createMenuBar());
		
		//Set up the backgound image
		desktopPane.setBackground(new Color(200,218,235));
		
		// Make dragging a little faster but perhaps uglier.
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	}
	
	public static MainFrame getMainFrame()
	{
		if(mainFrame==null)
		{
			mainFrame=new MainFrame();
		}
		return mainFrame;
	}

	/**
	 * ����������Ĳ˵���
	 * 
	 * @return JMenuBar
	 */
	protected JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();

		// Set up the info menu.
		JMenu menu = new JMenu("������Ϣ����");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("�ͻ���Ϣ����");
		menuItem.addActionListener(MainAction.clickCustomerInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("��Ʒ��Ϣ����");
		menuItem.addActionListener(MainAction.clickGoodsInfoManager());
		menu.add(menuItem);
		menuItem = new JMenuItem("��Ӧ����Ϣ����");
		menuItem.addActionListener(MainAction.clickProviderInfoManager());
		menu.add(menuItem);

		// Set up the inport menu.
		menu = new JMenu("��������");
		menuBar.add(menu);
		menuItem = new JMenuItem("����");
		menuItem.addActionListener(MainAction.importGoods());
		menu.add(menuItem);
		menuItem = new JMenuItem("�˻�");
		menuItem.addActionListener(MainAction.outportGoods());
		menu.add(menuItem);

		// Set up the sales menu.
		menu = new JMenu("���۹���");
		menuBar.add(menu);
		menuItem = new JMenuItem("����");
		menuItem.addActionListener(MainAction.sales());
		menu.add(menuItem);
		menuItem = new JMenuItem("����");
		menuItem.addActionListener(MainAction.salesBack());
		menu.add(menuItem);

		// Set up the select menu.
		menu = new JMenu("��ѯ����");
		menuBar.add(menu);
		menuItem = new JMenuItem("�ͻ���ѯ");
		menuItem.addActionListener(MainAction.clickCustomerInforserch());
		menu.add(menuItem);
		menuItem = new JMenuItem("��Ʒ��ѯ");
		menuItem.addActionListener(MainAction.clickGoodsInforserch());
		menu.add(menuItem);
		menuItem = new JMenuItem("��Ӧ�̲�ѯ");
		menuItem.addActionListener(MainAction.clickPrivoderInforSearch());
		menu.add(menuItem);
		menuItem = new JMenuItem("���۲�ѯ");
		menuItem.addActionListener(MainAction.clickSaleInforSearch());
		menu.add(menuItem);
		menuItem = new JMenuItem("���۲�ѯ");
		menuItem.addActionListener(MainAction.clickSaleBackInforSearch());
		menu.add(menuItem);
		menuItem = new JMenuItem("����ѯ");
		menuItem.addActionListener(MainAction.clickInputInforserch());
		menu.add(menuItem);
		menuItem = new JMenuItem("�˻���ѯ");
		menuItem.addActionListener(MainAction.clickOutputInforserch());
		menu.add(menuItem);

		// Set up the save menu.
		menu = new JMenu("������");
		menuBar.add(menu);
		menuItem = new JMenuItem("����̵�");
		menuItem.addActionListener(MainAction.storeHouseInfor());
		menu.add(menuItem);
		menuItem = new JMenuItem("�۸����");
		menuItem.addActionListener(MainAction.priceChange());
		menu.add(menuItem);

//		 Set up the system menu.
		menu = new JMenu("ϵͳ����");
		menuBar.add(menu);
		menuItem = new JMenuItem("��������");
		menuItem.addActionListener(MainAction.changePassword());
		menu.add(menuItem);
		menuItem = new JMenuItem("�˳�ϵͳ");
		menuItem.addActionListener(MainAction.exit());
		menu.add(menuItem);
		if (power.equals("����Ա"))
		{
			menuItem = new JMenuItem("����Ա����");
			menuItem.addActionListener(MainAction.operaterManager());
			menu.add(menuItem);
		}
		else if(power.equals("����Ա"))
		{
		}
		else
		{
			JOptionPane.showMessageDialog(null, "�Ƿ��û�������","����",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		return menuBar;
	}
}