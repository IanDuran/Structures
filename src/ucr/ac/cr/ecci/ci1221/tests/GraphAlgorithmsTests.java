package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.GraphAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyList;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyMatrix;
import ucr.ac.cr.ecci.ci1221.util.graph.EdgeList;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;

import java.util.Iterator;

public class GraphAlgorithmsTests {
    public static void main(String... args){
        Graph<Character> el = new AdjacencyList<>(false, true);
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
        Graph<Character> kruskal = GraphAlgorithms.getMinimumSpanningTreeKruskal(el);
        List<Character> l = kruskal.getValues();
        for(int i = 0; i < l.size(); i++){
            for(int j = 0; j < l.size(); j++){
                if(i != j && kruskal.areLinked(l.get(i), l.get(j))){
                    System.out.println(l.get(i) + " " + kruskal.getWeight(l.get(i), l.get(j)) + " "+ l.get(j));
                }
            }
        }
        Set<Character> s = new LinkedListSet<>();
        s.put('a');
        Set<Character> t = new LinkedListSet<>();

    }
}
