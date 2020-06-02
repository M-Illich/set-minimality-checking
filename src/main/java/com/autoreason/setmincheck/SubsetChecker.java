package com.autoreason.setmincheck;

import java.util.Set;

import com.autoreason.setmincheck.setobjects.SetRepresent;

/**
 * A class to determine if a {@link SetRepresent} implementation represents a
 * subset of another given set.
 *
 */
public class SubsetChecker {

	/**
	 * Check if an object of type {@link SetRepresent} represents a subset of a
	 * given {@link Set}
	 * 
	 * @param <S>  An implementation of {@link SetRepresent}
	 * @param cand An implementation of {@link SetRepresent}
	 * @param test A {@link Set}
	 * @return {@code true} if {@code cand} represents a subset of {@code test},
	 *         otherwise {@code false}
	 */
	public static <S extends SetRepresent<?>> boolean subsetOf(S cand, Set<?> test) {
		// check if original set of cand is a subset of test
		return test.containsAll(cand.originalSet);
	}

}
