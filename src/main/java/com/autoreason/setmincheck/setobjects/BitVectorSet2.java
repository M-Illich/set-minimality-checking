package com.autoreason.setmincheck.setobjects;

/**
 * An alternative version of {@link BitVectorSet} where the comparison
 * additionally considers the bit count of the long values
 *
 */
public class BitVectorSet2 extends BitVectorSet {

	// alternative compareTo-method based on number of set elements (and long
	// values for same size)
	@Override
	public int compareTo(BitVectorSet o) {
		// get bitVector values
		long[] ar1 = this.setRepresentation;
		long[] ar2 = o.setRepresentation;

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
			// get values
			long thisLong = this.setRepresentation[i];
			long oLong = o.setRepresentation[i];
			// use bit count for comparison
			int c = Integer.compare(Long.bitCount(thisLong), Long.bitCount(oLong));
			if (c != 0) {
				return c;
			} else {
				// same bit count -> compare actual value
				c = Long.compareUnsigned(thisLong, oLong);
				if (c != 0) {
					return c;
				}
			}
		}
		// bitVector elements are equal
		return 0;
	}
}
