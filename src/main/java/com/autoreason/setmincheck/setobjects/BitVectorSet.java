package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code long} values based on the
 * {@link #hashCode} of the set's elements
 *
 */
public class BitVectorSet extends AbstractSetRepresent<long[]> implements Comparable<BitVectorSet> {

	/**
	 * Create a {@link BitVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public BitVectorSet(Set<?> set) {
		this.originalSet = set;
		this.setRepresentation = convertSet(set, null);
	}

	/**
	 * Create a {@link BitVectorSet} object based on a {@link Set} and its
	 * {@code long[]} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@code long[]} representing the element {@code set}
	 */
	public BitVectorSet(Set<?> set, long[] bv) {
		this.originalSet = set;
		this.setRepresentation = bv;
	}

	/**
	 * Create a {@link BitVectorSet} object with the given {@code long[]}
	 * representation
	 * 
	 * <p>
	 * Note: The created {@code BitVectorSet} does not contain any original
	 * {@link Set} and, hence, should only be used to serve as reference or
	 * comparison based on the {@code setRepresentation} attribute
	 * </p>
	 * 
	 * @param bv A {@code long[]} representing a {@link Set}
	 */
	public BitVectorSet(long[] bv) {
		this.setRepresentation = bv;
	}

	/**
	 * For the {@link BitVectorSet} implementation, the parameter {@code attr}
	 * defines as {@link Integer} the length of the created {@code long[]}. If
	 * {@code attr} is not defined as {@code Integer}, the length is determined by the
	 * size of {@code set}
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
	public int compareTo(BitVectorSet o) {
		// get bitVector values
		long[] ar1 = this.setRepresentation;
		long[] ar2 = o.setRepresentation;

		// compare length of arrays
		int len1 = ar1.length;
		int c = Integer.compare(len1, ar2.length);
		// compare concrete values if same length
		if (c == 0) {
			int i = len1;
			do {
				i--;
				c = Long.compareUnsigned(ar1[i], ar2[i]);
			} while (c == 0 && i > 0);
		}

		// bitVector elements are equal
		return c;
	}

}
