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

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.MinimalityCheckerTest;
import com.autoreason.setmincheck.datagenerator.SetGenerator;
import com.autoreason.setmincheck.setobjects.BitVectorSet;

public class BitVectorSetCheckerTest extends MinimalityCheckerTest<BitVectorSet, BitVectorSetChecker> {

	// create random test objects based on seed
	private static final Random SEED_GENERATOR = new Random();
	private long currentSeed = SEED_GENERATOR.nextLong();
	// maximum size for randomly generated sets
	private final int MAX_SIZE = 10;
	// number of sets contained in randomly generated collection
	private final int NUM_SETS = 10;
	

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
	public void testGetLowestBit() {
		assertArrayEquals(new long[] { 0, 2 }, matchOperator.getLowestBit(new long[] { 0, 42, 4 }));
	}

	@Test
	public void testGetHighestBit() {
		assertArrayEquals(new long[] { 0, 0, 8 }, matchOperator.getHighestBit(new long[] { 0, 42, 13 }));
	}

	@Test
	public void testAdd() {
		assertArrayEquals(new long[] { 3, 5, 11 }, matchOperator.add(new long[] { 1, 2, 6 }, new long[] { 2, 3, 5 }));
	}

	@Test
	public void testSubtractOne() {
		assertArrayEquals(new long[] { -1, 13, 104 }, matchOperator.subtractOne(new long[] { 0, 14, 104 }));
	}

	@Test
	public void testComplementOf() {
		assertArrayEquals(new long[] { 1, 3, 8 }, matchOperator.complementOf(new long[] { ~1, ~3, ~8 }));
	}

	@Test
	public void testAnd() {
		assertArrayEquals(new long[] { 0, 2, 1 }, matchOperator.and(new long[] { 1, 2, 9 }, new long[] { 2, 3, 5 }));
	}

	@Test
	public void testXor() {
		assertArrayEquals(new long[] { 3, 1, 3 }, matchOperator.xor(new long[] { 1, 2, 6 }, new long[] { 2, 3, 5 }));
	}

	@Test
	public void testTransform() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(0);
		set.add(10);
		set.add(64);
		set.add(74);
		BitVectorSet example = new BitVectorSet(new long[] { 1025, 1025 });

		assertTrue(example.compareTo(new BitVectorSet(matchOperator.transform(set, 2))) == 0);
	}

	@Override
	public BitVectorSet convert(Set<?> set) {
		return new BitVectorSet(set);
	}

	@Override
	protected BitVectorSet getNext(BitVectorSet previous) {
		long[] next = previous.bitVector.clone();
		long sum;
		int i = 0;
		// increase value defined by array by 1
		do {
			sum = next[i] + 1;
		} while (sum == 0 && i < next.length);

		return new BitVectorSet(next);
	}

	@Override
	protected Set<?> defineTest(long seed) {
		// Set based on stored minSets.txt file
//		return new HashSet<Integer>(Set.of(14,15,16,17,18));		
		// create random Set based on given seed 
		return SetGenerator.randomSet(MAX_SIZE, seed);
	}

	@Override
	protected Collection<BitVectorSet> defineCollection(long seed) {
		// create Collection of random Set elements based on given seed
		Collection<Set<Integer>> col = SetGenerator.randomMinSetCollection(NUM_SETS, MAX_SIZE, seed);
		// collection taken from stored minSets.txt file
//		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("src\\test\\resources\\minSets.txt");	
		// return converted sets
		return convertCollection(col);
	}

	@Override
	protected BitVectorSet defineMatch(BitVectorSet test) {
		// remove one element from set to get a matching object
		Set<?> set = test.set;
		set.remove(set.iterator().next());		
		return new BitVectorSet(set);
	}

	@Override
	protected BitVectorSet defineNotMatch(BitVectorSet test) {
		// bit vector must contain at least one 1-bit at a different position compared to test
		long[] bv = test.bitVector;
		// use complement as new long value 
		bv[0] = ~bv[0];
		
		return new BitVectorSet(bv);
	}

	@Override
	protected BitVectorSet defineSmaller(BitVectorSet test) {
		Set<?> testSet = test.set;
		BitVectorSet smaller;
		Set<?> set = testSet;
		// remove one element from the test set until the related BitVectorSet is
		// smaller than the given test
		do {
			set.remove(testSet.iterator().next());
			smaller = new BitVectorSet(set);
		} while (smaller.compareTo(test) != -1 && smaller.set.size() > 0);

		return smaller;
	}

	@Override
	protected long getSeed() {
		return currentSeed;
	}

	@Override
	protected BitVectorSetChecker initializeOperator() {
		return new BitVectorSetChecker();
	}

}