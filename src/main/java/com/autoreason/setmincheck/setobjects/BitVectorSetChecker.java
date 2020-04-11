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
public class BitVectorSetChecker extends MinimalityChecker<BitVectorSet, Set<?>> {

	// hash table to store different BitVectorSet representations, i.e. long[] of
	// different lengths, of tested sets
	Hashtable<String, long[]> hashtable = new Hashtable<String, long[]>();

	@Override
	protected boolean subsetOf(BitVectorSet cand, Set<?> test) {
		// all elements of candidate's original set have to occur in test set
		return test.containsAll(cand.set);
	}

	@Override
	public BitVectorSet getNextMatch(BitVectorSet previous, Set<?> test) {
		// get long arrays
		long[] candArray = previous.setRepresentation;
		// convert test to appropriate bit vector representation
		long[] testArray = transform(test, candArray.length);

		// check if next match can be found
		if (previous.compareTo(new BitVectorSet(testArray)) != -1) {
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
		return new BitVectorSet(test, next);

	}

	/**
	 * Get a {@code long} array which contains the lowest 1-bit of {@code bitVector}
	 * as rightmost 1-bit entry
	 * 
	 * @param bitVector A {@code long[]}
	 * @return A {@code long[]} which contains the lowest 1-bit of {@code bitVector}
	 *         as only 1-bit entry, or {@code long} entries with value zero if no
	 *         bit could be found
	 */
	long[] getLowestBit(long[] bitVector) {

		int len = bitVector.length;
		long lowBit = 0;
		int i = 0;
		// go through long values of array until lowest 1-bit found
		do {
			lowBit = Long.lowestOneBit(bitVector[i]);
			i++;
		} while (lowBit == 0 && i < len);

		// transfer lowBit to long array
		long[] lowArray = new long[i];
		lowArray[i - 1] = lowBit;
		return lowArray;
	}

	/**
	 * Get a {@code long} array which contains the highest 1-bit of
	 * {@code bitVector} as only 1-bit entry
	 * 
	 * @param bitVector A {@code long[]}
	 * @return A {@code long[]} which contains the highest 1-bit of
	 *         {@code bitVector} as only 1-bit entry, or {@code long} entries with
	 *         value zero if no bit could be found
	 */
	long[] getHighestBit(long[] bitVector) {

		long highBit = 0;
		int i = bitVector.length - 1;
		// go through long values of array until highest 1-bit found
		while (highBit == 0 && i >= 0) {
			highBit = Long.highestOneBit(bitVector[i]);
			i--;
		}

		// transfer highBit to long array
		long[] highArray = new long[i + 2];
		highArray[i + 1] = highBit;
		return highArray;
	}

	/**
	 * Compute the sum of the values of each position of two provided {@code long[]}
	 * objects
	 * 
	 * @param a A {@code long[]}
	 * @param b A {@code long[]}
	 * @return A {@code long[]} that contains the sum of the values for each
	 *         position of {@code a} and {@code b}
	 * 
	 */
	long[] add(long[] a, long[] b) {

		int len = a.length;
		int lenB = b.length;

		// create new array containing the long values as sum
		long[] ab;
		if (len > lenB) {
			len = lenB;
			ab = a.clone();
			// smaller one is added to bigger array (see below)
			a = b.clone();
		} else {
			ab = b.clone();
		}

		// create sum for each array position
		for (int i = 0; i < len; i++) {
			ab[i] += a[i];
		}
		return ab;
	}

	/**
	 * Subtract 1 from the value defined by the bit vector that results from the
	 * appending of the {@code long} values of the given array
	 * 
	 * @param arr A {@code long[]}
	 * @return A {@code long[]} whose elements define a value being 1 smaller than
	 *         the value given by the {@code long} values of {@code arr}
	 */
	long[] subtractOne(long[] arr) {
		// define new array
		long[] sub = arr.clone();
		int i = 0;
		long val = 0;
		// subtract 1 from values starting at first array entry until one != 0 is found
		do {
			val = sub[i];
			sub[i] = val - 1;
			i++;
		} while (val == 0 && i < sub.length);

		return sub;
	}

	/**
	 * Compute complement of {@code long} values given in an array
	 * 
	 * @param arr A {@code long[]}
	 * @return A {@code long[]} containing the complement values of the given array
	 *         {@code a}
	 */
	long[] complementOf(long[] arr) {
		int len = arr.length;
		// create new array containing the complement long values
		long[] ac = new long[len];
		for (int i = 0; i < len; i++) {
			ac[i] = ~arr[i];
		}
		return ac;
	}

	/**
	 * Perform bitwise AND operation on the values of each position of the provided
	 * long arrays
	 * 
	 * @param a A {@code long[]}
	 * @param b A {@code long[]}
	 * @return A {@code long[]} with values computed by bitwise AND operations of
	 *         the arrays' {@code long} values
	 */
	long[] and(long[] a, long[] b) {

		int len = a.length;
		int lenB = b.length;

		// create new array containing the long values as sum
		long[] ab;
		if (len > lenB) {
			len = lenB;
			ab = a.clone();
			// smaller one is added to bigger array (see below)
			a = b.clone();
		} else {
			ab = b.clone();
		}

		// compute bitwise AND for each array position
		for (int i = 0; i < len; i++) {
			ab[i] &= a[i];
		}
		return ab;
	}

	/**
	 * Perform bitwise XOR operation on the values of each position of the provided
	 * long arrays
	 * 
	 * @param a A {@code long[]}
	 * @param b A {@code long[]}
	 * @return A {@code long[]} with values computed by bitwise XOR operations of
	 *         the arrays' {@code long} values
	 */
	long[] xor(long[] a, long[] b) {

		int len = a.length;
		int lenB = b.length;

		// create new array containing the long values as sum
		long[] ab;
		if (len > lenB) {
			len = lenB;
			ab = a.clone();
			// smaller one is added to bigger array (see below)
			a = b.clone();
		} else {
			ab = b.clone();
		}

		// compute bitwise XOR for each array position
		for (int i = 0; i < len; i++) {
			ab[i] ^= a[i];
		}
		return ab;
	}

	/**
	 * For set minimality checking, a match refers to a subset relation, which means
	 * that {@code candidate} must be a subset candidate of {@code test}
	 */
	@Override
	public boolean matches(BitVectorSet candidate, Set<?> test) {
		// handle null
		if (candidate == null || test == null) {
			return false;
		}
		// get long arrays
		long[] candArray = candidate.setRepresentation;
		// convert test to appropriate BitVectorSet representation
		long[] testArray = transform(test, candArray.length);

		// compare long values
		for (int i = 0; i < candArray.length; i++) {
			// subset can only have 1-bits at same positions as the superset bit vector
			if ((testArray[i] | candArray[i]) != testArray[i]) {
				// no subset candidate
				return false;
			}
		}
		// subset candidate confirmed
		return true;
	}

	/**
	 * Transform a {@link Set} element into a {@code long[]} of given length
	 * 
	 * @param set    A {@link Set}
	 * @param length An {@code int} that determines the length of the {@code long[]}
	 * @return A {@code long[]} that contains a bit vector representing the elements
	 *         of {@code set}
	 */
	long[] transform(Set<?> set, int length) {

		// look for long array representation of given length in hash table
		String key = defineHashKey(set, length);
		long[] bv = hashtable.get(key);

		// no element found -> create new one
		if (bv == null) {
			// create bit vector of given length
			bv = new long[length];

			/// use elements of set to define position of 1-bits
			for (Object e : set) {
				// determine position based on hash code
				int pos = e.hashCode() % (length * 64);
				// set bit in appropriate long value
				bv[pos / 64] |= (long) 1 << pos;
			}
			// add representation to hash table
			hashtable.put(key, bv);
		}

		// return bit vector representing the set
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
		return set.hashCode() + " " + i;
	}

}
