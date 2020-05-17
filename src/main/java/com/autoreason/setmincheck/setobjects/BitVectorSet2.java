package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * An alternative version of {@link BitVectorSet} where the comparison
 * additionally considers the bit count of the long values
 *
 */
public class BitVectorSet2 extends AbstractSetRepresent<long[]> implements Comparable<BitVectorSet2> {

	/**
	 * Create a {@link BitVectorSet2} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public BitVectorSet2(Set<?> set) {
		this.originalSet = set;
		this.setRepresentation = convertSet(set, null);
	}

	/**
	 * Create a {@link BitVectorSet2} object based on a {@link Set} and its
	 * {@code long[]} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@code long[]} representing the element {@code set}
	 */
	public BitVectorSet2(Set<?> set, long[] bv) {
		this.originalSet = set;
		this.setRepresentation = bv;
	}

	/**
	 * Create a {@link BitVectorSet2} object with the given {@code long[]}
	 * representation
	 * 
	 * <p>
	 * Note: The created {@code BitVectorSet2} does not contain any original
	 * {@link Set} and, hence, should only be used to serve as reference or
	 * comparison based on the {@code setRepresentation} attribute
	 * </p>
	 * 
	 * @param bv A {@code long[]} representing a {@link Set}
	 */
	public BitVectorSet2(long[] bv) {
		this.setRepresentation = bv;
	}

	/**
	 * For the {@link BitVectorSet2} implementation, the parameter {@code attr}
	 * defines as {@link Integer} the length of the created {@code long[]}. If
	 * {@code attr} is not defined as {@code Integer}, the length is determined by
	 * the size of {@code set}
	 */
	@Override
	public long[] convertSet(Set<?> set, Object attr) {
		int len;
		if (attr instanceof Integer) {
			// length of array provided by attr
			len = (int) attr;
			if (len < 1) {
				len = 1;
			}
		} else {
			// determine length of array based on set size
			len = set.size() / 64 + 1;
		}
		long[] convertedSet = new long[len];

		// use elements of set to define position of 1-bits
		for (Object e : set) {
			// determine position
			int pos = e.hashCode() % (len * 64);
			// only allow positive values
			if (pos < 0) {
				pos *= -1;
			}
			// set bit in appropriate long value
			convertedSet[pos / 64] |= (long) 1 << pos;
		}

		return convertedSet;
	}

	@Override
	public int compareTo(BitVectorSet2 o) {
		// get bitVector values
		long[] ar1 = this.setRepresentation;
		long[] ar2 = o.setRepresentation;

		// compare length of arrays
		int len1 = ar1.length;
		int len2 = ar2.length;

		// larger BitVectorSet has longer array
		if (len1 < len2)
			return -1;
		if (len1 > len2)
			return 1;

		// arrays have same length -> compare elements at each position of arrays
		for (int i = ar2.length - 1; i >= 0; i--) {
			// get values
			long thisLong = this.setRepresentation[i];
			long oLong = o.setRepresentation[i];
			// use bit count for comparison
			int c = Integer.compare(Long.bitCount(thisLong), Long.bitCount(oLong));
			if (c != 0) {
				return c;
			} else {
				// same bit count -> compare actual value
				c = Long.compareUnsigned(thisLong, oLong);
				if (c != 0) {
					return c;
				}
			}
		}
		// bitVector elements are equal
		return 0;
	}

}
