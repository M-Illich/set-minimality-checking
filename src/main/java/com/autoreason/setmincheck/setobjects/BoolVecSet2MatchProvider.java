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

	public BoolVecSet2MatchProvider(Set<?> test) {
		testRepresent = new BoolVectorSet2Converter().convertSet(test);
	}

	@Override
	public BoolVectorSet2 getSmallestMatchGreaterOrEqual(BoolVectorSet2 current, Set<?> test) {
		// return null if no match can be found
		if (current == null) {
			return null;
		}

		// get candidate BitSet
		BitSet candBitSet = (BitSet) current.setRepresentation.clone();		

		// get test BitSet representation
		BitSet testBitSet = (BitSet) testRepresent.clone();
		// highest position of true entry in candidate
		int highCand = candBitSet.length() - 1;

		// check if next match can be found
		boolean changed = false;
		// compare vectors
		int compareValue = 0;
		// remove all bits from candidate that appear in test
		BitSet candBitSetCopy = (BitSet) candBitSet.clone();
		candBitSetCopy.andNot(testBitSet);

		// candidate must not possess any entry beside the ones from test
		if (!candBitSetCopy.isEmpty()) {
			// compare highest bit from candidate not occurring in test with the highest one
			// from test not occurring in candidate
			BitSet testBitSetCopy = (BitSet) testBitSet.clone();
			testBitSetCopy.andNot(candBitSet);
			compareValue = Integer.compare(candBitSetCopy.length(), testBitSetCopy.length());
		}

		// check if current is already a match
		if (compareValue != 0) {
			// check if next match can be found
			if (compareValue == 1) {
				// next matching not available
				return null;

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
		// return BoolVectorSet2 representation of next match (with set of previous one
		// to distinguish between instances with equal set representations)
		return new BoolVectorSet2(current.originalSet, candBitSet);

	}

}
