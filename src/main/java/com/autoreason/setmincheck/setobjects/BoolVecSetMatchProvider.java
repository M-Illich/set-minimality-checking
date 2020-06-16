package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link BoolVectorSet}
 *
 */
public class BoolVecSetMatchProvider extends AbstractSetRepMatchProvider<BoolVectorSet, boolean[]> {

	public BoolVecSetMatchProvider(Set<?> test) {
		testRepresent = new BoolVectorSetConverter().convertSet(test);
	}

	@Override
	public BoolVectorSet getSmallestMatchGreaterOrEqual(BoolVectorSet current, Set<?> test) {
		// return null if no match can be found
		if (current == null) {
			return null;
		}

		// get boolean arrays
		boolean[] candArray = current.setRepresentation.clone();
		int candLength = candArray.length;
		// array for test set
		boolean[] testArray = testRepresent.clone();
		// highest position of true value only occurring in candidate
		int highCand;

		// compare vectors
		int compareValue = 0;
		int i = candLength - 1;
		boolean testGreater = false;
		while (i >= 0 && (!candArray[i] | testArray[i])) {
			// check if test has true value at higher position
			if (!testGreater) {
				testGreater = testArray[i] & !candArray[i];
			}
			i--;
		}
		if (i > -1) {
			// non-matching entry found -> check if candidate is smaller or greater
			if (testGreater) {
				compareValue = -1;
			} else {
				compareValue = 1;
			}
		}

		// check if current is already a match
		if (compareValue != 0) {
			// candidate is larger
			if (compareValue == 1) {
				// next matching not available
				return null;

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
			while ((lowTest < (candLength - 1)) && (!testArray[lowTest] | candArray[lowTest])) {
				lowTest++;
			}

			// set value in candidate at this position to true
			if (lowTest >= candLength) {
				lowTest = candLength - 1;
			}
			candArray[lowTest] = true;
			// set all the lower positions to false
			for (int j = 0; j < lowTest; j++) {
				candArray[j] = false;
			}
		}
		// return BoolVectorSet representation of next match (with set of previous one
		// to distinguish between instances with equal set representations)
		return new BoolVectorSet(current.originalSet, candArray);

	}

}
