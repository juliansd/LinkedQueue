package edu.nyu.cs.jsd410;

public class ServiceCounter extends LinkedQueue<Customer> {
	
	private int time;
	
	public ServiceCounter() {}
	
	public void help() {
		// mimicks helpings the customer
	}
	
	public void close() {
		// closes the counter for the day
		// saves the data at time of close
	}
	
	public void open() {
		// opens the counter for the day
		// keeps track of how long it's been open
	}
	
	public int timeToSeconds(String time) {
		return 0;
		// formats time by hours:minutes:seconds into seconds
	}
	
	public int breakTime() {
		return time;
		
	}
	
}
