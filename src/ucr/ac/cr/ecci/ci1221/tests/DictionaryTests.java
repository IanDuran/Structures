package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Dictionary;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Hashtable;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.RedBlackTree;

import java.util.Iterator;

public class DictionaryTests {
    public void hashTableTests(){
        Dictionary<Integer, String> ht = new Hashtable<>();
        ht.put(2, "Bruh");
        ht.put(1, "Broh");
        System.out.println(ht.containsKey(5));
    }

    public void redBlackTests(){
        Dictionary<Integer, String> rbt = new RedBlackTree<>();
        rbt.put(30, "qwerty");
        rbt.put(99, "asdf");
        rbt.put(100, "wewewe");
        rbt.remove(100);
        //System.out.println(rbt.containsKey(10));
    }

    public static void main(String[] args) {
        DictionaryTests dt = new DictionaryTests();
        dt.redBlackTests();
        //dt.hashTableTests();
    }
}
