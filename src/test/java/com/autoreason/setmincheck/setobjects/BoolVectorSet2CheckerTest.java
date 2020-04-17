package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.MinimalityCheckerTest;
import com.autoreason.setmincheck.datagenerator.SetGenerator;
import com.autoreason.setmincheck.setobjects.BoolVectorSet2;

public class BoolVectorSet2CheckerTest extends MinimalityCheckerTest<BoolVectorSet2, BoolVectorSet2Checker> {

	// create random test objects based on seed
	private static final Random SEED_GENERATOR = new Random();
	private long currentSeed =  SEED_GENERATOR.nextLong(); //Long.valueOf("7179516445710087373"); // 
	// maximum size for randomly generated sets
	private final int MAX_SIZE = 10;
	// number of sets contained in randomly generated collection
	private final int NUM_SETS = 10;

	@Test
	public void testTransform() {
		Set<Integer> set = new HashSet<Integer>();
		set.add(0);
		set.add(1);
		set.add(3);
		set.add(6);
		BitSet bs = new BitSet(4);
		bs.set(0, 4);
		BoolVectorSet2 example = new BoolVectorSet2(bs);

		assertTrue(example.compareTo(new BoolVectorSet2(matchOperator.transform(set, 4))) == 0);
	}

	@Override
	protected BoolVectorSet2 getNext(BoolVectorSet2 previous) {
		BitSet next = (BitSet) previous.setRepresentation.clone();
		int len = next.length();
		// get first occurring false entry
		int i = next.nextClearBit(0);
		// increase array length if only true values found
		if (i == len) {
			// new empty vector with increased size
			next = new BitSet(len + 1);			
		} else {
			// swap entries up to first false 
			next.clear(0, i);
			next.set(i);
		}

		return new BoolVectorSet2(next);
	}

	@Override
	protected Set<?> defineTest(long seed) {
		// Set based on stored minSets.txt file
		return new HashSet<Integer>(Set.of(14,15,16,17,18));		
		// create random Set based on given seed
//		return SetGenerator.randomSet(MAX_SIZE, seed);
	}

	@Override
	protected NavigableSet<BoolVectorSet2> defineCollection(long seed) {
		// create Collection of random Set elements based on given seed
//		Collection<Set<Integer>> col = SetGenerator.randomMinSetCollection(NUM_SETS, MAX_SIZE, seed);
		// collection taken from stored minSets.txt file
		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("src\\test\\resources\\minSets.txt");	
		// return converted sets
		return new TreeSet<BoolVectorSet2>(c.convertCollection(col));
	}

	@Override
	protected BoolVectorSet2 defineMatch(BoolVectorSet2 test) {
		// remove one element from set to get a matching object
		Set<?> set = test.set;
		if (set.size() > 1) {
			set.remove(set.iterator().next());
		}
		return new BoolVectorSet2(set);
	}

	@Override
	protected BoolVectorSet2 defineNotMatch(BoolVectorSet2 test) {
		// boolean vector must contain at least one true entry at a different position
		// compared to test
		BitSet bs = test.setRepresentation;
		int len = bs.length();
		int i = -1;
		do {
			i++;
		} while (i < len && bs.get(i));
		// increase array if only true values in test
		if (i == len) {
			len++;
			bs = new BitSet(len);
			bs.set(0, len);
		}
		bs.set(i);

		return new BoolVectorSet2(bs);
	}

	@Override
	protected BoolVectorSet2 defineSmaller(BoolVectorSet2 test) {
		Set<?> testSet = test.set;
		BoolVectorSet2 smaller;
		Set<?> set = testSet;
		// remove one element from the test set until the related BoolVectorSet2 is
		// smaller than the given test
		do {
			set.remove(testSet.iterator().next());
			smaller = new BoolVectorSet2(set);
		} while (smaller.compareTo(test) > -1 && smaller.set.size() > 0);

		return smaller;
	}

	@Override
	protected long getSeed() {
		return currentSeed;
	}

	@Override
	protected BoolVectorSet2Checker initializeOperator() {
		return new BoolVectorSet2Checker();
	}

	@Override
	protected BoolVectorSet2 initSetRepresent() {
		return new BoolVectorSet2();
	}

}
