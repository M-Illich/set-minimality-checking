package com.autoreason.setmincheck.setobjects;

import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

public abstract class SetRepresentTest<C extends SetRepresent<C, ?, ?>> {
	// operator to call methods
	C operator = initializeOperator();

	@Test
	public abstract void testConvertSet();

	@Test
	public void testCompareTo() {
		C a = operator.convertSet(Set.of(1, 2));
		C b = operator.convertSet(Set.of(1, 2));
		C c = operator.convertSet(Set.of(2, 4, 6));

		assertTrue(a.compareTo(b) == 0);
		assertTrue(a.compareTo(c) == -1);
		assertTrue(c.compareTo(b) == 1);
	}

	/**
	 * Initialize the object to call the tested methods
	 * 
	 * @return An (arbitrary) object of type {@code C}
	 */
	protected abstract C initializeOperator();

}
