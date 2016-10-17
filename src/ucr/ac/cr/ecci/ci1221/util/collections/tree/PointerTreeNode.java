package ucr.ac.cr.ecci.ci1221.util.collections.tree;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Student Name
 */
public class PointerTreeNode<T> implements Node<T> {

    /* @TODO add missing attributes and fill methods. */

    private T label = null;
    private PointerTreeNode<T> parent = null;
    private LinkedList<Node<T>> children = null;

    @Override
    public void setLabel(T data) {
        label = data;
    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public Node<T> getParent() {
        return parent;
    }

    @Override
    public void addChild(Node<T> child) {
        if(children == null)
            children = new LinkedList<>();
        if(child instanceof PointerTreeNode){
            ((PointerTreeNode) child).setParent(this);
            children.add(child);
        }
    }

    @Override
    public void addChildAt(int index, Node<T> child) {
        children.add(index, child);
    }

    @Override
    public void removeChildren() {
        children = null;
    }

    @Override
    public Node<T> removeChildAt(int index) {
        return children.remove(index);
    }

    @Override
    public List<Node<T>> getChildren() {
        return children;
    }

    @Override
    public Node<T> getChildAt(int index) {
        return children.get(index);
    }

    private void setParent(PointerTreeNode<T> parent){
        this.parent = parent;
    }
}
