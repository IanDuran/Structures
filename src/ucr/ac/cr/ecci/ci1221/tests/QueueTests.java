package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.queue.CircularArrayQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.Queue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.ArrayQueue;


public class QueueTests {
    public static void main(String[] args){
        System.out.println("Array Queue");
        Queue<Integer> aq = new ArrayQueue<>();
        for(int i = 0; i < 50; i++){
            aq.enqueue(i);
            System.out.println("Enqueuing " + i + " into the Array Queue");
        }
        System.out.println();
        System.out.println("Size should be 50: " + aq.size());
        System.out.println();
        System.out.println("First item that should be dequeued is: " + aq.peek());
        System.out.println();
        for(int i = 0; i < 25; i++){
            System.out.println("Dequeued " + aq.dequeue() + " from the Array Queue, next item to be dequeued is: " + aq.peek());
        }
        System.out.println();
        System.out.println("Size should be 25: " + aq.size());
        System.out.println();
        System.out.println("Dequeuing while the Array Queue is not empty");
        System.out.println("Next item that should be dequeued is: " + aq.peek());
        System.out.println();
        while(!aq.isEmpty()){
            System.out.println("Dequeued " + aq.dequeue() + " from the Array Queue, next item to be dequeued is: " + aq.peek());
        }
        System.out.println();
        System.out.println("Size should be zero: " + aq.size());
        System.out.println("Array Queue should be empty: " + aq.isEmpty());
        System.out.println();

        System.out.println("Circular Array Queue");
        Queue<Integer> ca = new CircularArrayQueue<>();
        for(int i = 0; i < 50; i++){
            ca.enqueue(i);
            System.out.println("Enqueuing " + i + " into the Circular Array Queue");
        }
        System.out.println();
        System.out.println("Size should be 50: " + ca.size());
        System.out.println();
        System.out.println("First item that should be dequeued is: " + ca.peek());
        System.out.println();
        for(int i = 0; i < 25; i++){
            System.out.println("Dequeued " + ca.dequeue() + " from the Circular Array Queue, next item to be dequeued is: " + ca.peek());
        }
        System.out.println();
        System.out.println("Size should be 25: " + ca.size());
        System.out.println();
        System.out.println("Dequeuing while the Circular Array Queue is not empty");
        System.out.println("Next item that should be dequeued is: " + ca.peek());
        System.out.println();
        while(!ca.isEmpty()){
            System.out.println("Dequeued " + ca.dequeue() + " from the Circular Array Queue, next item to be dequeued is: " + ca.peek());
        }
        System.out.println();
        System.out.println("Size should be zero: " + ca.size());
        System.out.println("Circular Array Queue should be empty: " + ca.isEmpty());
        System.out.println();


        System.out.println("Linked List Queue");
        Queue<Integer> ll = new LinkedListQueue<>();
        for(int i = 0; i < 50; i++){
            ll.enqueue(i);
            System.out.println("Enqueuing " + i + " into the Linked List Queue");
        }
        System.out.println();
        System.out.println("Size should be 50: " + ll.size());
        System.out.println();
        System.out.println("First item that should be dequeued is: " + ll.peek());
        System.out.println();
        for(int i = 0; i < 25; i++){
            System.out.println("Dequeued " + ll.dequeue() + " from the Linked List Queue, next item to be dequeued is: " + ll.peek());
        }
        System.out.println();
        System.out.println("Size should be 25: " + ll.size());
        System.out.println();
        System.out.println("Dequeuing while the Linked List Queue is not empty");
        System.out.println("Next item that should be dequeued is: " + ll.peek());
        System.out.println();
        while(!ll.isEmpty()){
            System.out.println("Dequeued " + ll.dequeue() + " from the Linked List Queue, next item to be dequeued is: " + ll.peek());
        }
        System.out.println();
        System.out.println("Size should be zero: " + ll.size());
        System.out.println("Linked List Queue should be empty: " + ll.isEmpty());
        System.out.println();
    }
}
