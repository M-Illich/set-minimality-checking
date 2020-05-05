package com.autoreason.setmincheck.setobjects;

import java.util.Arrays;
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
import com.autoreason.setmincheck.setobjects.BoolVectorSet;

public class BoolVectorSetCheckerTest extends MinimalityCheckerTest<BoolVectorSet, BoolVectorSetChecker> {

	// create random test objects based on seed
	private static final Random SEED_GENERATOR = new Random();
	private long currentSeed = SEED_GENERATOR.nextLong(); // Long.valueOf("5028188842633612834");
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
		BoolVectorSet example = new BoolVectorSet(new boolean[] { true, true, true, true });

		assertTrue(example.compareTo(new BoolVectorSet(matchOperator.transform(set, 4))) == 0);
	}

	@Override
	protected BoolVectorSet getNext(BoolVectorSet previous) {
		boolean[] next = previous.setRepresentation.clone();
		int len = next.length;
		int i = 0;
		// swap entries until false reached
		while (i < len && next[i]) {
			next[i] = !next[i];
			i++;
		}
		// increase array length if only true values found
		if (i == len) {
			next = new boolean[len + 1];
		} else {
			next[i] = true;
		}
		return new BoolVectorSet(next);
	}

	@Override
	protected Set<?> defineTest(long seed) {
		// Set based on stored minSets.txt file
//		return new HashSet<Integer>(Set.of(14,15,16,17,18));		
		// create random Set based on given seed
		return SetGenerator.randomSet(MAX_SIZE, seed);
	}

	@Override
	protected NavigableSet<BoolVectorSet> defineCollection(long seed) {
		// create Collection of random Set elements based on given seed
		Collection<Set<Integer>> col = SetGenerator.randomMinSetCollection(NUM_SETS, MAX_SIZE, seed);
		// collection taken from stored minSets.txt file
//		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("/minSets.txt");	
		// return converted sets
		return new TreeSet<BoolVectorSet>(c.convertCollection(col));
	}

	@Override
	protected BoolVectorSet defineMatch(BoolVectorSet test) {
		// remove one element from set to get a matching object
		Set<?> set = test.set;
		if (set.size() > 1) {
			set.remove(set.iterator().next());
		}
		return new BoolVectorSet(set);
	}

	@Override
	protected BoolVectorSet defineNotMatch(BoolVectorSet test) {
		// boolean vector must contain at least one true entry at a different position
		// compared to test
		boolean[] bv = test.setRepresentation;
		int len = bv.length;
		int i = -1;
		do {
			i++;
		} while (i < len && bv[i]);
		// increase array if only true values in test
		if (i == len) {
			bv = new boolean[len + 1];
			Arrays.fill(bv, true);
		}
		bv[i] = true;

		return new BoolVectorSet(bv);
	}

	@Override
	protected BoolVectorSet defineSmaller(BoolVectorSet test) {
		// define new boolean[] where one true value is changed to false
		boolean[] smaller = test.setRepresentation;
		smaller[Arrays.mismatch(smaller, new boolean[smaller.length])] = false;
		return new BoolVectorSet(smaller);

	}

	@Override
	protected long getSeed() {
		return currentSeed;
	}

	@Override
	protected BoolVectorSetChecker initializeOperator() {
		return new BoolVectorSetChecker();
	}

	@Override
	protected BoolVectorSet initSetRepresent() {
		return new BoolVectorSet();
	}

}
