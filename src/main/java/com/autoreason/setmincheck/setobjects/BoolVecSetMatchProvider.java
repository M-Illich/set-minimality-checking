package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link BoolVectorSet}
 *
 */
public class BoolVecSetMatchProvider extends AbstractSetRepMatchProvider<BoolVectorSet, boolean[]> {

	@Override
	public BoolVectorSet getSmallestMatchGreaterOrEqual(BoolVectorSet current, Set<?> test) {
		// get boolean arrays
		boolean[] candArray = current.setRepresentation.clone();
		int candLength = candArray.length;
		// array for test set
		boolean[] testArray;
		// highest position of true value only occurring in candidate
		int highCand;
		// upper limit of possible array length for test set
		int maxLenTest = (test.size() / 64 + 1) * 64;

		// look for next match
		if (candLength <= maxLenTest) {
			// convert test to appropriate boolean vector representation
			testArray = getRepresentation(test, candLength);

			// compare vectors
			int compareValue = 0;
			int i = candLength;
			boolean testGreater = false;
			do {
				i--;
				// check if test has true value at higher position
				if (!testGreater) {
					testGreater = testArray[i];
				}
			} while (i > -1 && (!candArray[i] | testArray[i]));
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
				// check if next match can be found
				if (compareValue == 1) {
					// current is not smaller than test -> no next match possible
					// try next length for representation
					if (candLength < maxLenTest) {
						candLength++;
						// start with empty candidate of increased size
						candArray = new boolean[candLength];
						// candidate does not contain any true value
						highCand = 0;
						// convert test to appropriate boolean vector representation
						testArray = getRepresentation(test, candLength);

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
			}

			return new BoolVectorSet(candArray);

		}
		// if candidate is already larger than the set it cannot be a match
		else {
			return null;
		}
	}

	@Override
	protected boolean[] convertSet(Set<?> set, Object attr) {
		return new BoolVectorSet(new boolean[1]).convertSet(set, attr);
	}

}
