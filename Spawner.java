/**
 * File: Grouper.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Spawner {
	
	//creates random object and array list of integers 
	private Random rand;
	private ArrayList<Integer> strat;

	//initializes random object and adds values 1,2,4 to array list (indicating strategies)
	public Spawner(){
		rand = new Random();
		strat = new ArrayList<Integer>();
		strat.add(1);
		strat.add(2);
		strat.add(4);
	}
	
	//creates a customer with random x,y location and random strategy
	public Customer makeCustomer(){
		return new Customer(strat.get(rand.nextInt(strat.size())) , 5 + rand.nextInt(50) , 5 + rand.nextInt(20) );
	}
	
	public static void main( String[] args ){
		Spawner spawn = new Spawner();
		System.out.println( spawn.makeCustomer().getStrat() );
		System.out.println( "They are located at (" + spawn.makeCustomer().getX() + ", " + spawn.makeCustomer().getY()+ ")");
	}

}