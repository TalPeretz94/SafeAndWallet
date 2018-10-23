package ood.hw3;

public interface Subject {

	public void addObserver(Observer o);

	public void unregister(Observer o);

	public void notifyObserver();
}
