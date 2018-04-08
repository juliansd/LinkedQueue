package edu.nyu.cs.jsd410;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class which runs the main functionality of the program.
 * The class has a static time variable which represents the time of day in SECONDS.
 * The reason time is represented in seconds is because the input of this program is usually
 * a string representation of what a customer arrives at the counter so we convert each string
 * into the seconds of the day that the customer arrives.  It is also easier to store time as a single
 * int variable rather than possible a hashmap with three seperate fields pointing to 'second',
 * 'minute', 'hour'.
 * @author juliansmithdeniro
 *
 */
public class Main {
	
	final static int SECONDS_PER_HOUR = 3600;
	final static int SECONDS_PER_MINUTE = 60;
	final static int OPENING_TIME = 32400;
	final static int CLOSING_TIME = 61200;
	static int time;
	
	/**
	 * Calculate the longest and total idle time that the service counter experiences.
	 * @param customers an ArrayList which holds references to each Customer object passed as input.
	 * @return a length 2 array from which the first and second element holds the 
	 * total idle time and the longest period of time that the service counter was idle respectively.
	 */
	public static int[] calculateIdleTime(ArrayList<Customer> customers) {
		int[] output = new int[2];
		int totalIdleTime = 0;
		int longestIdleTime = 0;
		for (int i = 0; i < customers.size() - 1; i++) {
			int currentCustomerArrivalTime = customers.get(i).getArrivalTime();
			if (customers.get(i).getId() != 1 && currentCustomerArrivalTime > OPENING_TIME) {
				int previousCustomerDoneTime = customers.get(i - 1).getDoneTime();
				if (currentCustomerArrivalTime - previousCustomerDoneTime > 0) {
					totalIdleTime += currentCustomerArrivalTime - previousCustomerDoneTime;
					if (longestIdleTime < currentCustomerArrivalTime - previousCustomerDoneTime) {
						longestIdleTime = currentCustomerArrivalTime - previousCustomerDoneTime;
					}
				}
			}
		}
		output[0] = totalIdleTime;
		output[1] = longestIdleTime;
		return output;
	}
	
	/**
	 * Calculate the average waiting time of the customers and the amount of customers that are served.
	 * This method calculates these values for each input separately.
	 * @param customers an ArrayList which holds references to each Customer object passed as input.
	 * @param CONSTANT_HELP_TIME
	 * @return
	 */
	public static int[] calculateAvgWaitAndCustomersServed(
			ArrayList<Customer> customers, int CONSTANT_HELP_TIME) {
		int[] output = new int[2];
		int averageWaitTime = 0;
		int customersServed = 0;
		for (int k = 0; k < customers.size(); k++) {
			int waitTime = calculateWaitTime(
					customers, k + 1, customers.get(k), CONSTANT_HELP_TIME, OPENING_TIME,
					CLOSING_TIME);
			if (waitTime + customers.get(k).getArrivalTime() > CLOSING_TIME) {
				customers.get(k).setWaitTime(0);
				
			} else {
				customers.get(k).setWaitTime(waitTime);
				customersServed += 1;
			}
			averageWaitTime += customers.get(k).getWaitTime();
		}
		averageWaitTime = (averageWaitTime/customers.size());
		output[0] = averageWaitTime;
		output[1] = customersServed;
		return output;
	}
	
	/**
	 * Simulate the queue and calculate the longest the queue gets.
	 * @param queue a LinkedQueue which represents the queue which customers wait in.
	 * @param customers an ArrayList which holds references to each Customer object passed as input.
	 * @param CONSTANT_HELP_TIME the time each customer will take when at the service counter.
	 * @return an int representing the longest the queue gets throughout the 'day'.
	 */
	public static int simulateQueue(
			LinkedQueue<Customer> queue, ArrayList<Customer> customers, int CONSTANT_HELP_TIME) {
		int i = 0;
		int maxQueueLength = queue.getList().getSize();
		int listCount = 0;
		while (time < CLOSING_TIME) {
			
			for (int k = 0; k < customers.size(); k++) {
				if (time == customers.get(k).getArrivalTime()) {
					queue.enqueue(customers.get(k));
					listCount++;
					queue.getList().setSize(listCount);
				}
				
				if (time == customers.get(k).getDoneTime()) {
					queue.dequeue();
					listCount--;
					queue.getList().setSize(listCount);
				}
				
				if (maxQueueLength < listCount)
					maxQueueLength = listCount;
			}
			time++;
		}
		return maxQueueLength - 1;
	}
	
