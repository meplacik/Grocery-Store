import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MyQueue<T> implements Iterable<T> {

	private Node head;
	private Node tail;
	private int length;
	
	public MyQueue(){
		this.head = null;
		this.tail = null;
		this.length = 0;
	}
	
	//add item at end of list
	public void offer( T item ){
		Node newNode = new Node(item, null);
		//find end of list so we can add to it there
		if (this.head == null){
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			// Node temp = this.head;
// 			while( temp.getNext() != null ){
// 				temp = temp.getNext();
// 			}
			this.tail.setNext(newNode);
			this.tail = newNode;
		}
		this.length++;
	}
	
	
	public T poll(){
		if ( this.head == null ){
			System.out.println( "Queue is empty." );
			return null;
		}
// 		else if ( this.head = this.tail ){
// 			T temp = this.head.getThing();
// 			this.head = null;
// 			this.tail = null;
// 		}
		else {
			T temp = this.head.getThing();
			this.head = this.head.getNext();
			this.length--;
			return temp;
		}	
	}
	
	
	public int size(){
		return this.length;
	}
	
	public T peek(){
		if( this.head == null ){
			System.out.println( "Queue is empty." );
			return null;
		}
		else{
			return this.head.getThing();
		}
	}
	
	
	public void print(){
	
		Node curNode = this.head;
		for(int i = 0 ; i<this.length ; i++ ){
			System.out.println(curNode.getThing());
			curNode = curNode.getNext();
		}
	}
	
	public Iterator iterator(){
		return new LLIterator( this.head );
	}

	
	//class to hold the data and links
	private class Node {
		private Node next;
		private T content;
	
		//constructor of generic type T, with null "next"
		public Node( T item , Node n ){
			this.content = item;
			this.next = n;
		}
		
		//accessor for node data
		public T getThing() {
			return content;
		}
		
		//sets next to given node (update next pointer)
		public void setNext( Node n ){
			this.next = n;
		}
		
		//accessor for next pointer
		public Node getNext() {
			return this.next;
		}
	}
	
	//iterator subclass, traverses list
	private class LLIterator implements Iterator<T>{
	
		//field
		private Node node_next;
	
		//creates an LLiterator given the head of the list
		public LLIterator( Node head ){
			this.node_next = head;
		}
	
		//returns true if there are still values to traverse (if current node ref !=null)
		public boolean hasNext(){
			if ( this.node_next != null ){
				return true;
			}
			else{
				return false;
			}

		}
		
		//returns next item in list
		//move traversal along to next node in list
		public T next(){
			if( this.node_next == null){
				return null;
			}
			else {
				T current = this.node_next.getThing();
				this.node_next = node_next.getNext();
				return current;
			}
		}
		
		public void remove(){
			return;
		}

	}
	
	public static void main(String[] args) {
		MyQueue<Integer> mQ = new MyQueue<Integer>();
		
		for( int i = 0 ; i<10 ; i++ ){
			mQ.offer(i);
		}
		
		for( int i = 0 ; i<5 ; i++ ){
			mQ.poll();
		}
		
		System.out.println( "Size of queue: " + mQ.size() );
		
		System.out.println( "The first item in the queue is: " + mQ.peek() );
		
		Iterator it = mQ.iterator();
		
		for (int i=0; i<6; i++) {
			System.out.println(it.next());
		}
		
// 		mQ.print();
	
	}
	
}