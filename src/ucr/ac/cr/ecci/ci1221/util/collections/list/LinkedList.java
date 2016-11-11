package ucr.ac.cr.ecci.ci1221.util.collections.list;

import java.util.Iterator;

/**
 * Linked pointer based implementation of the {@link List} model.
 *
 * @param <E> the type of elements in the list.
 * @author Ian Duran
 */
public class LinkedList<E> implements List<E> {

    private Node<E> first;
    private int numElem;

    /**
     * Constructor for the LinkedList class
     */
    public LinkedList(){
        first = null;
        numElem = 0;
    }

    /**
     * Returns the E type object in the position represented by index. To do it
     * it has a temporal variable called currentNode and sets it to be the first
     * Node in the list, and a counter. Then as the index is valid, it iterates
     * the Node to set it at the desired position, then it returns its value.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index index of the element to return.
     * @return the E type object in the position pointed at by index.
     */
    @Override
    public E get(int index) {
        E toReturn = null;
        if(index >= numElem || index < 0)
            throw new IndexOutOfBoundsException();

        if(first != null){
            Node<E> currentNode = first;
            int counter = 0;
            while(counter < index){
                currentNode = currentNode.getNext();
                counter++;
            }
            toReturn = currentNode.getValue();
        }
        return toReturn;
    }

    @Override
    public int first(){
        return 0;
    }

    /**
     * Returns the E type object next to the one at index.
     * To get there it iterates over the Nodes in the list until it gets
     * to the Node next to the one at index and returns its value.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the next element.
     * @return the position next to the one at index.
     */
    @Override
    public int next(int index) {
        if(index < 0 ||index >= numElem - 1)
            throw new IndexOutOfBoundsException();

        return index + 1;
    }

    /**
     * Returns the E type object on the position previous to the one pointed at by index.
     * To do this, it iterates over the Nodes in the list starting from the first one until
     * it reaches the desired position, the one before the element pointed at by index, and returns its value.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the previous element.
     * @return the E type object on the position previous to the one pointed at by index.
     */
    @Override
    public int previous(int index) {
        if(index <= 0 || index >= numElem)
            throw new IndexOutOfBoundsException();

        return index - 1;
    }

    /**
     * Sets the value of the Node pointed at by index to the value passed as parameter.
     * It iterates over the Nodes in the list until it reaches the desired one and
     * changes its value. Finally it returns the previous value of the Node.
     * @param index index of the element to replace.
     * @param element element to be stored at the specified position.
     * @return previous value of the Node.
     */
    @Override
    public E set(int index, E element) {
        E toReturn = null;
        if(index >= numElem || index < 0)
            throw new IndexOutOfBoundsException();

        if(element == null)
            throw new NullPointerException();

        if(first != null){
            if(index == 0){
                toReturn = first.getValue();
                first.setValue(element);
            }else if(index == numElem - 1){
                Node<E> currentNode = first;
                while(currentNode.getNext() != null){
                    currentNode = currentNode.getNext();
                }
                toReturn = currentNode.getValue();
                currentNode.setValue(element);
            } else{
                Node<E> currentNode = first;
                int counter = 0;
                while(counter < index){
                    currentNode = currentNode.getNext();
                    counter++;
                }
                toReturn = currentNode.getValue();
                currentNode.setValue(element);
            }
        }
        return toReturn;
    }

    /**
     * Adds a new E type element and inserts it in the position pointed at by index.
     * Iterates over the Nodes until the desired position is met, then it manipulates
     * the pointers of the surrounding Nodes to insert the new one.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @throws NullPointerException if the element to be stored is null.
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     */
    @Override
    public void add(int index, E element) {
        if(index > numElem || index < 0)
            throw new IndexOutOfBoundsException();

        if(element == null)
            throw new NullPointerException();

        Node<E> newNode = new Node<>(element);
        if(first == null)
            first = newNode;

        else{
            if(index == 0){
                newNode.setNext(first);
                first = newNode;
            }else if(index == numElem - 1){
                Node<E> currentNode = first;
                while(currentNode.getNext() != null){
                    currentNode = currentNode.getNext();
                }
                currentNode.setNext(newNode);
            }else{
                Node<E> currentNode = first;
                int counter = 0;
                while(counter < index - 1){
                    currentNode = currentNode.getNext();
                    counter++;
                }
                newNode.setNext(currentNode.getNext());
                currentNode.setNext(newNode);
            }
        }
        numElem++;
    }

