package com.autoreason.setmincheck.setobjects;

import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.autoreason.setmincheck.MinimalityCheckerTest;
import com.autoreason.setmincheck.setobjects.BitVectorSet;


public class BitVectorSetCheckerTest extends MinimalityCheckerTest<BitVectorSet, BitVectorSetChecker>  {
	
			
	private static final Random SEED_GENERATOR = new Random();
	private BitVectorSetChecker bvsChecker = new BitVectorSetChecker();
	
	
//	TODO define tests in interface / super class
	
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
	
	
//	@Test
//	public void testIsMinimal() {	
//		BitVectorSetChecker bvsChecker = new BitVectorSetChecker();    	
//    	Set<Integer> testSet = Set.of(1,32,70,104);		
//				
//		Collection<BitVectorSet> collection = new HashSet<BitVectorSet>();
//		Set<Integer> set = Set.of(5,13,66,120);
//		collection.add(new BitVectorSet(set,bvsChecker.convert(set,2)));
//		set = Set.of(2,3,8,34);
//		collection.add(new BitVectorSet(set,bvsChecker.convert(set,1)));
//		set = Set.of(5,13);
//		collection.add(new BitVectorSet(set,bvsChecker.convert(set,1)));
//		set = Set.of(10,30,50,80,110);
//		collection.add(new BitVectorSet(set,bvsChecker.convert(set,2)));
//		set = Set.of(0,1,94,138);
//		collection.add(new BitVectorSet(set,bvsChecker.convert(set,3)));
//		set = Set.of(1,5,83,120);
//		collection.add(new BitVectorSet(set,bvsChecker.convert(set,2)));
//						
//		assertTrue(bvsChecker.isMinimal(collection, testSet));
//		
//	}

	@Test
	public void testGetNextMatch() {
		
//		BitVectorSet previous = new BitVectorSet(new long[]{2,13});
//		BitVectorSet test = new BitVectorSet(new long[]{40,20});
//		assertArrayEquals(new long[]{0,16}, bvsChecker.getNextMatch(previous, test).bitVector);
//		
	}

	@Test
	public void testGetLowestBit() {			
		assertArrayEquals(new long[]{0,2}, bvsChecker.getLowestBit(new long[]{0,42,4}));		
	}
	
	@Test
	public void testGetHighestBit() {			
		assertArrayEquals(new long[]{0,0,8}, bvsChecker.getHighestBit(new long[]{0,42,13}));		
	}

	@Test
	public void testAdd() {
		assertArrayEquals(new long[]{3,5,11}, bvsChecker.add(new long[]{1,2,6}, new long[]{2,3,5}));
	}
	
	@Test
	public void testSubtractOne() {		
		assertArrayEquals(new long[]{-1,13,104}, bvsChecker.subtractOne(new long[]{0,14,104}));
	}

	@Test
	public void testComplementOf() {
		assertArrayEquals(new long[]{1,3,8}, bvsChecker.complementOf(new long[]{~1,~3,~8}));
	}
	
	@Test
	public void testAnd() {
		assertArrayEquals(new long[]{0,2,1}, bvsChecker.and(new long[]{1,2,9}, new long[]{2,3,5}));
	}
	
	@Test
	public void testXor() {
		assertArrayEquals(new long[]{3,1,3}, bvsChecker.xor(new long[]{1,2,6}, new long[]{2,3,5}));
	}
		
	@Test
	public void testConvert() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(0);
		set.add(10);
		set.add(64);
		set.add(74);	
		BitVectorSet example = new BitVectorSet(new long[]{1025,1025});
		
		assertTrue(example.compareTo(new BitVectorSet(bvsChecker.convert(set,2))) == 0);
	}
	
	@Test
	public void testMatches() {
		// TODO
	}
		
}
