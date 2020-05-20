package com.autoreason.setmincheck.setobjects;

import java.util.HashSet;
import java.util.Set;

import com.autoreason.setmincheck.MatchProvider;
import com.autoreason.setmincheck.MatchProviderTest;

public class SumVecSetMatchProviderTest extends MatchProviderTest<SumVectorSet, Set<?>> {

	@Override
	protected MatchProvider<SumVectorSet, Set<?>> defineMatchProvider() {
		return new SumVecSetMatchProvider();
	}

	@Override
	protected Set<?> defineTest() {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= 25; i++) {
			set.add(i);
		}
		// { 55, 155, 115 }
		return set;
	}

	@Override
	protected SumVectorSet defineMatch() {
		return new SumVectorSet(new long[] { 25, 101, 42 });
	}

	@Override
	protected SumVectorSet defineNotMatch() {
		return new SumVectorSet(new long[] { 42, 13, 201 });
	}

	@Override
	protected SumVectorSet defineNextMatch() {
		return new SumVectorSet(new long[] { 42, 14, 0 });
	}

}
