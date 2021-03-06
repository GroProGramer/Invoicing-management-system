package com.njue.mis.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import com.njue.mis.common.CommonFactory;
import com.njue.mis.handler.GoodsServicesHandler;
import com.njue.mis.handler.PortInServicesHandle;
import com.njue.mis.model.Goods;
import com.njue.mis.model.PortIn;


public class InportFrame extends JInternalFrame
{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	JTextField ID_importtextField;
	JTextField ID_privoderField;
	JTextField numberField;
	JComboBox paytypeComboBox;
	JTextField importtimeField;
	JTextField operaterField;
	JTextField goodsField;
	JTextField explainField;
	
	private double goodsPrices=0;  //记录商品的单价
	
	public InportFrame()
	{
		super("进货",true,true,true,true);
		
		this.setBounds(0, 0, screenSize.width * 2 / 3,
				screenSize.height * 2 / 3);
		this.getContentPane().add(importgoods());
	}

	public JPanel importgoods()
	{
		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g)
			{
				setDoubleBuffered(true);
				g.drawImage(new ImageIcon(LoginFrame.class.getResource("images/login2.jpg"))
						.getImage(), 0, 0, null);
			}
		};
		
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		JLabel ID_importlable = new JLabel("进货票号:");
		ID_importlable.setForeground(Color.WHITE);
		ID_importtextField = new JTextField(10);
		JLabel ID_privoderLabel = new JLabel("供应商编号:");
		ID_privoderLabel.setForeground(Color.WHITE);
		ID_privoderField = new JTextField(10); 
		JLabel numberLabel = new JLabel("数量:");
		numberLabel.setForeground(Color.WHITE);
		numberField = new JTextField(10);
		panel1.add(ID_importlable);
		panel1.add(ID_importtextField);
		panel1.add(ID_privoderLabel);
		panel1.add(ID_privoderField);
		panel1.add(numberLabel);
		panel1.add(numberField);
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		JLabel paytypeLabel = new JLabel("支付类型:");
		paytypeLabel.setForeground(Color.WHITE);
		paytypeComboBox = new JComboBox();
		paytypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] 
		{ "请选择支付类型", "现金", "银行卡", "支票" }));
		JLabel importtimeLabel = new JLabel("进货时间:");
		importtimeLabel.setForeground(Color.WHITE);
		importtimeField = new JTextField(10);	
		JLabel opreaterLabel = new JLabel("操作员:");
		opreaterLabel.setForeground(Color.WHITE);
		operaterField = new JTextField(10);
		panel2.add(paytypeLabel);
		panel2.add(paytypeComboBox);
		panel2.add(importtimeLabel);
		panel2.add(importtimeField);
		panel2.add(opreaterLabel);
		panel2.add(operaterField);
		
		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		JScrollPane goodScrollPane = new JScrollPane();
		
		final JTable goodsTable = new JTable(new MyTableModel());
		//表格行改变时发生的时间
		goodsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				ListSelectionModel model = (ListSelectionModel)e.getSource();
				int index = model.getMaxSelectionIndex();
				goodsField.setText(goodsTable.getValueAt(index, 0).toString());
				ID_privoderField.setText(goodsTable.getValueAt(index, 9).toString());
				goodsPrices=Double.valueOf(goodsTable.getValueAt(index, 8).toString());
			}
		});
		
		
		goodsTable.setPreferredScrollableViewportSize(new Dimension(screenSize.width * 2 / 3-60,
				screenSize.height  / 3));
		goodScrollPane.setViewportView(goodsTable);
		panel3.add(goodScrollPane);
		
		JPanel panel4 = new JPanel();
		panel4.setOpaque(false);
		JLabel goodsLabel = new JLabel("商品编号:");
		goodsLabel.setForeground(Color.WHITE);
		goodsField = new JTextField(10);
		JLabel explainLabel = new JLabel("商品注释:");
		explainLabel.setForeground(Color.WHITE);
		explainField = new JTextField(20);
		
		JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				Date date=new Date();
				SimpleDateFormat formate=new SimpleDateFormat("yyyyMMddHHmmss");
				ID_importtextField.setText("PI"+formate.format(date));
				formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				importtimeField.setText(formate.format(date));
				operaterField.setText(MainFrame.username);
				numberField.setText("");
				setEnableTrue();
			}
			
		});
		JButton inButton = new JButton("入库");
		inButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				String inportID=ID_importtextField.getText();
				String providerID=ID_privoderField.getText();
				String numberStr=numberField.getText();
				String payType=paytypeComboBox.getSelectedItem().toString();
				String inportTime=importtimeField.getText();
				String operator=operaterField.getText();
				String goodsID=goodsField.getText();
				String comment=explainField.getText();
				double price=0;
				if(numberStr==null||numberStr.trim().length()==0)
				{
					JOptionPane.showMessageDialog(null,"请输入商品数量","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				int number=0;
				try
				{
					number=Integer.valueOf(numberStr);
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null,"商品的数量不合法","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(paytypeComboBox.getSelectedIndex()==0)
				{
					JOptionPane.showMessageDialog(null,"请选择支付类型","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(providerID==null||providerID.length()==0)
				{
					JOptionPane.showMessageDialog(null,"请选择商品","警告",JOptionPane.WARNING_MESSAGE);
					return;
				}
				price=goodsPrices*number;  //计算出总价格
				PortIn portIn=new PortIn(inportID,providerID,goodsID,payType,number,
						                  price,inportTime,operator,comment);
				
				PortInServicesHandle handle=CommonFactory.getPortInServices();
				if (handle.addPortIn(portIn))
				{
					JOptionPane.showMessageDialog(null,"入货单添加成功","警告",JOptionPane.WARNING_MESSAGE);
					numberField.setText("");
					setEnableFalse();
				}
				else
				{
					JOptionPane.showMessageDialog(null,"入货单添加失败，请按要求输入数据","警告",JOptionPane.WARNING_MESSAGE);	
				}
				
			}
			
		});
		
		setEnableFalse();  
		panel4.add(goodsLabel);
		panel4.add(goodsField);
		panel4.add(explainLabel);
		panel4.add(explainField);
		panel4.add(addButton);
		panel4.add(inButton);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		return panel;
	}
	//设置部分控件为不可用状态
	private void setEnableFalse()
	{
		ID_importtextField.setEnabled(false);
		ID_privoderField.setEnabled(false);
		numberField.setEnabled(false);
		paytypeComboBox.setEnabled(false);
		importtimeField.setEnabled(false);
		operaterField.setEnabled(false);
		explainField.setEnabled(false);
		goodsField.setEnabled(false);
	}
	//设置部分控件为可用状态
	private void setEnableTrue()
	{
		numberField.setEnabled(true);
		paytypeComboBox.setEnabled(true);
		explainField.setEnabled(true);
	}
	
	
	class MyTableModel extends AbstractTableModel
	{
		GoodsServicesHandler handler=CommonFactory.getGoodsServices();
		Vector<Goods> goodsVector=handler.getAllGoods();
		
		private String[] columnNames =
		{
				"商品编号", "商品名称", "产地", "规格","包装","生产批号",
                "批准文号","描述","价格","供应商编号"
		};
		
		public int getColumnCount()
		{
			return columnNames.length;
		}

		public int getRowCount()
		{
			return goodsVector.size();
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public Object getValueAt(int row, int col)
		{
			Goods goods=goodsVector.get(row);
			return goods.getGoodsValue(col);
		}

		@SuppressWarnings("unchecked")
		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col)
		{
			return false;
		}		
	}
	
}
