package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code long} values based on the
 * {@link #hashCode} of the set's elements
 *
 */
public class BitVectorSet extends SetRepresent<BitVectorSet, long[], BitVectorSetChecker> {

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
			// only allow positive values
			if (pos < 0) {
				pos *= -1;
			}
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
		int c = Integer.compare(len1, ar2.length);
		// compare concrete values if same length
		if(c == 0) {
			int i = len1;
			do {
				i--;
				c = Long.compareUnsigned(ar1[i], ar2[i]);
			} while (c == 0 && i > 0);
		}
		
		// bitVector elements are equal
		return c;
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