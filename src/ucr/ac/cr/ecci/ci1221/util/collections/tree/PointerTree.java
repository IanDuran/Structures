package ucr.ac.cr.ecci.ci1221.util.collections.tree;

/**
 * @author Ian Duran
 */
public class PointerTree<T> implements Tree<T> {

    private Node<T> root = null;

    public PointerTree(Node<T> root){
        this.root = root;
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
