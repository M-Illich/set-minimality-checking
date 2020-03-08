package com.autoreason.setmincheck.setobjects;

import java.util.Set;
import com.autoreason.setmincheck.SetMinimalityChecker;


public class BitVectorSetChecker extends SetMinimalityChecker<BitVectorSet> {

	@Override
	public BitVectorSet getNextCandidate(BitVectorSet previous, BitVectorSet test) {
		// get long arrays
		long[] arrayTest = test.bitVector;
		long[] arrayPre = previous.bitVector;

		// get lowest bit not occurring in current candidate
		long[] low = getLowestOneBit(xorKeep(arrayPre, arrayTest));
		// add lowest bit to current candidate
		long[] sum = add(arrayPre, low);
		// remove all bits from current candidate that are smaller than lowest bit
		long[] comp = complement(subtractOne(low));
		long[] bitVector = andRemove(comp, sum);

		return new BitVectorSet(bitVector);
	}

	/**
	 * Get a long array which contains the lowest 1-bit of bitVector as rightmost
	 * 1-bit entry
	 * 
	 * @param bitVector A long[]
	 * @return A long[] which contains the lowest 1-bit of bitVector as rightmost
	 *         1-bit entry, or long values with value zero if no bit could be found
	 */
	long[] getLowestOneBit(long[] bitVector) {

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
	 * Perform bitwise XOR operation on the values of each position of the provided
	 * long[] a and b where only the 1-bits of the larger array are kept
	 * 
	 * @param a A long[]
	 * @param b A long[]
	 * @return A long[] of length = max(a.length,b.length) with long values computed
	 *         by bitwise XOR operations of the arrays' long values where only the
	 *         1-bits of the larger array are kept
	 */
	long[] xorKeep(long[] a, long[] b) {
		int lenA = a.length;
		int lenB = b.length;

		// assume that array a has minimum length
		int min = lenA;
		long[] y = a.clone();
		// create new array containing the long values as sum
		long[] ab = b.clone();
		// adapt initialization if necessary
		if (lenA > lenB) {
			min = lenB;
			ab = a.clone();
			y = b.clone();
		}

		// create XOR for each array position but only keep 1-bit if it occurs in ab and
		// not in y
		for (int i = 0; i < min; i++) {
			long x = ab[i] ^ y[i];
			ab[i] &= x;

		}
		return ab;
	}
	
	/**
	 * Compute the sum of the values of each position of the provided long[] a and
	 * b.
	 * 
	 * @param a A long[]
	 * @param b A long[]
	 * @return A long[] of length = max(a.length,b.length) with long values as sum
	 *         of the arrays a and b.
	 */
	long[] add(long[] a, long[] b) {

		int lenA = a.length;
		int lenB = b.length;

		// assume that array a has minimum length
		int min = lenA;
		long[] y = a.clone();
		// create new array containing the long values as sum
		long[] ab = b.clone();
		// adapt initialization if necessary
		if (lenA > lenB) {
			min = lenB;
			ab = a.clone();
			y = b.clone();
		}

		// create sum for each array position
		for (int i = 0; i < min; i++) {
			ab[i] += y[i];
		}

		return ab;
	}

	/**
	 * Compute complement of long values given in long array a
	 * 
	 * @param a A long[]
	 * @return A long[] containing the complement values of the given array a
	 */
	long[] complement(long[] a) {
		int len = a.length;
		// create new array containing the complement long values
		long[] ab = new long[len];
		for (int i = 0; i < len; i++) {
			ab[i] = ~a[i];
		}
		return ab;
	}

	/**
	 * Subtract 1 from the value defined by the appending of the long values of
	 * array
	 * 
	 * @param array A long[]
	 * @return A long[] whose elements define a value being 1 smaller than the value
	 *         given by the long values of array
	 */
	long[] subtractOne(long[] array) {
		// define new array
		long[] sub = array.clone();
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
	 * Perform bitwise AND operation on the values of each position of the provided
	 * long[] a and b. (Mainly used to remove bit entries)
	 * 
	 * @param a A long[]
	 * @param b A long[]
	 * @return A long[] of length = max(a.length,b.length) with long values computed
	 *         by bitwise AND operations of the arrays' long values
	 */
	long[] andRemove(long[] a, long[] b) {
		int lenA = a.length;
		int lenB = b.length;

		// assume that array a has minimum length
		int min = lenA;
		long[] y = a.clone();
		// create new array containing the long values as sum
		long[] ab = b.clone();
		// adapt initialization if necessary
		if (lenA > lenB) {
			min = lenB;
			ab = a.clone();
			y = b.clone();
		}

		// create AND for each array position
		for (int i = 0; i < min; i++) {
			ab[i] &= y[i];
		}

		return ab;
	}
	

	/**
	 * Here, the candidate relation is defined as first being a subset candidate of
	 * second based on their bitVector long values
	 */
	@Override
	public boolean isCandidateOf(BitVectorSet first, BitVectorSet second) {
		// get long arrays
		long[] arrayFirst = first.bitVector;
		long[] arraySecond = second.bitVector;

		// compare long values
		for (int i = 0; i < arrayFirst.length; i++) {
			// subset can only have 1-bits at same positions as the superset bit vector
			if ((arraySecond[i] | arrayFirst[i]) != arraySecond[i]) {
				// no subset candidate
				return false;
			}
		}
		// candidate confirmed
		return true;
	}

	@Override
	public BitVectorSet convert(BitVectorSet example, Set<?> set) {
		// create bit vector of same length as the one of example
		int len = example.bitVector.length;
		long[] bv = new long[len];

		/// use elements of set to define position of 1-bits
		for (Object e : set) {
			// determine position
			int pos = e.hashCode() % (len * 64);
			// set bit in appropriate long value
			bv[pos / 64] |= (long) 1 << pos;
		}
		// return BitVectorSet representing set with same length as given example
		return new BitVectorSet(set, bv);
	}

	@Override
	public boolean isSubsetOf(BitVectorSet first, BitVectorSet second) {
		// get long arrays
		long[] arrayFirst = first.bitVector;
		long[] arraySecond = second.bitVector;

		// compare long values
		for (int i = 0; i < arrayFirst.length; i++) {
			// subset can only have 1-bits at same positions as the superset bit vector
			if ((arraySecond[i] | arrayFirst[i]) != arraySecond[i]) {
				// no subset candidate
				return false;
			}
		}
		// subset candidate confirmed -> perform explicit subset check with original
		// sets
		return second.set.containsAll(first.set);

	}

	@Override
	public BitVectorSet getMaxValue(BitVectorSet o) {
		// create bit vector of same length as the one of o
		int len = o.bitVector.length;
		long[] bv = new long[len];
		// set all long values to maximum
		for (int i = 0; i < bv.length; i++) {
			bv[i] = Long.MAX_VALUE;
		}
		return new BitVectorSet(bv);
	}

}
