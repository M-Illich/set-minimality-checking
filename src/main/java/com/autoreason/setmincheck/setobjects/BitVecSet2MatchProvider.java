package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link BitVectorSet2}
 *
 */
public class BitVecSet2MatchProvider extends BitVecSetMatchProvider {
	
	public BitVectorSet2 getSmallestMatchGreaterOrEqual(BitVectorSet2 current, Set<?> test) {
		// return null if no match can be found
		if(current == null) {
			return null;
		}

		// bit vector representation of candidate
		long[] candArray = current.setRepresentation.clone();
		int candLength = candArray.length;
		// bit vector representation of test
		long[] testArray;
		// bit vector for next match
		long[] next;
		// upper limit of possible array length for test set
		int maxLenTest = test.size() / 64 + 1;

		// look for match
		if (candLength <= maxLenTest) {
			// convert test to appropriate bit vector representation
			testArray = getRepresentation(test, candLength);
			// get comparison value
			int compareValue = current.compareTo(new BitVectorSet2(testArray));
			// check if current is already a match
			if (compareValue == 0) {
				next = candArray;
			} else {
				// check if next match can be found
				if (compareValue == 1) {
					// previous is not smaller than test -> no next match possible
					// try next length for representation
					if (candLength < maxLenTest) {
						candLength++;
						// start with empty candidate of increased size
						candArray = new long[candLength];
						// convert test to new bit vector representation
						testArray = getRepresentation(test, candLength);
						// define bit vector of next match by adding new lowest bit taken from test
						next = add(candArray, getLowestBit(testArray));
					} else {
						return null;
					}

				}
				// current candidate is smaller than test -> compute next match
				else {
					// keep all different bits
					long[] xorArray = xor(testArray, candArray);
					// get bits that only occur in candidate array
					long[] onlyCand = and(xorArray, candArray);
					// initialize array for remaining test bits
					long[] remainTest;
					
					// check if candidate only contains bits from test, i.e., onlyCand is zero
					if (Arrays.equals(onlyCand, new long[onlyCand.length])) {
						// remaining test bits correlate to XOR result
						remainTest = xorArray;
					} else {
						// get highest bit from all bits that only occur in candidate
						long[] high = getHighestBit(onlyCand);
						// get all bits that only appear in test and are higher than high
						remainTest = removeLowBits(and(testArray, xorArray), high);
					}

					// get the lowest bit from the remaining test bits
					long[] low = getLowestBit(remainTest);
					// define bit vector of next match by adding the low bit and removing all
					// foregoing bits
					next = removeLowBits(add(candArray, low), low);

				}
			}

			// return BitVectorSet2 representation of next match
			return new BitVectorSet2(next);
		}
		// if candidate is already larger than the set it cannot be a match
		else {
			return null;
		}

	}

}