    /**
     * Adds a new element to the end of the list.
     * It iterates until the final position in the list is met, then the Node next to the last
     * is set to be the new one.
     * @throws NullPointerException if the element to be stored is null
     * @param e element to be appended to this list
     * @return true if the object was added successfully, false otherwise.
     */
    @Override
    public boolean add(E e) {
        boolean isAdded = false;
        if(e == null)
            throw new NullPointerException();

        Node<E> newNode = new Node<>(e);
        if(first == null){
            first = newNode;
            isAdded = true;
        }else{
            Node<E> currentNode = first;
            while(currentNode.getNext() != null){
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
            isAdded = true;
        }
        numElem++;
        return isAdded;
    }

    /**
     * Removes from the list the object in the position represented by index and returns the value of the removed object.
     * If index equals zero, the first Node is set to be the one next to it, getting the object of index zero out of scope
     * and therefore removed. If index equals the size of the list minus one, it iterates until it finds the element before the
     * last, and sets the Node next to it to be null getting the last element out of scope. If index falls anywhere
     * between the previous cases, a loop iterates over the list until it reaches the object previous to the one that will
     * be eliminated, then it sets the value of the Node next to it to be the one next to the one that will be erased,
     * getting it out of scope.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index of the element to be removed
     * @return the object removed from the list
     */
    @Override
    public E remove(int index) {
        if(index >= numElem || index < 0)
            throw new IndexOutOfBoundsException();

        E toReturn = null;
        if(first != null){
            if(index == 0){
                toReturn = first.getValue();
                first = first.getNext();
            }else if(index == numElem - 1){
                Node<E> c = first;
                while(c.getNext().getNext() != null){
                    c = c.getNext();
                }
                toReturn = c.getNext().getValue();
                c.setNext(null);
            }else{
                Node<E> c = first;
                int counter = 0;
                while(counter < index - 1){
                    c = c.getNext();
                    counter++;
                }
                toReturn = c.getNext().getValue();
                c.setNext(c.getNext().getNext());
            }
            numElem--;
        }
        return toReturn;
    }

    /**
     * Returns the size of the list.
     * @return the size of the list.
     */
    @Override
    public int size() {
        return numElem;
    }

    /**
     * Returns whether the list is empty or not.
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    /**
     * Returns a new instance of the iterator for this class.
     * @return the iterator for this class.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedIterator(first);
    }

    /**
     * Clears the list by getting all the elements out of scope.
     */
    @Override
    public void clear() {
        first = null;
        numElem = 0;
    }

    /**
     * Class that will contain the items stored in the list.
     * @param <T> class of the object that will be stored.
     * @author Ian Duran.
     */
    private class Node<T>{
        private Node<T> next;
        private T value;
        public Node(T value){
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    /**
     * Iterator class for the Linked List.
     * @author Ian Duran
     */
    private class LinkedIterator implements Iterator<E>{
        private Node<E> iterator;

        /**
         * Constructor for the Linked Iterator. It takes the first
         * Node from the list and sets its Node to be that one.
         * @param first first Node of the Linked List.
         */
        public LinkedIterator(Node<E> first){
            iterator = null;
            if(first != null)
                iterator = first;

        }

        /**
         * Returns whether the list has a next value or not.
         * @return true if there's a next value, false otherwise.
         */
        @Override
        public boolean hasNext(){
            return iterator != null;
        }

        /**
         * Returns the next value in the list, if there's one.
         * @return the next value found in the list, or null if there's none.
         */
        @Override
        public E next(){
            E toReturn = null;
            if(iterator != null){
                toReturn = iterator.getValue();
                iterator = iterator.getNext();
            }
            return toReturn;
        }
    }
}
