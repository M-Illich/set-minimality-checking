package com.autoreason.setmincheck;

import static org.junit.Assert.assertTrue;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import com.autoreason.setmincheck.setobjects.BitVectorSet;
import com.autoreason.setmincheck.setobjects.BitVectorSetCheckerOld;

public abstract class SetMinimalityCheckerTest<C extends Comparable<C>> implements MatchProvider<C> {

		
	@Test
	public void testIsMinimal() {	
		BitVectorSetCheckerOld bvsChecker = new BitVectorSetCheckerOld();    	
    	Set<Integer> testSet = Set.of(1,32,70,104);		
				
		NavigableSet<BitVectorSet> collection = new TreeSet<BitVectorSet>();
		collection.add(bvsChecker.convert(new BitVectorSet(new long[2]), Set.of(5,13,66,120)));
		collection.add(bvsChecker.convert(new BitVectorSet(new long[1]), Set.of(2,3,8,34)));
		collection.add(bvsChecker.convert(new BitVectorSet(new long[1]), Set.of(5,13)));
		collection.add(bvsChecker.convert(new BitVectorSet(new long[2]), Set.of(10,30,50,80,110)));
		collection.add(bvsChecker.convert(new BitVectorSet(new long[3]), Set.of(0,1,94,138)));
		collection.add(bvsChecker.convert(new BitVectorSet(new long[2]), Set.of(1,5,83,120)));
						
		assertTrue(bvsChecker.isMinimal(testSet, collection));
		
	}
	
		

}
