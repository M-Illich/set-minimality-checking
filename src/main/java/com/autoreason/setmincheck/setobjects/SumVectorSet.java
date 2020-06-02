package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code long} values, each
 * representing the sum of {@link #hashCode} values for a certain portion of set
 * elements
 *
 */
public class SumVectorSet extends SetRepresent<long[]> implements Comparable<SumVectorSet> {

	/**
	 * Create a {@link SumVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public SumVectorSet(Set<?> set) {
		this.originalSet = set;
		this.setRepresentation = new SumVectorSetConverter().convertSet(set);
	}

	/**
	 * Create a {@link SumVectorSet} object based on a {@link Set} and its
	 * {@code long[]} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@code long[]} representing the element {@code set}
	 */
	public SumVectorSet(Set<?> set, long[] bv) {
		this.originalSet = set;
		this.setRepresentation = bv;
	}

	/**
	 * Create a {@link SumVectorSet} object with the given {@code long[]}
	 * representation
	 * 
	 * <p>
	 * Note: The created {@code SumVectorSet} does not contain any original
	 * {@link Set} and, hence, should only be used to serve as reference or
	 * comparison based on the {@code setRepresentation} attribute
	 * </p>
	 * 
	 * @param bv A {@code long[]} representing a {@link Set}
	 */
	public SumVectorSet(long[] bv) {
		this.setRepresentation = bv;
	}

	@Override
	public int compareTo(SumVectorSet o) {
		// get sums
		long[] ar1 = this.setRepresentation;
		long[] ar2 = o.setRepresentation;

		// compare length of arrays
		int len1 = ar1.length;
		int len2 = ar2.length;
		// larger object has longer array
		if (len1 < len2)
			return -1;
		if (len1 > len2)
			return 1;

		// arrays have same length -> compare values at each position of arrays
		return Arrays.compare(ar1, ar2);
	}

}
