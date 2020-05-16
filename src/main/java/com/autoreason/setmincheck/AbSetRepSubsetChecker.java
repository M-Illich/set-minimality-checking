package com.autoreason.setmincheck;

import java.util.Set;

import com.autoreason.setmincheck.setobjects.AbstractSetRepresent;

/**
 * An implementation of {@link SubsetChecker} based on
 * {@link AbstractSetRepresent}
 *
 * @param <S> An implementation of {@link AbstractSetRepresent}
 */
public class AbSetRepSubsetChecker<S extends AbstractSetRepresent<?>> implements SubsetChecker<S> {

	@Override
	public boolean subsetOf(S cand, Set<?> test) {
		// check if original set of cand is a subset of test
		return test.containsAll(cand.originalSet);
	}

}
