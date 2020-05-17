package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code boolean} values based on
 * the {@link #hashCode} of the set's elements (alternative version to
 * {@link BitVectorSet})
 *
 */
public class BoolVectorSet extends AbstractSetRepresent<boolean[]> implements Comparable<BoolVectorSet> {
		
	/**
	 * Create a {@link BoolVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} that will be represented by the constructed object
	 */
	public BoolVectorSet(Set<?> set) {
		this.originalSet = set;
		this.setRepresentation = convertSet(set, null);
	}

	/**
	 * Create a {@link BoolVectorSet} object based on a {@link Set} and its
	 * {@code boolean[]} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@code boolean[]} representing the element {@code set}
	 */
	public BoolVectorSet(Set<?> set, boolean[] bv) {
		this.originalSet = set;
		this.setRepresentation = bv;
	}

	/**
	 * Create a {@link BoolVectorSet} object with the given {@code boolean[]}
	 * representation
	 * 
	 * <p>
	 * Note: The created {@code BoolVectorSet} does not contain any original
	 * {@link Set} and, hence, should only be used to serve as reference or
	 * comparison based on the {@code setRepresentation} attribute
	 * </p>
	 * 
	 * @param bv A {@code boolean[]} representing a {@link Set}
	 */
	public BoolVectorSet(boolean[] bv) {
		this.setRepresentation = bv;
	}

	@Override
	public boolean[] convertSet(Set<?> set, Object attr) {		
		int len;
		if (attr instanceof Integer) {
			// length of array provided by attr
			len = (int) attr;
			if (len < 1) {
				len = 1;
			}
		} else {
			// determine length of array based on set size
			len = set.size();
		}
		boolean[] convertedSet = new boolean[len];

		// use elements of set to define position of true values
		for (Object e : set) {
			int hashcode = e.hashCode();
			// only allow positive values
			if (hashcode < 0) {
				hashcode *= -1;
			}
			// set true value in appropriate position
			convertedSet[hashcode % len] = true;
		}
		
		return convertedSet;
	}

	@Override
	public int compareTo(BoolVectorSet other) {
		// comparison based on arrays
		boolean[] a = this.setRepresentation;
		boolean[] b = other.setRepresentation;
		// compare length
		int c = Integer.compare(a.length, b.length);
		// same length
		if (c == 0) {
			int i = a.length - 1;
			while (i >= 0 && c == 0) {
				// comparison based on last entry in array
				c = Boolean.compare(a[i], b[i]);
				i--;
			}
		}
		return c;
	}

}
