// Benjamin Luo
// 2021.12.13
// MSCI240: Data Structures and Algorithms - Final Project
// problemCount.java - Helper class 

import java.util.Objects;

/* 
 * Desc: problemCount object tracks information on each unique problem type:
 *	 .problem: The specific problem type
 *	 .count: The frequency of the given problem type
 *	 .removed: The minimum number of removals to achieve K consecutive robots
*/
public class problemCount {
	private int problem;
	private int count = 0;
	private int removed = 0;
	
	// Constructor
	public problemCount(int problem) {
		this.problem = problem;
	}
	
	// Getters
	public int getProblem() {return this.problem;}
	public int getCount() {return this.count;}
	public int getRemoved() {return this.removed;}
	
	// Modifiers
	public void increaseCount() {this.count++;}
	public void setRemoved(int val) {this.removed = val;}
	
	// Checking for equality
	// Used by Collections.contains()
	// Copied and Modified from A3 TweetCount class
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		
		// null check
		if (other == null)
		    return false;
		
		// type check and cast
		if (getClass() != other.getClass())
		    return false;
		
		problemCount count = (problemCount) other;
		
		// field comparison (only check degree for equality)
		return Objects.equals(problem, count.problem);
	}
	
}
