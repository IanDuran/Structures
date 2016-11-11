package ucr.ac.cr.ecci.ci1221.util.collections.queue;

/**
 * Circular array based implementation of the {@link Queue} model.
 * @param <E> the type of elements in the queue.
 * @author Ian Duran.
 */
public class CircularArrayQueue<E> implements Queue<E> {
    private final int ARRAY_SIZE = 100;
    private E[] queue;
    private int write;
    private int read;
    private int storedObjects;

    /**
     * Constructor for the CircularArrayQueue class
     */
    public CircularArrayQueue(){
        queue = (E[]) new Object[ARRAY_SIZE];
        write = -1;
        read = -1;
        storedObjects = 0;
    }

    /**
     * Adds a new object to the queue.
     * First it augments the write value by modulus of the value plus one so it won't
     * take an out of bounds value. Then the array position of the new value of write is
     * set to be the new object. If the queue was empty, it sets the value for read to be the
     * same as write. Finally it augments by one the number of objects stored.
     * @param element the element to add
     */
    @Override
    public void enqueue(E element) {
        write = (write + 1) % queue.length;
        queue[write] = element;
        if(read == - 1)
            read = write;

        storedObjects++;
    }

    /**
     * Gets the oldest element from the queue and logically erases it.
     * First it creates a variable for storing the return value, then checks if the
     * queue is empty. After that it sets the return variable to be the one in the
     * read index. After that, it checks if by removing the object the queue is empty
     * in which case read and write are set to -1. Otherwise the read value is set to be
     * read plus one modulus of the length of the array. Finally, it returns the object.
     * @return the oldest object inside the queue.
     */
    @Override
    public E dequeue() {
        E toReturn = null;
        if(read != - 1){
            toReturn = queue[read];
            if(read == write){
                read = -1;
                write = -1;
            }else
                read = (read + 1) % queue.length;

            storedObjects--;
        }
        return toReturn;
    }

    /**
     * Returns the next object that will be retrieved from the queue without removing it.
     * The return value is the one in the read index of the array.
     * @return the next object in the queue.
     */
    @Override
    public E peek() {
        E toReturn = null;
        if(read != - 1)
            toReturn = queue[read];

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
     * Returns whether the list is empty or not.
     * @return true if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    /**
     * Clears the queue by creating a new instance of the array
     * that contains the queue. After that, it sets the attributes'
     * values to the defaults needed.
     */
    @Override
    public void clear() {
        queue = (E[]) new Object[ARRAY_SIZE];
        write = -1;
        read = -1;
        storedObjects = 0;
    }
}
