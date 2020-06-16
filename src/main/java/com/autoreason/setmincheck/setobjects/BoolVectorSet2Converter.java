package com.autoreason.setmincheck.setobjects;

import java.util.BitSet;
import java.util.Set;

/**
 * A {@link SetConverter} implementation for {@link BoolVectorSet2}
 *
 */
public class BoolVectorSet2Converter extends AbstractSetConverter<BitSet> {

	@Override
	public BitSet convertSet(Set<?> set) {
		// initialize BitSet based on the value given by setRepresentLength
		BitSet convertedSet = new BitSet(setRepresentLength);
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
