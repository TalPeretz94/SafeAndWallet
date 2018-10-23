package ood.hw3;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class HotelGuest implements HotelGuestMoneyManagement {

	private static HotelGuest single_instance = null;
	private Wallet myWallet;
	private Safe mySafe;
	private SafeAndWalletState tempStateAll;

	private Caretaker caretaker = new Caretaker();
	private Originator originator = new Originator();
	
	

	private HotelGuest() {
		this.myWallet = new Wallet();
		this.mySafe = new Safe();
		
		save();

	}

	public static HotelGuest getInstance() {
		if (single_instance == null)
			single_instance = new HotelGuest();

		return single_instance;
	}

	@Override
	public void addBillToWallet(Bill bill) {
		myWallet.addBill(bill);
		

	}

	@Override
	public void moveMoneyToSafe(String safeCode, Currency currency, double amount) {
		Bill bill = myWallet.removeBill(currency, amount);
		BillAdpater billAdapter = new BillAdpater(bill);
		mySafe.addToSafe(billAdapter, safeCode);

	}

	@Override
	public void setSafeCode(String oldCode, String newCode) {
		mySafe.setCode(oldCode, newCode);
		

	}

	@Override
	public Wallet getWalletStatus() {
		Wallet walletReturn = new Wallet();

		try {
			walletReturn = myWallet.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return walletReturn;
	}

	@Override
	public Safe getSafeStatus() {
		Safe safeReturn = new Safe();

		try {
			safeReturn = mySafe.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return safeReturn;

	}

	@Override
	public void save() {
		Safe memSafe = new Safe();
		Wallet memWallet = new Wallet();
		try {
			memSafe = mySafe.clone();
			memWallet = myWallet.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SafeAndWalletState mem = new SafeAndWalletState(memSafe, memWallet);
		originator.setState(mem);
		caretaker.addMemento(originator.save());

	}

	@Override
	public void undo() {
		this.tempStateAll = originator.restore(caretaker.getMemento());
		mySafe = tempStateAll.getTheSafe();
		myWallet = tempStateAll.getTheWallet();

	}
	
	

}

class SafeAndWalletState {
	private Safe theSafe;
	private Wallet theWallet;

	public SafeAndWalletState(Safe theSafe, Wallet theWallet) {
		this.theSafe = theSafe;
		this.theWallet = theWallet;

	}

	public Safe getTheSafe() {
		return theSafe;
	}

	public Wallet getTheWallet() {
		return theWallet;
	}

}

class Memento {
	private SafeAndWalletState state;

	public Memento(SafeAndWalletState state) {
		this.state = new SafeAndWalletState(state.getTheSafe(),state.getTheWallet());
	}

	public SafeAndWalletState getState() {
		return state;
	}
}

class Originator {
	private SafeAndWalletState state;

	public void setState(SafeAndWalletState state) {

		this.state = state;
	}

	public Memento save() {
		return new Memento(state);
	}

	public SafeAndWalletState restore(Memento m) {
		state = m.getState();
		return state;

	}
}

class Caretaker {
	private ArrayList<Memento> mementos = new ArrayList<>();
	private int index;

	public void addMemento(Memento m) {
		mementos.add(m);
		index = mementos.size() - 1;
	}

	public Memento getMemento() {
		if (mementos.isEmpty() || index <= 0) {
			throw new EmptyStackException();
		}
		
		return mementos.get(--index);
	}

}
