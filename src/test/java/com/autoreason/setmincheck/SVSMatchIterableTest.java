package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.setobjects.SumVecSetMatchProvider;
import com.autoreason.setmincheck.setobjects.SumVectorSet;

public class SVSMatchIterableTest extends MatchIterableTest<SumVectorSet, Set<?>> {

	@Override
	protected MatchProvider<SumVectorSet, Set<?>> defineMatchProvider() {
		return new SumVecSetMatchProvider();
	}

	@Override
	protected NavigableSet<SumVectorSet> defineCollections() {
		// collection taken from stored minSets.txt file
		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("/minSets.txt");
		// convert sets to BitVectorSet2 elements
		TreeSet<SumVectorSet> naviCol = new TreeSet<SumVectorSet>();
		for (Set<Integer> set : col) {
			naviCol.add(new SumVectorSet(set));
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
