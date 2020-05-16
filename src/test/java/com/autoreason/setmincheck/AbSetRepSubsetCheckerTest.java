package com.autoreason.setmincheck;

import java.util.HashSet;
import java.util.Set;

import com.autoreason.setmincheck.datagenerator.SetGenerator;
import com.autoreason.setmincheck.setobjects.AbstractSetRepresent;
import com.autoreason.setmincheck.setobjects.BitVectorSet;

public class AbSetRepSubsetCheckerTest<C extends AbstractSetRepresent<?>> extends SubsetCheckerTest<BitVectorSet> {

	// maximum size for randomly generated sets
	private final int MAX_SIZE = 10;

	@Override
	protected SubsetChecker<BitVectorSet> defineSubsetChecker() {
		return new AbSetRepSubsetChecker<BitVectorSet>();
	}

	@Override
	protected BitVectorSet defineSubsetCand(Set<Integer> set) {
		return new BitVectorSet(set);
	}

	@Override
	protected BitVectorSet defineNotSubsetCand(Set<Integer> set) {
		Set<Integer> bigSet = new HashSet<Integer>(set);
		// create new set with an element not occurring in test set
		do {
			bigSet.add(SEED_GENERATOR.nextInt());
		} while (bigSet.size() <= set.size());

		return new BitVectorSet(bigSet);
	}

	@Override
	protected Set<Integer> defineTest(long seed) {
		return SetGenerator.randomSet(MAX_SIZE, currentSeed);
	}

}
