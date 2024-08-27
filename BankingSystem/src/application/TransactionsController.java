package application;

import networking.ClientServer;

public class TransactionsController {
	
	static public void sendMoney(String Payee, String AccNumber, int Amount,String Remarks) {
    	ClientServer.sendMoney(Payee,AccNumber,Amount,Remarks);
	} // sendMoney().
	
	static public void requestMoney(String Payer, int Amount, String Remarks) {
    	ClientServer.requestMoney(Payer,Amount,Remarks);
	} // sendMoney().
	
	static public void checkBalance() {
		
	} // checkBalance().
	 
} // TransactionsController.