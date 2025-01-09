package Zheng.Kobe;

import java.util.Random;


/**
* Purpose: Replicates the rolling of 2 dice
* @author Kobe Zheng
* @date Dec 20, 2024
*/
public class Die {
	private int value;
	private int numSides;
	
	Die (){
		numSides = 6;
		value = roll();
	}
	
	Die(int n){
		numSides = n;
		value = roll();
	}
	
	/**
	 * Purpose: rolls the dice and generates a number from 1-6 and returns it
	 * @param none
	 * @param the roll value
	 */

	public int roll() {
		Random rand = new Random();
		value = rand.nextInt(1, numSides+1);
		return value;
	}
	
	/**
	 * Purpose: sets the number of sides the dice has
	 * @param number of sides
	 * @param none
	 */
	public void setSides(int n) {
		numSides = n;
	}
}
