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
	protected BoolVectorSet defineEqualMatch() {
		return new BoolVectorSet(new boolean[] { true, false, true});
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
