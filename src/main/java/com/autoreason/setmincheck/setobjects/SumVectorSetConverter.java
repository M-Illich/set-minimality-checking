package com.autoreason.setmincheck.setobjects;

import java.util.Set;
import java.util.TreeSet;

/**
 * A {@link SetConverter} implementation for {@link SumVectorSet}
 *
 */
public class SumVectorSetConverter extends AbstractSetConverter<long[], Integer> {

	/**
	 * Create a {@code SumVectorSetConverter} instance where the number of elements
	 * that contribute to a sum in the set representation generated by
	 * {@link #convertSet(Set)} is set to the standard value 10
	 * 
	 * <p>
	 * Note: Tests showed that smaller values are often faster than large ones. If
	 * the value is changed, the test cases in {@code SumVectorSetTest} and
	 * {@code SumVecSetMatchProviderTest} have to be adapted accordingly.
	 * </p>
	 * 
	 * @param len An {@code int} greater than 1
	 */
	SumVectorSetConverter() {
		// length of set representation is determined by set size
		this.convertAttribute = 10;
	}

	@Override
	public long[] convertSet(Set<?> set) {
		int len = set.size() / convertAttribute + 1;
		long[] convertedSet = new long[len];

		// counter for used summands
		int summands = 0;
		// counter for computed sums
		int num_sums = 0;

		// sort hash codes of set
		TreeSet<Integer> sortedSet = new TreeSet<>();
		for (Object e : set) {
			sortedSet.add(e.hashCode());
		}
		for (Integer e : sortedSet) {
			// check if sum completed
			if (summands == convertAttribute) {
				num_sums++;
				summands = 0;
			}
			// add hash code value to current sum
			convertedSet[num_sums] += e;
			summands++;
		}

		// unsorted approach; seems to be better for very large sets
//		// compute sums for set elements
//		for (Object e : set) {
//			// check if sum completed
//			if (summands == convertAttribute) {
//				num_sums++;
//				summands = 0;
//			}
//			// add hash code value to current sum
//			convertedSet[num_sums] += e.hashCode();
//			summands++;
//		}

		return convertedSet;
	}

}
