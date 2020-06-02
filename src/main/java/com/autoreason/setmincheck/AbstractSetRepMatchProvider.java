package com.autoreason.setmincheck;

import java.util.HashMap;
import java.util.Set;

import com.autoreason.setmincheck.setobjects.SetRepresent;

/**
 * An abstract class for {@link MatchProvider} when applied on
 * {@link SetRepresent}
 *
 * @param <S> An implementation of {@link SetRepresent} and {@link Comparable}
 * @param <R> The data type for the set representation used by the
 *            implementation of {@link Represent}
 * @param <T> The data type of the parameter that determines the form of the set
 *            representation of type {@code R}
 */
public abstract class AbstractSetRepMatchProvider<S extends SetRepresent<R> & Comparable<S>, R, T>
		implements MatchProvider<S, Set<?>> {

	// hash table to store different set representations
	HashMap<Integer, R> hashtable = new HashMap<Integer, R>();

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
	public R getRepresentation(Set<?> set, T attr) {

		// look for representation with given attribute in hash table
		Integer key = defineHashKey(set, attr);
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
	 * based on a given attribute (e.g. length)
	 * 
	 * @param set  The {@link Set} that is transformed into the representation of
	 *             type {@code R}
	 * @param attr An object of type {@code T} that determines the form of the
	 *             intended set representation
	 * @return An object of type {@code R} that represents the given {@code set}
	 */
	protected abstract R convertSet(Set<?> set, T attr);

	/**
	 * Define a {@link Integer} that serves as key for a hash table
	 * 
	 * @param set  A {@link Set}
	 * @param attr An object of type {@code T} serving as additional attribute to
	 *             define the hash key
	 * @return A {@link Integer} based on {@code set} and {@code attr}
	 */
	private Integer defineHashKey(Set<?> set, T attr) {
		// in the associated setmincheck-experiment, the keys are only defined for one
		// test set, which is why we can directly use attr as key being an Integer for
		// every tested SetRepresent implementation
		return (Integer) attr;
	}

}
