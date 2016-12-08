package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

/**
 * A dictionary that doesn't allow nulls as keys or values, it allows at most one value for
 * each given key.
 *
 * @author Rodrigo A. Bartels
 */
public interface Dictionary<K, V> {

    /**
     * Returns the number of key-value mappings in this dictionary.  If the
     * dictionary contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this dictionary
     */
    int size();

    /**
     * Returns <tt>true</tt> if this dictionary contains no key-value mappings.
     *
     * @return <tt>true</tt> if this dictionary contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Returns <tt>true</tt> if this dictionary contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this dictionary contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this dictionary is to be tested
     * @return <tt>true</tt> if this dictionary contains a mapping for the specified
     *         key
     * @throws NullPointerException if the specified key is null and this dictionary
     *         does not permit null keys.
     */
    boolean containsKey(Object key);

    /**
     * Returns <tt>true</tt> if this dictionary maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this dictionary contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.
     *
     * @param value value whose presence in this dictionary is to be tested
     * @return <tt>true</tt> if this dictionary maps one or more keys to the
     *         specified value
     * @throws NullPointerException if the specified value is null.
     */
    boolean containsValue(Object value);

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this dictionary contains no mapping for the key.
     *
     * <p>More formally, if this dictionary contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this dictionary contains no mapping for the key
     * @throws NullPointerException if the specified key is null.
     */
    V get(K key);

    /**
     * Associates the specified value with the specified key in this dictionary.
     * If the dictionary previously contained a mapping for the key, the old value
     * is replaced by the specified value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws NullPointerException if the specified key or value is null
     *         and this dictionary does not permit null keys or values
     */
    V put(K key, V value);

    /**
     * Removes the mapping for a key from this dictionary if it is present
     * (optional operation).   More formally, if this dictionary contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The dictionary can contain at most one such mapping.)
     *
     * <p>Returns the value to which this dictionary previously associated the key,
     * or <tt>null</tt> if the dictionary contained no mapping for the key.
     *
     * <p>The dictionary will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the dictionary
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws NullPointerException if the specified key is null and this
     *         dictionary does not permit null keys
     */
    V remove(K key);

    /**
     * Removes all of the mappings from this dictionary. The dictionary will be empty after
     * this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *         is not supported by this dictionary
     */
    void clear();

    /**
     * Returns a {@link Set} view of the keys contained in this dictionary.
     *
     * @return a set view of the keys contained in this dictionary
     */
    Set<K> keySet();

    /**
     * Returns a {@link List} view of the values contained in this dictionary.
     *
     * @return a collection view of the values contained in this dictionary
     */
    List<V> values();
}
