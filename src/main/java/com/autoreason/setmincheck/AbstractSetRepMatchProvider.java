package com.autoreason.setmincheck;

import java.util.Set;

import com.autoreason.setmincheck.setobjects.SetRepresent;

/**
 * An abstract class for {@link MatchProvider} when applied on
 * {@link SetRepresent}
 *
 * @param <S> An implementation of {@link SetRepresent} and {@link Comparable}
 * @param <R> The data type for the set representation used by the
 *            implementation of {@link Represent}
 */
public abstract class AbstractSetRepMatchProvider<S extends SetRepresent<R> & Comparable<S>, R>
		implements MatchProvider<S, Set<?>> {

	/**
	 * Set representation for test set with which matchings are defined
	 */
	public R testRepresent;

}
