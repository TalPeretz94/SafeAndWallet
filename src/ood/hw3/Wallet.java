package ood.hw3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class Wallet implements Cloneable,Iterable<Bill> {
	private ArrayList<Bill> theWallet;

	public Wallet() {
		theWallet = new ArrayList<>();
	}

	public void addBill(Bill theBill) {
		theWallet.add(theBill);
	}

	public Bill removeBill(Currency currency, double amount) {
		Bill returnWallet=new Bill();
		 try {
			 for (Bill bill : theWallet) {
					if(bill.getCurrency()==currency && bill.getAmount() == amount) {
						int index = theWallet.indexOf(bill);
						returnWallet= theWallet.remove(index);
						return returnWallet;
						
					 
					
					}
				}
			 throw new IllegalArgumentException();
			 
		 }catch (IllegalArgumentException e) {
			System.out.println("non such bill");
		}
		return returnWallet;
		 
		 	 
		 
		 
	 }

	protected Wallet clone() throws CloneNotSupportedException {
		Wallet copy = (Wallet) super.clone();
		ArrayList<Bill> copyOfBills = new ArrayList<>(theWallet);
		copy.theWallet = copyOfBills;
		return copy;
	}

	@Override
	public Iterator<Bill> iterator() {
		return theWallet.iterator();
	}

	

}
