package ucr.ac.cr.ecci.ci1221.util.collections.stack;

import ucr.ac.cr.ecci.ci1221.util.collections.Collection;

/**
 * Interface for all the implementations of the Stack model. The <code>Stack</code> interface
 * represents a last-in-first-out (LIFO) stack of objects. Do not modify.
 *
 * @author Rodrigo A. Bartels
 */
public interface Stack<E> extends Collection{

    /**
     * Pushes an item onto the top of this stack.
     *
     * @param   element the item to be pushed onto this stack.
     */
     void push(E element);

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return  The object at the top of this stack or null if this stack is empty.
     */
     E pop();

    /**
     * Looks at the object at the top of this stack without removing it
     *
     *
     * from the stack.
     *
     * @return  the object at the top of this stack or null if this stack is empty.
     */
    E peek();
}
