package edu.nyu.cs.jsd410;

import java.util.ArrayList;

public class TestCalculateWaitTime {
	
	public static int calculateWaitTime(ArrayList<Customer> customers, int id, Customer customer,
			int helpTime, int openTime, int closeTime) {
		if (id == 1) {
			return openTime - customer.getArrivalTime();
		} else {
			int waitTime;
			if (customer.getArrivalTime() < openTime) {
				return openTime - customer.getArrivalTime() + (helpTime * (id - 1)) + calculateWaitTime(
						customers, id - 1, customers.get(id - 2), helpTime, openTime, closeTime);
			} else {
				
			}
		}
	}
	
	public static void main(String[] args) {
		
	}
}
