package ucr.ac.cr.ecci.ci1221.util.collections.stack;

/**
 * Array based implementation of the {@link Stack} model.
 *
 * @param <E> the type of elements in the stack.
 * @author Ian Duran
 */
public class InvertedArrayStack<E> implements Stack<E> {
    private final int INITIAL_SIZE = 20;
    private E[] stack;
    private int storedElements;

    public InvertedArrayStack(){
        stack = (E[]) new Object[INITIAL_SIZE];
        storedElements = 0;
    }

    /**
     * Adds an element in top of the Stack
     * @param element the item to be pushed onto this stack.
     */
    @Override
    public void push(E element) {
        if(storedElements == stack.length - 1)
            this.enlarge();

        stack[storedElements] = element;
        storedElements++;
    }

    /**
     * Returns the element in the top of the Stack and removes it.
     * @return the element in the top of the Stack.
     */
    @Override
    public E pop() {
        E toReturn = null;
        if(storedElements > 0){
            toReturn = stack[storedElements - 1];
            storedElements--;
        }
        return  toReturn;
    }

    /**
     * Returns the element in the top of the Stack without removing it.
     * @return the element in the top of the Stack.
     */
    @Override
    public E peek() {
        return stack[storedElements - 1];
    }

    /**
     * Returns the size of the Stack.
     * @return the size of the Stack.
     */
    @Override
    public int size() {
        return storedElements;
    }

    /**
     * Returns whether the Stack is empty or not.
     * @return true if the Stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedElements == 0;
    }

    /**
     * Clears the Stack by assigning the attribute array to a new empty one.
     */
    @Override
    public void clear() {
        stack = (E[]) new Object[INITIAL_SIZE];
        storedElements = 0;
    }

    /**
     * Called when the Stack is full and an element needs to be added.
     * Creates a new array bigger than the one in use and copies all of its elements,
     * then its used as the attribute array.
     */
    public void enlarge(){
        E[] newArray = (E[]) new Object[stack.length + 10];
        for(int i = 0; i < stack.length; i++)
            newArray[i] = stack[i];

        stack = newArray;
    }
}
