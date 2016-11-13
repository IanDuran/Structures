package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

import java.util.Objects;

/**
 * @author Ian Duran
 */
public class RedBlackTree<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
    private TreeNode<K, V> root;
    private int storedObjects;

    /**
     * Constructor for the Red-Black Tree
     */
    public RedBlackTree(){
        root = null;
        storedObjects = 0;
    }

    /**
     * Returns the number of elements stored in the Tree
     * @return the number of elements stored in the Tree
     */
    @Override
    public int size() {
        return storedObjects;
    }

    /**
     * Tells whether the Tree is empty or not
     * @return true if the Tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    /**
     * Given any object, it tells of the object is in the Tree as a key to some value
     * @param key key whose presence in this dictionary is to be tested
     * @return true if the object is a key, false otherwise
     */
    @Override
    public boolean containsKey(Object key) {
        return key instanceof Comparable && this.get((K)key) != null;
    }

    /**
     * Given any object, it tells if the object is stored in the Tree as a value.
     * @param value value whose presence in this dictionary is to be tested
     * @return true if the object is contained in the Tree, false otherwise
     */
    @Override
    public boolean containsValue(Object value) {
        boolean contained = false;

        return contained;
    }

    /**
     * Returns the value associated with a given key.
     * It calls the private recursive method get to do so.
     * @param key the key whose associated value is to be returned
     * @return the object associated with the given key, or null if there is none
     */
    @Override
    public V get(K key) {
        return this.get(root, key);
    }

    /**
     * Private recursive method that returns the value associated to a given key.
     * First it checks where to look for the value in the Tree, if it has to go left or right,
     * it makes the recursive call. If the key equals tho the one in the node, returns that value
     * @param node Node currently being traveled.
     * @param key Key that is being searched
     * @return The value inside the Node that contains the key
     */
    private V get(TreeNode<K, V> node, K key){
        V toReturn = null;
        if(node != null){
            if(node.getKey().compareTo(key) < 0)
                if(node.getRightSon() != null)
                    toReturn = this.get(node.getRightSon(), key);

            else if(node.getKey().compareTo(key) > 0)
                if(node.getLeftSon() != null)
                    toReturn = this.get(node.getLeftSon(), key);

            else
                toReturn = node.getValue();

        }
        return toReturn;
    }

    /**
     * Inserts and element into the Red-Black Tree and calls the method to evaluate
     * the inserted Node.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return
     */
    @Override
    public V put(K key, V value) {
        TreeNode<K, V> newNode = new TreeNode<>(key, value);
        newNode.setRed(true);
        if(root == null){
            root = newNode;
            repaint(root);
        }else
            put(root, newNode);

        storedObjects++;
        this.evaluate(newNode);
        return newNode.getValue();
    }

    /**
     * Recursively searches for a place to put a given Node and puts it there
     * @param parent Current Node being traveled
     * @param node Node to place in the Tree
     */
    private void put(TreeNode<K, V> parent, TreeNode<K, V> node){
        if(parent.getKey().compareTo(node.getKey()) > 0){
            if(parent.getLeftSon() == null){
                node.setParent(parent);
                parent.setLeftSon(node);
            }else
                put(parent.getLeftSon(), node);

        }else{
            if(parent.getRightSon() == null){
                node.setParent(parent);
                parent.setRightSon(node);
            }else
                put(parent.getRightSon(), node);

        }
    }

    @Override
    public V remove(K key) {
        V toReturn = null;
        if(this.get(key) != null){

        }
        return toReturn;
    }

    /**
     * Clears the Red-Black Tree of all it's contents
     */
    @Override
    public void clear() {
        root = null;
        storedObjects = 0;
    }

    /**
     * Returns a set containing all the keys of the Tree
     * @return A set with all the keys in the Tree
     */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new BinarySearchTree<K>();
        this.storeKeys(root, keys);
        return keys;
    }

    /**
     * Method that iterates the tree In-Order storing the keys in the tree.
     * @param node current node in the iteration.
     * @param set set that will hold the keys.
     */
    private void storeKeys(TreeNode<K, V> node, Set<K> set){
        if(node.getLeftSon() != null)
            this.storeKeys(node.getLeftSon(), set);

        set.put(node.getKey());
        if(node.getRightSon() != null)
            this.storeKeys(node.getRightSon(), set);

    }

    /**
     * Returns a list with all the values stored inside the Tree.
     * @return a list with all the values stored inside the Tree.
     */
    @Override
    public List<V> values() {
        List<V> values = new LinkedList<V>();
        this.listValues(root, values);
        return values;
    }

    /**
     * Method that iterates the tree In-Order inserting the values in the list passed
     * as a parameter, called by the values method.
     * @param node current node being listed.
     * @param list list that will hold all the values in the tree.
     */
    private void listValues(TreeNode<K, V> node, List<V> list){
        if(node.getLeftSon() != null)
            this.listValues(node.getLeftSon(), list);

        list.add(node.getValue());
        if(node.getRightSon() != null)
            this.listValues(node.getRightSon(), list);

    }

    /**
     * Evaluates a given node so that the Tree can do the re-balancing.
     * First it checks if the node is the root and then repaints it. Otherwise,
     * it checks if the Node's parent is red. If it is, it checks the uncle of the Node.
     * If the uncle is black, it does the needed rotation (single or double).
     * If the uncle is red, it calls the repaint method passing the Node as parameter.
     * @param node Node that is being evaluated.
     */
    private void evaluate(TreeNode<K, V> node){
        if(node == root)
            this.repaint(node);
        else if(node.getParent().isRed()){
            // Parent is red
            if(node.getParent() == node.getParent().getParent().getLeftSon()){
                //Parent in the left side
                if(node.getParent().getParent().getRightSon() == null || !node.getParent().getParent().getRightSon().isRed()){
                    //Uncle is black
                    if(node == node.getParent().getLeftSon())
                        this.simpleRightRotation(node.getParent());

                    else
                        this.doubleRightRotation(node.getParent());

                }else
                    this.repaint(node);

            }else{
                //Parent in the right side
                if(node.getParent().getParent().getLeftSon() == null || !node.getParent().getParent().getLeftSon().isRed()){
                    //Uncle is black
                    if(node == node.getParent().getRightSon())
                        this.simpleLeftRotation(node.getParent());

                    else
                        this.doubleLeftRotation(node.getParent());

                }else
                    this.repaint(node);

            }
        }
    }

    /**
     * Repaint method. If the node is the root, it is painted black.
     * Otherwise, the parent of the Node is painted black, the uncle of the node is
     * painted black. The grandparent of the Node is painted red and is evaluated.
     * @param node node to start repainting from.
     */
    public void repaint(TreeNode<K, V> node){
        if(node == root)
            node.setRed(false);

        else{
            //Repaint parent to black
            node.getParent().setRed(false);
            if(node.getParent().getParent() != null) {
                //Repaint uncle to black
                if (node.getParent() == node.getParent().getParent().getLeftSon())
                    //Parent in the left side, Uncle in the right side
                    node.getParent().getParent().getRightSon().setRed(false);

                else
                    //Parent in the right side, Uncle in the left side
                    node.getParent().getParent().getLeftSon().setRed(false);

                //Repaint Grandparent red and evaluate it
                node.getParent().getParent().setRed(true);
                this.evaluate(node.getParent().getParent());
            }
        }
    }

    /**
     * Simple left rotation for balancing.
     * First it checks if the Node is the root, if it is, the root variable is updated.
     * Then sets the right son of the Parent of the node to be it's (node's) left son, it's left son to be its current parent,
     * the parent of the node is set to be the current grandparent. Then, if the new parent of the node is no null,
     * it puts the rotated subtree under the node where it belongs. Finally it sets the parent of the new left son to be the node
     * and repaints the two sons red and itself black.
     * @param node rotation axis
     */
    private void simpleLeftRotation(TreeNode<K, V> node){
        if(node.getParent() == root){
            root = node;
            repaint(root);
        }
        node.getParent().setRightSon(node.getLeftSon());
        node.setLeftSon(node.getParent());
        node.setParent(node.getParent().getParent());

        if(node.getParent() != null){
            if(node.getKey().compareTo(node.getParent().getKey()) < 0)
                node.getParent().setLeftSon(node);

            else
                node.getParent().setRightSon(node);
        }
        node.getLeftSon().setParent(node);
        node.getLeftSon().setRed(true);
        node.getRightSon().setRed(true);
        node.setRed(false);
    }

    /**
     * Simple right rotation for balancing. It does all the same things described in
     * the previous method, but with reversed directions
     * @param node rotation axis.
     */
    private void simpleRightRotation(TreeNode<K, V> node){
        if(node.getParent() == root){
            root = node;
            repaint(root);
        }
        node.getParent().setLeftSon(node.getRightSon());
        node.setRightSon(node.getParent());
        node.setParent(node.getParent().getParent());
        if(node.getParent() != null){
            if(node.getKey().compareTo(node.getParent().getKey()) > 0)
                node.getParent().setRightSon(node);

            else
                node.getParent().setLeftSon(node);
        }
        node.getRightSon().setParent(node);
        node.getRightSon().setRed(true);
        node.getLeftSon().setRed(true);
        node.setRed(false);
    }

    /**
     * Double left rotation for balancing.
     * It firsts sets the right son of the parent to be the left node of the son.
     * Then it sets the parent of the left son to be the parent of the node,
     * then sets the parent of the node to be the left son of the node.
     * After that, if the left son is not null, it sets the parent of the new left son to be the node
     * and the right son of the parent to be the node.
     * Finally it calls the simpleRightRotation method.
     * @param node rotation axis
     */
    private void doubleLeftRotation(TreeNode<K, V> node){
        node.getParent().setRightSon(node.getLeftSon());
        node.getLeftSon().setParent(node.getParent());
        node.setParent(node.getLeftSon());
        node.setLeftSon(node.getParent().getRightSon());
        if(node.getLeftSon() != null)
            node.getLeftSon().setParent(node);
        node.getParent().setRightSon(node);
        this.simpleLeftRotation(node.getParent());
    }

    /**
     * Double right rotation for balancing.
     * Does the same thing as the method described above, just with inverted sides.
     * @param node rotation axis
     */
    private void doubleRightRotation(TreeNode<K, V> node){
        node.getParent().setLeftSon(node.getRightSon());
        node.getRightSon().setParent(node.getParent());
        node.setParent(node.getRightSon());
        node.setRightSon(node.getParent().getLeftSon());
        if(node.getRightSon() != null)
            node.getRightSon().setParent(node);
        node.getParent().setLeftSon(node);
        this.simpleRightRotation(node.getParent());
    }

    /**
     * Private class that conforms the Nodes of the Red-Black Tree.
     * @param <K>
     * @param <V>
     */
    private class TreeNode<K, V>{
        private K key = null;
        private V value = null;
        private TreeNode<K, V> leftSon = null;
        private TreeNode<K, V> rightSon = null;
        private TreeNode<K, V> parent = null;
        private boolean red = true;
        private boolean doubleBlack = false;

        /**
         * Constructor of the private TreeNode class in the Red-Black Tree
         * @param key key of the value inside the Node
         * @param value value stored inside the Node
         */
        public TreeNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        public TreeNode<K, V> getLeftSon() {
            return leftSon;
        }

        public void setLeftSon(TreeNode<K, V> leftSon) {
            this.leftSon = leftSon;
        }

        public TreeNode<K, V> getRightSon() {
            return rightSon;
        }

        public void setRightSon(TreeNode<K, V> rightSon) {
            this.rightSon = rightSon;
        }

        public TreeNode<K, V> getParent() {
            return parent;
        }

        public void setParent(TreeNode<K, V> parent) {
            this.parent = parent;
        }

        public boolean isRed() {
            return red;
        }

        public void setRed(boolean red) {
            this.red = red;
        }

        public V getValue(){
            return value;
        }

        public K getKey(){
            return key;
        }

        public void setValue(V value){
            this.value = value;
        }

        public void setKey(K key){
            this.key = key;
        }

        public boolean isDoubleBlack() {
            return doubleBlack;
        }

        public void setDoubleBlack(boolean doubleBlack) {
            this.doubleBlack = doubleBlack;
        }
    }
}
