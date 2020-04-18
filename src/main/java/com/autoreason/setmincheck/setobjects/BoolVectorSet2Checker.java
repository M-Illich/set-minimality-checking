package com.autoreason.setmincheck.setobjects;

import java.util.BitSet;
import java.util.Hashtable;
import java.util.Set;

import com.autoreason.setmincheck.MinimalityChecker;

public class BoolVectorSet2Checker extends MinimalityChecker<BoolVectorSet2> {

	// hash table to store different BoolVectorSet2 representations, i.e. BitSet
	// of different lengths, of tested sets
	Hashtable<String, BitSet> hashtable = new Hashtable<String, BitSet>();

	@Override
	public BoolVectorSet2 getNextMatch(BoolVectorSet2 previous, Set<?> test) {
		// get candidate BitSet
		BitSet candBitSet = (BitSet) previous.setRepresentation.clone();
		int candSize = candBitSet.size();

		// convert test to appropriate BitSet representation
		BitSet testBitSet = transform(test, candSize);
		// highest position of true entry in candidate
		int highCand = candBitSet.length() - 1;

		// check if next match can be found
		boolean changed = false;
		if ((previous.compareTo(new BoolVectorSet2(testBitSet)) > -1)) {
			// previous is not smaller than test -> no next match possible
			// try next length for representation
			if (candSize < test.size()) {
				// BitSet size is defined as multiple of 64 -> increase by 64
				candSize += 64;
				// create new candidate for new size
				candBitSet = new BitSet(candSize);
				changed = true;
				// candidate is empty -> no true value
				highCand = -1;
				// convert test to appropriate BitSet representation
				testBitSet = transform(test, candSize);
			} else {
				return null;
			}
		} else {
			// get position of highest true value that only occurs in the candidate
			while ((highCand > 0) && testBitSet.get(highCand)) {
				highCand = candBitSet.previousSetBit(highCand - 1);
			}
		}

		// get lowest position of true value that only occurs in test starting from
		// highCand
		int lowTest = testBitSet.nextSetBit(highCand + 1);
		// TODO
		if (lowTest > -1) {
			while ((lowTest < (testBitSet.length() - 1)) && candBitSet.get(lowTest)) {
				lowTest = testBitSet.nextSetBit(lowTest + 1);
			}
		} else {
			lowTest = 0;
		}

		// set true value at lowTest-position in candidate
		candBitSet.set(lowTest);
		if (!changed) {
			// clear all foregoing positions
			candBitSet.clear(0, lowTest);
		}

//		// TEST TODO
//		System.out.println("test: " + testBitSet.toString());
//		System.out.println("pre: " + previous.setRepresentation.toString());
//		System.out.println("next: " + candBitSet.toString());

		return new BoolVectorSet2(candBitSet);

	}

	@Override
	public boolean matches(BoolVectorSet2 candidate, Set<?> test) {
		// handle null
		if (candidate == null || test == null) {
			return false;
		}
		// get BitSet
		BitSet candBitSet = (BitSet) candidate.setRepresentation.clone();
		// candidate cannot be larger than test
		if (candBitSet.cardinality() > test.size()) {
			return false;
		} else {
			// convert test to appropriate BoolVectorSet2 representation
			BitSet testBitSet = transform(test, candBitSet.size());

			// subset candidate can only have 'true' entries at the same positions like
			// testBitSet
			candBitSet.andNot(testBitSet);
			return candBitSet.isEmpty();
		}

	}

	@Override
	protected boolean subsetOf(BoolVectorSet2 cand, Set<?> test) {
		// all elements of candidate's original set have to occur in test set
		return test.containsAll(cand.set);
	}

	/**
	 * Transform a {@link Set} element into a {@link BitSet} of given length
	 * 
	 * @param set    A {@link Set}
	 * @param length An {@code int} that determines the length of the {@link BitSet}
	 * @return A {@link BitSet} that contains a bit vector representing the elements
	 *         of {@code set}
	 */
	BitSet transform(Set<?> set, int length) {

		// look for long array representation of given length in hash table
		String key = defineHashKey(set, length);
		BitSet bv = hashtable.get(key);

		// no element found -> create new one
		if (bv == null) {
			// create bit vector of given length
			bv = new BitSet(length);

			// use elements of set to define position of true values
			for (Object e : set) {
				int hashcode = e.hashCode();
				// only allow positive values
				if (hashcode < 0) {
					hashcode *= -1;
				}
				// set true value in appropriate position
				bv.set(hashcode % length);
			}
			// add representation to hash table
			hashtable.put(key, bv);
		}

		// return boolean vector representing the set
		return bv;
	}

	/**
	 * Define a {@link String} that serves as key for a hash table
	 * 
	 * @param set A {@link Set}
	 * @param i   An {@link int} value
	 * @return A {@link String} based on the hashcode of {@code set} appended by the
	 *         space-separated value {@code i}
	 */
	private String defineHashKey(Set<?> set, int i) {
		// use hash code of set together with the given int value as key
		// TODO hashCode() not efficient for sets
		return set.size() + " " + i;
	}

}
