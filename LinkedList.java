import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class LinkedList<T> implements Iterable<T> {

	private Node head;
	private int length;
	private Node tail;
	
	//constructor creates empty list by default
	public LinkedList() {
		this.head = null;
		this.length = 0;
	}
	
	//empties the list
	public void clear() {
		this.head = null;
		this.length = 0;
	}
	
	//accessor for list size
	public int size() {
		return this.length;
	}
	
	public Node getHead(){
		return this.head;
	}
	
	public Node getTail(){
		return this.tail;
	}
	
	// add an item at the beginning of the list
	public void addFirst( T item ) {
		Node node = new Node(item);
		node.setNext( head );
		head = node;
		length++;
	}
	
	//add item at end of list
	public void addLast( T item ){
		Node newNode = new Node(item);
		//find end of list so we can add to it there
		if (this.head == null){
			this.head = newNode;
            this.tail = newNode;
		}
		else {
			Node temp = this.head;
			while( temp.getNext() != null ){
				temp = temp.getNext();
                
			}
			temp.setNext(newNode);
            this.tail = temp;
		}
		this.length++;
        
	}
	
	//add item at end of list
	public void add( int index , T item ){
		Node node = new Node( item );
		Node current = head;
		int position = 0;
		//find end of list so we can add to it there
		if ( current != null ){
			while( current.getNext() != null && position < index - 1 ){
			current = current.getNext();
			position++;
			}
			node.setNext( current.getNext() );
			current.setNext( node );
		}
		else {
			head = node;
		}
		length++;
	}
		
	
	
	public T remove(int index){
		Node previous = head;
		Node node = null;
		T item = null;
		int position = 0;
		//if there is only 1 item in this list
		//cant access head.getNext().getNext()
		if ( index  == 0 && head != null ){
			node = head;
			head = head.getNext();
			item = node.getThing();
		}
		//for multiple items in the list
		else if ( previous != null){
			//brings us right before node we want to move
			while( previous.getNext() != null && position < index - 1 ){
				previous = previous.getNext();
				position++;
			}
			node = previous.getNext();
			previous.setNext( previous.getNext().getNext() );
			node.setNext( null );
			item = node.getThing();
		}
		length--;
		return item;
	}
	
	public Iterator iterator(){
		return new LLIterator( this.head );
	}
	
	//returns an ArrayList of the list contents in order
	public ArrayList<T> toArrayList(){
		ArrayList<T> al = new ArrayList<T>();
		Iterator<T> iterator = iterator();
		for ( int i = 0 ; i < this.length ; i++ ){
			al.add( iterator.next() );
		}
		return al;
	}


	
	
	//returns an ArrayList of the list contents in shuffled order
	public ArrayList<T> toShuffledList(){
	
		ArrayList<T> shuffled = new ArrayList<T>();
		ArrayList<T> al = new ArrayList<T>();
		al = toArrayList();
		Random randy = new Random( System.currentTimeMillis() );
		
		for ( int i = 0 ; i < this.length ; i++){
			int index = randy.nextInt( al.size() );
			shuffled.add( al.get(index) );
			al.remove(index);
		}
		al = shuffled;
		return al;
	}
	
	//class to hold the data and links
	private class Node {
		private Node next;
		private T content;
	
		//constructor of generic type T, with null "next"
		public Node( T item ){
			this.content = item;
			this.next = null;
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
		
		LinkedList<Integer> llist = new LinkedList<Integer>();
		
		llist.addFirst(5);
		llist.addFirst(10);
		llist.addFirst(20);
	
		System.out.printf("\nAfter setup %d\n", llist.size());
		for(Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
	
		llist.clear();
	
		System.out.printf("\nAfter clearing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		llist.addLast(5);
		llist.addLast(10);
		llist.addLast(20);
	
		System.out.printf("\nAfter setup %d\n", llist.size());
		for(Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
	
		llist.clear();
	
		System.out.printf("\nAfter clearing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
	
		for (int i = 10; i > 0; i -= 2) {
			llist.add(0, i);
		}
		llist.add(5, 12);
		llist.add(3, 0);

		System.out.printf("\nAfter setting %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}

		llist.remove(0);
		System.out.printf("\nAfter removing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		llist.remove(2);
		System.out.printf("\nAfter removing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		llist.remove(4);
		System.out.printf("\nAfter removing %d\n", llist.size());
		for (Integer item: llist) {
			System.out.printf("thing %d\n", item);
		}
		
		ArrayList<Integer> alist = llist.toArrayList();
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}						
	
		alist = llist.toShuffledList();	
		System.out.printf("\nAfter copying %d\n", alist.size());
		for(Integer item: alist) {
			System.out.printf("thing %d\n", item);
		}
	
	}
	
}
