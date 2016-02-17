package com.kang.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import com.kang.domain.ItemID;
import com.kang.domain.Money;
import com.kang.domain.ProductCatalog;


// 물품을 등록하는 화면.
public class EnterItem extends JFrame{
	private JTextField itemIDField;
	private JList itemLines;
	
	private DefaultListModel<String> defaultListModel;
	
	private JTextField itemID;
	private JTextField itemPrice;
	private JTextField itemDescription;
	
	private ProductCatalog productCatalog;
	
	public EnterItem(ProductCatalog _productCatalog) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("물품 등록");
		setSize(300, 480);
		getContentPane().setLayout(null);
		
		this.defaultListModel = new DefaultListModel<String>();
		this.productCatalog = _productCatalog;
		
		JScrollPane itemLinesScrollPane = new JScrollPane();
		itemLinesScrollPane.setBounds(0, 122, 290, 320);
		getContentPane().add(itemLinesScrollPane);
		
		itemLines = new JList(defaultListModel);
		itemLinesScrollPane.setViewportView(itemLines);
		itemLines.setBorder(new TitledBorder(null, "물품 등록", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		itemLines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		itemLines.setVisibleRowCount(-1);
		
		itemID = new JTextField();
		itemID.setBounds(0, 35, 75, 21);
		getContentPane().add(itemID);
		itemID.setColumns(10);
		
		itemPrice = new JTextField();
		itemPrice.setBounds(87, 35, 81, 21);
		getContentPane().add(itemPrice);
		itemPrice.setColumns(10);
		
		for(Integer i : productCatalog.getDescriptions().keySet()){
			defaultListModel.addElement(productCatalog.getProductDescription(i).toString());
		}
		
		// 버튼 클릭시 물품 등록.
		final JButton enterItemBtn = new JButton("등록");
		enterItemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(itemID.getText());
				int price = Integer.parseInt(itemPrice.getText());
				String desc = itemDescription.getText();
				
				ItemID itemID = new ItemID(id);
				Money money = new Money(price);
				
				productCatalog.putItem(itemID.getId(), money, desc);
				defaultListModel.addElement(productCatalog.getProductDescription(itemID.getId()).toString());
				
			}
		});
		enterItemBtn.setBounds(180, 34, 102, 71);
		getContentPane().add(enterItemBtn);
		
		itemDescription = new JTextField();
		itemDescription.setBounds(0, 84, 168, 21);
		getContentPane().add(itemDescription);
		itemDescription.setColumns(10);
		
		JLabel itemIDLabel = new JLabel("물품 ID");
		itemIDLabel.setBounds(18, 10, 57, 15);
		getContentPane().add(itemIDLabel);
		
		JLabel itemPriceLabel = new JLabel("물품 가격");
		itemPriceLabel.setBounds(97, 10, 57, 15);
		getContentPane().add(itemPriceLabel);
		
		JLabel label = new JLabel("물품명");
		label.setBounds(57, 66, 57, 15);
		getContentPane().add(label);
		
		this.setVisible(true);
	}
}
