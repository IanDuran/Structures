package ucr.ac.cr.ecci.ci1221.util.collections.set;

import ucr.ac.cr.ecci.ci1221.util.collections.stack.LinkedListStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.Stack;

import java.util.Iterator;

/**
 * @author Ian Duran
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements Set<T> {

    private TreeNode<T> root;
    private int storedObjects;

    /**
     * Constructor for the BinarySearchTree class
     */
    public BinarySearchTree() {
        root = null;
        storedObjects = 0;
    }

    /**
     * Returns a Set containing the union of the current one and the one passed as parameter.
     * To do this it creates two iterators, and adds all the elements of the current set to the new one.
     * Then it adds the elements of the second set that the new set does not have already. Finally it
     * returns the new set.
     * @param set the set to union.
     * @return a new set containing the union of the current one and the one passed as parameter.
     */
    @Override
    public Set<T> union(Set<T> set) {
        Set<T> union = new BinarySearchTree<>();
        Iterator<T> ownIterator = this.iterator();
        Iterator<T> otherIterator = set.iterator();
        while(ownIterator.hasNext())
            union.put(ownIterator.next());

        while(otherIterator.hasNext()){
            T key = otherIterator.next();
            if(!union.isMember(key))
                union.put(key);

        }
        return union;
    }

    /**
     * Returns a set containing the intersection of the current set and the one passed as parameter.
     * In order to do this, it creates an iterator for the other set. Then iterates over it's elements
     * and if the current element is a member of the current set, it is added to the intersection set.
     * It finally returns the intersection set.
     * @param set the set to intersect.
     * @return a set containing the intersection of the current set and the one passed as parameter.
     */
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

    /**
     * Returns a set with the elements inside the current set minus the ones in the set passed as parameter.
     * To do this, it makes an iterator for the current BST, then as it goes from value to value, it
     * checks if the values are members of the other set, if they are not, it adds them to the difference set
     * to finaly return it.
     * @param set the set to substract.
     * @return a set containing all the elements of the current set minus the ones if the other one.
     */
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

    /**
     * Tells if a given Comparable value is a member of the set.
     * It iterates over the BST comparing the value and going through the according node.
     * if the node contains the value, the boolean is set to true. Otherwise it remains in false.
     * @param key the element to look for.
     * @return true if the key is in the Tree, false otherwise.
     */
    @Override
    public boolean isMember(T key) {
        TreeNode<T> currentNode = root;
        boolean isMember = false;
        while(currentNode != null && !isMember){
            if(currentNode.getValue().compareTo(key) == 0)
                isMember = true;

            else{
                if(currentNode.getValue().compareTo(key) > 0)
                    currentNode = currentNode.getLeftSon();

                else
                    currentNode = currentNode.getRightSon();

            }
        }
        return isMember;
    }

    /**
     * Tells if the BST is empty or not
     * @return true if the Tree is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    /**
     * Clears the BST by setting it's variables to the default.
     */
    @Override
    public void clear() {
        root = null;
        storedObjects = 0;
    }

    /**
     * Inserts a new Comparable object in the tree if its not already a member.
     * To do this, if the root is null, it sets the root to be the new Node.
     * Otherwise it calls the private recursive method put to insert the node where it goes.
     * @param key the element to add.
     */
    @Override
    public void put(T key) {
        if(!isMember(key)){
            TreeNode<T> newNode = new TreeNode<>(key);
            if(root == null)
                root = newNode;

            else
                this.put(root, newNode);

            storedObjects++;
        }
    }

    /**
     * Searches the Tree for the correct place to store the node passed as a parameter.
     * First it compares if the value is greater or lesser than the one in the parent node,
     * then it moves accordingly. If the next node to travel is not null, it recursively calls
     * itself again, if it is null, then that is the place where the new node belongs.
     * @param parent current node traveled by.
     * @param node node to add to the BST.
     */
    private void put(TreeNode<T> parent, TreeNode<T> node){
        if(parent.getValue().compareTo(node.getValue()) < 0){
            if(parent.getRightSon() == null){
                node.setParent(parent);
                parent.setRightSon(node);
            }
            else
                this.put(parent.getRightSon(), node);

        }else{
            if(parent.getLeftSon() == null){
                node.setParent(parent);
                parent.setLeftSon(node);
            }
            else
                this.put(parent.getLeftSon(), node);

        }
    }

    /**
     * Removes a node from the BST iteratively.
     * It moves until the current node is the one with the value that is going to
     * be removed. Then it considers several cases. The first is that the node is a leaf,
     * in which case it is removed easily. The second is that the node has only one son,
     * in that case the node is replaced with it's child. And the last is that the node
     * has two children. In that case, it creates a Node to be deleted, that node is set
     * to be the rightmost of the left son of the node. Then the values in the nodes are
     * swapped and the node to be deleted is set to null.
     * @param key the element to remove.
     */
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
                this.remove(currentNode);
                storedObjects--;
            }
        }
    }

    /**
     * Removes a node from the Tree.
     * It considers several cases. The first is that the node is a leaf,
     * in which case it is removed easily. The second is that the node has only one son,
     * in that case the node is replaced with it's child. And the last is that the node
     * has two children. In that case, it creates a Node to be deleted, that node is set
     * to be the rightmost of the left son of the node. Then the values in the nodes are
     * swapped and calls itself recursively to delete the node.
     * @param node node that is going to be deleted.
     */
    private void remove(TreeNode<T> node){
        if(node.getRightSon() == null && node.getLeftSon() == null){
            // Node is a leaf
            if(node.getParent().getRightSon().getValue().compareTo(node.getValue()) == 0)
                node.getParent().setRightSon(null);

            else
                node.getParent().setLeftSon(null);

        }else if(node.getRightSon() != null && node.getLeftSon() == null){
            //Node only has right son
            if(node == root){
                root = root.getRightSon();
                root.setParent(null);
            }else if(node.getParent().getLeftSon() == node)//Node is left child of parent
                node.getParent().setLeftSon(node.getRightSon());

            else//Node is right child of parent
                node.getParent().setRightSon(node.getRightSon());

        }else if(node.getLeftSon() != null && node.getRightSon() == null){
            //Node only has left son
            if(node == root){
                root = root.getLeftSon();
                root.setParent(null);
            }else if(node.getParent().getLeftSon() == node)//Node is left child of parent
                node.getParent().setLeftSon(node.getLeftSon());

            else//Node is right child of parent
                node.getParent().setRightSon(node.getLeftSon());

        }else{
            //Node has two children
            TreeNode<T> toDelete = node.getLeftSon();
            while(toDelete.getRightSon() != null)
                toDelete = toDelete.getRightSon();

            this.swap(node, toDelete);
            this.remove(toDelete);
        }
    }

    /**
     * Returns the number of objects stored in the BST.
     * @return the number of objects stored in the BST.
     */
    @Override
    public int size() {
        return storedObjects;
    }

    /**
     * Returns a new instance of the BinaryTreeIterator class.
     * @return a new instance of the BinaryTreeIterator class.
     */
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(root);
    }

    /**
     * Swaps the values stored inside two given nodes.
     * @param x first node.
     * @param y second node.
     */
    private void swap(TreeNode<T> x, TreeNode<T> y){
        T temp = x.getValue();
        x.setValue(y.getValue());
        y.setValue(temp);
    }

    /**
     * Private class used to serve as the nodes of the BST.
     * @param <E> class of the objects that will be stored.
     */
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

    /**
     * Iterator for the Binary Search Tree class
     * @param <E> class that is being stored in the Tree
     */
    private class BinaryTreeIterator<E> implements Iterator<E>{

        private Stack<E> values = null;

        public BinaryTreeIterator(TreeNode<E> root){
            values = new LinkedListStack<>();
            this.addNodes(values, root);
        }

        /**
         * Private recursive method used to store the values of the nodes of the tree in a Stack.
         * It stores the post-order so that by getting them from the stack they come out in-order.
         * @param stack stack that will contain all the values from the BST.
         * @param node current node being traveled.
         */
        private void addNodes(Stack<E> stack, TreeNode<E> node){
            if(node != null){
                if(node.getRightSon() != null)
                    this.addNodes(stack, node.getRightSon());

                stack.push(node.getValue());
                if(node.getLeftSon() != null)
                    this.addNodes(stack, node.getLeftSon());

            }
        }

        @Override
        public boolean hasNext() {
            return !values.isEmpty();
        }

        @Override
        public E next() {
            E toReturn = null;
            if(!values.isEmpty())
                toReturn = values.pop();

            return toReturn;
        }
    }
}
