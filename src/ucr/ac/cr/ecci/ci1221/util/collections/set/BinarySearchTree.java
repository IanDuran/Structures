package ucr.ac.cr.ecci.ci1221.util.collections.set;

import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Student Name
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Set<T> {

    /* @TODO add missing attributes and fill methods. */
    private TreeNode root = null;
    private int storedObjects = 0;

    @Override
    public Set<T> union(Set<T> set) {
        return null;
    }

    @Override
    public Set<T> intersection(Set<T> set) {
        return null;
    }

    @Override
    public Set<T> difference(Set<T> set) {
        return null;
    }

    @Override
    public boolean isMember(T key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public void put(T key) {
        TreeNode<T> newNode = new TreeNode<>(key);
        if(root == null)
            root = newNode;
        else
            put(root, newNode);
        storedObjects++;
    }

    private void put(TreeNode<T> parent, TreeNode<T> node){
        if(parent.getValue().compareTo(node.getValue()) >= 0){
            if(parent.getRightSon() == null)
                parent.setRightSon(node);
            else
                put(parent.getRightSon(), node);
        }else{
            if(parent.getLeftSon() == null)
                parent.setLeftSon(node);
            else
                put(parent.getLeftSon(), node);
        }
    }

    @Override
    public void remove(T key) {

    }

    @Override
    public int size() {
        return storedObjects;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>();
    }

    private class TreeNode<E>{
        private E value = null;
        private TreeNode<E> leftSon = null;
        private TreeNode<E> rightSon = null;

        public TreeNode(E value){
            this.value = value;
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
    }

    private class BinaryTreeIterator<E> implements Iterator<E>{

        public BinaryTreeIterator(){

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }
}
