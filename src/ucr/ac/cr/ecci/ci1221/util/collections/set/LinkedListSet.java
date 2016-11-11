package ucr.ac.cr.ecci.ci1221.util.collections.set;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class LinkedListSet<T> implements Set<T> {

    private Node<T> firstNode = null;
    private int storedObjects = 0;

    public LinkedListSet(){
        firstNode = null;
        storedObjects = 0;
    }

    /**
     * Makes a set that is the union of the current one and another passed as parameter.
     * First, it creates a new set that will be returned. The it makes two iterators,
     * one for every set. Then it adds all the elements of the first one, after that,
     * it goes checking all the elements of the other set. If the return list does not
     * have the current element, it is added. Then the union Set is returned.
     * @param set the set to union.
     * @return A set corresponding to the union of the current set and the one passed as parameter.
     */
    @Override
    public Set<T> union(Set<T> set) {
        Set<T> union = new LinkedListSet<>();
        Iterator<T> ownIterator = this.iterator();
        Iterator<T> otherIterator = set.iterator();
        while(ownIterator.hasNext())
            union.put(ownIterator.next());

        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(!union.isMember(key))
                union.put(key);

        }
        return union;
    }

    /**
     * It returns a set corresponding to the intersection of the current set and another passed as a parameter.
     * First it creates a new Set that will be returned. Then it creates the iterator of the other set.
     * Then, for every element in the other set, it checks if it is a member of the current one. If it is a member,
     * it is added to the intersection set. Finally it returns the set of the intersection.
     * @param set the set to intersect.
     * @return a Set corresponding to the intersection.
     */
    @Override
    public Set<T> intersection(Set<T> set) {
        Set<T> intersection = new LinkedListSet<>();
        Iterator<T> otherIterator = set.iterator();
        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(this.isMember(key))
                intersection.put(key);

        }
        return intersection;
    }

    /**
     * First it creates a new Set that will be returned. It then creates an iterator of the
     * current Set. Then, for every element in the Set, it checks if it is a member of the
     * other set, if it is not a member, it is added to the difference Set. Finally it returns the difference Set.
     * @param set the set to substract.
     * @return a Set that has the elements of the current set minus the ones on the other one.
     */
    @Override
    public Set<T> difference(Set<T> set) {
        Set<T> difference = new LinkedListSet<>();
        Iterator<T> ownIterator = this.iterator();
        while(ownIterator.hasNext()){
            T key = ownIterator.next();
            if(!set.isMember(key))
                difference.put(key);

        }
        return difference;
    }

    /**
     * The method returns whether the object passed as parameter is a member of the set.
     * It creates a boolean and iterates over the set checking of the element is equal to any of the members.
     * If it is, returns true, otherwise returns false.
     * @param key the element to look for.
     * @return true if the key passed is a member of the set, false otherwise.
     */
    @Override
    public boolean isMember(T key) {
        boolean isMember = false;
        if(firstNode != null){
            Node<T> currentNode = firstNode;
            while(!isMember && currentNode != null) {
                if(currentNode.getValue().equals(key))
                    isMember = true;

                else
                    currentNode = currentNode.getNext();

            }
        }
        return isMember;
    }

    /**
     * Returns true if the set is empty, false otherwise.
     * @return true if the set is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    /**
     * Clears the set by setting the first Node to null and the storedObjects to zero.
     */
    @Override
    public void clear() {
        firstNode = null;
        storedObjects = 0;
    }

    /**
     * Puts an element passed as parameter in the set if its not a member already.
     * @param key the element to add.
     */
    @Override
    public void put(T key) {
        if(!isMember(key)){
            Node<T> newNode = new Node<>(key);
            if(firstNode == null)
                firstNode = newNode;

            else{
                newNode.setNext(firstNode);
                firstNode = newNode;
            }
            storedObjects++;
        }
    }

    /**
     * Remobves an element of the set if the set is not empty and the object is a member.
     * @param key the element to remove.
     */
    @Override
    public void remove(T key) {
        if(firstNode != null && isMember(key)){
            if(firstNode.getValue().equals(key)){
                firstNode = firstNode.getNext();

            }else{
                Node<T> currentNode = firstNode;
                boolean found = false;
                while(!found && currentNode.getNext() != null){
                    if(currentNode.getNext().getValue().equals(key))
                        found = true;

                    else
                        currentNode = currentNode.getNext();

                }
                currentNode.setNext(currentNode.getNext().getNext());
            }
            storedObjects--;
        }
    }

    /**
     * Returns the size of the set.
     * @return the size of the set.
     */
    @Override
    public int size() {
        return storedObjects;
    }

    /**
     * Creates a new instance of the LinkedSetIterator and returns it.
     * @return a new instance of the LinkedSetIterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedSetIterator<T>(firstNode);
    }

    /**
     * Node class used to store the values inside the set.
     * @param <E> type of the object that will be stored.
     */
    private class Node<E>{
        private Node<E> next;
        private E value;

        /**
         * Constructor of the Node class
         * @param value the values that will be stored in the Node.
         */
        public Node(E value){
            this.value = value;
        }

        /**
         * Returns the Node next the the current one.
         * @return the Node next the the current one.
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * Setter for the next Node.
         * @param next the Node that will be the next.
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Getter for the value inside the Node.
         * @return the value inside the Node.
         */
        public E getValue() {
            return value;
        }

        /**
         * Setter for the value of the Node.
         * @param value the new value for the Node.
         */
        public void setValue(E value) {
            this.value = value;
        }
    }

    /**
     * Iterator class for the LinkedListSet.
     * @param <E>
     */
    private class LinkedSetIterator<E> implements Iterator<E> {
        private Node<E> currentNode = null;
        public LinkedSetIterator(Node<E> first){
            currentNode = first;
        }

        /**
         * Returns whether there is a next value or not.
         * @return true if there is a next value, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * Returns the next value in the set, if there is one.
         * @return the next value in the set.
         */
        @Override
        public E next() {
            E toReturn = null;
            if(currentNode != null){
                toReturn = currentNode.getValue();
                currentNode = currentNode.getNext();
            }
            return toReturn;
        }
    }
}
