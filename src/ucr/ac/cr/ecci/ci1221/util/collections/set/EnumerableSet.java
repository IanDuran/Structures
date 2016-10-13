package ucr.ac.cr.ecci.ci1221.util.collections.set;

/**
 * @author Rodrigo A. Bartels
 */
public interface EnumerableSet<T extends Enumerable>{

    /**
     * Returns a new enumerable set containing the union of the set and the given parameter.
     *
     * @param set the set to union.
     *
     * @return an enumerable set.
     */
    EnumerableSet<T> union(EnumerableSet<T> set);

    /**
     * Returns a new enumerable set containing the intersection of the set and the given parameter.
     *
     * @param set the set to intersect.
     *
     * @return a enumerable set.
     */
    EnumerableSet<T> intersection(EnumerableSet<T> set);

    /**
     * Returns a new enumerable set containing the symmetric difference of the set and the given parameter.
     *
     * @param set the set to substract.
     *
     * @return a enumerable set.
     */
    EnumerableSet<T> difference(EnumerableSet<T> set);

    /**
     * Validates if the set contains the given element.
     *
     * @param key the element to look for.
     *
     * @return true if the set contains the element, false otherwise.
     */
    boolean isMember(T key);

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
     * Returns the quantity of elements contained in the set.
     *
     * @return set size.
     */
    int size();
}
