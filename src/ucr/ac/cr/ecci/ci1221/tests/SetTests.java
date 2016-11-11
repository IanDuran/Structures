package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Trie;

import java.util.Iterator;

public class SetTests {

    public void linkedListSetTests(){
        Set<Integer> lls = new LinkedListSet<>();
    }

    public void binarySearchTreeTests(){
        Set<Integer> bst = new BinarySearchTree<>();
    }

    public void trieTests(){
        Set<String> t = new Trie();

    }

    public static void main(String... args){
        SetTests st = new SetTests();
        st.linkedListSetTests();
        st.binarySearchTreeTests();
        st.trieTests();
    }
}
