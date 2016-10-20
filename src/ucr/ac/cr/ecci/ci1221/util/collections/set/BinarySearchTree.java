package ucr.ac.cr.ecci.ci1221.util.collections.set;

import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Iterator;

/**
 * @author Student Name
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Set<T> {

    private TreeNode<T> root = null;
    private int storedObjects = 0;

    @Override
    public Set<T> union(Set<T> set) {
        Set<T> union = new BinarySearchTree<>();
        Iterator<T> ownIterator = this.iterator();
        Iterator<T> otherIterator = set.iterator();
        while(ownIterator.hasNext())
            union.put(ownIterator.next());

        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(!union.isMember(key)){
                union.put(key);
            }
        }
        return union;
    }

    @Override
    public Set<T> intersection(Set<T> set) {
        Set<T> intersection = new BinarySearchTree<>();
        Iterator<T> otherIterator = set.iterator();
        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(isMember(key))
                intersection.put(key);
        }
        return intersection;
    }

    @Override
    public Set<T> difference(Set<T> set) {
        Set<T> difference = new BinarySearchTree<>();
        Iterator<T> ownIterator = this.iterator();
        while(ownIterator.hasNext()){
            T key = ownIterator.next();
            if(!set.isMember(key))
                difference.put(key);
        }
        return difference;
    }

    @Override
    public boolean isMember(T key) {
        TreeNode<T> currentNode = root;
        boolean isMember = false;
        while(currentNode != null && !isMember){
            if(currentNode.getValue().compareTo(key) == 0)
                isMember = true;
            else{
                if(currentNode.getValue().compareTo(key) >= 0){
                    currentNode = currentNode.getLeftSon();
                }else{
                    currentNode = currentNode.getRightSon();
                }
            }
        }
        return isMember;
    }

    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    @Override
    public void clear() {
        root = null;
        storedObjects = 0;
    }

    @Override
    public void put(T key) {
        if(!isMember(key)){
            TreeNode<T> newNode = new TreeNode<>(key);
            if(root == null)
                root = newNode;
            else
                put(root, newNode);
            storedObjects++;
        }
    }

    private void put(TreeNode<T> parent, TreeNode<T> node){
        if(parent.getValue().compareTo(node.getValue()) < 0){
            if(parent.getRightSon() == null){
                node.setParent(parent);
                parent.setRightSon(node);
            }
            else
                put(parent.getRightSon(), node);
        }else{
            if(parent.getLeftSon() == null){
                node.setParent(parent);
                parent.setLeftSon(node);
            }
            else
                put(parent.getLeftSon(), node);
        }
    }

    @Override
    public void remove(T key) {
        if(root != null){
            if(this.isMember(key)){
                TreeNode<T> currentNode = root;
                while(currentNode.getValue().compareTo(key) != 0){
                    if(currentNode.getValue().compareTo(key) > 0)
                        currentNode = currentNode.getLeftSon();
                    else
                        currentNode = currentNode.getRightSon();
                }
                if(currentNode.getRightSon() == null && currentNode.getLeftSon() == null){
                    if(currentNode.getParent().getRightSon().getValue().compareTo(currentNode.getValue()) == 0)
                        currentNode.getParent().setRightSon(null);
                    else
                        currentNode.getParent().setLeftSon(null);
                }else{

                }
                storedObjects--;
            }
        }
    }

    @Override
    public int size() {
        return storedObjects;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(root);
    }

    private void swap(TreeNode<T> x, TreeNode<T> y){
        T temp = x.getValue();
        x.setValue(y.getValue());
        y.setValue(temp);
    }

    private class TreeNode<E>{
        private E value = null;
        private TreeNode<E> parent = null;
        private TreeNode<E> leftSon = null;
        private TreeNode<E> rightSon = null;

        public TreeNode(E value){
            this.value = value;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public void setParent(TreeNode<E> parent) {
            this.parent = parent;
        }

        public TreeNode<E> getLeftSon() {
            return leftSon;
        }

        public void setLeftSon(TreeNode<E> leftSon) {
            this.leftSon = leftSon;
        }

        public TreeNode<E> getRightSon() {
            return rightSon;
        }

        public void setRightSon(TreeNode<E> rightSon) {
            this.rightSon = rightSon;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    private class BinaryTreeIterator<E> implements Iterator<E>{

        private List<E> values = null;
        private int size = 0;
        private int currentIndex = 0;

        public BinaryTreeIterator(TreeNode<E> root){
            values = new ArrayList<>();
            addNodes(values, root);
            size = values.size();
        }

        private void addNodes(List<E> list, TreeNode<E> node){
            if(node != null){
                if(node.getLeftSon() != null)
                    addNodes(list, node.getLeftSon());
                
                list.add(node.getValue());

                if(node.getRightSon() != null)
                    addNodes(list, node.getRightSon());
            }
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            E toReturn = null;
            if(currentIndex < size){
                toReturn = values.get(currentIndex);
                currentIndex++;
            }
            return toReturn;
        }
    }
}
