package ucr.ac.cr.ecci.ci1221.util.collections.stack;

/**
 * Array based implementation of the {@link Stack} model.
 * @param <E> the type of elements in the stack.
 * @author Ian Duran
 */
public class LinkedListStack<E> implements Stack<E> {
    private Container<E> top;
    private int numElem = 0;

    /**
     * Stores a new object in the top of the stack.
     * First it creates a new Container with the value to be stored.
     * Then, if the top Container is null, top is set to be the new Container.
     * If top isn't null, the Container next to the new one is set to be top,
     * and top is then set to be the new Container.
     * @param element the item to be pushed onto this stack.
     */
    @Override
    public void push(E element) {
        Container<E> newContainer = new Container<>(element);
        if(top == null){
            top = newContainer;
        }else{
            newContainer.next = top;
            top = newContainer;
        }
        numElem++;
    }

    /**
     * Returns the object in the top of the stack.
     * First it creates a return variable, then, if the top Container
     * isn't null, the return variable is set to be the value inside of
     * the top Container, then top is set to be the Container next to it.
     * @return the object in top of the stack.
     */
    @Override
    public E pop() {
        E toReturn = null;
        if(top != null){
            toReturn = top.value;
            top = top.next;
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
            toReturn = top.value;
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
        top.next = null;
        top = null;
        numElem = 0;
    }

    /**
     * Container class for the Linked List Stack.
     * @param <T> class of the objects stored in the stack
     * @author Ian Duran
     */
    private class Container<T>{
        T value;
        Container<T> next;
        public Container(T value){
            this.value = value;
            this.next = null;
        }
    }
}
