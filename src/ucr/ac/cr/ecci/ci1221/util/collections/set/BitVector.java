package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class BitVector<T extends Enumerable> implements EnumerableSet<T> {

    private final int INITIAL_SIZE = 20;
    private boolean[] members = new boolean[20];
    private T[] values = (T[]) new Enumerable[20];
    private int storedObjects = 0;

    public BitVector(){
        members = new boolean[INITIAL_SIZE];
        values = (T[]) new Enumerable[INITIAL_SIZE];
    }

    @Override
    public EnumerableSet<T> union(EnumerableSet<T> set) {
        EnumerableSet<T> union = new BitVector<>();
        Iterator<T> ownIterator = this.iterator();
        Iterator<T> otherIterator = set.iterator();
        while(ownIterator.hasNext())
            union.put(ownIterator.next());

        while(otherIterator.hasNext()){
            T currentValue = otherIterator.next();
            if(!union.isMember(currentValue))
                union.put(currentValue);

        }
        return union;
    }

    @Override
    public EnumerableSet<T> intersection(EnumerableSet<T> set) {
        EnumerableSet<T> intersection = new BitVector<>();
        Iterator<T> otherIterator = set.iterator();
        while(otherIterator.hasNext()){
            T currentValue = otherIterator.next();
            if(this.isMember(currentValue))
                intersection.put(currentValue);

        }
        return intersection;
    }

    @Override
    public EnumerableSet<T> difference(EnumerableSet<T> set) {
        EnumerableSet<T> difference = new BitVector<>();
        Iterator<T> ownIterator = this.iterator();
        while(ownIterator.hasNext()){
            T currentValue = ownIterator.next();
            if(!set.isMember(currentValue))
                difference.put(currentValue);

        }
        return difference;
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
        members = new boolean[INITIAL_SIZE];
        values = (T[]) new Enumerable[INITIAL_SIZE];
    }

    @Override
    public void put(T key) {
        if (!members[key.getIndex()]) {
            int index = key.getIndex();
            while (values.length <= index)
                this.enlarge();

            values[index] = key;
            members[index] = true;
            storedObjects++;
        }
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
        return new BitVectorIterator<T>(values, members);
    }

    @Override
    public int size() {
        return storedObjects;
    }

    public void enlarge(){
        T[] newArray = (T[]) new Enumerable[values.length + 10];
        boolean[] newBoolArray = new boolean[members.length + 10];
        for(int i = 0; i < values.length; i++){
            newArray[i] = values[i];
            newBoolArray[i] = members[i];
        }
        values = newArray;
        members = newBoolArray;
    }

    private class BitVectorIterator<E> implements Iterator<E>{
        private boolean[] members = null;
        private E[] values = null;
        private int currentIndex = 0;

        public BitVectorIterator(E[] values, boolean[] members){
            this.values = values;
            this.members = members;
            while(currentIndex < members.length && !members[currentIndex])
                currentIndex++;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < members.length && members[currentIndex];
        }

        @Override
        public E next() {
            E toReturn = null;
            if(members[currentIndex]){
                toReturn = values[currentIndex];
                currentIndex++;
                while(currentIndex < members.length && !members[currentIndex])
                    currentIndex++;

            }
            return toReturn;
        }
    }
}
