package com.autoreason.setmincheck.setobjects;

import java.util.BitSet;
import java.util.Set;

/**
 * A {@link SetConverter} implementation for {@link BoolVectorSet2}
 *
 */
public class BoolVectorSet2Converter extends AbstractSetConverter<BitSet, Integer> {

	/**
	 * Create a {@code BoolVectorSet2Converter} instance where the length for the
	 * {@link BitSet} set representation generated by {@link #convertSet(Set)} is
	 * determined by the converted set's size
	 * 
	 * @param len An {@code int} greater than 1
	 */
	BoolVectorSet2Converter() {
		// length of set representation is determined by set size
		this.convertAttribute = -1;
	}

	/**
	 * Create a {@code BoolVectorSetConverter} instance with predefined length for
	 * the {@link BitSet} set representation generated by {@link #convertSet(Set)}
	 * 
	 * @param len An {@code int} greater than 1
	 */
	BoolVectorSet2Converter(int len) {
		this.convertAttribute = len;
	}

	@Override
	public BitSet convertSet(Set<?> set) {
		int initSize = convertAttribute;
		if (initSize < 1) {
			// initialize BitSet size with set size
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

}
