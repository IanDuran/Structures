package ucr.ac.cr.ecci.ci1221.util.collections.tree;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Rodrigo A. Bartels
 */
public interface Node<T> {

    /**
     * Sets the value element of the node.
     *
     * @param data the element to set as value.
     */
    void setLabel(T data);

    /**
     * Returns the value of the node.
     *
     * @return value.
     */
    T getLabel();

    /**
     * Returns the parent node of this node.
     *
     * @return a node.
     */
    Node<T> getParent();

    /**
     * Adds the given node as a child of the current node.
     *
     * @param child the child to add.
     */
    void addChild(Node<T> child);

    /**
     *
     * Adds the given node as a child of the node in the given position.
     *
     * @param index the position.
     * @param child the child to be added.
     */
    void addChildAt(int index, Node<T> child);

    /**
     * Clears the list of children of the node.
     */
    void removeChildren();

    /**
     * Adds the given node as a child of the node in the given position.
     *
     * @param index the index of the child to be removed.
     *
     * @return the removed child.
     */
    Node<T> removeChildAt(int index);

    /**
     * Returns a list of nodes containing the children of the node.

     * @return list of nodes.
     */
    List<Node<T>> getChildren();

    /**
     * Returns the child node at position {@code index}.
     *
     * @param index the child position.
     * @return node child.
     */
    Node<T> getChildAt(int index);
}