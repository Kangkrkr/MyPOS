package com.kang.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.kang.domain.Money;
import com.kang.domain.Register;

public class RequestPayment extends JFrame{
	private JTextField receivedPriceTextField;
	private JLabel toReceivePriceLabel;
	private JLabel toReceivePrice;
	private JLabel receivedPriceLabel;
	private JLabel dueLabel;
	private JLabel due;
	private JButton completeBtn;
	
	private int currentReceivedPrice = 0;
	private int currentToReceivePrice = 0;

	public RequestPayment(final Register register) {
		setTitle("지불 요청");
		getContentPane().setLayout(null);
		setSize(280, 180);
		
		if(register == null){
			System.out.println("NULL!!!");
		}
		
		toReceivePriceLabel = new JLabel("받을 금액");
		toReceivePriceLabel.setBounds(12, 10, 57, 15);
		getContentPane().add(toReceivePriceLabel);
		
		currentToReceivePrice = register.getCurrentSale().getTotal().getTotal();
		toReceivePrice = new JLabel(currentToReceivePrice+"");
		toReceivePrice.setBounds(81, 10, 57, 15);
		getContentPane().add(toReceivePrice);
		
		receivedPriceLabel = new JLabel("받은 금액");
		receivedPriceLabel.setBounds(12, 52, 57, 15);
		getContentPane().add(receivedPriceLabel);
		
		
		// 금액을 모두 입력후 스페이스바를 한번더 누르셔야 합니다.
		receivedPriceTextField = new JTextField();
		receivedPriceTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(!receivedPriceTextField.getText().equals("")){
					currentReceivedPrice = Integer.parseInt(receivedPriceTextField.getText());
					due.setText((currentReceivedPrice - currentToReceivePrice)+"");
				}
			}
		});
		receivedPriceTextField.setBounds(81, 49, 116, 21);
		getContentPane().add(receivedPriceTextField);
		receivedPriceTextField.setColumns(10);
		
		dueLabel = new JLabel("거스름돈");
		dueLabel.setBounds(12, 97, 57, 15);
		getContentPane().add(dueLabel);
		
		due = new JLabel("0");
		due.setBounds(81, 97, 57, 15);
		getContentPane().add(due);
		
		completeBtn = new JButton("지불 완료");
		completeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				register.getCurrentSale().makePayment(new Money(currentReceivedPrice));
				new Receipt(register);
				
			}
		});
		completeBtn.setBounds(150, 93, 97, 23);
		getContentPane().add(completeBtn);
		
		this.setVisible(true);
	}
	
}
