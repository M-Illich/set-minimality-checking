package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.MinimalityChecker;

/**
 * Representation of a {@link Set} as array of {@code long} values based on the
 * {@link #hashCode} of the set's elements
 *
 */
public class BitVectorSet extends SetRepresent<BitVectorSet, long[], BitVectorSetChecker> { // implements Comparable<BitVectorSet> {

	/**
	 * Original {@link Set} represented by the {@link BitVectorSet} object
	 */
	public Set<?> set;
	
	/**
	 * Create an empty {@link BitVectorSet} object
	 */
	public BitVectorSet() {
		this.setRepresentation = null;
		this.set = null;
	}
	
	/**
	 * Create a {@link BitVectorSet} object based on a {@code long} array
	 * 
	 * @param bv A {@code long[]} used as {@code bitVector}
	 */
	public BitVectorSet(long[] bv) {
		this.setRepresentation = bv;
		this.set = null;
	}

	/**
	 * Create a {@link BitVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} used to define a {@code long[]} based on its
	 *            elements' {@link #hashCode}
	 */
	public BitVectorSet(Set<?> set) {
		this.set = set;
		// determine length of array based on set size
		int len = set.size() / 64 + 1;
		this.setRepresentation = new long[len];

		// use elements of set to define position of 1-bits
		for (Object e : set) {
			// determine position
			int pos = e.hashCode() % (len * 64);
			// set bit in appropriate long value
			this.setRepresentation[pos / 64] |= (long) 1 << pos;
		}
	}

	/**
	 * Create a {@link BitVectorSet} object based on a {@link Set} and its
	 * {@code long[]} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@code long[]} representing the element {@code set}
	 */
	public BitVectorSet(Set<?> set, long[] bv) {
		this.set = set;
		this.setRepresentation = bv;
	}

	@Override
	public int compareTo(BitVectorSet o) {
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
			// compare long values (highest bit is not sign but rather position of set
			// element)
			int c = Long.compareUnsigned(this.setRepresentation[i], o.setRepresentation[i]);
			if (c != 0)
				return c;
		}
		// bitVector elements are equal
		return 0;
	}
	

	@Override
	public <S> BitVectorSet convertSet(Set<S> set) {
		return new BitVectorSet(set);
	}

	@Override
	public BitVectorSetChecker getMinChecker() {
		return new BitVectorSetChecker();
	}

	

}