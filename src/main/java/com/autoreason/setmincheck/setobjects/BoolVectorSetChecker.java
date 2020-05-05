package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import com.autoreason.setmincheck.MinimalityChecker;

public class BoolVectorSetChecker extends MinimalityChecker<BoolVectorSet> {

	// hash table to store different BoolVectorSet representations, i.e. boolean[]
	// of different lengths, of tested sets
	HashMap<Integer, boolean[]> hashtable = new HashMap<Integer, boolean[]>();

	@Override
	public BoolVectorSet getNextMatch(BoolVectorSet previous, Set<?> test) {
		// get boolean arrays
		boolean[] candArray = previous.setRepresentation.clone();
		int candLength = candArray.length;
		// array for test set
		boolean[] testArray;
		// highest position of true value only occurring in candidate
		int highCand;

		// look for next match
		if (candLength <= test.size()) {
			// convert test to appropriate boolean vector representation
			testArray = transform(test, candLength);

			// check if next match can be found
			if ((previous.compareTo(new BoolVectorSet(testArray)) > -1)) {
				// previous is not smaller than test -> no next match possible
				// try next length for representation
				if (candLength < test.size()) {
					candLength++;
					// start with empty candidate of increased size
					candArray = new boolean[candLength];
					// candidate does not contain any true value
					highCand = 0;
					// convert test to appropriate boolean vector representation
					testArray = transform(test, candLength);

				} else {
					return null;
				}

			} else {
				// determine highest position of true value only occurring in candidate
				highCand = candLength - 1;
				while ((highCand > 0) && (testArray[highCand] | !candArray[highCand])) {
					highCand--;
				}
			}

			// get lowest position of true value in test that is higher than highCand
			int lowTest = highCand + 1;
			if (highCand == 0) {
				// start from 0 if candidate only contains true values at same positions like
				// test
				lowTest = 0;
			}
			while ((lowTest < candLength) && (!testArray[lowTest] | candArray[lowTest])) {
				lowTest++;
			}

			// set value in candidate at this position to true
			candArray[lowTest] = true;
			// set all the lower positions to false
			Arrays.fill(candArray, 0, lowTest, false);

			return new BoolVectorSet(candArray);

		}
		// if candidate is already larger than the set it cannot be a match
		else {
			return null;
		}

	}

	@Override
	public boolean matches(BoolVectorSet candidate, Set<?> test) {
		// handle null
		if (candidate == null || test == null) {
			return false;
		}
		// get boolean arrays
		boolean[] candArray = candidate.setRepresentation;
		// candidate cannot be larger than test
		int candLength = candArray.length;
		if (candLength > test.size()) {
			return false;
		} else {
			// convert test to appropriate BoolVectorSet representation
			boolean[] testArray = transform(test, candLength);

			// subset candidate can only have 'true' entries at the same positions like
			// testArray
			int i = candLength;
			do {
				i--;
			} while (i > -1 && (!candArray[i] | testArray[i]));
			// if i == 0, no conflicting position was found
			return i == -1;
		}

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
		int key = defineHashKey(set, length);
		boolean[] bv = hashtable.get(key);

		// no element found -> create new one
		if (bv == null) {
			// create bit vector of given length
			bv = new boolean[length];

			// use elements of set to define position of true values
			for (Object e : set) {
				int hashcode = e.hashCode();
				// only allow positive values
				if (hashcode < 0) {
					hashcode *= -1;
				}
				// set true value in appropriate position
				bv[hashcode % length] = true;
			}
			// add representation to hash table
			hashtable.put(key, bv);
		}

		// return boolean vector representing the set
		return bv;
	}

	/**
	 * Define an {@link int} value that serves as key for a hash table
	 * 
	 * @param set A {@link Set}
	 * @param i   An {@link int} value
	 * @return An {@link int}
	 */
	private int defineHashKey(Set<?> set, int i) {
		// Here, the key is directly defined as i which stands for the length of the set
		// representation, since the transform method is only called for the static test
		// set
		return i;
	}

}
