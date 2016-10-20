package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class BitVector<T extends Enumerable> implements EnumerableSet<T> {

    private boolean[] members = new boolean[20];
    private T[] values = (T[]) new Enumerable[20];
    private int storedObjects = 0;

    @Override
    public EnumerableSet<T> union(EnumerableSet<T> set) {
        return null;
    }

    @Override
    public EnumerableSet<T> intersection(EnumerableSet<T> set) {
        EnumerableSet<T> newSet = new BitVector<>();
        T[] ownElements = (T[]) new Enumerable[storedObjects];
        T[] otherElements = (T[]) new Enumerable[set.size()];
        Iterator<T> ownIterator = this.iterator();
        Iterator<T> otherIterator = set.iterator();
        int firstIndex = 0;
        while(ownIterator.hasNext()){
            ownElements[firstIndex] = ownIterator.next();
            firstIndex++;
        }
        int secondIndex = 0;
        while(otherIterator.hasNext()){
            otherElements[secondIndex] = otherIterator.next();
            secondIndex++;
        }
        int firstSize = ownElements.length;
        int secondSize = otherElements.length;
        for(int i = 0; i < firstSize; i++){
            for(int j = 0; j < secondSize; j++){
                if(ownElements[i].equals(otherElements[j]))
                    newSet.put(ownElements[i]);
            }
        }
        return newSet;
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
        values = (T[]) new Enumerable[20];
    }

    @Override
    public void put(T key) {
        //if(!members[key.getIndex()]){
        int index = key.getIndex();
        while(values.length <= index)
            enlarge();
        values[index] = key;
        members[index] = true;
        storedObjects++;
        //}
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
            boolean hasNext = false;
            if(currentIndex < members.length)
                if(members[currentIndex])
                    hasNext = true;
            return hasNext;
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
