package com.autoreason.setmincheck.setobjects;

import java.util.Set;


/**
 * Representation of a Set as array of long values based on the hash codes of the set's elements
 *
 */
public class BitVectorSet implements Comparable<BitVectorSet> {

	// original set
	Set<?> set;	
	// array of long values determined by the elements of the related set
	long[] bitVector;

	/**
	 * Create a BitVectorSet object based on a long array
	 * @param bv A long[] used as bitVector
	 */
	public BitVectorSet(long[] bv) {
		this.bitVector = bv;
		this.set = null;
	}
	
	/**
	 * Create a BitVectorSet object based on a Set
	 * @param set A Set used to define a long[] based on its elements' hash codes 
	 */
	public BitVectorSet(Set<?> set) {
		this.set = set;
		// determine length of array based on set size
		int len = set.size() / 64 + 1;		
		this.bitVector = new long[len];
		
		// use elements of set to define position of 1-bits
		for (Object e : set) {
			// determine position 
			int pos = e.hashCode() % (len * 64);
			// set bit in appropriate long value
			this.bitVector[pos/64] |= (long) 1<<pos;					
		}		
	}
	
	/**
	 * Create a BitVectorSet object based on a Set and its long[] representation
	 * @param set A Set
	 * @param bv A long[] representing set
	 */
	public BitVectorSet(Set<?> set, long[] bv) {
		this.set = set;
		this.bitVector = bv;
	}
	
	
	@Override
	public int compareTo(BitVectorSet o) {
		// get bitVector values
		long[] ar1 = this.bitVector;
		long[] ar2 = o.bitVector;

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
			int c = Long.compareUnsigned(this.bitVector[i], o.bitVector[i]);
			if (c != 0)
				return c;
		}
		// bitVector elements are equal
		return 0;

	}
		
}