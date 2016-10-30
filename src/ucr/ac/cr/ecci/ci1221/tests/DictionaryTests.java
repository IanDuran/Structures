package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.RedBlackTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;

import java.util.Iterator;

public class DictionaryTests {
    public static void main(String[] args){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.put(3);
        bst.put(2);
        bst.put(1);

        bst.remove(2);
        Iterator<Integer> i = bst.iterator();
        while(i.hasNext())
            System.out.println(i.next());
    }
}
