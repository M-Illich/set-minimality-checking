package com.autoreason.setmincheck.setobjects;

import java.util.BitSet;
import java.util.Set;

import com.autoreason.setmincheck.AbstractSetRepMatchProvider;

/**
 * An implementation of {@link AbstractSetRepMatchProvider} for
 * {@link BoolVectorSet2}
 *
 */
public class BoolVecSet2MatchProvider extends AbstractSetRepMatchProvider<BoolVectorSet2, BitSet> {

	@Override
	public BoolVectorSet2 getSmallestMatchGreaterOrEqual(BoolVectorSet2 current, Set<?> test) {
		// get candidate BitSet
		BitSet candBitSet = (BitSet) current.setRepresentation.clone();
		int candSize = candBitSet.size();

		// convert test to appropriate BitSet representation
		BitSet testBitSet = getRepresentation(test, candSize);
		// highest position of true entry in candidate
		int highCand = candBitSet.length() - 1;

		// check if next match can be found
		boolean changed = false;
		// get comparison value
		int compareValue = current.compareTo(new BoolVectorSet2(testBitSet));
		// check if current is already a match
		if (compareValue != 0) {
			// check if next match can be found
			if (compareValue == 1) {
				// current is not smaller than test -> no next match possible
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
					testBitSet = getRepresentation(test, candSize);
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
		}
		return new BoolVectorSet2(candBitSet);
	}

	@Override
	protected BitSet convertSet(Set<?> set, Object attr) {
		return new BoolVectorSet2(new BitSet()).convertSet(set, attr);
	}

}
