package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class BitVector<T extends Enumerable> implements EnumerableSet<T> {

    private final int INITIAL_SIZE = 20;
    private final int ENLARGING_SIZE = 10;
    private boolean[] members;
    private T[] values;
    private int storedObjects = 0;

    /**
     * Constructor for the BitVector class
     */
    public BitVector(){
        members = new boolean[INITIAL_SIZE];
        values = (T[]) new Enumerable[INITIAL_SIZE];
    }

    /**
     * Method that returns an Enumerable set containing the union of the
     * current set and the one passed as parameter.
     * To do this it creates a new Enumerable set and stores the values of the
     * current set, then it iterates over the values of the other set and if they're
     * not already in the union set, they are added.
     * @param set the set to union.
     * @return the set containing the elements of both sets without repeating.
     */
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

    /**
     * Returns an Enumerable set containing the elements inside both sets.
     * To do this, it creates an iterator for the set passed as parameter. It then iterates over
     * all of it's elements checking if they're members of the current set and if so, it stores them in the
     * intersection set that will be returned.
     * @param set the set to intersect.
     * @return a set containing the elements that are in both sets.
     */
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

    /**
     * Returns an Enumerable set containing the elements inside the current set that are not in the other set.
     * In order to do this, it creates an iterator for the current set, then it iterates over all the elements,
     * and if they are not contained in the other set, they are added to the difference set.
     * @param set the set to substract.
     * @return a set containing all the elements in the current set that are not contained in the other one.
     */
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

    /**
     * Tells if a given object is a member of the Bit Vector.
     * @param key the element to look for.
     * @return true if the object is a member of the set, false otherwise.
     */
    @Override
    public boolean isMember(T key) {
        return members[key.getIndex()];
    }

    /**
     * Tells if the Set is empty
     * @return true if the set is empty, false if not.
     */
    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    /**
     * Clears the Set by returning the set's variables to the default state.
     */
    @Override
    public void clear() {
        members = new boolean[INITIAL_SIZE];
        values = (T[]) new Enumerable[INITIAL_SIZE];
    }

    /**
     * Inserts an element into the Bit Vector in the position specified by the getIndex method
     * of the Enumerable interface.
     * @param key the element to add.
     */
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

    /**
     * Removes an element from the Bit Vector. The one eliminated is
     * the one at the position specified by the getIndex method of the
     * Enumerable interface
     * @param key the element to remove.
     */
    @Override
    public void remove(T key) {
        int index = key.getIndex();
        if(members[index]){
            values[index] = null;
            members[index] = false;
            storedObjects--;
        }
    }

    /**
     * Returns a new instance of the BitVectorIterator class
     * @return a new instance of the BitVectorIterator class
     */
    @Override
    public Iterator<T> iterator() {
        return new BitVectorIterator<T>(values, members);
    }

    /**
     * Returns the number of objects stored in the Bit Vector
     * @return the number of objects stored in the Bit Vector
     */
    @Override
    public int size() {
        return storedObjects;
    }

    /**
     * Enlarges the value array of the bit vector and the members array as well
     * by ENLARGING_SIZE
     */
    public void enlarge(){
        T[] newArray = (T[]) new Enumerable[values.length + ENLARGING_SIZE];
        boolean[] newBoolArray = new boolean[members.length + ENLARGING_SIZE];
        for(int i = 0; i < values.length; i++){
            newArray[i] = values[i];
            newBoolArray[i] = members[i];
        }
        values = newArray;
        members = newBoolArray;
    }

    /**
     * Iterator class for the Bit Vector
     * @param <E>
     */
    private class BitVectorIterator<E> implements Iterator<E>{
        private boolean[] members = null;
        private E[] values = null;
        private int currentIndex = 0;

        /**
         * Constructor for the BitVectorIterator class.
         * It takes the current index (currently at zero) and gives it the number of the
         * position of the first member.
         * @param values array with the members of the Bit Vector
         * @param members array that shows which elements from the previous array are in the Bit Vector
         */
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
