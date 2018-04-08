package edu.nyu.cs.jsd410;

/**
 * This class constructs a singly linked list to be used by the LinkedQueue class.
 * It has a nested private static class which is used to construct nodes in the linked list.
 * @author juliansmithdeniro
 *
 * @param <E> Any generic type may be cast for this linked list.
 */
public class SinglyLinkedList<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	
	/**
	 * Default constructor.
	 */
	public SinglyLinkedList() {}
	
	/**
	 * Return the size of the linked list.
	 * @return int representing the size.
	 */
	public int size() {
		return this.getSize();
	}
	
	/**
	 * Return a boolean as to whether or not the linked list is empty.
	 * @return a boolean for whether or not if the object is empty.
	 */
	public boolean isEmpty() {
		return this.getSize() == 0;
	}
	
	/**
	 * Return the first element in the linked list.
	 * @return any generic type which was cast for the linked list.
	 */
	public E first() {
		if (this.isEmpty())
			return null;
		else
			return this.getHead().getElement();
	}
	
	/**
	 * Return the last element in the linked list.
	 * @return any generic type which the last element was cast as.
	 */
	public E last() {
		if (this.isEmpty())
			return null;
		else
			return this.getTail().getElement();
	}
	
	/**
	 * Add a node to the end of the linked list.
	 * @param e a generic type to be added to the linked list.
	 */
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);
		if (this.isEmpty())
			this.setHead(newest);
		else
			this.getTail().setNext(newest);
		this.setTail(newest);
		this.setSize(this.getSize() + 1);
	}
	
	/**
	 * Remove and return the first node in the linked list.
	 * @return a generic type for which the last node was cast as.
	 */
	public E removeFirst() {
		if (this.isEmpty())
			return null;
		E answer = this.getHead().getElement();
		this.setHead(this.getHead().getNext());
		this.setSize(this.getSize() - 1);
		if (this.getSize() == 0)
			this.setTail(null);
		return answer;
	}
	
	/**
	 * Return the head of the linked list.
	 * @return a Node which holds data for the first element in the linked list 
	 * as well as a reference to the next.
	 */
	public Node<E> getHead() {
		return this.head;
	}
	
	/**
	 * Set the head of the linked list
	 * @param head a Node object to be set as the head of the linked list.
	 */
	public void setHead(Node<E> head) {
		this.head = head;
	}
	
	/**
	 * Return the last node in the linked list.
	 * @return a Node which represents the last element of the linked list.
	 */
	public Node<E> getTail() {
		return this.tail;
	}
	
	/**
	 * Set the last Node in the linked list.
	 * @param tail a Node to be set as the tail of linked list.
	 */
	public void setTail(Node<E> tail) {
		this.tail = tail;
	}
	
	/**
	 * Return the size of the linked list.
	 * @return an int representing the size of the linked list.
	 */
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Set the size of the linked list.
	 * @param size is an int which the linked list's size will be set to.
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * A class which represents the nodes that are stored in the linked list.  
	 * They hold data as well as references to the subsequent node in the linked list.
	 * @author juliansmithdeniro
	 *
	 * @param <E> any generic type to be cast for a Node object.
	 */
	private static final class Node<E> {
		
		private E element;
		private Node<E> next;
		
		/**
		 * Default constructor for a Node object.
		 */
		@SuppressWarnings("unused")
		public Node() {}
		
		/**
		 * Constructor which allows two input parameters.
		 * @param e
		 * @param n
		 */
		public Node(E e, Node<E> n) {
			this.setElement(e);
			this.setNext(n);
		}
		
		/**
		 * Return any generic type element which has been stored in a Node object.
		 * @return a generic type.
		 */
		public E getElement() {
			return this.element;
		}
		
		/**
		 * Set an element for the specified Node object.
		 * @param e a generic type to represent the data within the Node object.
		 */
		public void setElement(E e) {
			this.element = e;
		}
		
		/**
		 * Return the specified Node's 'next' reference which is a Node
		 * in the linked list placed 'after' the current Node.
		 * @return the current Node's reference to the Node next in the linked list,
		 * which is also a Node.
		 */
		public Node<E> getNext() {
			return this.next;
		}
		
		/**
		 * Set the current Node's next reference.
		 * @param n a Node to be set as the current Node's next reference.
		 */
		public void setNext(Node<E> n) {
			this.next = n;
		}
		
	}
	
}
