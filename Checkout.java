 /**
 * File: Checkout.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Iterator;
 import java.awt.Color;


public class Checkout extends Agent{
	
	//field variable of a queue object containing customer objects
	private MyQueue<Customer> checkoutQueue;
	
	//constructor method
	public Checkout(int x0, int y0){
		super(x0,y0);
		//initializes Queue
		this.checkoutQueue = new MyQueue<Customer>();
	}
	
	//returns size of Queue
	public int getSize(){
		return this.checkoutQueue.size();
	}
	
	//adds customer object to queue
	public void addCustomer( Customer cust ){
		this.checkoutQueue.offer( cust );
		cust.setX( this.getX() +10);
		cust.setY( this.getY() - 20 * (this.getSize()));
	}
	
	//removes customer from head of queue
	public Customer removeCustomer(){
		return this.checkoutQueue.poll();
		//loop through remaining and move down
	}
	
	//indicates how many items customer at head has
	public String toString(){
		return "The first customer in this queue has " + this.checkoutQueue.peek().getItems() + " items.";
	}
	
	//draw checkout area
	public void draw( Graphics g ){
		//draws black cashier
		g.setColor(Color.black);
		g.drawRect(this.getX()+10,this.getY(),15,20);
	
		g.drawString("$" , this.getX()+13,this.getY()+15);
		//draws each customer in queue
		for( Customer cust: this.checkoutQueue ){
			cust.draw( g );
		}
	}
	
	//updates state of Queue based on states of customers in queue
	public void updateState(){
		//if customer at head is present, remove their items
		if( this.checkoutQueue.peek() != null ){
			this.checkoutQueue.peek().removeItems();
		}
		//if there arent any customers in queue, do nothing
		else if( this.getSize() == 0 ){
			return;
		}
		//if customer at head does not have any items, remove them from queue
		if( this.checkoutQueue.peek().getItems() == 0 ){
			this.removeCustomer().setPhase(2);
			int i=0;
			for( Customer cust: this.checkoutQueue ){
				// int y = cust.getY();
				i++;
				//change position of remaining customers in queue
				cust.setY( this.getY() - 20*i );
			}
		}
		
	}
	
	public static void main( String[] args ){
		Checkout checkout = new Checkout(5,5);
		ArrayList<Integer> strat = new ArrayList<Integer>();
		strat.add(1);
		strat.add(2);
		strat.add(4);
		Random r = new Random();
		for( int i = 0 ; i<10 ; i++ ){
			Customer cust = new Customer(strat.get(r.nextInt(strat.size())), 5,5+10*i );
			cust.setItems();
			checkout.addCustomer( cust );
		}
		System.out.println("The checkout line has " +checkout.getSize()+ " customers.");
		checkout.removeCustomer();
		System.out.println("The checkout line has " +checkout.getSize() +" customers.");
		System.out.println( checkout.toString() );
	}

}