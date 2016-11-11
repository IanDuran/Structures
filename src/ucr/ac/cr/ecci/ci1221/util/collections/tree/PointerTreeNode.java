package ucr.ac.cr.ecci.ci1221.util.collections.tree;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Student Name
 */
public class PointerTreeNode<T> implements Node<T> {

    private T label;
    private PointerTreeNode<T> parent;
    private LinkedList<Node<T>> children;

    /**
     * Creates a new instance of a PointerTreeNode with the label passed as parameter
     * @param label
     */
    public PointerTreeNode(T label){
        this.label = label;
        parent = null;
        children = null;
    }

    /**
     * Setter for the label of the Node
     * @param data the element to set as value.
     */
    @Override
    public void setLabel(T data) {
        label = data;
    }

    /**
     * Getter method for the label in the Node
     * @return The value of the label of the Node
     */
    @Override
    public T getLabel() {
        return label;
    }

    /**
     * Returns the Node that is the parent of this one, if it has one.
     * @return the parent of the Node.
     */
    @Override
    public Node<T> getParent() {
        return parent;
    }

    /**
     * Adds a Node passed as parameter to the list of the children of the current Node.
     * @param child the child to add.
     */
    @Override
    public void addChild(Node<T> child) {
        if(children == null)
            children = new LinkedList<>();

        if(child instanceof PointerTreeNode){
            ((PointerTreeNode) child).setParent(this);
            children.add(child);
        }
    }

    /**
     * Adds a Node to the list of the current Node children in the given position.
     * @param index the position.
     * @param child the child to be added.
     */
    @Override
    public void addChildAt(int index, Node<T> child) {
        children.add(index, child);
    }

    /**
     * Removes the children of the current node.
     */
    @Override
    public void removeChildren() {
        children = null;
    }

    /**
     * Removes a Node from the list of children in the position of the index passed as a parameter.
     * @param index the index of the child to be removed.
     * @return The Node containing the removed child.
     */
    @Override
    public Node<T> removeChildAt(int index) {
        return children.remove(index);
    }

    /**
     * Returns a List with the children of the Node.
     * @return a List woth the children of the Node.
     */
    @Override
    public List<Node<T>> getChildren() {
        return children;
    }

    /**
     * Returns the child of the Node that is in the position of the index passed as parameter.
     * @param index the child position.
     * @return the child of the Node at the Index.
     */
    @Override
    public Node<T> getChildAt(int index) {
        return children.get(index);
    }

    /**
     * Private method that sets the parent of the Node. Done in order to simplify
     * getting the parent of the Node
     * @param parent parent Node.
     */
    private void setParent(PointerTreeNode<T> parent){
        this.parent = parent;
    }
}
