package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Set;

import com.autoreason.setmincheck.MinimalityChecker;

/**
 * Use the {@link BitVectorSet} representation for {@link Set} objects to check
 * set minimality
 * 
 */
public class BitVectorSet2Checker extends BitVectorSetChecker {

	public BitVectorSet2 getNextMatch(BitVectorSet2 previous, Set<?> test) {
		// get long arrays
		long[] candArray = previous.setRepresentation;
		// convert test to appropriate bit vector representation
		long[] testArray = transform(test, candArray.length);

		// check if next match can be found
		if (previous.compareTo(new BitVectorSet2(testArray)) != -1) {
			// previous is not smaller than test -> no next match possible
			return null;
		}

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
			// define vector where every position after highest bit is zero
			long[] lowRemover = complementOf(subtractOne(high));
			// remove bits from the vector that only contains bits from test
			remainTest = and(lowRemover, and(testArray, xorArray));
		}

		// get the lowest bit from the remaining test bits
		long[] low = getLowestBit(remainTest);
		// define vector where every position after lowest bit is zero
		long[] lowRemover = complementOf(subtractOne(low));
		// define bit vector of next match by adding new lowest bit
		long[] next = and(add(candArray, low), lowRemover);
		// return BitVectorSet representation of test
		return new BitVectorSet2(test, next);

	}

}