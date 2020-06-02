package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link SumVectorSet}
 *
 */
public class SumVecSetMatchProvider extends AbstractSetRepMatchProvider<SumVectorSet, long[], Integer> {

	public SumVectorSet getSmallestMatchGreaterOrEqual(SumVectorSet current, Set<?> test) {
		// return null if no match can be found
		if (current == null) {
			return null;
		}

		// sum vector representation of current candidate
		long[] candArray = current.setRepresentation.clone();
		int candLength = candArray.length;
		// sum vector representation of test
		long[] testArray = getRepresentation(test, 0);

		// look for match
		if (candLength <= testArray.length) {
			// look if any candidate sum is larger than the related test value
			int i = 0;
			while (candArray[i] <= testArray[i]) {
				i++;
				// all candidate values are smaller or equal
				if (i == candLength) {
					// current candidate is a match of test
					return new SumVectorSet(candArray);
				}
			}
			// determine index of value that has to be adjusted to define the next match
			do {
				i--;
			} while (i > 0 && candArray[i] == testArray[i]);
			if (i <= 0) {
				// no greater or equal match available
				return null;
			} else {
				// increase the sum being strictly smaller than the related test value at the
				// closest position to where the candidate value was greater
				candArray[i]++;
				// reset entries after adapted position
				for (int j = i + 1; j < candLength; j++) {
					candArray[j] = 0;
				}
			}

			// return SumVectorSet representation of next match
			return new SumVectorSet(candArray);
		}
		// if candidate is already larger than the set, there is no next match
		else {
			return null;
		}

	}

	@Override
	protected long[] convertSet(Set<?> set, Integer attr) {
		// convert set to long[] with sums
		return new SumVectorSetConverter().convertSet(set);
	}

}
