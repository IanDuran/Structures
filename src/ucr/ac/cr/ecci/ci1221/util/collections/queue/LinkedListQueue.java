package ucr.ac.cr.ecci.ci1221.util.collections.queue;

/**
 * Linked based implementation of the {@link Queue} model.
 *
 * @param <E> the type of elements in the queue.
 * @author Ian Duran
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node<E> tail;
    private Node<E> head;
    private int numElem;

    /**
     * Constructor for the LinkedListQueue class
     */
    public LinkedListQueue(){
        tail = null;
        head = null;
        numElem = 0;
    }


    /**
     * Adds a new object to the queue.
     * First it creates a new Node and sets its value to be the object that will be added.
     * Then, if the list is empty, head and tail are set to be equal to the new Node.
     * Otherwise, the Node next to the new one is set to be tail, and tail is set to be the new
     * Node.
     * @param element the element to add
     */
    @Override
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if(head == null && tail == null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.setNext(tail);
            tail = newNode;
        }
        numElem++;
    }

    /**
     * Returns the next object to be retrieved from the queue and removes it.
     * First it creates a return variable and sets it to null, then it checks if the
     * list is empty. If its not, the return variable is set to be the value inside head.
     * Then, if head was the last item in the queue, then tail and head are set to null.
     * If there were more, it goes into a loop until the Node before head is reached
     * and erases its next Node (i.e. current head), then head is set to be this
     * Node.
     * @return the next object in the queue.
     */
    @Override
    public E dequeue() {
        E toReturn = null;
        if(tail != null && head != null){
            toReturn = head.getValue();
            if(head == tail)
                head = tail = null;

            else{
                Node<E> currentNode = tail;
                while(currentNode.getNext() != head)
                    currentNode = currentNode.getNext();

                currentNode.setNext(null);
                head = currentNode;
            }

        }
        numElem--;
        return toReturn;
    }

    /**
     * Returns the next value in the queue without removing it.
     * @return the next value in the queue.
     */
    @Override
    public E peek() {
        E toReturn = null;
        if(head != null && tail != null)
            toReturn = head.getValue();

        return toReturn;
    }

    /**
     * Returns the size of the queue.
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return numElem;
    }

    /**
     * Returns whether the list is empty or not
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    /**
     * Clears the queue by getting all of its elements out of scope.
     */
    @Override
    public void clear() {
        tail = null;
        head = null;
        numElem = 0;
    }

    /**
     * Node class for the queue.
     * This class contains the objects that will be sotred in the queue.
     * @param <T>
     * @author Ian Duran
     */
    private class Node<T>{
        private T value;
        private Node<T> next;
        public Node(T value){
            this.value = value;
            this.next = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
