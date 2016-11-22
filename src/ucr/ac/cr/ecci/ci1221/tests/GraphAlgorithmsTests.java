package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyList;

public class GraphAlgorithmsTests {
    public static void main(String... args){
        AdjacencyList<Character> al = new AdjacencyList<>(false);
        al.addNode('a');
        al.addNode('b');
        al.addEdge('a', 'b', 3.7);
        al.addEdge('b', 'a', 3.5);
        al.addEdge('c', 'd', 5.6);
        System.out.println(al.getWeight('b', 'a'));
    }
}
