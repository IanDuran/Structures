package ucr.ac.cr.ecci.ci1221.util.collections.stack;

/**
 * Array based implementation of the {@link Stack} model.
 *
 * @param <E> the type of elements in the stack.
 * @author Ian Duran
 */
public class ArrayStack<E> implements Stack<E>{
    private E[] stack = (E[]) new Object[20];
    private int storedElements = 0;

    /**
     * Inserts an object in the top of the stack.
     * First it checks if the stack array is full, it if is, it is enlarged.
     * After that, if the stack is not empty, all of the elements are moved up by one position.
     * Then, the first position in the stack array is set to be the new object.
     * @param element the item to be pushed onto this stack.
     */
    @Override
    public void push(E element) {
        if(storedElements == stack.length - 1){
            enlarge();
        }
        if(storedElements > 0)
            moveUp();
        stack[0] = element;
        storedElements++;
    }

    /**
     * Returns the element in top of the stack.
     * First the return variable is set to be the one at the first position of the
     * stack array, being this the last item entered. Then it moves down by one
     * position all the objects after the first to remove it, and the stored elements variable is decremented.
     * Finally, the object is returned.
     * @return the last object inserted in the stack.
     */
    @Override
    public E pop() {
        E toReturn = stack[0];
        moveDown();
        storedElements--;
        return toReturn;
    }

    /**
     * Returns the last element inserted in the stack without removing it.
     * This being the object in the first position of the stack array.
     * @return the last object inserted in the stack.
     */
    @Override
    public E peek() {
        return stack[0];
    }

    /**
     * Returns the size of the stack
     * @return the size of the stack
     */
    @Override
    public int size() {
        return storedElements;
    }

    /**
     * Returns whether the stack is empty or not.
     * @return true if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedElements == 0;
    }

    /**
     * Clears the stack by creating a new array and setting the attribute one to be the new one.
     * It also sets the stored objects variable to be zero.
     */
    @Override
    public void clear() {
        stack = (E[]) new Object[20];
        storedElements = 0;
    }

    /**
     * Enlarges the stack array by ten.
     * First, a new, bigger array is created and the values from the previous one are copied into it.
     * Then the attribute array is set to be the new one.
     */
    private void enlarge(){
        E[] newArray = (E[]) new Object[stack.length + 10];
        int size = stack.length;
        for(int i = 0; i < size; i++)
            newArray[i] = stack[i];
        stack = newArray;
    }

    /**
     * Moves all the elements from the stack one position up to free the first position in the stack array
     * so it could be given a new value without losing previous values.
     */
    private void moveUp(){
        for(int i = storedElements; i >= 1; i--){
            stack[i] = stack[i - 1];
        }
    }

    /**
     * Moves all the elements from the stack one position down to keep the stack values starting at the zero index of the
     * stack array.
     */
    private void moveDown(){
        for(int i = 1; i < storedElements; i++){
            stack[i - 1] = stack[i];
        }
    }
}
