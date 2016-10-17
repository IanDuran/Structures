package ucr.ac.cr.ecci.ci1221.util.collections.list;

import java.util.Iterator;

/**
 * Linked pointer based implementation of the {@link List} model.
 *
 * @param <E> the type of elements in the list.
 * @author Ian Duran
 */
public class LinkedList<E> implements List<E> {

    private Container<E> first = null;
    private int numElem = 0;

    /**
     * Returns the E type object in the position represented by index. To do it
     * it has a temporal variable called currentContainer and sets it to be the first
     * Container in the list, and a counter. Then as the index is valid, it iterates
     * the container to set it at the desired position, then it returns its value.
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
            Container<E> currentContainer = first;
            int counter = 0;
            while(counter < index){
                currentContainer = currentContainer.getNext();
                counter++;
            }
            toReturn = currentContainer.getValue();
        }
        return toReturn;
    }

    @Override
    public int first(){
        return 0;
    }

    /**
     * Returns the E type object next to the one at index.
     * To get there it iterates over the Containers in the list until it gets
     * to the Container next to the one at index and returns its value.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the next element.
     * @return the E type object next to the one at index.
     */
    @Override
    public int next(int index) {
        return 0;
    }

    /**
     * Returns the E type object on the position previous to the one pointed at by index.
     * To do this, it iterates over the Containers in the list starting from the first one until
     * it reaches the desired position, the one before the element pointed at by index, and returns its value.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the previous element.
     * @return the E type object on the position previous to the one pointed at by index.
     */
    @Override
    public int previous(int index) {
        return 0;
    }

    /**
     * Sets the value of the Container pointed at by index to the value passed as parameter.
     * It iterates over the Containers in the list until it reaches the desired one and
     * changes its value. Finally it returns the previous value of the Container.
     * @param index index of the element to replace.
     * @param element element to be stored at the specified position.
     * @return previous value of the Container.
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
                Container<E> currentContainer = first;
                while(currentContainer.getNext() != null){
                    currentContainer = currentContainer.getNext();
                }
                toReturn = currentContainer.getValue();
                currentContainer.setValue(element);
            } else{
                Container<E> currentContainer = first;
                int counter = 0;
                while(counter < index){
                    currentContainer = currentContainer.getNext();
                    counter++;
                }
                toReturn = currentContainer.getValue();
                currentContainer.setValue(element);
            }
        }
        return toReturn;
    }

    /**
     * Adds a new E type element and inserts it in the position pointed at by index.
     * Iterates over the Containers until the desired position is met, then it manipulates
     * the pointers of the surrounding Containers to insert the new one.
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
        Container<E> newContainer = new Container<>(element);
        if(first == null)
            first = newContainer;
        else{
            if(index == 0){
                newContainer.setNext(first);
                first = newContainer;
            }else if(index == numElem - 1){
                Container<E> currentContainer = first;
                while(currentContainer.getNext() != null){
                    currentContainer = currentContainer.getNext();
                }
                currentContainer.setNext(newContainer);
            }else{
                Container<E> currentContainer = first;
                int counter = 0;
                while(counter < index - 1){
                    currentContainer = currentContainer.getNext();
                    counter++;
                }
                newContainer.setNext(currentContainer.getNext());
                currentContainer.setNext(newContainer);
            }
        }
        numElem++;
    }

    /**
     * Adds a new element to the end of the list.
     * It iterates until the final position in the list is met, then the Container next to the last
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

        Container<E> newContainer = new Container<>(e);
        if(first == null){
            first = newContainer;
            isAdded = true;
        }else{
            Container<E> currentContainer = first;
            while(currentContainer.getNext() != null){
                currentContainer = currentContainer.getNext();
            }
            currentContainer.setNext(newContainer);
            isAdded = true;
        }
        numElem++;
        return isAdded;
    }

    /**
     * Removes from the list the object in the position represented by index and returns the value of the removed object.
     * If index equals zero, the first container is set to be the one next to it, getting the object of index zero out of scope
     * and therefore removed. If index equals the size of the list minus one, it iterates until it finds the element before the
     * last, and sets the Container next to it to be null getting the last element out of scope. If index falls anywhere
     * between the previous cases, a loop iterates over the list until it reaches the object previous to the one that will
     * be eliminated, then it sets the value of the container next to it to be the one next to the one that will be erased,
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
                Container<E> c = first;
                while(c.getNext().getNext() != null){
                    c = c.getNext();
                }
                toReturn = c.getNext().getValue();
                c.setNext(null);
            }else{
                Container<E> c = first;
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
        first.setNext(null);
        first = null;
        numElem = 0;
    }

    /**
     * Class that will contain the items stored in the list.
     * @param <T> class of the object that will be stored.
     * @author Ian Duran.
     */
    private class Container<T>{
        private Container<T> next;
        private T value;
        public Container(T value){
            this.value = value;
        }

        public Container<T> getNext() {
            return next;
        }

        public void setNext(Container<T> next) {
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
        private Container<E> iterator;

        /**
         * Constructor for the Linked Iterator. It takes the first
         * Container from the list and sets its Container to be that one.
         * @param first first Container of the Linked List.
         */
        public LinkedIterator(Container<E> first){
            iterator = null;
            if(first != null){
                iterator = first;
            }
        }

        /**
         * Returns whether the list has a next value or not.
         * @return true if there's a next value, false otherwise.
         */
        @Override
        public boolean hasNext(){
            boolean hasNext = false;
            if(iterator != null)
                hasNext = true;
            return hasNext;
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
