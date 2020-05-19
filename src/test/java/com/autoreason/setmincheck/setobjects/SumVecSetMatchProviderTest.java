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
		for (int i = 1; i <= 250; i++) {
			set.add(i);
		}
		// { 5050, 15050, 11275 }
		return set;
	}

	@Override
	protected SumVectorSet defineEqualMatch() {
		return new SumVectorSet(new long[] { 500, 4201, 6543 });
	}

	@Override
	protected SumVectorSet defineNotMatch() {
		return new SumVectorSet(new long[] { 1000, 4000, 12000 });
	}

	@Override
	protected SumVectorSet defineNextMatch() {
		return new SumVectorSet(new long[] { 1000, 4001, 0 });
	}

}
