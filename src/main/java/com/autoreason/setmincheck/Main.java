package com.autoreason.setmincheck;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
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
//
//		BitVectorSetChecker bvsChecker = new BitVectorSetChecker();
//		Set<Integer> testSet = Set.of(1, 2, 3, 4, 7, 0, 5);
//		BitVectorSet testBVS = new BitVectorSet(testSet);
//		long[] testBV = testBVS.bitVector;
//		System.out.println("test: ");
//		for (int i = 0; i < testBV.length; i++) {
//			System.out.print(Long.toBinaryString(testBV[i]) + " ");
//		}
//		System.out.println();
//
//		NavigableSet<BitVectorSet> collection = new TreeSet<BitVectorSet>();
//		collection.add(new BitVectorSet(Set.of(1, 2, 3)));
//		collection.add(new BitVectorSet(Set.of(2, 4, 7)));
//		collection.add(new BitVectorSet(Set.of(5, 13)));
//		collection.add(new BitVectorSet(Set.of(8, 12, 4, 5)));
//		collection.add(new BitVectorSet(Set.of(0, 5, 2, 7)));
//		collection.add(new BitVectorSet(Set.of(10, 11, 9, 3)));
//
//		Iterable<BitVectorSet> matchIter = bvsChecker.matchesOf(collection, testSet);
//
//		System.out.println();
//		System.out.println("assumed: ");
//		for (BitVectorSet bvs : matchIter) {
//			long[] bv = bvs.bitVector;
//			for (int i = 0; i < bv.length; i++) {
//				System.out.print(Long.toBinaryString(bv[i]) + " ");
//			}
//			System.out.println();
//		}
//
//		Iterable<BitVectorSet> expected = new Iterable<BitVectorSet>() {
//			// transform Collection to NavigableSet
//			NavigableSet<BitVectorSet> naviCol = new TreeSet<BitVectorSet>(collection);
//
//			@Override
//			public Iterator<BitVectorSet> iterator() {
//				return new Iterator<BitVectorSet>() {
//					// initialize next element with first match
//					BitVectorSet next = getFirstCandidate();
//
//					@Override
//					public boolean hasNext() {
//						return next != null;
//					}
//
//					@Override
//					public BitVectorSet next() {
//						if (next != null) {
//							// safe current element
//							BitVectorSet cur = next;
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
//					private BitVectorSet getFirstCandidate() {
//						// get first element of collection
//						BitVectorSet cur = naviCol.first();
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
//					private BitVectorSet getNextCandidate(BitVectorSet cur) {
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
//		for (BitVectorSet bvs : expected) {
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
