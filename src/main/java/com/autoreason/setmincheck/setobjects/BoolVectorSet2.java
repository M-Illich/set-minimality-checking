package com.autoreason.setmincheck.setobjects;

import java.util.BitSet;
import java.util.Set;

/**
 * Representation of a {@link Set} as {@link BitSet} based on the
 * {@link #hashCode} of the set's elements (alternative version to
 * {@link BoolVectorSet})
 *
 */
public class BoolVectorSet2 extends AbstractSetRepresent<BitSet> implements Comparable<BoolVectorSet2> {

	/**
	 * Create a {@link BoolVectorSet2} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public BoolVectorSet2(Set<?> set) {
		this.originalSet = set;
		this.setRepresentation = convertSet(set, null);
	}

	/**
	 * Create a {@link BoolVectorSet2} object based on a {@link Set} and its
	 * {@code BitSet} representation
	 * 
	 * @param set A {@link Set}
	 * @param bs  A {@code BitSet} representing the element {@code set}
	 */
	public BoolVectorSet2(Set<?> set, BitSet bs) {
		this.originalSet = set;
		this.setRepresentation = bs;
	}

	/**
	 * Create a {@link BoolVectorSet2} object with the given {@code BitSet}
	 * representation
	 * 
	 * <p>
	 * Note: The created {@code BoolVectorSet2} does not contain any original
	 * {@link Set} and, hence, should only be used to serve as reference or
	 * comparison based on the {@code setRepresentation} attribute
	 * </p>
	 * 
	 * @param bs A {@code BitSet} representing a {@link Set}
	 */
	public BoolVectorSet2(BitSet bs) {
		this.setRepresentation = bs;
	}

	@Override
	public BitSet convertSet(Set<?> set, Object attr) {
		int initSize;
		if (attr instanceof Integer) {
			// size of BitSet initialized by attr
			initSize = (int) attr;
			if (initSize < 1) {
				initSize = 1;
			}
		} else {
			// initialize BitSet size with on set size
			initSize = set.size();
		}
		// initialize BitSet
		BitSet convertedSet = new BitSet(initSize);
		// get actually used size
		int size = convertedSet.size();

		// use elements of set to define position of true values
		for (Object e : set) {
			int hashcode = e.hashCode();
			// only allow positive values
			if (hashcode < 0) {
				hashcode *= -1;
			}
			// set true value in appropriate position
			convertedSet.set(hashcode % size);
		}

		return convertedSet;
	}

	@Override
	public int compareTo(BoolVectorSet2 o) {
		// comparison based on BitSet instances
		BitSet a = this.setRepresentation;
		BitSet b = o.setRepresentation;

		// compare size
		int c = Integer.compare(a.size(), b.size());
		// same size
		if (c == 0) {
			// compare bits starting at highest one
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

}