package com.kang.main;

import com.kang.domain.Register;
import com.kang.domain.Store;
import com.kang.ui.ProcessSaleJFrame;

public class Main {

	public static void main(String[] args) {
		
		// Store 객체 생성 후, 판매기(Register)를 가져온다.
		Store store = new Store();
		Register register = store.getRegister();
		
		// 판매기에 해당하는 작업을 하는 프레임을 사용자(Cashier)에게 보여준다.
		ProcessSaleJFrame frame = new ProcessSaleJFrame(register);
		
	}

}
