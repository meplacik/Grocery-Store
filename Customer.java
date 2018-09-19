 /**
 * File: Agent.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
 import java.awt.Color;

public class Customer extends Agent{
	
	//number of items in basket
	private int items;
	//current phase (either -1, 0, or 1)
	private int phase;
	//how much time until customer can choose line
	//either 1, 2, or 4
	private int waitTime;
	//customer selection strategy
	//indicated by 1, 2, and 4
	private int strategy;

	
	/*number of turns it takes from spawning
	until customer reaches head of checkout line
	*/
	private int turns = 0;
	
	//constructor that sets position
	public Customer( int strat , int x0 , int y0){
		super(x0,y0);
		//each customer starts with one item
		this.items = 1;
		//initial phase of customer is selecting which queue to join
		this.phase = -1;
		this.strategy = strat;
		
		//gives customer random number of items between 1 and 20
		this.setItems();
		
		//choose random queue
		if (strat == 1 ){
			this.waitTime = 1;
		}
		
		//choose best of 2 random lines
		else if (strat == 2 ){
			this.waitTime = 2;
		}
		
		//check all lines
		else if ( strat == 4 ){
			this.waitTime = 4;
		}
	}
	
	//updates state of customer based on phase and waitTime
	public void updateState(ArrayList<Checkout> checkoutQueues){
		//creates random object
		Random gen = new Random();
		//if phase<2
		this.turns++;
		//if the customer is in the selection phase, the waitTime must decrease
		if ( this.phase == -1 && this.waitTime != 0){
			this.waitTime--;
		}
		
		//customer enters wait phase at end of selection phase
		else if (this.phase == -1 && this.waitTime == 0 ){
			//moves customer to next phase
			this.phase = 0;
			//if choosing random line
			if( this.strategy == 1){
				//pick random line from arraylist input
				int lineNum = gen.nextInt( checkoutQueues.size() );
				//customer joins randomly chosen line
				checkoutQueues.get(lineNum).addCustomer(this);
			}
			//if choosing best of 2 random lines
			else if( this.strategy == 2 ){
				//picks random line
				int line1 = gen.nextInt( checkoutQueues.size() );
				//picks random line
				int line2 = gen.nextInt( checkoutQueues.size() );
					//checks which line is shorter, joins shorter line
					if( checkoutQueues.get(line1).getSize() >= checkoutQueues.get(line2).getSize() ){
						checkoutQueues.get(line2).addCustomer(this);
					}
					else{
						checkoutQueues.get(line1).addCustomer(this);
					}
			}
			//if checking length of all lines
			else if( this.strategy == 4 ){
				//sets smallest line to first line in given arraylist
				Checkout smallestQ = checkoutQueues.get(0);
				//loops through each line in array list comparing size to first queue
				for( int i = 0 ; i < checkoutQueues.size() ; i++){
					if( checkoutQueues.get(i).getSize() < smallestQ.getSize() ){
						//reassigns variable to the queue that is actually the shortest
						smallestQ = checkoutQueues.get(i);
					}
				}
				//adds customer to shortest queue
				smallestQ.addCustomer(this);		
			}
		}
		
	}
	
	//returns the number of items a given customer has
	public int getItems(){
		return this.items;
	}
	
	//randomly picks a number of items ranging from 0 to 20
	public void setItems(){
		Random gen = new Random();
		this.items = 1 + gen.nextInt( 20 );
	}
	
	//returns a string indicating which phase the customer is in
	public String getPhase(){
		if( this.phase == -1 ){
			return "Customer is selecting which queue to join";
		}
		else if( this.phase == 0 ){
			return "Customer is waiting in line";
		}
		else{
			return "Customer is at the front of the queue";
		}
	}
	
	//returns a string indicating which phase the customer is in
	public int getIntPhase(){
		if( this.phase == -1 ){
			return -1;
		}
		else if( this.phase == 0 ){
			return 0;
		}
		else{
			return 1;
		}
	}
	
	//sets phase of customer
	public void setPhase( int newPhase ){
		this.phase = newPhase;
	}
	
	
	//returns a string indicating which strategy the customer is using
	public String getStrat(){
		if (this.strategy == 1 ){
			return "This customer is using the 'Random' strategy.";
		}
		else if (this.strategy == 2){
			return "This customer is using the 'Best of 2' strategy.";
		}
		else {
			return "This customer is using the 'Check All' strategy.";
		}
	}
	
	//returns int indicating which strategy the customer is using
	public int getIntStrat(){
		if (this.strategy == 1 ){
			return 1;
		}
		else if (this.strategy == 2){
			return 2;
		}
		else {
			return 4;
		}
	}
	
	//returns the number of timesteps that have occurred since spawning
	public int numTurns(){
		return this.turns;
	}
	
	//decreases number of items in basket one at a time until there are 0 remaining
	public void removeItems(){
		if( this.items > 0 ){
			this.items--;
		}
	}
	
	//returns a string indicating which phase the customer is in and how many items they have in their basket
	public String toString(){
		return this.getPhase() + " and has " + this.items + " items." ;
	}

	
	//draws a circle of radius 5 in x,y position
	public void draw( Graphics g ){
		//if customer has finished checking out, they are not drawn
		if( this.phase == 2 ){
			return;
		}
		//if customer is waiting in line they are orange
		else if( this.phase == 0 || this.phase == 1 ){
			g.setColor(Color.orange);
		}
		//if customer is selecting, they appear blue
		else {
			g.setColor(Color.blue);
		}
		g.fillOval(this.getX(),this.getY(),10,10);
		
	}

	public static void main( String[] args ){
		Customer customer1 = new Customer(1,5,5);
		customer1.setItems();
		System.out.println( "Customer1 has " + customer1.getItems() + " items." );
		System.out.println( customer1.getPhase() );
		System.out.println( customer1.getStrat() );
		System.out.println( customer1.toString() );
		
		Customer customer2 = new Customer(2,10,10);
		customer2.setItems();
		System.out.println( "Customer1 has " + customer2.getItems() + " items." );
		System.out.println( customer2.getPhase() );
		System.out.println( customer2.getStrat() );
		System.out.println( customer2.toString() );
		
		Customer customer3 = new Customer(4,20,20);
		customer3.setItems();
		System.out.println( "Customer1 has " + customer3.getItems() + " items." );
		System.out.println( customer3.getPhase() );
		System.out.println( customer3.getStrat() );
		System.out.println( customer3.toString() );
	}

}

