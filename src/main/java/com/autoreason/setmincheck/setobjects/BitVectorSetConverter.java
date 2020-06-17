package com.autoreason.setmincheck.setobjects;

import java.util.Set;

/**
 * A {@link SetConverter} implementation for {@link BitVectorSet}
 *
 */
public class BitVectorSetConverter extends AbstractSetConverter<long[]> {

	@Override
	public long[] convertSet(Set<?> set) {
		// define array length as smallest value for which the 64-bit long values are
		// able to comprise the bit vector length given by setRepresentLength
		int len = setRepresentLength / 64 + ((setRepresentLength % 64 == 0) ? 0 : 1);
		long[] convertedSet = new long[len];

		// use elements of set to define position of 1-bits
		for (Object e : set) {
			// determine position in complete bit vector consisting of 64-bit long values
			int pos = e.hashCode() % (len * 64);
			// only allow positive values
			if (pos < 0) {
				pos *= -1;
			}
			// set bit in appropriate long value of the array
			convertedSet[pos / 64] |= (long) 1 << pos;
		}

		return convertedSet;
	}

}
