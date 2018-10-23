package ood.hw3;

public class BillAdpater implements Item {

	private Bill bill;

	public BillAdpater(Bill newBill) {
		bill = newBill;
	}

	@Override
	public String getId() {

		return bill.getSerialNumber();
	}

	@Override
	public double getValue() {

		return bill.getAmount();
	}

	@Override
	public String getDescription() {
		return bill.getCurrency().name();
	}
	@Override
	public String toString() {
		return getDescription() + " " + getId() + " value: " + getValue();
	}

}
