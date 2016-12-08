package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.GraphAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyList;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyMatrix;
import ucr.ac.cr.ecci.ci1221.util.graph.EdgeList;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;

import java.util.Iterator;

public class GraphAlgorithmsTests {
    public static void main(String... args){
        Graph<Character> el = new AdjacencyList<>(false);
        el.addNode('a');
        el.addNode('b');
        el.addNode('c');
        el.addNode('d');
        el.addNode('e');
        el.addEdge('a', 'b', 0.1);
        el.addEdge('d', 'a', 3);
        el.addEdge('b', 'c', 6);
        el.addEdge('c', 'd', 5);
        el.addEdge('e', 'd', 7.4);
        List<Graph<Character>> l = GraphAlgorithms.getConnectedComponents(el);
        for(int i = 0; i < l.size(); i++){
            List<Character> list = l.get(i).getValues();
            for(int j = 0; j < list.size(); j++)
                System.out.print(list.get(j) + " ");

            System.out.println();
        }
    }
}
