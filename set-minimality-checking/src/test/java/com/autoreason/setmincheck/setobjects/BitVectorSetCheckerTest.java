package com.autoreason.setmincheck.setobjects;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.autoreason.setmincheck.SetMinimalityChecker;
import com.autoreason.setmincheck.setobjects.BitVectorSet;


public class BitVectorSetCheckerTest  {
	
	private static final Random SEED_GENERATOR = new Random();
	private BitVectorSetChecker bvsChecker = new BitVectorSetChecker();
	
	@Test
	public void runTests() {
		// generate random seed
		long seed = SEED_GENERATOR.nextLong();
		// conduct tests
		try {
			
		} catch (Throwable e) {
			throw new RuntimeException("seed: " + seed, e);
		}
	}

	@Test
	public void testGetNextCandidate(BitVectorSet previous, BitVectorSet test) {
		// TODO
	}

	@Test
	public void testGetLowestOneBit() {			
		assertEquals(new long[]{2,0}, bvsChecker.getLowestOneBit(new long[]{0,42,4}));		
	}

	@Test
	public void testXorKeep() {
		assertEquals(new long[]{2,1,1}, bvsChecker.xorKeep(new long[]{1,2,6}, new long[]{2,3,5}));
	}
	
	@Test
	public void testAdd() {
		assertEquals(new long[]{3,5,11}, bvsChecker.add(new long[]{1,2,6}, new long[]{2,3,5}));
	}

	@Test
	public void testComplement() {
		assertEquals(new long[]{1,3,8}, bvsChecker.complement(new long[]{~1,~3,~8}));
	}

	@Test
	public void testSubtractOne() {		
		assertEquals(new long[]{-1,13,104}, bvsChecker.subtractOne(new long[]{0,14,104}));
	}

	@Test
	public void testAndRemove() {
		assertEquals(new long[]{0,2,1}, bvsChecker.andRemove(new long[]{1,2,9}, new long[]{2,3,5}));
	}
	

	@Test
	public void testIsCandidateOf() {
		BitVectorSet first = new BitVectorSet(new long[]{5,0,3});
		BitVectorSet second = new BitVectorSet(new long[]{13,8,7});
		assertTrue(bvsChecker.isCandidateOf(first,second));
		
		second = new BitVectorSet(new long[]{13,8,4});
		assertFalse(bvsChecker.isCandidateOf(first,second));
	}

	@Test
	public void testConvert() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(1);
		// TODO ...
		
		BitVectorSet example = new BitVectorSet(new long[]{});
		
		assertEquals(example, bvsChecker.convert(example, set));
	}

	@Test
	public void testIsSubsetOf(BitVectorSet first, BitVectorSet second) {
		// TODO

	}

	@Test
	public void testGetMaxValue(BitVectorSet o) {
		// TODO
	}

	
}
