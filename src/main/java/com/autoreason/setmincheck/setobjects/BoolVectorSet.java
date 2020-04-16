package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * Representation of a {@link Set} as array of {@code boolean} values based on the
 * {@link #hashCode} of the set's elements (alternative version to {@link BitVectorSet})
 *
 */
public class BoolVectorSet extends SetRepresent<BoolVectorSet, boolean[], BoolVectorSetChecker> {
	
	/**
	 * Original {@link Set} represented by the {@link BoolVectorSet} object
	 */
	public Set<?> set;
	
	/**
	 * Create an empty {@link BoolVectorSet} object
	 */
	public BoolVectorSet() {
		this.setRepresentation = null;
		this.set = null;
	}
	
	/**
	 * Create a {@link BoolVectorSet} object based on a {@code boolean} array
	 * 
	 * @param bv A {@code boolean[]} used as {@code bitVector}
	 */
	public BoolVectorSet(boolean[] bv) {
		this.setRepresentation = bv;
		this.set = null;
	}

	/**
	 * Create a {@link BoolVectorSet} object based on a {@link Set}
	 * 
	 * @param set A {@link Set} used to define a {@code boolean[]} based on its
	 *            elements' {@link #hashCode}
	 */
	public BoolVectorSet(Set<?> set) {
		this.set = set;
		// determine length of array based on set size
		int len = set.size();
		this.setRepresentation = new boolean[len];

		// use elements of set to define position of true values
		for (Object e : set) {		
			int hashcode = e.hashCode();
			// only allow positive values
			if(hashcode < 0) {
				hashcode *= -1;
			}			
			// set true value in appropriate position
			this.setRepresentation[hashcode % len] = true;
		}
	}

	/**
	 * Create a {@link BoolVectorSet} object based on a {@link Set} and its
	 * {@code boolean[]} representation
	 * 
	 * @param set A {@link Set}
	 * @param bv  A {@code long[]} representing the element {@code set}
	 */
	public BoolVectorSet(Set<?> set, boolean[] bv) {
		this.set = set;
		this.setRepresentation = bv;
	}

	@Override
	public int compareTo(BoolVectorSet other) {
		// comparison based on arrays
		boolean[] a = this.setRepresentation;
		boolean[] b = other.setRepresentation;
		// compare length
		int c = Integer.compare(a.length, b.length);
		// same length
		if(c == 0) {
			int i = a.length - 1;
			while (i >= 0 && c == 0) {
				// comparison based on last entry in array
				c = Boolean.compare(a[i], b[i]);
				i--;				
			}
		}
		return c;
	}

	@Override
	public <S> BoolVectorSet convertSet(Set<S> set) {
		return new BoolVectorSet(set);
	}

	@Override
	public BoolVectorSetChecker getMinChecker() {
		return new BoolVectorSetChecker();
	}

}
