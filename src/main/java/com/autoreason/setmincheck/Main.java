package com.autoreason.setmincheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;

import com.autoreason.setfileconverter.FileSetConverter;
import com.autoreason.setmincheck.setobjects.BoolVectorSet;
import com.autoreason.setmincheck.setobjects.BoolVectorSetChecker;


/**
 * Main class to execute implementation
 *
 */
public class Main {
	public static void main(String[] args) {
				
	
		BoolVectorSetChecker bvsChecker = new BoolVectorSetChecker();
		Set<Integer> testSet = Set.of(0,3,5,8,10); // Set.of(1, 2, 3, 4, 7, 0, 5);
		BoolVectorSet testBVS = new BoolVectorSet(testSet);
		boolean[] testBV = testBVS.setRepresentation;
//		System.out.println("test: ");		
//		System.out.println(Arrays.toString(testBV));	
//		System.out.println();

		NavigableSet<BoolVectorSet> collection = new TreeSet<BoolVectorSet>();
		collection.add(new BoolVectorSet(Set.of(1, 2, 3)));
		collection.add(new BoolVectorSet(Set.of(2, 4, 7)));
		collection.add(new BoolVectorSet(Set.of(5, 13)));
		collection.add(new BoolVectorSet(Set.of(8, 12, 4, 5)));
		collection.add(new BoolVectorSet(Set.of(0, 5, 2, 7)));
		collection.add(new BoolVectorSet(Set.of(10, 11, 9, 3)));
		
		BoolVectorSet cand = new BoolVectorSet(new boolean[]{false, true, true, true, true});
//		System.out.println(Arrays.toString(bvsChecker.getNextMatch(cand, testSet).setRepresentation));
//		System.out.println();
		
		System.out.println(bvsChecker.matches(cand, testSet));
		System.out.println(Arrays.compare(new boolean[] {false, true, true, true, true}, new boolean[] {true, false, false, true, false}));
		
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
