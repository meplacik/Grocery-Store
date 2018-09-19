 /**
 * File: Landscape.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.awt.Font;
import java.lang.Math;


public class Landscape {

	//fields
	private int width;
	private int height;
	private ArrayList<Checkout> checkoutLanes;
	private ArrayList<Customer> customers;
	
	//constructor that sets the width and height fields
	//initializes lists of checkout objects and customer objects
	public Landscape( int w , int h ){
		this.width = w;
		this.height = h;
		this.checkoutLanes = new ArrayList<Checkout>();
		this.customers = new ArrayList<Customer>();
		
	}
	
	//returns the height
	public int getHeight(){
		return this.height;
	}
	
	//returns the width
	public int getWidth(){
		return this.width;
	}
	
	//returns array list of customers
	public ArrayList<Customer> getCust(){
		return this.customers;
	}
	
	//adds checkout object to array list named checkoutLanes
	public void addQueue( Checkout queue ){
		this.checkoutLanes.add(queue);
	}
	
	//returns a string representing the landscape
	//indicates number of checkout lanes on the landscape
	public String toString(){
		return "There are " + this.checkoutLanes.size() + " checkout lanes in the store.";
	}

	//draws all of the checkout counters and present customers
	public void draw ( Graphics g ){
		Font b = new Font("Helvetica", Font.BOLD, 16);
		g.setFont(b);
		g.drawString("WELCOME TO HANNAFORD!" , 120 , 50 );
		g.drawString("let's buy some groceries" , 120 , 70 );
		Font h = new Font("Helvetica", Font.PLAIN, 14);
		g.setFont(h);
		for ( Checkout queue: this.checkoutLanes ){
			queue.draw(g);
		}
		for ( Customer customers: this.customers ){
			customers.draw(g);
		}
	}
	
	public double calcAvg( ArrayList<Integer> nums ){
		Integer sum = 0;
		for( Integer num: nums ){
			sum+= num;
		}
		return sum.doubleValue() / nums.size();
	}
	
	public double stDev( ArrayList<Integer> nums ){
		double avg = this.calcAvg( nums );
		double temp = 0;
		for( int i = 0 ; i < nums.size() ; i++ ){
			int val = nums.get(i);
			double sqrDifftoAvg = Math.pow(val - avg,2);
			temp += sqrDifftoAvg;
		}
		double avgOfDiffs = (double) temp / (double) (nums.size() );
		return Math.sqrt(avgOfDiffs);
	}
	
	
	//updates state of customers and queues on landscape using updateState methods
	public void updateState(){
		Spawner spawn = new Spawner();
		this.customers.add( spawn.makeCustomer() );
		for (int i = 0 ; i < this.customers.size() ; i++ ){
			this.customers.get(i).updateState(this.checkoutLanes);
		}
		for (int i = 0 ; i < this.checkoutLanes.size() ; i++){
			this.checkoutLanes.get(i).updateState();
		}
	}
	
	//main method to test class methods
	public static void main( String[] args ){
		Landscape scape = new Landscape(100,100);
		for( int i = 0 ; i < 10 ; i++){
			Checkout cH = new Checkout(50,50+10*i);
			scape.addQueue( cH );
		}
		
		System.out.println( scape.toString() );
	}

}