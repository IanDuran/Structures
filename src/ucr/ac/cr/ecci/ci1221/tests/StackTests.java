package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.collections.stack.ArrayStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.InvertedArrayStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.LinkedListStack;
import ucr.ac.cr.ecci.ci1221.util.collections.stack.Stack;

public class StackTests {
    public static void main(String[] args){
        System.out.println("Array Stack");
        System.out.println();
        Stack<Integer> a = new ArrayStack<>();
        for(int i = 0; i < 50; i++){
            a.push(i);
            System.out.println("Pushed: " + a.peek() + " into the Array Stack");
        }
        System.out.println();
        System.out.println("Size should be 50: " + a.size());
        System.out.println();
        for(int i = 0; i < 25; i++){
            System.out.println("Popped: " + a.pop() + " from the Array Stack");
        }
        System.out.println();
        System.out.println("Size should be 25: " + a.size());
        System.out.println();
        System.out.println("Popping while the Stack is not empty");
        while(!a.isEmpty()){
            System.out.println("Popped: " + a.pop() + " from the Array Stack");
        }
        System.out.println();
        System.out.println("Size should be zero: " + a.size());
        System.out.println("Array Stack should be empty: " + a.isEmpty());
        System.out.println();


        System.out.println("Linked List Stack");
        System.out.println();
        Stack<Integer> l = new LinkedListStack<>();
        for(int i = 0; i < 50; i++){
            l.push(i);
            System.out.println("Pushed: " + l.peek() + " into the Linked ListStack");
        }
        System.out.println();
        System.out.println("Size should be 50: " + l.size());
        System.out.println();
        for(int i = 0; i < 25; i++){
            System.out.println("Popped: " + l.pop() + " from the Linked List Stack");
        }
        System.out.println();
        System.out.println("Size should be 25: " + l.size());
        System.out.println();
        System.out.println("Popping while the Stack is not empty");
        while(!l.isEmpty()){
            System.out.println("Popped: " + l.pop() + " from the Linked List Stack");
        }
        System.out.println();
        System.out.println("Size should be zero: " + l.size());
        System.out.println("Linked List Stack should be empty: " + l.isEmpty());
        System.out.println();

        System.out.println("Inverted Array Stack");
        System.out.println();
        Stack<Integer> ia = new InvertedArrayStack<>();
        for(int i = 0; i < 50; i++){
            ia.push(i);
            System.out.println("Pushed: " + ia.peek() + " into the Inverted Array Stack");
        }
        System.out.println();
        System.out.println("Size should be 50: " + ia.size());
        System.out.println();
        for(int i = 0; i < 25; i++){
            System.out.println("Popped: " + ia.pop() + " from the Inverted Array Stack");
        }
        System.out.println();
        System.out.println("Size should be 25: " + ia.size());
        System.out.println();
        System.out.println("Popping while the Stack is not empty");
        while(!ia.isEmpty()){
            System.out.println("Popped: " + ia.pop() + " from the Inverted Array Stack");
        }
        System.out.println();
        System.out.println("Size should be zero: " + ia.size());
        System.out.println("Inverted Array Stack should be empty: " + ia.isEmpty());
        System.out.println();
    }
}
