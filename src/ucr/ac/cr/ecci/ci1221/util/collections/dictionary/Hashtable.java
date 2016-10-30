package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

import java.util.ListIterator;

/**
 * @author Student Name
 */
public class Hashtable<K extends Comparable<? super K>, V> implements Dictionary<K, V> {

    private K[] keys = (K[]) new Comparable[20];
    private V[][] values = (V[][]) new Object[20][2];

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public void clear() {
        keys = (K[]) new Comparable[20];
        values = (V[][]) new Object[20][2];
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new BinarySearchTree<>();
        for(int i = 0; i < keys.length;i++){
            if(keys[i] != null)
                keySet.put(keys[i]);
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> valuesList = new LinkedList<>();
        for(int i = 0; i < values.length; i++){
            for(int j = 0; j < values[i].length; j++) {
                if(values[i][j] != null)
                    valuesList.add(values[i][j]);
            }
        }
        return valuesList;
    }
}
