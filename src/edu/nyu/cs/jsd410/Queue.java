package edu.nyu.cs.jsd410;

/**
 * An interface which implements method signatures that are applicable by the LinkedQueue class.
 * @author juliansmithdeniro
 *
 * @param <E>
 */
public interface Queue<E> {
	/**
	 * Check whether or not the queue is empty.
	 * @return a boolean value; true if empty, false otherwise.
	 */
	boolean isEmpty();
	
	/**
	 * Enqueue an object into the queue.
	 * @param e any generic type that wishes to be passed.
	 */
	void enqueue(E e);
	
	/**
	 * Return the first object in the queue.
	 * @return
	 */
	E first();
	
	/**
	 * Dequeue the first object in the queue.
	 * @return
	 */
	E dequeue();
	
}
