package ucr.ac.cr.ecci.ci1221.util.collections.queue;

/**
 * Array based implementation of the {@link Queue} model.
 *
 * @param <E> the type of elements in the queue.
 * @author Ian Duran
 */
public class ArrayQueue<E> implements Queue<E> {
    private final int INITIAL_SIZE = 20;
    private E[] queue;
    private int storedObjects;

    /**
     * Constructor for the ArrayQueue class
     */
    public ArrayQueue(){
        queue = (E[]) new Object[INITIAL_SIZE];
        storedObjects = 0;
    }

    /**
     * Adds a new object to the queue.
     * First it checks if the queue is full, in such case, the queue is enlarged.
     * After that, it moves all the elements in the array up to make available the
     * first position in the array. Then the new object is stored in the first position
     * of the array and the amount of objects stored augments by one.
     * @param element the element to add
     */
    @Override
    public void enqueue(E element) {
        if(storedObjects == queue.length - 1)
            this.enlarge();

        this.moveUp();
        queue[0] = element;
        storedObjects++;
    }

    /**
     * Returns the next object to be retrieved from the queue and erases it from the queue.
     * First a return variable is created, then, if the queue is not empty, the return variable
     * is set to be the last item in the queue array and the stored objects variable is
     * decremented by one, thus logically erasing the last element.
     * @return the next element in the queue.
     */
    @Override
    public E dequeue() {
        E toReturn = null;
        if(storedObjects > 0)
            toReturn = queue[storedObjects - 1];

        storedObjects--;
        return toReturn;
    }

    /**
     * Returns the next object in the queue without removing it.
     * @return the next object in the queue.
     */
    @Override
    public E peek() {
        E toReturn = null;
        if(storedObjects > 0)
            toReturn = queue[storedObjects - 1];

        return toReturn;
    }

    /**
     * Returns the size of the queue.
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return storedObjects;
    }

    /**
     * Returns whether the queue is empty or not
     * @return true of the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    /**
     * Clears the list by reseting the attributes' values to the defaults needed.
     * A new array for the queue is created and set to be the class' attribute array and the stored objects are set to zero.
     */
    @Override
    public void clear() {
        queue = (E[]) new Object[20];
        storedObjects = 0;
    }

    /**
     * Creates a bigger array to be used when the previous one is full.
     * First it creates a new bigger array, copies the values from the
     * previous one and then the attribute array is set to be the new one.
     */
    private void enlarge(){
        E[] newArray = (E[]) new Object[queue.length + 10];
        for(int i = 0; i < queue.length; i++)
            newArray[i] = queue[i];

        queue = newArray;
    }

    /**
     * Moves the objects in the queue array and puts them a position up.
     * This is to free the first position of the array to put a new value inside.
     */
    private void moveUp(){
        for(int i = storedObjects; i > 0; i--)
            queue[i] = queue[i - 1];
    }
}
