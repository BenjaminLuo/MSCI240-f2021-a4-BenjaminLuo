// Benjamin Luo
// 2021.12.14
// MSCI240: Data Structures and Algorithms - Final Project
// problemCount.java - Helper class 

/* 
 * Desc: ArrayMap object map will map an Array into value and position
 *	 .value: The value of the array element
 *	 .position: The position of the corresponding array element
*/
public class ArrayMap {
	private int value;
	private int position;
	
	// Constructor
	public ArrayMap(int value, int position) {
		this.value = value;
		this.position = position;
	}
	
	// Getters
	public int getValue() {return this.value;}
	public int getPosition() {return this.position;}
	
}
