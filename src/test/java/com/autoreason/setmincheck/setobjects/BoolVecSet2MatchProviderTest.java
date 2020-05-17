package com.autoreason.setmincheck.setobjects;

import java.util.BitSet;
import java.util.Set;

import com.autoreason.setmincheck.MatchProvider;
import com.autoreason.setmincheck.MatchProviderTest;

public class BoolVecSet2MatchProviderTest extends MatchProviderTest<BoolVectorSet2, Set<?>> {

	@Override
	protected MatchProvider<BoolVectorSet2, Set<?>> defineMatchProvider() {
		return new BoolVecSet2MatchProvider();
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(5,8,13);
	}

	@Override
	protected BoolVectorSet2 defineEqualMatch() {
		BitSet bs = new BitSet(3);
		bs.set(5);
		bs.set(8);
		bs.set(13);
		return new BoolVectorSet2(bs);
	}

	@Override
	protected BoolVectorSet2 defineNotMatch() {
		BitSet bs = new BitSet(2);
		bs.set(4);
		bs.set(7);
		return new BoolVectorSet2(bs);
	}

	@Override
	protected BoolVectorSet2 defineNextMatch() {
		BitSet bs = new BitSet(1);
		bs.set(8);
		return new BoolVectorSet2(bs);
	}

}
