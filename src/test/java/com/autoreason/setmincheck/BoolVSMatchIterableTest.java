package com.autoreason.setmincheck;

import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.setobjects.BoolVecSet2MatchProvider;
import com.autoreason.setmincheck.setobjects.BoolVecSetMatchProvider;
import com.autoreason.setmincheck.setobjects.BoolVectorSet;
import com.autoreason.setmincheck.setobjects.BoolVectorSet2;

public class BoolVSMatchIterableTest extends MatchIterableTest<BoolVectorSet2, Set<?>> {

	@Override
	protected MatchProvider<BoolVectorSet2, Set<?>> defineMatchProvider() {
		return new BoolVecSet2MatchProvider();
	}

	@Override
	protected NavigableSet<BoolVectorSet2> defineCollections() {
		// collection taken from stored minSets.txt file
		Collection<Set<Integer>> col = FileSetConverter.readSetsFromFile("/minSets.txt");
		// convert sets to BoolVectorSet elements
		TreeSet<BoolVectorSet2> naviCol = new TreeSet<BoolVectorSet2>();
		for (Set<Integer> set : col) {
			naviCol.add(new BoolVectorSet2(set));
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
