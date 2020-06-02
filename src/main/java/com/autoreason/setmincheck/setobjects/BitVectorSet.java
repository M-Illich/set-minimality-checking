package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code long} values based on the
 * {@link #hashCode} of the set's elements
 *
 */
public class BitVectorSet extends SetRepresent<long[]> implements Comparable<BitVectorSet> {

	/**
	 * Create a {@link BitVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public BitVectorSet(Set<?> set) {
		this.originalSet = set;
		// convert set to long[] with length based on set size
		this.setRepresentation = new BitVectorSetConverter().convertSet(set);
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
