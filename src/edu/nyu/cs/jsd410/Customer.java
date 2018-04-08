package edu.nyu.cs.jsd410;

/**
 * A class which represents customers to be partake in the queue that is exhibited in the Main class.
 * @author juliansmithdeniro
 *
 */
public class Customer {
	
	private int id;
	private int arrivalTime;
	private int waitTime;
	private int doneTime;
	
	/**
	 * Default constructor for a Customer object.
	 */
	public Customer() {}
	
	/**
	 * Constructor which allows the object to be instantiated an id and arrival time.
	 * @param id an int representing the ID number of the customer.
	 * @param arrivalTime an int representing the time of arrival by the customer in seconds.
	 */
	public Customer(int id, int arrivalTime) {
		this.setId(id);
		this.setArrivalTime(arrivalTime);
	}

	/**
	 * Return the customer id.
	 * @return an int representing the customer's id.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set the customer id.
	 * @param id an int representing the customer's id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return the arrival time of the customer.
	 * @return an int representing the customer arrival time in seconds.
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}

	/**
	 * Set the arrival time of the customer.
	 * @param arrivalTime an int representing the customer arrival time in seconds.
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * Return the time the customer waits to be served in seconds.
	 * @return an int representing the amount of time the customer waits to be served in seconds.
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Set the wait time of the customer.
	 * @param waitTime an int representing the wait time of the customer in seconds.
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	/**
	 * Return the time that the customer is done being served.
	 * @return an int representing the time the customer is done being served in seconds.
	 */
	public int getDoneTime() {
		return doneTime;
	}

	/**
	 * Set the time the customer is done being served
	 * @param doneTime an int representing the time the customer is done being served in seconds.
	 */
	public void setDoneTime(int doneTime) {
		this.doneTime = doneTime;
	}
	
}
