package com.autoreason.setmincheck;

import java.util.Comparator;

import com.autoreason.setmincheck.setobjects.SetRepresent;

/**
 * Implementation of {@link Comparator} where two {@link SetRepresent} elements
 * are only regarded as equal if their sets are the same, hence preventing the
 * removal of duplicate set representations during sorting
 */
public class KeepSameSetRepComparator<S extends SetRepresent<?> & Comparable<S>> implements Comparator<S> {

	@Override
	public int compare(S a, S b) {
		int c = a.compareTo(b);
		if (c == 0) {
			// compare original sets
			if (a.originalSet == null) {
				c = 0;
			} else if (b.originalSet == null) {
				c = 0;
			} else {
				c = Integer.compare(a.originalSet.hashCode(), b.originalSet.hashCode());
			}
		}
		return c;
	}

}
