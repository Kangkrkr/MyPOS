package com.kang.ui;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import com.kang.domain.ItemLine;
import com.kang.domain.Register;
import com.kang.domain.SalesLineItem;

import javax.swing.JScrollBar;

import java.awt.Font;


public class Receipt extends JFrame{
	private JTextField itemIDField;
	private JList itemLines;
	
	private DefaultListModel<SalesLineItem> defaultListModel;

	
	public Receipt(Register register) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("영수증");
		setSize(250, 480);
		getContentPane().setLayout(null);
		
		defaultListModel = new DefaultListModel<>();
		
		int received = register.getCurrentSale().getPayment().getAmount().getPrice();
		int total = register.getCurrentSale().getTotal().getTotal();
		int due = received-total;
		
		JLabel lblLineItemsAre = new JLabel("영수증");
		lblLineItemsAre.setFont(new Font("굴림", Font.BOLD, 18));
		lblLineItemsAre.setBounds(12, 10, 119, 27);
		getContentPane().add(lblLineItemsAre);
		
		JScrollPane itemLinesScrollPane = new JScrollPane();
		itemLinesScrollPane.setBounds(0, 50, 250, 277);
		getContentPane().add(itemLinesScrollPane);
		
		JList itemLines_1 = new JList(defaultListModel);
		itemLinesScrollPane.setViewportView(itemLines_1);
		itemLines_1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		itemLines_1.setVisibleRowCount(-1);
		
		for(SalesLineItem sli : register.getCurrentSale().getSalesLineItem()){
			defaultListModel.addElement(sli);
		}
		
		JLabel totalPrice = new JLabel("총액");
		totalPrice.setFont(new Font("굴림", Font.PLAIN, 13));
		totalPrice.setBounds(12, 345, 57, 27);
		getContentPane().add(totalPrice);
		
		JLabel receivedLabel = new JLabel("받은 돈");
		receivedLabel.setBounds(12, 382, 57, 15);
		getContentPane().add(receivedLabel);
		
		JLabel dueLabel = new JLabel("거스름돈");
		dueLabel.setFont(new Font("굴림", Font.BOLD, 15));
		dueLabel.setBounds(12, 412, 80, 30);
		getContentPane().add(dueLabel);
		
		JLabel totalMoney = new JLabel(total+"");
		totalMoney.setBounds(140, 351, 57, 15);
		getContentPane().add(totalMoney);
		
		JLabel receivedMoney = new JLabel(received+"");
		receivedMoney.setBounds(140, 382, 57, 15);
		getContentPane().add(receivedMoney);
		
		JLabel dueMoney = new JLabel(due+"");
		dueMoney.setFont(new Font("굴림", Font.BOLD, 15));
		dueMoney.setBounds(124, 412, 108, 30);
		getContentPane().add(dueMoney);
		
		this.setVisible(true);
	}
}
