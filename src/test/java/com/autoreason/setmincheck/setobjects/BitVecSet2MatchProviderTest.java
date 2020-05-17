package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.MatchProvider;
import com.autoreason.setmincheck.MatchProviderTest;

public class BitVecSet2MatchProviderTest extends MatchProviderTest<BitVectorSet2, Set<?>> {

	@Override
	protected MatchProvider<BitVectorSet2, Set<?>> defineMatchProvider() {
		return new BitVecSet2MatchProvider();
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(1, 3, 5);
	}

	@Override
	protected BitVectorSet2 defineEqualMatch() {
		return new BitVectorSet2(new long[] { 42 });
	}

	@Override
	protected BitVectorSet2 defineNotMatch() {
		return new BitVectorSet2(new long[] { 3 });
	}

	@Override
	protected BitVectorSet2 defineNextMatch() {
		return new BitVectorSet2(new long[] { 8 });
	}

}
