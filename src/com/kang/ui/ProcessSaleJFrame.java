package com.kang.ui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import com.kang.domain.ItemID;
import com.kang.domain.ProductCatalog;
import com.kang.domain.ProductDescription;
import com.kang.domain.Register;
import com.kang.domain.SalesLineItem;


public class ProcessSaleJFrame extends JFrame{
	private JTextField itemIDField;
	private JTextField quantityField;
	private JButton endSale;
	private JButton addItem;
	private JButton registNewProduct;
	private JButton requestPaymentBtn;
	private JList itemLines;
	private JLabel totalPriceField;
	private JLabel itemID;
	private JLabel quantity;
	private JLabel lineItem;
	private JLabel totalPriceLabel;
	private JLabel title;
	private JScrollPane itemLinesScrollPane;
	
	
	private DefaultListModel<SalesLineItem> defaultListModel;
	
	private ProductCatalog productCatalog;
	private Register register;
	
	
	public ProcessSaleJFrame(Register _register) {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Process Sale");
		setSize(620, 480);
		getContentPane().setLayout(null);
		JOptionPane.showMessageDialog(null, "판매가 시작되었습니다.");
		
		defaultListModel = new DefaultListModel<>();

		productCatalog = new ProductCatalog();
		register = new Register(productCatalog);
		
		// Process Sale 중 첫번째 단계인 makeNewSale()
		register.makeNewSale();
		
		
		// 현재 Sale의 SalesLineItem에 물품정보를 표시하는 버튼
		addItem = new JButton("물품 추가");
		addItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					ItemID itemID = new ItemID(Integer.parseInt(itemIDField.getText().toString()));
					int itemQuantity = Integer.parseInt(quantityField.getText().toString());
					
					// Process Sale의 두번째 단계인 enterItem().
					register.enterItem(itemID, itemQuantity);
					
					ProductDescription description = productCatalog.getProductDescription(itemID.getId());
					SalesLineItem sli = new SalesLineItem(description, itemQuantity);
					
					// Cashier에게 물품의 description과 total을 보여준다.
					defaultListModel.addElement(sli);
				} catch(Exception error){
					JOptionPane.showMessageDialog(null, "오류가 발생하였습니다.");
					error.printStackTrace();
				}
			}
		});
		addItem.setBounds(477, 79, 107, 23);
		getContentPane().add(addItem);
		
		
		// 물품의 정보를 등록하는 버튼
		registNewProduct = new JButton("물품 등록");
		registNewProduct.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new EnterItem(productCatalog);
			}
		});
		registNewProduct.setBounds(477, 123, 107, 23);
		getContentPane().add(registNewProduct);
		
		
		// 판매종료 및 영수증 출력 버튼.
		endSale = new JButton("판매종료");
		endSale.setBounds(477, 171, 107, 23);
		endSale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// sale의 속성 isComplete를 바꾸고 현재의 판매를 종료한다.
				register.endSale();
				
				// 세금을 포함한 총액을 보여준다.
				totalPriceField.setText(register.getCurrentSale().getTotal().getTotal()+"");
				
				if(register.getCurrentSale().isComplete()){
					requestPaymentBtn.setEnabled(true);
				}
				
			}
		});
		getContentPane().add(endSale);
		
		
		// 지불 요청 버튼.
		requestPaymentBtn = new JButton("지불 요청");
		requestPaymentBtn.setEnabled(false);
		requestPaymentBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new RequestPayment(register);
				
			}
		});
		requestPaymentBtn.setBounds(477, 219, 107, 23);
		getContentPane().add(requestPaymentBtn);
		
		itemID = new JLabel("Item ID");
		itemID.setBounds(50, 83, 57, 15);
		getContentPane().add(itemID);
		
		itemIDField = new JTextField();
		itemIDField.setColumns(10);
		itemIDField.setBounds(145, 80, 320, 21);
		getContentPane().add(itemIDField);
		
		quantity = new JLabel("Quantity");
		quantity.setBounds(50, 127, 57, 15);
		getContentPane().add(quantity);
		
		quantityField = new JTextField();
		quantityField.setColumns(10);
		quantityField.setBounds(145, 124, 320, 21);
		getContentPane().add(quantityField);
		
		lineItem = new JLabel("Line Item");
		lineItem.setBounds(50, 175, 57, 15);
		getContentPane().add(lineItem);
		
		itemLinesScrollPane = new JScrollPane();
		itemLinesScrollPane.setBounds(145, 174, 320, 205);
		getContentPane().add(itemLinesScrollPane);
		
		itemLines = new JList(defaultListModel);
		itemLinesScrollPane.setViewportView(itemLines);
		itemLines.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		itemLines.setVisibleRowCount(-1);
		
		totalPriceLabel = new JLabel("총 금액");
		totalPriceLabel.setBounds(50, 409, 57, 15);
		getContentPane().add(totalPriceLabel);
		
		totalPriceField = new JLabel("");
		totalPriceField.setBounds(145, 403, 320, 28);
		getContentPane().add(totalPriceField);
		
		title = new JLabel("Kang POS");
		title.setFont(new Font("굴림", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(176, 21, 254, 28);
		getContentPane().add(title);
		
		
		this.setVisible(true);
	}
}
