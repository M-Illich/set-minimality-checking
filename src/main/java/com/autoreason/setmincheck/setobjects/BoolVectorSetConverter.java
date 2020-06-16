package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * A {@link SetConverter} implementation for {@link BoolVectorSet}
 *
 */
public class BoolVectorSetConverter extends AbstractSetConverter<boolean[]> {

	/**
	 * Note: The length of the resulting {@code boolean[]} is the smallest multiple
	 * of 64 that holds the size of the set
	 */
	@Override
	public boolean[] convertSet(Set<?> set) {
		// define array length as smallest multiple of 64 that comprises the bit vector
		// length given by setRepresentLength
		int len = (setRepresentLength / 64 + ((setRepresentLength % 64 == 0) ? 0 : 1)) * 64;
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

}
