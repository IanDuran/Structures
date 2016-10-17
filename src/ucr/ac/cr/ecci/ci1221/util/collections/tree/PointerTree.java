package ucr.ac.cr.ecci.ci1221.util.collections.tree;

/**
 * @author Student Name
 */
public class PointerTree<T> implements Tree<T> {

    /* @TODO add missing attributes and fill methods. */

    PointerTreeNode<T> root = null;

    public PointerTree(Node<T> root){
        this.root = (PointerTreeNode)root;
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
