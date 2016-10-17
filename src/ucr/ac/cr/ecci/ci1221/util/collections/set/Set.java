package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Rodrigo A. Bartels
 */
public interface Set<T>{

    /**
     * Returns a new set containing the union of the set and the given parameter.
     *
     * @param set the set to union.
     *
     * @return a set.
     */
    Set<T> union(Set<T> set);

    /**
     * Returns a new set containing the intersection of the set and the given parameter.
     *
     * @param set the set to intersect.
     *
     * @return a set.
     */
    Set<T> intersection(Set<T> set);

    /**
     * Returns a new set containing the symmetric difference of the set and the given parameter.
     *
     * @param set the set to substract.
     *
     * @return a set.
     */
    Set<T> difference(Set<T> set);

    /**
     * Validates if the set contains the given element.
     *
     * @param key the element to look for.
     *
     * @return true if the set contains the element, false otherwise.
     */
    boolean isMember(T key);

    /**
     * Adds the given element to the set.
     *
     * @param key the element to add.
     */
    void put(T key);

    /**
     * Removes the given element to the set.
     *
     * @param key the element to remove.
     */
    void remove(T key);

    /**
     * Checks if the set is empty or not.
     *
     * @return true if the set has no elements, false otherwise.
     */
    boolean isEmpty();

    /**
     * Removes all the elements from the set.
     */
    void clear();

    /**
     * Returns the quantity of elements contained in the set.
     *
     * @return set size.
     */
    int size();

    /**
     * Returns an iterator over the elements of the set. It doesn't guarantee any given order of
     * the elements.
     *
     * @return an iterator.
     */
    Iterator<T> iterator();
}
