package com.autoreason.setmincheck.setobjects;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.autoreason.setmincheck.SetMinimalityChecker;
import com.autoreason.setmincheck.setobjects.BitVectorSet;


public class BitVectorSetCheckerTest  {
	
	private static final Random SEED_GENERATOR = new Random();
	private BitVectorSetChecker bvsChecker = new BitVectorSetChecker();
	
// 			schema for random sets	
//	@Test
//	public void runTests() {
//		// generate random seed
//		long seed = SEED_GENERATOR.nextLong();
//		// conduct tests
//		try {
//			
//		} catch (Throwable e) {
//			throw new RuntimeException("seed: " + seed, e);
//		}
//	}

	@Test
	public void testGetNextCandidate() {
		BitVectorSet previous = new BitVectorSet(new long[]{2,13});
		BitVectorSet test = new BitVectorSet(new long[]{40,20});
		
		assertArrayEquals(new long[]{8,4}, bvsChecker.getNextCandidate(previous, test).bitVector);
		
	}

	@Test
	public void testGetLowestOneBit() {			
		assertArrayEquals(new long[]{0,2}, bvsChecker.getLowestOneBit(new long[]{0,42,4}));		
	}

	@Test
	public void testXorKeep() {
		assertArrayEquals(new long[]{2,1,1}, bvsChecker.xorKeep(new long[]{1,2,6}, new long[]{2,3,5}));
	}
	
	@Test
	public void testAdd() {
		assertArrayEquals(new long[]{3,5,11}, bvsChecker.add(new long[]{1,2,6}, new long[]{2,3,5}));
	}

	@Test
	public void testComplement() {
		assertArrayEquals(new long[]{1,3,8}, bvsChecker.complement(new long[]{~1,~3,~8}));
	}

	@Test
	public void testSubtractOne() {		
		assertArrayEquals(new long[]{-1,13,104}, bvsChecker.subtractOne(new long[]{0,14,104}));
	}

	@Test
	public void testAndRemove() {
		assertArrayEquals(new long[]{0,2,1}, bvsChecker.and(new long[]{1,2,9}, new long[]{2,3,5}));
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
		set.add(0);
		set.add(10);
		set.add(64);
		set.add(74);		
		BitVectorSet example = new BitVectorSet(new long[]{1025,1025});
		
		assertTrue(example.compareTo(bvsChecker.convert(example, set)) == 0);
	}

	@Test
	public void testIsSubsetOf() {
		Set<Integer> set1 = new HashSet<Integer>();
		set1.add(4);		
		set1.add(25);
		// define set2 as superset of set1
		Set<Integer> set2 = new HashSet<Integer>();
		set2.add(4);
		set2.add(13);
		set2.add(25);
		set2.add(64);
		// define BitVectorSet elements based on sets
		BitVectorSet first = new BitVectorSet(set1);
		BitVectorSet second = new BitVectorSet(set2);
		
		assertTrue(bvsChecker.isSubsetOf(first,second));
		
		// add element to set1 not contained in set2 -> no subset anymore
		set1.add(1);
		assertFalse(bvsChecker.isSubsetOf(first,second));

	}

	@Test
	public void testGetMaxValue() {
		BitVectorSet bvs = new BitVectorSet(new long[]{13,42});		
		assertTrue(new BitVectorSet(new long[]{-1,-1}).compareTo(bvsChecker.getMaxValue(bvs)) == 0);
	}

	
}
