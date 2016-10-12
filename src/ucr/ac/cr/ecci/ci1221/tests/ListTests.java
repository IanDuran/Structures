package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.list.DoubleLinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;

import java.util.Iterator;
import java.util.Random;

public class ListTests {
    public static void main(String[] args){
        Random r = new Random();
        System.out.println("Array List");
        System.out.println();
        List<Integer> a = new ArrayList<>();
        System.out.println("Size should be zero: " + a.size());
        System.out.println("Array List should be empty: " + a.isEmpty());
        System.out.println();
        for(int i = 0; i < 50; i++){
            int toAdd = r.nextInt(100);
            a.add(toAdd);
            System.out.println("Added " + a.get(a.size() - 1) + " to the Array List");
        }
        System.out.println();
        System.out.println("Size should be 50: " + a.size());
        System.out.println();
        System.out.println("Removing items from random positions");
        for(int i = 0; i < 20; i++){
            int pos = r.nextInt(a.size());
            System.out.println("Removed " + a.remove(pos) + " from the position " + pos + " of the Array List");
        }
        System.out.println();
        System.out.println("Size should be 30: " + a.size());
        System.out.println();
        System.out.println("Getting items from random positions");
        for(int i = 0; i < 10; i++){
            int pos = r.nextInt(a.size());
            System.out.println("Got " + a.get(pos) + " from position " + pos);
        }
        System.out.println();
        System.out.println("Array List Iterator");
        Iterator arrayIterator = a.iterator();
        int counter = 0;
        while(arrayIterator.hasNext()){
            System.out.println("Index: " + counter + ", item: " +arrayIterator.next());
            counter++;
        }
        System.out.println();
        System.out.println("Changing random elements in the list, note that the indexes can be repeated");
        System.out.println();
        for(int i = 0; i < 10; i++){
            int pos = r.nextInt(a.size());
            int newElement = r.nextInt(100);
            System.out.println("Changed item in position " + pos + " from " + a.set(pos, newElement) + " to " + a.get(pos));
        }
        System.out.println();
        System.out.println("Final List");
        System.out.println();
        arrayIterator = a.iterator();
        counter = 0;
        while(arrayIterator.hasNext()){
            System.out.println("Index: " + counter + ", item: " +arrayIterator.next());
            counter++;
        }
        System.out.println();
        System.out.println();



        System.out.println("Linked List");
        System.out.println();
        List<Integer> l = new LinkedList<>();
        System.out.println("Size should be zero: " + l.size());
        System.out.println("Linked List should be empty: " + l.isEmpty());
        System.out.println();
        for(int i = 0; i < 50; i++){
            int toAdd = r.nextInt(100);
            l.add(toAdd);
            System.out.println("Added " + l.get(l.size() - 1) + " to the Linked List");
        }
        System.out.println();
        System.out.println("Size should be 50: " + l.size());
        System.out.println();
        System.out.println("Removing items from random positions");
        for(int i = 0; i < 20; i++){
            int pos = r.nextInt(l.size());
            System.out.println("Removed " + l.remove(pos) + " from the position " + pos + " of the Linked List");
        }
        System.out.println();
        System.out.println("Size should be 30: " + l.size());
        System.out.println();
        System.out.println("Getting items from random positions");
        for(int i = 0; i < 10; i++){
            int pos = r.nextInt(l.size());
            System.out.println("Got " + l.get(pos) + " from position " + pos);
        }
        System.out.println();
        System.out.println("Linked List Iterator");
        Iterator linkedIterator = l.iterator();
        counter = 0;
        while(linkedIterator.hasNext()){
            System.out.println("Index: " + counter + ", item: " + linkedIterator.next());
            counter++;
        }
        System.out.println();
        System.out.println("Changing random elements in the list, note that the indexes can be repeated");
        System.out.println();
        for(int i = 0; i < 10; i++){
            int pos = r.nextInt(l.size());
            int newElement = r.nextInt(100);
            System.out.println("Changed item in position " + pos + " from " + l.set(pos, newElement) + " to " + l.get(pos));
        }
        System.out.println();
        System.out.println("Final Linked List");
        System.out.println();
        linkedIterator = l.iterator();
        counter = 0;
        while(linkedIterator.hasNext()){
            System.out.println("Index: " + counter + ", item: " + linkedIterator.next());
            counter++;
        }
        System.out.println();
        System.out.println();


        System.out.println("Double Linked List");
        System.out.println();
        List<Integer> dl = new DoubleLinkedList<>();
        System.out.println("Size should be zero: " + dl.size());
        System.out.println("Double Linked List should be empty: " + dl.isEmpty());
        System.out.println();
        for(int i = 0; i < 50; i++){
            int toAdd = r.nextInt(100);
            dl.add(toAdd);
            System.out.println("Added " + dl.get(dl.size() - 1) + " to the Double Linked List");
        }
        System.out.println();
        System.out.println("Size should be 50: " + dl.size());
        System.out.println();
        System.out.println("Removing items from random positions");
        for(int i = 0; i < 20; i++){
            int pos = r.nextInt(dl.size());
            System.out.println("Removed " + dl.remove(pos) + " from the position " + pos + " of the Double Linked List");
        }
        System.out.println();
        System.out.println("Size should be 30: " + dl.size());
        System.out.println();
        System.out.println("Getting items from random positions");
        for(int i = 0; i < 10; i++){
            int pos = r.nextInt(dl.size());
            System.out.println("Got " + dl.get(pos) + " from position " + pos);
        }
        System.out.println();
        System.out.println("Double Linked List Iterator");
        Iterator doubleLinkedIterator = dl.iterator();
        counter = 0;
        while(doubleLinkedIterator.hasNext()){
            System.out.println("Index: " + counter + ", item: " + doubleLinkedIterator.next());
            counter++;
        }
        System.out.println();
        System.out.println("Changing random elements in the list, note that the indexes can be repeated");
        System.out.println();
        for(int i = 0; i < 10; i++){
            int pos = r.nextInt(dl.size());
            int newElement = r.nextInt(100);
            System.out.println("Changed item in position " + pos + " from " + dl.set(pos, newElement) + " to " + dl.get(pos));
        }
        System.out.println();
        System.out.println("Final List");
        System.out.println();
        doubleLinkedIterator = dl.iterator();
        counter = 0;
        while(doubleLinkedIterator.hasNext()){
            System.out.println("Index: " + counter + ", item: " + doubleLinkedIterator.next());
            counter++;
        }
        System.out.println();
        System.out.println();
    }
}
