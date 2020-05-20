package com.autoreason.setmincheck.setobjects;

import java.util.Set;

import com.autoreason.setmincheck.MatchProvider;
import com.autoreason.setmincheck.MatchProviderTest;

public class BoolVecSetMatchProviderTest extends MatchProviderTest<BoolVectorSet, Set<?>> {

	@Override
	protected MatchProvider<BoolVectorSet, Set<?>> defineMatchProvider() {
		return new BoolVecSetMatchProvider();
	}

	@Override
	protected Set<?> defineTest() {
		return Set.of(0, 2, 5);
	}

	@Override
	protected BoolVectorSet defineMatch() {
		boolean[] bv = new boolean[64];
		bv[0] = true;
		bv[2] = true;
		bv[5] = true;
		return new BoolVectorSet(bv);
	}

	@Override
	protected BoolVectorSet defineNotMatch() {
		return new BoolVectorSet(new boolean[] { true, true, false });
	}

	@Override
	protected BoolVectorSet defineNextMatch() {
		return new BoolVectorSet(new boolean[] { false, false, true });
	}

}
