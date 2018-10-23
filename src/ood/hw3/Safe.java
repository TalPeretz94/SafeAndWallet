package ood.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ood.hw3.WrongCodeEvent.errorType;

public class Safe implements Cloneable, Iterable<Item>, Subject {
	private String code;

	private HashMap<String, Item> theMap;
	private final static String DEFULT_CODE = "1234";

	private ArrayList<Observer> observers;

	private WrongCodeEvent theEvent;

	private WrongCodeEvent eventType;

	public Safe(String code) {
		this.code = code;
		theMap = new HashMap<>();
		observers = new ArrayList<Observer>();
		theEvent= new WrongCodeEvent();

	}

	public Safe() {
		this(DEFULT_CODE);

	}

	public void addToSafe(Item theItem, String theCode) {
		if (theCode.equalsIgnoreCase(code)) {
			theMap.put(theItem.getId(), theItem);

		} else {
			theEvent.seteType(WrongCodeEvent.errorType.ADDING_ITEM);
			notifyObserver();
		}

	}

	public void getFromSafe(Item theItem, String theCode) {
		if (theCode.equalsIgnoreCase(code)) {
			theMap.remove(theItem.getId(), theItem);

		} else {
			theEvent.seteType(WrongCodeEvent.errorType.TAKING_ITEM);
			notifyObserver();

		}

	}

	public void setCode(String oldCode, String newCode) {
		if (oldCode.equalsIgnoreCase(code)) {
			this.code = newCode;
		}
		else {
			theEvent.seteType(WrongCodeEvent.errorType.CHANGE_CODE);
			notifyObserver();
		}

	}

	protected Safe clone() throws CloneNotSupportedException {
		Safe copy = (Safe) super.clone();

		HashMap<String, Item> copyOfItems = new HashMap<>(theMap);
		copy.theMap = copyOfItems;
		return copy;
	}

	@Override
	public Iterator<Item> iterator() {

		return theMap.values().iterator();
	}

	public void addObserver(Observer newObserver) {

		observers.add(newObserver);

	}

	public void unregister(Observer deleteObserver) {

		int observerIndex = observers.indexOf(deleteObserver);

		System.out.println("Observer " + (observerIndex + 1) + " deleted");

		observers.remove(observerIndex);

	}

	public void notifyObserver() {

		for (Observer observer : observers) {

			observer.onPublish(theEvent);

		}

	}

	public void setIBMPrice(WrongCodeEvent theEvent) {

		this.theEvent = theEvent;

		notifyObserver();

	}

}
