package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link BitVectorSet}
 *
 */
public class BitVecSetMatchProvider extends AbstractSetRepMatchProvider<BitVectorSet, long[]> {

	public BitVecSetMatchProvider(Set<?> test) {
		testRepresent = new BitVectorSetConverter().convertSet(test);
	}

	@Override
	public BitVectorSet getSmallestMatchGreaterOrEqual(BitVectorSet current, Set<?> test) {
		// return null if no match can be found
		if (current == null) {
			return null;
		}

		// bit vector representation of candidate
		long[] candArray = current.setRepresentation.clone();
		int candLength = candArray.length;
		// bit vector representation of test
		long[] testArray = testRepresent.clone();

		// compare vectors
		int i = candLength;
		while (i > 0) {
			i--;
			// check if candidate is larger
			if (Long.compareUnsigned(candArray[i], testArray[i]) == 1) {
				return null;
			}

			// check if candidate is a match of test
			// subset can only have 1-bits at same positions as the superset bit vector
			if ((testArray[i] | candArray[i]) != testArray[i]) {
				// get different bits
				long xorCT = candArray[i] ^ testArray[i];
				// get highest bit only occurring in candidate
				long highCand = Long.highestOneBit(candArray[i] & xorCT);
				// get lowest bit only occurring in test and being greater than highCand
				long lowTest = Long.lowestOneBit(~(highCand - 1) & testArray[i] & xorCT);

				// consider next test long value if lowTest not found yet
				while (lowTest == 0 && i < (candLength - 1)) {
					i++;
					lowTest = Long.lowestOneBit(testArray[i] & (candArray[i] ^ testArray[i]));
				}

				// add lowTest to candidate
				candArray[i] += lowTest;
				// remove all bits that occur in the candidate vector before the new set bit
				candArray[i] &= ~(lowTest - 1);
				for (int j = i - 1; j >= 0; j--) {
					candArray[j] = 0;
				}
				break;
			}

		}

		// return BitVectorSet representation of next match (with set of previous one to
		// distinguish between instances with equal set representations)
		return new BitVectorSet(current.originalSet, candArray);

	}

}
