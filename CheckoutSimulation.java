 /**
 * File: GrouperSimulation.java
 * Author: Maddy Placik
 * Date: 10/08/2017
 * CS 231: Project 4
 */


 import java.util.ArrayList;
 import java.util.Random;
 import java.awt.Graphics;
 import java.awt.Color;
 import javax.swing.JFrame;
 import javax.swing.JPanel;


public class CheckoutSimulation {


	public static void main( String[] args ) throws InterruptedException{

		//sets up landscape with dimensions specified in standard input
		Landscape scape = new Landscape( 400 , 400 );
		
		
		// generates 10 checkout agents
		for (int i = 0 ; i < 10 ; i++ ){
			Checkout cH = new Checkout( 50 + 30*i , 350 );
			scape.addQueue(cH);
			// cH.addCustomer(spawn.makeCustomer());
		}
		
		LandscapeDisplay display = new LandscapeDisplay(scape, 1);
		
		
		
		for ( int i=0 ; i < 200 ; i++){
				Thread.sleep(1);
				scape.updateState();
				display.repaint();
				//after each iteration, an image of the Landscape is saved
				display.saveImage( "agent_frame_" + String.format( "%03d", i ) + ".png" );
			
		}
		
		
		ArrayList<Integer> numTurns1 = new ArrayList<Integer>();
		ArrayList<Integer> numTurns2 = new ArrayList<Integer>();
		ArrayList<Integer> numTurns4 = new ArrayList<Integer>();
		
		
		for (int i = 0 ; i < scape.getCust().size() ; i++ ){
			if( scape.getCust().get(i).getIntPhase() == 1 ){
				if( scape.getCust().get(i).getIntStrat() == 1 ){
					numTurns1.add( scape.getCust().get(i).numTurns() );
				}
				if (scape.getCust().get(i).getIntStrat() == 2 ){
					numTurns2.add( scape.getCust().get(i).numTurns() );
				}
				if (scape.getCust().get(i).getIntStrat() == 4 ){
					numTurns4.add( scape.getCust().get(i).numTurns() );
				}
			}
		}
		
		// System.out.println( "Strat 1 " + numTurns1 );
		System.out.println( "Average number of time steps for strategy 1: " + scape.calcAvg( numTurns1 ) );
		System.out.println( "Standard Deviation of time steps for strategy 1: " + scape.stDev( numTurns1 ) );
		// System.out.println( "Strat 2 " + numTurns2 );
		System.out.println( "Average number of time steps for strategy 2: " + scape.calcAvg( numTurns2 ) );
		System.out.println( "Standard Deviation of time steps for strategy 2: " + scape.stDev( numTurns2 ) );
		// System.out.println( "Strat 4 " + numTurns4 );
		System.out.println( "Average number of time steps for strategy 4: " + scape.calcAvg( numTurns4 ) );	
		System.out.println( "Standard Deviation of time steps for strategy 4: " + scape.stDev( numTurns4 ) );
	}

}

