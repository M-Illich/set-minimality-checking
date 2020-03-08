package com.autoreason.setmincheck.setobjects;

import java.util.Random;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
		
	}

	@Test
	public void testGetLowestOneBit() {
		// define bit vector
		long[] bv = new long[3];
		bv[1] = 42;
		// test		
		assertEquals(new long[]{0,2,0}, bvsChecker.getLowestOneBit(bv));		
	}

	@Test
	public void testXorKeep(long[] a, long[] b) {
		
	}
	
	@Test
	public void testAdd(long[] a, long[] b) {

	}

	@Test
	public void testComplement(long[] a) {
		
	}

	@Test
	public void testSubtractOne(long[] array) {
		
	}

	@Test
	public void testAndRemove(long[] a, long[] b) {
		
	}
	

	@Test
	public void testIsCandidateOf(BitVectorSet first, BitVectorSet second) {
		
	}

	@Test
	public void testConvert(BitVectorSet example, Set<?> set) {
		
	}

	@Test
	public void testIsSubsetOf(BitVectorSet first, BitVectorSet second) {
		

	}

	@Test
	public void testGetMaxValue(BitVectorSet o) {
		
	}

	
}
