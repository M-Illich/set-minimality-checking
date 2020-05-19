package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code long} values, each
 * representing the sum of {@link #hashCode} values for a certain portion of set
 * elements
 *
 */
public class SumVectorSet extends AbstractSetRepresent<long[]> implements Comparable<SumVectorSet> {

	/**
	 * number of elements used to compute the sum
	 */
	private final int SUM_SIZE = 100;

	/**
	 * Create a {@link SumVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public SumVectorSet(Set<?> set) {
		this.originalSet = set;
		this.setRepresentation = convertSet(set, null);
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

	/**
	 * For the {@link SumVectorSet} implementation, the parameter {@code attr}
	 * defines as {@link Integer} the number of elements used to compute the sum. If
	 * {@code attr} is not defined as {@code Integer}, the sum is determined by the
	 * global parameter {@link #SUM_SIZE}
	 */
	@Override
	public long[] convertSet(Set<?> set, Object attr) {
		int sum_size;
		if (attr instanceof Integer) {
			// size of sum provided by attr
			sum_size = (int) attr;
			if (sum_size < 1) {
				sum_size = -1 * sum_size;
			}
		} else {
			// determine length of array based on set size
			sum_size = SUM_SIZE;
		}
		int len = set.size() / sum_size + 1;
		long[] convertedSet = new long[len];

		// counter for used summands
		int summands = 0;
		// counter for computed sums
		int num_sums = 0;
		// compute sums for set elements
		for (Object e : set) {
			// check if sum completed
			if (summands == sum_size) {
				num_sums++;
				summands = 0;
			}
			// add hash code value to current sum
			convertedSet[num_sums] += e.hashCode();
			summands++;
		}

		return convertedSet;
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
