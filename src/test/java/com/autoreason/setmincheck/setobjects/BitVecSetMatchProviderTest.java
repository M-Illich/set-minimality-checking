package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertArrayEquals;

import java.util.Set;

import org.junit.Test;

import com.autoreason.setmincheck.MatchProvider;
import com.autoreason.setmincheck.MatchProviderTest;

public class BitVecSetMatchProviderTest extends MatchProviderTest<BitVectorSet, Set<?>> {
	
	@Test
	public void testGetLowestBit() {
		assertArrayEquals(new long[] { 0, 2 }, BitVecSetMatchProvider.getLowestBit(new long[] { 0, 42, 4 }));
	}

	@Test
	public void testGetHighestBit() {
		assertArrayEquals(new long[] { 0, 0, 8 }, BitVecSetMatchProvider.getHighestBit(new long[] { 0, 42, 13 }));
	}

	@Test
	public void testAdd() {
		assertArrayEquals(new long[] { 3, 5, 11 }, BitVecSetMatchProvider.add(new long[] { 1, 2, 6 }, new long[] { 2, 3, 5 }));
	}

	@Test
	public void testSubtractOne() {
		assertArrayEquals(new long[] { -1, 13, 104 }, BitVecSetMatchProvider.subtractOne(new long[] { 0, 14, 104 }));
	}

	@Test
	public void testComplementOf() {
		assertArrayEquals(new long[] { 1, 3, 8 }, BitVecSetMatchProvider.complementOf(new long[] { ~1, ~3, ~8 }));
	}

	@Test
	public void testAnd() {
		assertArrayEquals(new long[] { 0, 2, 1 }, BitVecSetMatchProvider.and(new long[] { 1, 2, 9 }, new long[] { 2, 3, 5 }));
	}

	@Test
	public void testXor() {
		assertArrayEquals(new long[] { 3, 1, 3 }, BitVecSetMatchProvider.xor(new long[] { 1, 2, 6 }, new long[] { 2, 3, 5 }));
	}

	@Test
	public void testRemoveLowBits() {
		assertArrayEquals(new long[] { 0, 4, 6 },
				BitVecSetMatchProvider.removeLowBits(new long[] { 1, 5, 6 }, new long[] { 0, 4 }));
	}

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
		return new BitVectorSet(new long[] {42});
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
