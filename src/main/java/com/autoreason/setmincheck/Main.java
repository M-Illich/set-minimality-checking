package com.autoreason.setmincheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BooleanSupplier;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.setobjects.BitVectorSet;
import com.autoreason.setmincheck.setobjects.BitVectorSetChecker;
import com.autoreason.setmincheck.setobjects.BoolVectorSet;
import com.autoreason.setmincheck.setobjects.BoolVectorSet2;
import com.autoreason.setmincheck.setobjects.BoolVectorSet2Checker;
import com.autoreason.setmincheck.setobjects.BoolVectorSetChecker;

/**
 * Main class to execute implementation
 *
 */
public class Main {
	public static void main(String[] args) {
//		
//		BitVectorSetChecker checker = new BitVectorSetChecker();
//		Set<Integer> set = Set.of(1,2,3,4,5,6,7,8,9,11,33,44,65,656,123);
//		long start = System.nanoTime();
//		for (int j = 1; j < 20; j++) {
//			checker.transform(set,j);			
//		}
//		System.out.println(System.nanoTime() - start);
//		start = System.nanoTime();
//		for (int j = 1; j < 8; j++) {
//			checker.transform(set,j);
//		}
//		System.out.println(System.nanoTime() - start);
					

//		boolean[] a = new boolean[1000];
//		boolean[] b = new boolean[1000];
//		a[a.length - 1] = true;
//		b[b.length - 1] = true;
//		a[0] = true;
//		b[0] = true;
//
//		int c = 0;
//		long start = System.nanoTime();

//		int m;
//		int j = a.length;
//		int i = j / 2;			
//		boolean foundMis = false;
//		while (i > 0 && i < j) {				
//			// get position of next mismatch
//			m = Arrays.mismatch(a, i, j, b, i, j);
//			
//			// TEST TODO
////			System.out.println("i: " + i + ", j: " + j + ", m: " + m);
//			
//			// no mismatch found
//			if (m < 0) {
//				// if mismatch found previously, stop search
//				if (foundMis) {
//					break;
//				} 
//				// consider foregoing half of indices
//				else {						
//					int end = j;
//					j = i;
//					i -= (end - i) / 2 + (end - i) % 2;
//				}
//
//			} 
//			// mismatch found
//			else {
//				// keep looking for further mismatch at higher position
//				i += m + 1;
//				foundMis = true;
//			}
//		}
//		// compare values at highest found mismatch position
//		if(i < 1) {
//			i = 1;
//		}
//		c = Boolean.compare(a[i-1], b[i-1]);
//		
//		
//		int i = a.length - 1;
//		while (i >= 0 && c == 0) {
//			// comparison based on last entry in array
//			c = Boolean.compare(a[i], b[i]);
//			i--;				
//		}
//
//		System.out.println(c);
//		System.out.println(System.nanoTime() - start);

//		
//		Set<Integer> set =  new TreeSet<Integer>();
//		for (int i = 0; i < 66666666; i++) {
//			set.add(i);
//		}				
//		BoolVectorSet a = new BoolVectorSet(set);
//		set.remove(5);
//		BoolVectorSet b = new BoolVectorSet(set);
//		
//		long start = System.nanoTime();
//		for (int i = 0; i < 100; i++) {
//			a.compareTo(b);
//		}		
//		System.out.println(System.nanoTime() - start);
//	
//		BoolVectorSet2Checker bvsChecker = new BoolVectorSet2Checker();
//		Set<Integer> testSet = Set.of(0,3,5,8,10); // Set.of(1, 2, 3, 4, 7, 0, 5);
//		BoolVectorSet2 testBVS = new BoolVectorSet2(testSet);
//		BitSet testBV = testBVS.setRepresentation;
//		System.out.println("test: ");		
//		System.out.println(testBV.toString());	
//		System.out.println();
//		
//		NavigableSet<BoolVectorSet2> collection = new TreeSet<BoolVectorSet2>();
//		collection.add(new BoolVectorSet2(Set.of(1, 2, 3)));
//		collection.add(new BoolVectorSet2(Set.of(2, 4, 7)));
//		collection.add(new BoolVectorSet2(Set.of(5, 13)));
//		collection.add(new BoolVectorSet2(Set.of(8, 12, 4, 5)));
//		collection.add(new BoolVectorSet2(Set.of(0, 5, 2, 7)));
//		collection.add(new BoolVectorSet2(Set.of(10, 11, 9, 3)));	
//		
//		for(Set<Integer> set : FileSetConverter.readSetsFromFile("src\\test\\resources\\minSets.txt")) {
//			collection.add(new BoolVectorSet2(set));
//		}
//		Iterable<BoolVectorSet2> matchCol = bvsChecker.matchesOf(collection, Set.of(14, 15, 16, 17, 18));	
//		for (BoolVectorSet2 b : matchCol) {
//			System.out.println(b.setRepresentation.toString());
//		}
//		
//		BoolVectorSet2 cand = new BoolVectorSet2(Set.of(33, 36, 50));
//		System.out.println(bvsChecker.getNextMatch(cand, Set.of(14, 15, 16, 17, 18)).setRepresentation.toString());
//		System.out.println();
//		
//		System.out.println(bvsChecker.matches(cand, testSet));
//		System.out.println(Arrays.compare(new boolean[] {false, true, true, true, true}, new boolean[] {true, false, false, true, false}));
//		
//		
//		NavigableSet<BoolVectorSet> col2 = new TreeSet<BoolVectorSet>();
//		col2.add(new BoolVectorSet(Set.of(1,2,3,4,6)));
//		BoolVectorSet nxt = col2.ceiling(new BoolVectorSet(Set.of(1,2,3,4)));
//		System.out.println(Arrays.toString(nxt.setRepresentation));

//		
//		for (BoolVectorSet bvs : collection) {
//			boolean[] bv = bvs.setRepresentation;
//			System.out.println(Arrays.toString(bv));		
//		}
//		System.out.println();
//
//		Iterable<BoolVectorSet> matchIter = bvsChecker.matchesOf(collection, testSet);
//
//		System.out.println();
//		System.out.println("assumed: ");
//		for (BoolVectorSet bvs : matchIter) {
//			boolean[] bv = bvs.setRepresentation;
//			System.out.println(Arrays.toString(bv));			
//		}
//
//		Iterable<BoolVectorSet> expected = new Iterable<BoolVectorSet>() {
//			// transform Collection to NavigableSet
//			NavigableSet<BoolVectorSet> naviCol = new TreeSet<BoolVectorSet>(collection);
//
//			@Override
//			public Iterator<BoolVectorSet> iterator() {
//				return new Iterator<BoolVectorSet>() {
//					// initialize next element with first match
//					BoolVectorSet next = getFirstCandidate();
//
//					@Override
//					public boolean hasNext() {
//						return next != null;
//					}
//
//					@Override
//					public BoolVectorSet next() {
//						if (next != null) {
//							// safe current element
//							BoolVectorSet cur = next;
//							// get next match being greater than current
//							next = getNextCandidate(next);
//							// return current element
//							return cur;
//						}
//						// no next element available
//						else {
//							throw new NoSuchElementException();
//						}
//					}
//
//					private BoolVectorSet getFirstCandidate() {
//						// get first element of collection
//						BoolVectorSet cur = naviCol.first();
//						// return first element if it is a match of test
//						if (bvsChecker.matches(cur, testSet)) {
//							return cur;
//						}
//						// else, look for next match in collection
//						else {
//							return getNextCandidate(cur);
//						}
//					}
//
//					private BoolVectorSet getNextCandidate(BoolVectorSet cur) {
//						// look for next match in collection
//						while (cur != null) {
//							// get next element from collection
//							cur = naviCol.higher(cur);
//
//							// check if candidate is a match of test
//							if (bvsChecker.matches(cur, testSet)) {
//								break;
//							}
//
//						}
//						// return next element
//						return cur;
//					}
//
//				};
//			}
//
//		};
//
//		System.out.println();
//		System.out.println("expected: ");
//		for (BoolVectorSet bvs : expected) {
//			boolean[] bv = bvs.setRepresentation;
//			System.out.println(Arrays.toString(bv));
//		}

//		
//		System.out.println(bvsChecker.isMinimal(testSet, collection));

	}
}
