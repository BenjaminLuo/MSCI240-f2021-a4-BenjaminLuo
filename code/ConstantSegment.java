// Benjamin Luo
// 21.12.14
// MSCI240: Data Structures and Algorithms - Final Project
// ConstantSegment challenge from TopCoder:
// 		https://community.topcoder.com/stat?c=problem_statement&pm=17008

// Desc: Returns the minimum number of robots that need to be removed from a line
// 		of robots to acheive 'K' consecutive robots with the same problem type

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ConstantSegment {
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,1,2,3,1,2,3,1,2,3};
		int a = sendSomeHome(12, 3, 3, arr, 0);
		System.out.println(a);
		
	}
	
	
	/*
	 * N: Number of robots in the line
	 * K: Minimum number of consecutive robots required
	 * M: Range of problem types
	 * Pprefix: Array of each robot's problem. Length is N
	 * seed: Designation for creating a specific set of Pprefix
	 *		 Set to '0' to directly use the Pprefix array
	 */
	public static int sendSomeHome(int N, int K, int M, int[] Pprefix, int seed) {
		
	// Provided code to generate a randomized Pprefix array based on a 'seed'
	// Array P[int] now stores the array of problem types
		int[] P = new int[N];
		int L = Pprefix.length;
		
		for (int i=0; i<L; ++i) P[i] = Pprefix[i]; 
		long state = seed;
		
		for (int i=L; i<N; ++i) {
		    state = (state * 1103515245 + 12345) % (1L << 31);
		    P[i] = (int)((state / 16) % M);
		}

		
	// 'problems' ArrayList stores problemCount objects for each problem type
	// Traverse and add to 'problems' if the problem type is not already contained
	// 		though, if it already exists, then increment the '.count' field 
		ArrayList<problemCount> problems = new ArrayList<problemCount>();
		for (int i = 0; i < P.length; i++) {
			problemCount temp = new problemCount(P[i]);
			if (problems.contains(temp)) {
				problems.get(problems.indexOf(temp)).increaseCount();
			} else {
				problems.add(temp);
			}
		}
		
				
	// 'feeder' ArrayList is a copy of 'problems' after eliminating problem types with .count < K
	// 'sortingQueue PriorityQueue is a copy of 'feeder' after determining dismissals at each problem
	// 		ascending() comparator sorts by ascending dismissals
		ArrayList<problemCount> feeder = new ArrayList<problemCount>();
		PriorityQueue<problemCount> sortingQueue = new PriorityQueue<problemCount>(new ascending());
		
		
	// Copying ArrayList to a PriorityQueue
	// Eliminating problem types with .count < K 
		for (problemCount i : problems) 
			if (i.getCount() >= K) feeder.add(i);
		
		
	// 'sortThis' ArrayList contains an ArrayMap(value, position) of each element in the given array
	//		It's then sorted by ascending value rather than the default ascending position
		ArrayList<ArrayMap> sortThis = new ArrayList<ArrayMap>();
		for (int i = 0; i < P.length; i++) 
			sortThis.add(new ArrayMap(P[i], i));
		
		Collections.sort(sortThis, new arraySort());
		
		
	// Transfer elements from 'feeder' to 'sortingQueue' after determining dismissals at each problem
	// Since 'feeder' is sorted by ascending dismissals, return the front of the queue 
		for (problemCount i : feeder) 
			sortingQueue.add(minDismissals(i.getProblem(), sortThis, K));
		
		return sortingQueue.poll().getRemoved();
	}
	
	
	
	/* 
	 * minDismissals() determines dismissals needed for a given problem type
	 * value: A given problem type
	 * arr: The sorted ArrayList of ArrayMap(value, position) objects
	 * K: The minimum number of consecutive robots required
	 */
	public static problemCount minDismissals(int value, ArrayList<ArrayMap> arr, int K) {
		
	// pos ArrayList stores the positions of the given problem type in the original array
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for (ArrayMap i : arr) 
			if (i.getValue() == value) pos.add(i.getPosition());
		
	// Creating a problemCount object to return, with the '.removed' field being the dismissals		
		problemCount temp = new problemCount(value);
		temp.setRemoved(pos.get(K-1) - pos.get(0) - K + 1);
		
		return temp;
	}
	
	
	
	// Custom comparator to sort Queue by ascending dismissals
	public static class ascending implements Comparator<problemCount> {
		@Override 
		public int compare(problemCount a, problemCount b) {
			return a.getRemoved() < b.getRemoved() ? -1 : a.getRemoved() > b.getRemoved() ? +1 : 0;
		}
	}
	
	// Custom comparator to sort ArrayList by ascending value
	public static class arraySort implements Comparator<ArrayMap> {
		@Override 
		public int compare(ArrayMap a, ArrayMap b) {
			return a.getValue() > b.getValue() ? -1 : a.getValue() < b.getValue() ? +1 : 0;
		}
	}
	
}
