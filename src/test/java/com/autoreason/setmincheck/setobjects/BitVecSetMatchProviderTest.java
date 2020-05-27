package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.MatchProvider;
import com.autoreason.setmincheck.MatchProviderTest;

public class BitVecSetMatchProviderTest extends MatchProviderTest<BitVectorSet, Set<?>> {

	@Override
	protected MatchProvider<BitVectorSet, Set<?>> defineMatchProvider() {
		return new BitVecSetMatchProvider();
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1,3,5);
	}

	@Override
	protected BitVectorSet defineMatch() {
		return new BitVectorSet(new long[] {10});
	}

	@Override
	protected BitVectorSet defineNotMatch() {
		return new BitVectorSet(new long[] {3});
	}

	@Override
	protected BitVectorSet defineNextMatch() {
		return new BitVectorSet(new long[] {8});
	}

}
