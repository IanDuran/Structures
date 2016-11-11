package ucr.ac.cr.ecci.ci1221.util.collections.tree;

/**
 * @author Ian Duran
 */
public class PointerTree<T> implements Tree<T> {

    private Node<T> root;

    /**
     * Creates a new instance of this class and assigns it a Node passed as a parameter as it's root.
     * @param root Node to be the root of the Tree
     */
    public PointerTree(Node<T> root){
        this.root = root;
    }

    /**
     * Returns the root Node of the Tree
     * @return the root Node of the Tree
     */
    @Override
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Returns whether the Tree has a root or not
     * @return true of the root is null, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
