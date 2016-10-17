package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class BitVector<T extends Enumerable> implements EnumerableSet<T> {

    /* @TODO add missing attributes and fill methods. */
    private boolean[] members = new boolean[20];
    private T[] values = (T[]) new Object[20];
    private int storedObjects = 0;

    @Override
    public EnumerableSet<T> union(EnumerableSet<T> set) {
        return null;
    }

    @Override
    public EnumerableSet<T> intersection(EnumerableSet<T> set) {
        return null;
    }

    @Override
    public EnumerableSet<T> difference(EnumerableSet<T> set) {
        return null;
    }

    @Override
    public boolean isMember(T key) {
        return members[key.getIndex()];
    }

    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    @Override
    public void clear() {
        members = new boolean[20];
        values = (T[]) new Object[20];
    }

    @Override
    public void put(T key) {
        int index = key.getIndex();
        while(values.length <= index)
            enlarge();
        values[index] = key;
        members[index] = true;
        storedObjects++;
    }

    @Override
    public void remove(T key) {
        int index = key.getIndex();
        if(members[index]){
            values[index] = null;
            members[index] = false;
            storedObjects--;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return storedObjects;
    }

    public void enlarge(){
        T[] newArray = (T[]) new Object[values.length + 10];
        boolean[] newBoolArray = new boolean[members.length];
        for(int i = 0; i < values.length; i++){
            newArray[i] = values[i];
            newBoolArray[i] = members[i];
        }
        values = newArray;
        members = newBoolArray;
    }
}
