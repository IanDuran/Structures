package ucr.ac.cr.ecci.ci1221.tests;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import ucr.ac.cr.ecci.ci1221.util.collections.set.BinarySearchTree;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Trie;

import java.util.Iterator;
import java.util.Random;

public class SetTests {

    public void linkedListSetTests(){
        System.out.println("LinkedListSet");
        Set<Integer> lls = new LinkedListSet<>();
        Random r = new Random();
        while(lls.size() < 10)
            lls.put(r.nextInt(20));

        System.out.println("Values of the first set:");
        Iterator<Integer> i = lls.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();

        Set<Integer> lls2 = new LinkedListSet<>();
        while(lls2.size() < 10)
            lls2.put(r.nextInt(20));
        System.out.println("Values of the second set:");
        i = lls2.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();

        System.out.println("Union");
        Set<Integer> union = lls.union(lls2);
        i = union.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();

        System.out.println("Intersection");
        Set<Integer> intersection = lls.intersection(lls2);
        i = intersection.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();

        System.out.println("Difference");
        Set<Integer> difference = lls.difference(lls2);
        i = difference.iterator();
        while(i.hasNext())
            System.out.println(i.next());
    }

    public void binarySearchTreeTests(){
        System.out.println("BinarySearchTree");
        Set<Integer> bst = new BinarySearchTree<>();
        Random r = new Random();
        while(bst.size() < 10)
            bst.put(r.nextInt(20));
        System.out.println("Values of the first set:");
        Iterator<Integer> i = bst.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println();
        Set<Integer> bst2 = new BinarySearchTree<>();
        while(bst2.size() < 10)
            bst2.put(r.nextInt(20));

        System.out.println("Values of the second set:");
        i = bst2.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println();

        System.out.println("Union");
        Set<Integer> union = bst.union(bst2);
        i = union.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println();

        System.out.println("Intersection");
        Set<Integer> intersection = bst.intersection(bst2);
        i = intersection.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println();

        System.out.println("Difference");
        Set<Integer> difference = bst.difference(bst2);
        i = difference.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println();
    }

    public void trieTests(){
        Set<String> t = new Trie();
        t.put("God Emperor Trump");
        t.put("USA");
        t.put("Broh");
        t.put("Bruh");
        t.put("Brah");
        System.out.println("Strings in the first Trie:");
        Iterator<String> i = t.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();
        System.out.println("Size should be 5: " + t.size());
        System.out.println();
        Set<String> t2 = new Trie();
        t2.put("Wall");
        t2.put("Snow Mexicans");
        t2.put("USA");
        t2.put("My name is Borat");
        t2.put("Broh");
        t2.put("Food Library");
        System.out.println("Strings in the second Trie");
        i = t2.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();
        Set<String> union = t.union(t2);
        System.out.println("Union");
        i = union.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();
        Set<String> intersection = t.intersection(t2);
        System.out.println("Intersection");
        i = intersection.iterator();
        while(i.hasNext())
            System.out.println(i.next());

        System.out.println();
        Set<String> difference = t.difference(t2);
        System.out.println("Difference");
        i = difference.iterator();
        while(i.hasNext())
            System.out.println(i.next());
        System.out.println();
    }

    public static void main(String... args){
        SetTests st = new SetTests();
        st.linkedListSetTests();
        st.binarySearchTreeTests();
        st.trieTests();
    }
}
