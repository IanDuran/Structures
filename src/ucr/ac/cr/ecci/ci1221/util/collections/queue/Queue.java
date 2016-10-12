package ucr.ac.cr.ecci.ci1221.util.collections.queue;

import ucr.ac.cr.ecci.ci1221.util.collections.Collection;

/**
 * Interface for all the implementations of the Queue model. Do not modify.
 *
 * @author Rodrigo A. Bartels
 */
public interface Queue<E> extends Collection{

    /**
     * Inserts the specified element into this queue.
     *
     * @param element the element to add
     */
    void enqueue(E element);

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E dequeue();

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E peek();
}
