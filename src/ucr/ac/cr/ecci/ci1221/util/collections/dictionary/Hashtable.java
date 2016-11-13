package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

import java.util.ListIterator;

/**
 * @author Ian Duran
 */
public class Hashtable<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    private final int INITIAL_SIZE = 40;
    private final int INITIAL_SUBARRAY_SIZE = 1;
    private K[] keys;
    private V[][] values;
    private int size;
    private int numOfKeys;

    /**
     * Costructor for the Hashtable class
     */
    public Hashtable(){
        keys = (K[]) new Comparable[INITIAL_SIZE];
        values = (V[][]) new Object[INITIAL_SIZE][INITIAL_SUBARRAY_SIZE];
        size = 0;
        numOfKeys = 0;
    }

    /**
     * Returns the number of elements stored in the Hash Table
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether the Hash Table is empty or not
     * @return true if its empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether a given object is and object of the K class, and if so, it tells if its a key in the Hash Table.
     * @param key key whose presence in this dictionary is to be tested
     * @return true if the key is in the Hash Table, false otherwise.
     */
    @Override
    public boolean containsKey(Object key) {
        return values[key.hashCode() % values.length] != null;
    }

    /**
     * Returns whether a given object is an object stored in the Hash.
     * @param value value whose presence in this dictionary is to be tested
     * @return
     */
    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    /**
     * Returns the first element in the bucket of the given key.
     * @param key the key whose associated value is to be returned
     * @return the object to whom the key is associated, null if there's no object associated to the key.
     */
    @Override
    public V get(K key) {
        return values[key.hashCode() % values.length][0];
    }

    /**
     * Stores an element in the Hash Table and if there's already an item in the bucket, returns it.
     * First it gets the modulus of the hash code of the key and assigns the value object to the bucket
     * corresponding to that number, if there is already and object in the bucket, the bucket is enlarged.
     * It also stores the key in an array for the keys.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return
     */
    @Override
    public V put(K key, V value) {
        V toReturn = null;
        int bucket = key.hashCode() % values.length;
        if(values[bucket][0] != null)
            toReturn = values[bucket][0];

        size++;
        keys[numOfKeys] = key;
        numOfKeys++;
        if(values[bucket][values[bucket].length - 1] != null)
            this.enlargeBucket(bucket);

        values[bucket][values[bucket].length - 1] = value;
        return toReturn;
    }

    /**
     * Clears the Hash Table from any value associated to the given key
     * and removes the key from the key array.
     * @param key key whose mapping is to be removed from the dictionary.
     * @return the first object of the removed hash.
     */
    @Override
    public V remove(K key) {
        int bucket = key.hashCode() % values.length;
        V toReturn = values[bucket][0];
        if(toReturn != null){
            values[bucket] = (V[]) new Object[INITIAL_SUBARRAY_SIZE];
            int index = 0;
            for(int i = 0; i < numOfKeys; i++)
                if(keys[i].compareTo(key) == 0)
                    index = i;
            this.moveDown(index);
            numOfKeys--;
        }
        return toReturn;
    }

    /**
     * Clears the Hash Table by setting it's variables to the default state
     */
    @Override
    public void clear() {
        keys = (K[]) new Comparable[INITIAL_SIZE];
        values = (V[][]) new Object[INITIAL_SIZE][INITIAL_SUBARRAY_SIZE];
        numOfKeys = 0;
        size = 0;
    }

    /**
     * Puts all the keys in a set and returns the set.
     * @return a set containing all the keys in the hash.
     */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new BinarySearchTree<>();
        for(int i = 0; i < numOfKeys;i++)
            if(keys[i] != null)
                keySet.put(keys[i]);

        return keySet;
    }

    /**
     * Returns a list containing all the values of the Hash Table.
     * @return a list containing all the values of the Hash Table.
     */
    @Override
    public List<V> values() {
        List<V> valuesList = new LinkedList<>();
        for(int i = 0; i < values.length; i++)
            for(int j = 0; j < values[i].length; j++)
                if(values[i][j] != null)
                    valuesList.add(values[i][j]);

        return valuesList;
    }

    /**
     * Moves down one position all the keys form the key array starting from a given index.
     * @param index index to start moving the keys.
     */
    private void moveDown(int index){
        for(int i = index; i < numOfKeys; i++)
            keys[i] = keys[i + 1];
    }

    /**
     * Private method that enlarges the bucked in the position index of the values matrix.
     * @param index index of the bucket that should be enlarged.
     */
    private void enlargeBucket(int index){
        V[] newBucket = (V[]) new Object[values[index].length + INITIAL_SUBARRAY_SIZE];
        for(int i = 0; i < values[index].length; i++)
            newBucket[i] = values[index][i];

        values[index] = newBucket;
    }
}
