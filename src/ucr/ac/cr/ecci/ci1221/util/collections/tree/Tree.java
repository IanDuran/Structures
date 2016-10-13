package ucr.ac.cr.ecci.ci1221.util.collections.tree;

/**
 * ADT Tree interface.
 *
 * @author Rodrigo A. Bartels
 */
public interface Tree<T> {

    /**
     * Returns the root of the tree.
     *
     * @return the root.
     */
    Node<T> getRoot();

    /**
     * Whether the tree is empty or not.
     *
     * @return true if empty.
     */
    boolean isEmpty();
}
