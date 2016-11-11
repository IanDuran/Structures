package ucr.ac.cr.ecci.ci1221.util.collections.stack;

/**
 * Array based implementation of the {@link Stack} model.
 * @param <E> the type of elements in the stack.
 * @author Ian Duran
 */
public class LinkedListStack<E> implements Stack<E> {
    private Node<E> top;
    private int numElem;

    public LinkedListStack(){
        top = null;
        numElem = 0;
    }

    /**
     * Stores a new object in the top of the stack.
     * First it creates a new Node with the value to be stored.
     * Then, if the top Node is null, top is set to be the new Node.
     * If top isn't null, the Node next to the new one is set to be top,
     * and top is then set to be the new Node.
     * @param element the item to be pushed onto this stack.
     */
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        if(top == null)
            top = newNode;

        else{
            newNode.setNext(top);
            top = newNode;
        }
        numElem++;
    }

    /**
     * Returns the object in the top of the stack.
     * First it creates a return variable, then, if the top Node
     * isn't null, the return variable is set to be the value inside of
     * the top Node, then top is set to be the Node next to it.
     * @return the object in top of the stack.
     */
    @Override
    public E pop() {
        E toReturn = null;
        if(top != null){
            toReturn = top.getValue();
            top = top.getNext();
            numElem--;
        }
        return toReturn;
    }

    /**
     * Returns the object in the top of the stack without removing it.
     * @return the object in top of the stack.
     */
    @Override
    public E peek() {
        E toReturn = null;
        if(top != null)
            toReturn = top.getValue();

        return toReturn;
    }

    /**
     * Returns the size of the stack.
     * @return the size of the stack.
     */
    @Override
    public int size() {
        return numElem;
    }

    /**
     * Returns whether the stack is empty or not
     * @return true if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return numElem == 0;
    }

    /**
     * Clears the stack by removing its contents out of scope.
     */
    @Override
    public void clear() {
        top = null;
        numElem = 0;
    }

    /**
     * Node class for the Linked List Stack.
     * @param <T> class of the objects stored in the stack
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

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
