package Zheng.Kobe;


/**
* Purpose: Manages the state and holds a value for tiles
* @author Kobe Zheng
* @date Dec 20, 2024
*/
public class Tile {
	private int value;
	private boolean isUp;
	private boolean selected;
	
	Tile(int n){
		value = n;
		isUp = true;
		selected = false;
	}
	
	/**
	 * Purpose: Gets the value of the tile
	 * @param none
	 * @param return the tiles value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Purpose: checks if the tile in up/available
	 * @param none
	 * @param return a boolean if it is up or not
	 */
	public boolean isUp() {
		return isUp;
	}
	
	/**
	 * Purpose: puts the tile down
	 * @param none
	 * @param none
	 */
	public void putDown() {
		isUp = false;
	}
	/**
	 * Purpose: puts the tile up
	 * @param none
	 * @param none
	 */
	public void putUp() {
		isUp = true;
	}
	
	/**
	 * Purpose: checks if the user has clicked/selected the tile
	 * @param none
	 * @param if the tile is selected
	 */
	public boolean selected() {
		return selected;
	}
	
	/**
	 * Purpose: makes the tile selected
	 * @param none
	 * @param none
	 */
	public void select() {
		selected = true;
	}
	
	/**
	 * Purpose: unselects the tile
	 * @param none
	 * @param none
	 */
	public void deselect() {
		selected = false;
	}

	
	@Override
	public String toString() {
		String response = "";
		if (isUp) {
			response = "" + value + "(U) ";
		}
		else {
			response = "" + value + "(D) ";			
		}
		return response;
	}
}
