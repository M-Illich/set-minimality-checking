package com.autoreason.setmincheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import com.autoreason.setmincheck.setobjects.BitVectorSet;
import com.autoreason.setmincheck.setobjects.BitVectorSetChecker;

/**
 * Main class to execute implementation
 *
 */
public class Main {
	public static void main(String[] args) {
		
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		
		String a = Set.of(1,2,3,5,6,7,23,54).toString() + " " + 2;
		String b = Set.of().toString() + " " + 2;
		
		hashtable.put(a, "a");
		hashtable.put(b, "a");
		
		System.out.println(hashtable.size());
		System.out.println(a);
		
		
			
//    	BitVectorSetChecker bvsChecker = new BitVectorSetChecker();    	
//    	Set<Integer> testSet = Set.of(3,6,7);	
//    	   					
//		NavigableSet<BitVectorSet> collection = new TreeSet<BitVectorSet>();
//		collection.add(bvsChecker.convert(new BitVectorSet(new long[2]), Set.of(5,13,66,120)));
//		collection.add(bvsChecker.convert(new BitVectorSet(new long[1]), Set.of(2,3,8,34)));
//		collection.add(bvsChecker.convert(new BitVectorSet(new long[1]), Set.of(5,13)));
//		collection.add(bvsChecker.convert(new BitVectorSet(new long[2]), Set.of(10,30,50,80,110)));
//		collection.add(bvsChecker.convert(new BitVectorSet(new long[3]), Set.of(0,1,94,138)));
//		collection.add(bvsChecker.convert(new BitVectorSet(new long[2]), Set.of(1,5,83,120)));
//		
//		for (BitVectorSet bvs : collection) {
//			long[] bv = bvs.bitVector;
//			for (int i = 0; i < bv.length; i++) {
//				System.out.print(Long.toBinaryString(bv[i]) + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println(bvsChecker.isMinimal(testSet, collection));

	}
}
