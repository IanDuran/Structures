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
        EdgeList<Character> el = new EdgeList<>(false);
        el.addNode('a');
        el.addNode('b');
        el.addNode('c');
        el.addNode('d');
        el.addNode('e');
        el.addEdge('a', 'b', 0.1);
        el.addEdge('a', 'd', 3);
        el.addEdge('c', 'b', 6);
        el.addEdge('c', 'd', 5);
        el.addEdge('e', 'd', 7.4);
        Graph<Character> g = GraphAlgorithms.getMinimumSpanningTreeKruskal(el);
        Iterator<Character> i = g.iterator();
        while(i.hasNext()){
            Character t = i.next();
            List<Character> l = g.getAdjacentNodes(t);
            for(int j = 0; j < l.size(); j++){
                System.out.println(t + " " + g.getWeight(t, l.get(j)) + " " + l.get(j));
            }
        }
    }
}
