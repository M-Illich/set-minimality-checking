package com.autoreason.setmincheck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SetGeneratorTest {
	
	@Test
	public void testAddMinimal() {
		Collection<Set<Integer>> col = new HashSet<Set<Integer>>();
		col.add(Set.of(1,2,3,4));
		col.add(Set.of(2,5,6));
		col.add(Set.of(1,5));
		col.add(Set.of(7,8,9));
		col.add(Set.of(6,8));	
		
		Set<Integer> set;
		
		// add superset -> should not be added
		set = Set.of(1,5,7);
		assertFalse(SetGenerator.addMinimal(col, set).contains(set));
		
		// add subset -> should replace supersets
		set = Set.of(8,9);
		Collection<Set<Integer>> col2 = SetGenerator.addMinimal(col, set);
		assertFalse(col2.contains(Set.of(7,8,9)));
		assertTrue(col2.contains(set));
		
		// add minimal -> only added without any change
		set = Set.of(1,3,8);
		Collection<Set<Integer>> col3 = SetGenerator.addMinimal(col, set);
		col.add(set);
		assertEquals(col3,col);
	}

}
