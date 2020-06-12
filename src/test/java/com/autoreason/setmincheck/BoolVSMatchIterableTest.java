package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.setobjects.BoolVecSetMatchProvider;
import com.autoreason.setmincheck.setobjects.BoolVectorSet;

public class BoolVSMatchIterableTest extends MatchIterableTest<BoolVectorSet, Set<?>> {

	@Override
	protected MatchProvider<BoolVectorSet, Set<?>> defineMatchProvider() {
		return new BoolVecSetMatchProvider();
	}

	@Override
	protected NavigableSet<BoolVectorSet> defineCollections() {
		// collection taken from stored minSets.txt file
		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("/minSets.txt");
		// convert sets to BoolVectorSet elements
		TreeSet<BoolVectorSet> naviCol = new TreeSet<BoolVectorSet>(new KeepSameSetRepComparator<BoolVectorSet>());
		for (Set<Integer> set : col) {
			naviCol.add(new BoolVectorSet(set));
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
