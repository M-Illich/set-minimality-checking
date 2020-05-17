package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public abstract class SetRepresentTest<R> {

	SetRepresent<R> setRepresent = defineSetRepresent();

	@Test
	public void testConvertSet() {
		Set<?> test = defineTest();
		R expectConSet = defineConvert();

		assertTrue(equal(expectConSet, setRepresent.convertSet(test, null)));
	}

	/**
	 * Determine if two objects of type {@code R} are equal
	 * 
	 * @param a An object of type {@code R}
	 * @param b An object of type {@code R}
	 * @return {@code true} if {@code a} and {@code b} are equal, otherwise
	 *         {@code false}
	 */
	protected abstract boolean equal(R a, R b);

	/**
	 * Define an object of type {@code R} that represents the used test {@link Set}
	 * provided by {@link #defineTest()}
	 * 
	 * @return An instance of type {@code R} that represents {@code set}
	 */
	protected abstract R defineConvert();

	/**
	 * Define an instance of the tested {@link SetRepresent} implementation deployed
	 * for calling the {@link SetRepresent#convertSet} method
	 * 
	 * @return An instance of the tested {@link SetRepresent} implementation
	 */
	protected abstract SetRepresent<R> defineSetRepresent();

	/**
	 * Define an {@link Set} that can be used for testing
	 * 
	 * @return A {@link Set} randomly generated with {@code seed}
	 */
	protected abstract Set<?> defineTest();

}