	/**
	 * Calculate the individual waiting time of each customer passed into the program.
	 * @param customers an ArrayList which holds references to each Customer object passed as input.
	 * @param id an int representing the Customer's id.
	 * @param customer an object representing the customers who are waiting in the queue.
	 * @param helpTime an int representing the constant time it takes in seconds for a Customer object to be helped.
	 * @param openTime an int representing the opening time of the 'service counter' in seconds.
	 * @param closeTime an int representing the closing time of the 'service counter' in seconds.
	 * @return an int representing the average waiting time for each customer in the queue in seconds.
	 */
	public static int calculateWaitTime(ArrayList<Customer> customers, int id, Customer customer,
			int helpTime, int openTime, int closeTime) {
		int totalWaitTime;
		if (customer.getId() == 1) {
			if (customer.getArrivalTime() < OPENING_TIME) {
				totalWaitTime = (OPENING_TIME - customer.getArrivalTime());
				return totalWaitTime;
			} else {
				return 0;
			}
		} else {
			if (customer.getArrivalTime() < customers.get(id - 2).getDoneTime()) {
				totalWaitTime = customers.get(id - 2).getDoneTime() - customer.getArrivalTime();
				return totalWaitTime;
			} else if (customer.getArrivalTime() > CLOSING_TIME) {
				return 0;
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Calculate the time in seconds that each Customer object is done being helped.
	 * @param customers an ArrayList which holds references to each Customer object passed as input.
	 * @param id an int representing the Customer's id.
	 * @param customer an object representing the customers who are waiting in the queue.
	 * @param helpTime an int representing the constant time it takes in seconds for a Customer object to be helped.
	 * @param openTime an int representing the opening time of the 'service counter' in seconds.
	 * @param closeTime an int representing the closing time of the 'service counter' in seconds.
	 * @return an int representing the done time for the Customer object.
	 */
	public static int calculateDoneTime(ArrayList<Customer> customers, int id, Customer customer,
			int helpTime, int openTime, int closeTime) {
		if (customer.getId() == 1) {
			if (customer.getArrivalTime() < OPENING_TIME) {
				customer.setDoneTime(OPENING_TIME + helpTime);
				return customer.getDoneTime();
			} else {
				customer.setDoneTime(customer.getArrivalTime() + helpTime);
				return 0;
			}
		} else {
			int mostRecentDoneTime = calculateDoneTime(
					customers, id - 1, customers.get(id - 2), helpTime, OPENING_TIME, closeTime) +
					helpTime;
			if (customer.getArrivalTime() > mostRecentDoneTime) {
				customer.setDoneTime(customer.getArrivalTime() + helpTime);
				return customer.getDoneTime();
			} else {
				customer.setDoneTime(
						calculateDoneTime(customers, id - 1, customers.get(id - 2),
								helpTime, OPENING_TIME, closeTime) + helpTime);
				return customer.getDoneTime();
			}
		}
	}

	/**
	 * The main method which runs the main functionality of the program.
	 * @param args command line arguments passed for this program include a customers file
	 * which is used to create the customer objects and calculate wait time, idle time, etc.
	 * The second argument should be a file which prompts the program which values it would like to
	 * output to the console.  For example if you want to know the wait time of the customer
	 * with id 3, then you should specify in this file.  For specific formatting of these
	 * files please refer to the README.md
	 */
	public static void main(String[] args) {
		
		ArrayList<Integer> output = new ArrayList<>(); // Is ultimately the output of the main method
		
		int[] avgWaitAndCustomersServed = new int[2];
		int[] totalIdleAndLongestIdle = new int[2];
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<String> customerFileData = new ArrayList<String>();
		ArrayList<String> queryFileData = new ArrayList<String>();
		LinkedQueue<Customer> serviceCounterQueue = new LinkedQueue<Customer>();
		
		String line = null;
		String line2 = null;
		
		String customerFile = args[0];
		String queriesFile = args[1];
		
		try {
			FileReader customerFileReader = new FileReader(customerFile);
			BufferedReader customerBufferedReader = new BufferedReader(customerFileReader);
			
			FileReader queriesFileReader = new FileReader(queriesFile);
			BufferedReader queriesBufferedReader = new BufferedReader(queriesFileReader);
			
			while((line = customerBufferedReader.readLine()) != null) {
				customerFileData.add(line);
			}
			
			while(((line2 = queriesBufferedReader.readLine()) != null)) {
				queryFileData.add(line2);
			}	
			
			customerBufferedReader.close();
			queriesBufferedReader.close();
			
		} catch(FileNotFoundException e) {
            e.printStackTrace();

        } catch(IOException e) {
            e.printStackTrace();                  
        }
		
		// Store the constant help time.
		final int CONSTANT_HELP_TIME = Integer.parseInt(customerFileData.get(0));
		
		// Create customer objects from customer input data.
		for (int i = 0; i < customerFileData.size(); i++) {
			int id = 0;
			int arrivalTime = 0;
			String[] time = new String[3];
			
			if (customerFileData.get(i).contains("ID-NUMBER")) {
				id = Integer.parseInt(customerFileData.get(i).substring(12));
				
				for (int j = i + 1; j < customerFileData.size(); j++) {
					if (customerFileData.get(j).contains("ARRIVAL-TIME")) {
						time = customerFileData.get(j).substring(14).split(":");
						if (Integer.parseInt(time[0]) <= 5) {
							arrivalTime += ((Integer.parseInt(time[0]) + 12) * SECONDS_PER_HOUR) +
									(Integer.parseInt(time[1]) * SECONDS_PER_MINUTE) + 
									Integer.parseInt(time[2]);
							break;
						} else {
							arrivalTime += (Integer.parseInt(time[0]) * SECONDS_PER_HOUR) +
									(Integer.parseInt(time[1]) * SECONDS_PER_MINUTE) + 
									Integer.parseInt(time[2]);
							break;
						}
					}
				}
			}
			
			if (id != 0 && arrivalTime != 0) {
				Customer customer = new Customer(id, arrivalTime);
				customers.add(customer);
			}
		}
		
		// Collect data on queue and output to console.
		calculateDoneTime(
				customers, customers.size(), customers.get(customers.size() - 1),
				CONSTANT_HELP_TIME, OPENING_TIME, CLOSING_TIME);
		calculateWaitTime(
				customers, 1, customers.get(0), CONSTANT_HELP_TIME, OPENING_TIME, CLOSING_TIME);
		avgWaitAndCustomersServed = calculateAvgWaitAndCustomersServed(customers, CONSTANT_HELP_TIME);
		totalIdleAndLongestIdle = calculateIdleTime(customers);
		int maxQueueLength = simulateQueue(serviceCounterQueue, customers, CONSTANT_HELP_TIME);
		int i = 0;
		for (String query : queryFileData) {
			switch (query) {
			case "NUMBER-OF-CUSTOMERS-SERVED":
				output.add(avgWaitAndCustomersServed[1]);
				break;
				
			case "LONGEST-BREAK-LENGTH":
				output.add(totalIdleAndLongestIdle[1]);
				break;
				
			case "TOTAL-IDLE-TIME":
				output.add(totalIdleAndLongestIdle[0]);
				break;
				
			case "MAXIMUM-NUMBER-OF-PEOPLE-IN-QUEUE-AT-ANY-TIME":
				output.add(maxQueueLength);
				break;
				
			}
			if (query.contains("WAITING-TIME-OF")) {
				if (query.length() == 17) {
					int id = Character.getNumericValue(query.charAt(16));
					for (int j = 0; j < customers.size(); j++) {
						if (customers.get(j).getId() == id) {
							output.add(customers.get(j).getWaitTime());
							break;
						}
					}
				} else {
					int id = Integer.parseInt(query.substring(16));
					for (int j = 0; j < customers.size(); j++) {
						if (customers.get(j).getId() == id) {
							output.add(customers.get(j).getWaitTime());
							break;
						}
					}
				}
			}
			i++;
		}
		for (int k = 0; k < output.size(); k++) {
			System.out.println(queryFileData.get(k) + ": " + output.get(k));
		}
	}
}
