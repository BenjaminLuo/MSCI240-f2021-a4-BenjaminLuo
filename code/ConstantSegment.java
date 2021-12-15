import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConstantSegment {
	
	public static void main(String[] args) {
	
//		System.out.println("Test");
		int[] arr = {1,2,3,1,2,3,1,2,3,1,2,3};
		int a = sendSomeHome(12, 3, 3, arr, 0);
		System.out.println(a);
		
	}
	
	
	/*
	 * N: Number of robots
	 * K: Number of consecutive robots required
	 * M: Number of problem types
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
		
		// Step 1: Copying P[] to an ArrayList
		// Step 2: Create problemCount object for each unique problem type
		ArrayList<problemCount> problems = new ArrayList<problemCount>();
		for (int i = 0; i < P.length; i++) {
			problemCount temp = new problemCount(P[i]);
			if (problems.contains(temp)) {
				problems.get(problems.indexOf(temp)).increaseCount();
			} else {
				problems.add(temp);
			}
		}
				
		// Step 3: Eliminating problem types with .count < K		
		// Step 4: Copying ArrayList to a PriorityQueue
		ArrayList<problemCount> feeder = new ArrayList<problemCount>();
		
		for (problemCount i : problems) 
			if (i.getCount() >= K) feeder.add(i);
		
		
		ArrayList<ArrayMap> sortThis = new ArrayList<ArrayMap>();
		for (int i = 0; i < P.length; i++) 
			sortThis.add(new ArrayMap(P[i], i));
		
		Collections.sort(sortThis, new arraySort());
		
		int min = 0;
		int record;
		for (int i = 0; i < sortThis.size() - 1; i++) {
			if (sortThis.get(i).value == sortThis.get(i+1).value) {
				min++;
			}
		}
		
		return 0;
	}
	
	// Custom comparator to sort Queue by descending degrees
	public static class arraySort implements Comparator<ArrayMap> {
		@Override // Checks which object's score is higher, then moves accordingly 
		public int compare(ArrayMap a, ArrayMap b) {
			return a.value > b.value ? -1 : a.value < b.value ? +1 : 0;
		}
	}
	
}
