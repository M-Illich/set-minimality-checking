package com.autoreason.setmincheck.setobjects;

import java.util.Set;
import java.util.BitSet;

/**
 * Representation of a {@link Set} as {@link BitSet} based on the
 * {@link #hashCode} of the set's elements (alternative version to
 * {@link BoolVectorSet})
 *
 */
public class BoolVectorSet2 extends SetRepresent<BoolVectorSet2, BitSet, BoolVectorSet2Checker> {

	/**
	 * Original {@link Set} represented by the {@link BoolVectorSet2} object
	 */
	public Set<?> set;

	/**
	 * Create an empty {@link BoolVectorSet2} object
	 */
	public BoolVectorSet2() {
		this.setRepresentation = null;
		this.set = null;
	}

	/**
	 * Create a {@link BoolVectorSet2} object based on a {@code boolean} array
	 * 
	 * @param bv A {@link BitSet} used as {@code setRepresentation}
	 */
	public BoolVectorSet2(BitSet bv) {
		this.setRepresentation = bv;
		this.set = null;
	}

	/**
	 * Create a {@link BoolVectorSet2} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} used to define a {@link BitSet} based on its
	 *            elements' {@link #hashCode}
	 */
	public BoolVectorSet2(Set<?> set) {
		this.set = set;
		// determine size of BitSet based on set size
		this.setRepresentation = new BitSet(set.size());
		int size = this.setRepresentation.size();

		// use elements of set to define position of true values
		for (Object e : set) {
			int hashcode = e.hashCode();
			// only allow positive values
			if (hashcode < 0) {
				hashcode *= -1;
			}
			// set true value in appropriate position
			this.setRepresentation.set(hashcode % size);
		}
	}

	/**
	 * Create a {@link BoolVectorSet2} object based on a {@link Set} and its
	 * {@link BitSet} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@link BitSet} representing the element {@code set}
	 */
	public BoolVectorSet2(Set<?> set, BitSet bv) {
		this.set = set;
		this.setRepresentation = bv;
	}

	@Override
	public int compareTo(BoolVectorSet2 other) {
		// comparison based on arrays
		BitSet a = this.setRepresentation;
		BitSet b = other.setRepresentation;

		// compare size
		int c = Integer.compare(a.size(), b.size());
		// same length
		if (c == 0) {
			int i = a.length();
			int j = b.length();
			while (i == j && (i > 0) && (j > 0)) {
				i = a.previousSetBit(i - 1);
				j = b.previousSetBit(j - 1);
			}
			c = Integer.compare(i, j);
		}

		return c;
	}

	@Override
	public <S> BoolVectorSet2 convertSet(Set<S> set) {
		return new BoolVectorSet2(set);
	}

	@Override
	public BoolVectorSet2Checker getMinChecker() {
		return new BoolVectorSet2Checker();
	}

}
