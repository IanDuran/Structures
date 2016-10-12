package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.SearchAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.algorithm.SortingAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.ArrayList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.DoubleLinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

import java.sql.Time;
import java.util.Random;

public class TimeTests {
    public void bubbleSortTest(){
        Random r = new Random();
        System.out.println("Bubble Sort");
        System.out.println("Array List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> a = new ArrayList<>();
            for(int j = 0; j < i; j++){
                a.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.bubbleSort(a);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> l = new LinkedList<>();
            for(int j = 0; j < i; j++){
                l.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.bubbleSort(l);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Double Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> d = new DoubleLinkedList<>();
            for(int j = 0; j < i; j++){
                d.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.bubbleSort(d);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();
    }

    public void insertionSortTest(){
        Random r = new Random();
        System.out.println("Insertion Sort");
        System.out.println("Array List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> a = new ArrayList<>();
            for(int j = 0; j < i; j++){
                a.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.insertionSort(a);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> l = new LinkedList<>();
            for(int j = 0; j < i; j++){
                l.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.insertionSort(l);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Double Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> d = new DoubleLinkedList<>();
            for(int j = 0; j < i; j++){
                d.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.insertionSort(d);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();
    }

    public void selectionSortTest(){
        Random r = new Random();
        System.out.println("Selection Sort");
        System.out.println("Array List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> a = new ArrayList<>();
            for(int j = 0; j < i; j++){
                a.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.selectionSort(a);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> l = new LinkedList<>();
            for(int j = 0; j < i; j++){
                l.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.selectionSort(l);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Double Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> d = new DoubleLinkedList<>();
            for(int j = 0; j < i; j++){
                d.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.selectionSort(d);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();
    }

    public void mergeSortTest(){
        Random r = new Random();
        System.out.println("Merge Sort");
        System.out.println("Array List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> a = new ArrayList<>();
            for(int j = 0; j < i; j++){
                a.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.mergeSort(a);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> l = new LinkedList<>();
            for(int j = 0; j < i; j++){
                l.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.mergeSort(l);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Double Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> d = new DoubleLinkedList<>();
            for(int j = 0; j < i; j++){
                d.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.mergeSort(d);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();
    }

    public void quickSortTest(){
        Random r = new Random();
        System.out.println("Quick Sort");
        System.out.println("Array List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> a = new ArrayList<>();
            for(int j = 0; j < i; j++){
                a.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.quickSort(a);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> l = new LinkedList<>();
            for(int j = 0; j < i; j++){
                l.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.quickSort(l);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();

        System.out.println("Double Linked List");
        for(int i = 1; i <= 100000; i *= 10){
            List<Integer> d = new DoubleLinkedList<>();
            for(int j = 0; j < i; j++){
                d.add(r.nextInt(100));
            }
            long before = System.currentTimeMillis();
            SortingAlgorithms.quickSort(d);
            long after = System.currentTimeMillis();
            System.out.println("Size of the list: " + i + ", time in milliseconds: " + (after - before));
        }
        System.out.println();
    }
    public static void main(String[] args){
        TimeTests t = new TimeTests();
        t.bubbleSortTest();
        System.out.println();
        t.insertionSortTest();
        System.out.println();
        t.selectionSortTest();
        System.out.println();
        t.mergeSortTest();
        System.out.println();
        t.quickSortTest();
    }
}
