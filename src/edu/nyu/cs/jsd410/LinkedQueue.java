package edu.nyu.cs.jsd410;

/**
 * This class represents a queue which operates using a linked list.
 * @author juliansmithdeniro
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
	
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	/**
	 * Default constructor for the queue.
	 */
	public LinkedQueue() {}

	@Override
	public boolean isEmpty() {
		return this.getList().isEmpty();
	}

	@Override
	public void enqueue(E e) {
		this.getList().addLast(e);
	}

	@Override
	public E first() {
		if (this.isEmpty())
			return null;
		else
			return this.getList().first();
	}

	@Override
	public E dequeue() {
		if (this.isEmpty())
			return null;
		else
			return this.getList().removeFirst();
	}

	/**
	 * Return the linked list which holds Nodes for the queue.
	 * @return a SinglyLinkedList object
	 */
	public SinglyLinkedList<E> getList() {
		return this.list;
	}

	/**
	 * Set the list for the queue.
	 * @param list is a SinglyLinkedList object
	 */
	public void setList(SinglyLinkedList<E> list) {
		this.list = list;
	}
	
}
