package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link BitVectorSet}
 *
 */
public class BitVecSetMatchProvider extends AbstractSetRepMatchProvider<BitVectorSet, long[], Integer> {

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
		long[] testArray;
		// upper limit of possible array length for test set
		int maxLenTest = test.size() / 64 + 1;

		// look for match
		if (candLength <= maxLenTest) {
			// convert test to appropriate bit vector representation
			testArray = getRepresentation(test, candLength);

			// compare vectors
			int i = candLength;
			while (i > 0) {
				i--;
				// check if candidate is larger
				if (Long.compareUnsigned(candArray[i], testArray[i]) == 1) {
					// try next length for representation
					if (candLength < maxLenTest) {
						candLength++;
						// start with empty candidate of increased size
						candArray = new long[candLength];
						// convert test to new bit vector representation
						testArray = getRepresentation(test, candLength);

						long lowTest;
						// go through long values of test array until lowest 1-bit found
						int j = 0;
						do {
							lowTest = Long.lowestOneBit(testArray[j]);
							j++;
						} while (lowTest == 0 && j < candLength);
						// define bit vector of next match by adding new lowest bit taken from test
						candArray[j] = lowTest;
						break;
					} else {
						return null;
					}
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

			// return BitVectorSet representation of next match
			return new BitVectorSet(candArray);
		}
		// if candidate is already larger than the set it cannot be a match
		else {
			return null;
		}

	}

	@Override
	protected long[] convertSet(Set<?> set, Integer attr) {
		// convert set to long[] with length attr
		return new BitVectorSetConverter(attr).convertSet(set);
	}

}
