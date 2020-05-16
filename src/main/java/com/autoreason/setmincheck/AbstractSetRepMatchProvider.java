package com.autoreason.setmincheck;

import java.util.HashMap;
import java.util.Set;

import com.autoreason.setmincheck.setobjects.AbstractSetRepresent;

/**
 * An abstract class for {@link MatchProvider} when applied on
 * {@link AbstractSetRepresent}
 *
 * @param <S> An implementation of {@link AbstractSetRepresent} and
 *            {@link Comparable}
 * @param <R> The data type for the set representation used by the
 *            implementation of {@link AbstractSetRepresent}
 */
public abstract class AbstractSetRepMatchProvider<S extends AbstractSetRepresent<R> & Comparable<S>, R>
		implements MatchProvider<S, Set<?>> {

	// hash table to store different set representations
	HashMap<String, R> hashtable = new HashMap<String, R>();

	/**
	 * Get the {@code R}-type representation with a certain attribute of a
	 * {@link Set}. The representation is either retrieved from a hash table or
	 * created if not present.
	 * 
	 * @param set  The {@link Set} for which the representation is returned
	 * @param attr An {@code Object} serving as attribute to define the form of the
	 *             representation (e.g. the length of an array)
	 * @return An object of type {@code R} that represents {@code set}
	 */
	public R getRepresentation(Set<?> set, Object attr) {

		// look for representation with given attribute in hash table
		String key = defineHashKey(set, attr);
		R setRep = hashtable.get(key);

		// no element found -> create new one
		if (setRep == null) {
			// transform set to representation form
			setRep = convertSet(set, attr);
			// add representation to hash table
			hashtable.put(key, setRep);
		}

		return setRep;
	}

	/**
	 * Convert a {@link Set} into the defined representation form of type {@code R}
	 * w.r.t. to a given attribute
	 * 
	 * @param set  The {@link Set} that is transformed into the representation of
	 *             type {@code R}
	 * @param attr An {@code Object} serving as attribute to define the form of the
	 *             intended representation (e.g. the length of a generated array)
	 * @return An object of type {@code R} that represents the given {@code set}
	 */
	protected abstract R convertSet(Set<?> set, Object attr);

	/**
	 * Define a {@link String} that serves as key for a hash table
	 * 
	 * @param set  A {@link Set}
	 * @param attr An {@code Object} serving as additional attribute to define the
	 *             hash key
	 * @return A {@link String} based on {@code set} and {@code attr}
	 */
	private String defineHashKey(Set<?> set, Object attr) {
		// create String TODO
		return set.size() + " " + attr;
	}

}
