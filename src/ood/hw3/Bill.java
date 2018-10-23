package ood.hw3;

import java.io.Serializable;

public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;
	private Currency currency;
	private double amount;
	private String serialNumber;

	public Bill() {

	}

	public Bill(String serialNumber, double amount, Currency kind) {
		super();
		this.currency = kind;
		this.amount = amount;

		this.serialNumber = serialNumber;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency kind) {
		this.currency = kind;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return serialNumber + " " + amount + " " + currency;
	}

}
