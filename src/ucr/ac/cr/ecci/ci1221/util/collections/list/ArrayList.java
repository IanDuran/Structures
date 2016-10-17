package ucr.ac.cr.ecci.ci1221.util.collections.list;

import java.util.Iterator;

/**
 * Array based implementation of the {@link List} model.
 * @param <E> the type of elements in the list.
 * @author Ian Duran
 */
public class ArrayList<E> implements List<E> {

    private E[] list = (E[]) new Object[20];
    private int storedElements = 0;

    /**
     * Returns the E type element in the index passed as parameter.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index index of the element to return.
     * @return the E type element in the passed index, if it is valid.
     */
    @Override
    public E get(int index) {
        if(index >= storedElements || index < 0)
            throw new IndexOutOfBoundsException();
        E toReturn = list[index];
        return toReturn;
    }

    @Override
    public int first(){
        return 0;
    }

    /**
     * Returns the E type element in the index next to the one passed as a parameter.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the next element.
     * @return the object next to the position passed as parameter.
     */
    @Override
    public int next(int index) {
        if(index >= storedElements - 1 || index < 0)
            throw new IndexOutOfBoundsException();
        //return list[index + 1];
        return 0;
    }

    /**
     * Returns the E type element in the index before the one passed as a parameter.
     * @param index the index used to calculate the previous element.
     * @return the object in the position before the index passed.
     */
    @Override
    public int previous(int index) {
        if(index >= storedElements || index < 1)
            throw new IndexOutOfBoundsException();
        //return list[index - 1];
        return 0;
    }

    /**
     * Sets the value of the object stored in the passed index to the value passed as a parameter.
     * It returns the previous value of the object.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @throws NullPointerException of the element passed is null.
     * @param index index of the element to replace.
     * @param element element to be stored at the specified position.
     * @return the previous value of the changed object.
     */
    @Override
    public E set(int index, E element) {
        if(index >= storedElements || index < 0)
             throw new IndexOutOfBoundsException();
        if(element == null)
            throw new NullPointerException();
        E toReturn = list[index];
        list[index] = element;
        return toReturn;
    }

    /**
     * Adds a new object of class E to the list and stores it in the position that equates to the
     * index passed as parameter.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @throws NullPointerException if the object being inserted has null value.
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     */
    @Override
    public void add(int index, E element) {
        if(index > storedElements || index < 0)
            throw new IndexOutOfBoundsException();
        if(element == null)
            throw new NullPointerException();
        if(storedElements == list.length - 1)
            enlarge();
        moveUp(index);
        list[index] = element;
        storedElements++;
    }

    /**
     * Appends a new object to the end of the list asd returns whether it was successfully added or not
     * @throws NullPointerException if the element to be added is null.
     * @param e element to be appended to this list
     * @return true if the object was inserted successfully, false otherwise.
     */
    @Override
    public boolean add(E e) {
        boolean isAdded = false;
        if(e == null)
            throw new NullPointerException();
        if(storedElements == list.length - 1)
            enlarge();
        list[storedElements] = e;
        isAdded = true;
        storedElements++;
        return isAdded;
    }

    /**
     * Removes from the list the element in the index passed as parameter, if the index is valid.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index of the element to be removed.
     * @return the instance of the object being removed.
     */
    @Override
    public E remove(int index) {
        if(index >= list.length || index < 0)
            throw new IndexOutOfBoundsException();
        E toReturn = list[index];
        moveDown(index);
        storedElements--;
        return toReturn;
    }

    /**
     * Returns the size of the list, it corresponds to the number of elements stored.
     * @return the size of the list.
     */
    @Override
    public int size() {
        return storedElements;
    }

    /**
     * Returns whether the list is empty of not by returning the value of the boolean expression
     * checking if the elements stored equals zero.
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedElements == 0;
    }

    /**
     * Clears the list by creating a new Array and assigning the attribute array to it.
     */
    @Override
    public void clear() {
        list = (E[]) new Object[20];
        storedElements = 0;
    }

    /**
     * Returns a new instance of the iterator for this class.
     * @return the iterator for this class.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator(list, storedElements);
    }

    /**
     * Creates a new array with ten more spaces that the current one, copies the values of the current array and then
     * assigns the new bigger array to the attribute one.
     */
    private void enlarge(){
        E[] newArray = (E[]) new Object[list.length + 10];
        for(int i = 0; i < list.length; i++)
            newArray[i] = list[i];
        list = newArray;
    }

    /**
     * Moves up the values in the array to make space for a new value to be stored in the space that corresponds to
     * the index passed as a parameter.
     * @param index position after which the elements are moved one position up.
     */
    private void moveUp(int index){
        for(int i = storedElements - 1; i > index; i--)
            list[i] = list[i - 1];
    }

    /**
     * When the remove method is called, it class this method to overwrite the values from the index to the value next to it and so on
     * until it reaches the end of the array, so that the object at the index exists no more.
     * @param index position around which the objects of the array are moved.
     */
    private void moveDown(int index){
        for(int i = index; i < storedElements; i++)
            list[i] = list[i + 1];

    }

    /**
     * Iterator class for the ArrayList class.
     * @author Ian Duran
     */
    private class ArrayIterator implements Iterator<E> {
        private E[] array;
        private int storedElements;
        private int iteratorPosition;

        /**
         * ArrayIterator constructor, it receives the E class array that corresponds to the list of its
         * host class and the number of element stored in it.
         * @param passedArray the array that contains the elements in the ArrayList.
         * @param storedElements number of elements stored in the array needed to not return a null object.
         */
        public ArrayIterator(E[] passedArray, int storedElements) {
            this.storedElements = storedElements;
            array = passedArray;
            iteratorPosition = 0;
        }

        /**
         * Returns whether there's a next object or not by checking if the position of the
         * iterator is lesser that the number of elements stored in the array. If it is equal,
         * the end of the array has been met and returns false.
         * @return true if there's a next item in the iterator, false otherwise.
         */
        @Override
        public boolean hasNext(){
            boolean hasNext = false;
            if(storedElements > 0 && storedElements > iteratorPosition){
                hasNext = true;
            }
            return hasNext;
        }

        /**
         * Returns the next object of class E that is stored in the array
         * first it returns the element in the current position pointed at
         * by iteratorPosition, then it augments iteratorPosition by one
         * for the next call.
         * @return the next object of class E in the iterator.
         */
        @Override
        public E next(){
            E toReturn = null;
            if(storedElements > 0 && storedElements > iteratorPosition){
                toReturn = array[iteratorPosition];
                iteratorPosition++;
            }
            return toReturn;
        }
    }
}
