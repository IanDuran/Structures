package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.TreeAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Dictionary;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Hashtable;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.RedBlackTree;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Trie;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Node;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTree;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.PointerTreeNode;
import ucr.ac.cr.ecci.ci1221.util.collections.tree.Tree;

import java.util.Iterator;

public class DictionaryTests {
    public static void main(String[] args){
        Dictionary<Integer, Integer> rb = new RedBlackTree<>();
        rb.put(10, 10);
        rb.put(85, 85);
        rb.put(15, 15);
        rb.put(70, 70);
        rb.put(20, 20);
        rb.put(60, 60);
        rb.put(30, 30);
        rb.put(50, 50);
        rb.put(65, 65);
        rb.put(80, 80);
        rb.put(90, 90);
        rb.put(40, 40);
        rb.put(5, 5);
        rb.put(55, 55);
        rb.put(7, 7);
        System.out.println();
    }
}
