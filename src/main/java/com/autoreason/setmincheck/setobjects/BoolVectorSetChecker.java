package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

import com.autoreason.setmincheck.MinimalityChecker;

public class BoolVectorSetChecker extends MinimalityChecker<BoolVectorSet, Set<?>> {

	// hash table to store different BoolVectorSet representations, i.e. boolean[]
	// of different lengths, of tested sets
	Hashtable<String, boolean[]> hashtable = new Hashtable<String, boolean[]>();

	@Override
	public BoolVectorSet getNextMatch(BoolVectorSet previous, Set<?> test) {
		// get boolean arrays
		boolean[] candArray = previous.setRepresentation.clone();
		int candLength = candArray.length;
		// convert test to appropriate boolean vector representation
		boolean[] testArray = transform(test, candLength);

		// check if next match can be found
		if (previous.compareTo(new BoolVectorSet(testArray)) != -1) {
			// previous is not smaller than test -> no next match possible
			return null;
		}
		
		// determine highest position of true value only occurring in candidate
		int highCand = candLength;
		while((highCand > 0) && (testArray[highCand] | !candArray[highCand])) {
			highCand--;
		}
		// get lowest position of true value in test that is higher than highCand
		int lowTest = highCand + 1;
		while((lowTest < testArray.length) && (!testArray[lowTest] | candArray[lowTest])) {
			lowTest++;
		}
		// set value in candidate at this position to true
		candArray[lowTest] = true;
		// set all the lower positions to false
		Arrays.fill(candArray, 0, lowTest, false);
				
		return new BoolVectorSet(candArray);
	}

	@Override
	public boolean matches(BoolVectorSet candidate, Set<?> test) {
		// handle null
		if (candidate == null || test == null) {
			return false;
		}
		// get boolean arrays
		boolean[] candArray = candidate.setRepresentation;
		// convert test to appropriate BoolVectorSet representation
		boolean[] testArray = transform(test, candArray.length);

		// subset candidate can only have 'true' entries at the same positions like
		// testArray
		return Arrays.compare(candArray, testArray) < 1;
	}

	@Override
	protected boolean subsetOf(BoolVectorSet cand, Set<?> test) {
		// all elements of candidate's original set have to occur in test set
		return test.containsAll(cand.set);
	}

	/**
	 * Transform a {@link Set} element into a {@code boolean[]} of given length
	 * 
	 * @param set    A {@link Set}
	 * @param length An {@code int} that determines the length of the
	 *               {@code boolean[]}
	 * @return A {@code boolean[]} that contains a bit vector representing the
	 *         elements of {@code set}
	 */
	boolean[] transform(Set<?> set, int length) {

		// look for long array representation of given length in hash table
		String key = defineHashKey(set, length);
		boolean[] bv = hashtable.get(key);

		// no element found -> create new one
		if (bv == null) {
			// create bit vector of given length
			bv = new boolean[length];

			// use elements of set to define position of true values
			for (Object e : set) {
				// set true value in appropriate position
				bv[e.hashCode() % length] = true;
			}
			// add representation to hash table
			hashtable.put(key, bv);
		}

		// return boolean vector representing the set
		return bv;
	}

	/**
	 * Define a {@link String} that serves as key for a hash table
	 * 
	 * @param set A {@link Set}
	 * @param i   An {@link int} value
	 * @return A {@link String} based on the hashcode of {@code set} appended by the
	 *         space-separated value {@code i}
	 */
	private String defineHashKey(Set<?> set, int i) {
		// use hash code of set together with the given int value as key
		// TODO hashcode()
		return set.size() + " " + i;
	}

}
