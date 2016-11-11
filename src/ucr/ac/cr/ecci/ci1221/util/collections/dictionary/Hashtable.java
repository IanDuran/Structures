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
    private V[] values;
    private int size;
    private int numOfKeys;

    public Hashtable(){
        keys = (K[]) new Comparable[INITIAL_SIZE];
        values = (V[]) new Object[INITIAL_SIZE];
        size = 0;
        numOfKeys = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return values[key.hashCode() % values.length] != null;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(K key) {
        return values[key.hashCode() % values.length];
    }

    @Override
    public V put(K key, V value) {
        V toReturn = null;
        int bucket = key.hashCode() % values.length;
        if(values[bucket] != null)
            toReturn = values[bucket];
        else{
            size++;
            keys[numOfKeys] = key;
            numOfKeys++;
        }
        values[bucket] = value;
        return toReturn;
    }

    @Override
    public V remove(K key) {
        int bucket = key.hashCode() % values.length;
        V toReturn = values[bucket];
        if(toReturn != null){
            values[bucket] = null;
            int index = 0;
            for(int i = 0; i < numOfKeys; i++)
                if(keys[i].compareTo(key) == 0)
                    index = i;
            moveDown(index);
            numOfKeys--;
        }
        return toReturn;
    }

    @Override
    public void clear() {
        keys = (K[]) new Comparable[40];
        values = (V[]) new Object[40];
        numOfKeys = 0;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new BinarySearchTree<>();
        for(int i = 0; i < numOfKeys;i++){
            if(keys[i] != null)
                keySet.put(keys[i]);
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> valuesList = new LinkedList<>();
        for(int i = 0; i < values.length; i++){
            if(values[i] != null)
                valuesList.add(values[i]);
        }
        return valuesList;
    }

    private void moveDown(int index){
        for(int i = index; i < numOfKeys; i++)
            keys[i] = keys[i + 1];
    }
}
