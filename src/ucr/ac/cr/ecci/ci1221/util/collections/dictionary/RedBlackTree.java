package ucr.ac.cr.ecci.ci1221.util.collections.dictionary;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;

/**
 * @author Ian Duran
 */
public class RedBlackTree<K extends Comparable<? super K>, V> implements Dictionary<K, V> {
    private TreeNode<K, V> root;
    private int storedObjects;

    public RedBlackTree(){
        root = null;
        storedObjects = 0;
    }

    @Override
    public int size() {
        return storedObjects;
    }

    @Override
    public boolean isEmpty() {
        return storedObjects == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean contained = false;
        if(key instanceof Comparable)
            if(this.get((K)key) != null)
                contained = true;

        return contained;
    }

    @Override
    public boolean containsValue(Object value) {
        boolean contained = false;
        return contained;
    }

    @Override
    public V get(K key) {
        return this.get(root, key);
    }

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

        return toReturn;
    }

    private void removeNode(TreeNode<K, V> node){

    }

    private void doubleBlackDelete(TreeNode<K, V> node){

    }

    @Override
    public void clear() {
        root = null;
        storedObjects = 0;
    }

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
     * Repaint method
     * @param node node to start repainting from
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
     * Simple left rotation for balancing
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
     * Simple right rotation for balancing
     * @param node rotation axis
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
     * Double left rotation for balancing
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
     * Double right rotation for balancing
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

    private class TreeNode<K, V>{
        private K key = null;
        private V value = null;
        private TreeNode<K, V> leftSon = null;
        private TreeNode<K, V> rightSon = null;
        private TreeNode<K, V> parent = null;
        private boolean red = true;
        private boolean doubleBlack = false;

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
