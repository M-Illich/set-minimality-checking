package com.autoreason.setmincheck;
import java.util.Set;

/**
 * 
 *
 * @param <C>
 */
public interface CandidateIteratorTest<C extends Comparable<C>> {
	
	// TODO tests
	
	C getNextCandidate(C previous, C test);
	
	
	boolean isCandidateOf(C first, C second);
	
	
	C convert(C example, Set<?> set);
	
		
}