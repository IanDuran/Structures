package ucr.ac.cr.ecci.ci1221.util.collections.list;


import java.util.Iterator;

/**
 * Doubly Linked pointer based implementation of the {@link List} model.
 *
 * @param <E> the type of elements in the list.
 * @author Ian Duran
 */
public class DoubleLinkedList<E> implements List<E>  {

    private Node<E> first;
    private Node<E> last;
    private int numElem;

    /**
     * Constructor for the DoubleLinkedList class
     */
    public DoubleLinkedList(){
        first = null;
        last = null;
        numElem = 0;
    }

    /**
     * Returns the object in the list position that is represented by index.
     * To get to the element faster, if index is in the lesser half of the list,
     * the iteration begins from the first Node. If index is in the greater half,
     * it starts from the last Node.
     * @param index index of the element to return.
     * @return the E type object in the position represented by index.
     */
    @Override
    public E get(int index) {
        E toReturn = null;
        if(index >= numElem || index < 0)
            throw new IndexOutOfBoundsException();
        if(first != null && last != null){
            if(index == 0)
                toReturn = first.getValue();

            else if(index == numElem - 1)
                toReturn = last.getValue();

            else{
                Node<E> currentNode = null;
                if(index <= numElem / 2){
                    currentNode = first;
                    int counter = 0;
                    while(counter < index){
                        currentNode = currentNode.getNext();
                        counter++;
                    }
                }else{
                    currentNode = last;
                    int counter = numElem - 1;
                    while(counter > index){
                        currentNode = currentNode.getPrevious();
                        counter--;
                    }
                }
                toReturn = currentNode.getValue();
            }
        }
        return toReturn;
    }

    @Override
    public int first(){
        return 0;
    }

    /**
     * Returns the element next to the one pointer at by index.
     * To get to the desired element faster, if index is in the first half of the list,
     * it begins from the first Node, if its in the last half, it begins from the last
     * Node.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the next element.
     * @return the E type element next to the one pointed at by index.
     */
    @Override
    public int next(int index) {
        if(index < 0 || index >= numElem)
            throw new IndexOutOfBoundsException();

        return index + 1;
    }

    /**
     * Returns the E type element in the position before the one pointed at by index.
     * To get to the desired element faster, if index is in the first half of the list,
     * it begins from the first Node, if its in the last half, it begins from the last
     * Node.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index used to calculate the previous element.
     * @return the E type element before the one pointed at by index.
     */
    @Override
    public int previous(int index) {
        if(index < 1 || index >= numElem)
            throw new IndexOutOfBoundsException();

        return index - 1;
    }

    /**
     * Sets the value in the Node pointed at by index to the value of element.
     * After that it returns the previous value of the object inside the Node.
     * @throws NullPointerException if the element to be stored is null.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index index of the element to replace.
     * @param element element to be stored at the specified position.
     * @return the previous value of the object that got changed.
     */
    @Override
    public E set(int index, E element) {
        E toReturn = null;
        if(index >= numElem || index < 0)
            throw new IndexOutOfBoundsException();

        if(element == null)
            throw new NullPointerException();

        if(first != null && last != null){
            if(index == 0){
                toReturn = first.getValue();
                first.setValue(element);
            }else if(index == numElem - 1){
                toReturn = last.getValue();
                last.setValue(element);
            }else{
                Node<E> currentNode = null;
                if(index <= numElem / 2){
                    currentNode = first;
                    int counter = 0;
                    while(counter < index){
                        currentNode = currentNode.getNext();
                        counter++;
                    }
                }else{
                    currentNode = last;
                    int counter = numElem - 1;
                    while(counter > index){
                        currentNode = currentNode.getPrevious();
                        counter--;
                    }
                }
                toReturn = currentNode.getValue();
                currentNode.setValue(element);
            }
        }
        return toReturn;
    }

    /**
     * Adds a new E type object to the list. Its added to the position represented by index.
     * First it creates a new Node with its value set to element. Then to get to the position there are 3 cases.
     * The first is that index is zero, in that case, the Node next to the new one, is set to be the first Node,
     * then, first is set to be the new Node. The second case is that index equals the number of elements,
     * in which case is added to the end of the list, to do that, the Node next to the last Node is set to be
     * the new Node, and then the new Node is set to be the last one. The third case is that index falls in between
     * zero and the number of elements, in this case, if the index is in the first half of the list, it begins iterating from
     * the first Node, if its in the last half, it begins iterating from the last Node. Then it assigns
     * the pointers from the Nodes previous to it and the next one to add the new Node to the desired position.
     * Finally it increases the number of elements by one.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @throws NullPointerException if the element to be inserted is null.
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     */
    @Override
    public void add(int index, E element) {
        if(index > numElem || index < 0)
            throw new IndexOutOfBoundsException();

        if(element == null)
            throw new NullPointerException();

        if(first != null && last != null){
            Node<E> newNode = new Node<>(element);
            if(index == 0){
                first.setPrevious(newNode);
                newNode.setNext(first);
                first = newNode;
            }else if(index == numElem){
                last.setNext(newNode);
                newNode.setPrevious(last);
                last = newNode;
            }else{
                if(index <= numElem / 2){
                    Node<E> currentNode = first;
                    int counter = 0;
                    while(counter < index - 1){
                        currentNode = currentNode.getNext();
                        counter++;
                    }
                    currentNode.getNext().setPrevious(newNode);
                    newNode.setNext(currentNode.getNext());
                    currentNode.setNext(newNode);
                    newNode.setPrevious(currentNode);
                }else{
                    Node<E> currentNode = last;
                    int counter = numElem;
                    while(counter > index + 1){
                        currentNode = currentNode.getPrevious();
                        counter--;
                    }
                    currentNode.getPrevious().setNext(newNode);
                    newNode.setPrevious(currentNode.getPrevious());
                    currentNode.setPrevious(newNode);
                    newNode.setNext(currentNode);
                }
            }
            numElem++;
        }
    }

