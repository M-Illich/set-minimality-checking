package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.setobjects.BitVecSetMatchProvider;
import com.autoreason.setmincheck.setobjects.BitVectorSet;

public class BitVSMatchIterableTest extends MatchIterableTest<BitVectorSet, Set<?>> {

	@Override
	protected MatchProvider<BitVectorSet, Set<?>> defineMatchProvider(Set<?> test) {
		return new BitVecSetMatchProvider(test);
	}

	@Override
	protected NavigableSet<BitVectorSet> defineCollections() {
		// collection taken from stored minSets.txt file
		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("/minSets.txt");
		// convert sets to BitVectorSet elements
		TreeSet<BitVectorSet> naviCol = new TreeSet<BitVectorSet>(new KeepSameSetRepComparator<BitVectorSet>());
		for (Set<Integer> set : col) {
			naviCol.add(new BitVectorSet(set));
		}
		// return converted sets
		return naviCol;
	}

	@Override
	protected Set<?> defineTest() {
		/// Set based on stored minSets.txt file
		return new HashSet<Integer>(Set.of(14, 15, 16, 17, 18));
	}

}