    /**
     * Adds a new E type object to the end of the list. If the list has no elements,
     * it initializes the pointers to the last and the first Nodes to be equal to the new one.
     * If the list already has stored elements, the Node next to the last one is set to be the new one
     * and then the last Node is set to be the new one. Finally it increases the number of elements by one.
     * @throws NullPointerException of the element to be stored is null.
     * @param e element to be appended to this list.
     * @return true if the element is added successfully, false otherwise.
     */
    @Override
    public boolean add(E e) {
        boolean isAdded = false;
        if(e == null)
            throw new NullPointerException();

        Node<E> newNode = new Node<>(e);
        if(first == null && last == null){
            first = newNode;
            last = first;
            isAdded = true;
        }else{
            last.setNext(newNode);
            last.getNext().setPrevious(last);
            last = last.getNext();
            isAdded = true;
        }
        numElem++;
        return isAdded;
    }

    /**
     * Removes the element stored in the position pointed at by index and returns is value.
     * The method is divided in three cases. In the first case, the element to be removed is
     * the first one in the list, in which case the first Node is set to be the one next to it.
     * In the second case, the element to be removed is the last one in the list, in this case,
     * the last Node is set to be the one previous to it. In the last case, is where the position
     * falls anywhere in between the previous cases (i.e. index > 0 && index < numElem - 1). In this
     * last case, it checks in which half of the list does the index falls to choose the iteration from the
     * closest pointer. After it gets to the desired position, it manipulates the pointers in the current
     * Node to get the unwanted element out of scope, thus removing it. Finally it decreases the number
     * of elements by one.
     * @throws IndexOutOfBoundsException if the index is not valid.
     * @param index the index of the element to be removed.
     * @return the E type element of the Node that was removed.
     */
    @Override
    public E remove(int index) {
        if(index >= numElem || index < 0)
            throw new IndexOutOfBoundsException();

        E toReturn = null;
        if(first != null && last != null){
            if(index == 0){
                toReturn = first.getValue();
                first = first.getNext();
                first.setPrevious(null);
            }else if(index == numElem - 1){
                toReturn = last.getValue();
                last = last.getPrevious();
                last.setNext(null);
            }else{
                if(index <= numElem / 2){
                    Node<E> currentNode = first;
                    int counter = 0;
                    while(counter < index - 1){
                        currentNode = currentNode.getNext();
                        counter++;
                    }
                    toReturn = currentNode.getNext().getValue();
                    currentNode.getNext().getNext().setPrevious(currentNode);
                    currentNode.setNext(currentNode.getNext().getNext());
                }else{
                    Node<E> currentNode = last;
                    int counter = numElem - 1;
                    while(counter > index + 1){
                        currentNode = currentNode.getPrevious();
                        counter--;
                    }
                    toReturn = currentNode.getPrevious().getValue();
                    currentNode.getPrevious().getPrevious().setNext(currentNode);
                    currentNode.setPrevious(currentNode.getPrevious().getPrevious());
                }
            }
            numElem--;
        }
        return toReturn;
    }

    /**
     * Returns the size of the list by returning the number of elements inside it.
     * @return the size of the list.
     */
    @Override
    public int size() {
        return numElem;
    }

    /**
     * Returns the result of the boolean operation that checks if the number of elements equals zero.
     * @return whether the list is empty or not.
     */
    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    /**
     * Returns a new instance of the DoubleLinked Iterator class which is the iterator for this class.
     * @return an iterator for the elements of this class.
     */
    @Override
    public Iterator<E> iterator() {
        return new DoubleLinkedIterator(first);
    }

    /**
     * Empties the list by getting all the elements out of scope.
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        numElem = 0;
    }

    /**
     * Private class that will be containing the elements of the list. It has as attributes
     * an E type object that will be its value and pointers to a Node next to it and a
     * Node previous to it.
     * @param <T> class that will be stored.
     */
    private class Node<T>{
        private Node<T> next;
        private Node<T> previous;
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

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    /**
     * Iterator class for the DoubleLinkedList class.
     * @author Ian Duran
     */
    private class DoubleLinkedIterator implements Iterator<E>{

        private Node<E> iterator;

        /**
         * Constructor of the Double Linked Iterator.
         * It receives a Node that corresponds to the first Node in the host
         * DoubleLinkedList class.
         * @param first the first Node in the List.
         */
        public DoubleLinkedIterator(Node<E> first){
            iterator = null;
            if(first != null)
                iterator = first;
        }

        /**
         * Returns whether the current Node is null or not.
         * If its null, there is no next value, if it isn't, there is.
         * @return whether there exists a next object or not.
         */
        @Override
        public boolean hasNext(){
            return iterator != null;
        }

        /**
         * Checks if the current Node is null, if its not, returns its
         * value and assigns to be the one next to it.
         * @return the next object in the iterator.
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
