package ucr.ac.cr.ecci.ci1221.tests;

import ucr.ac.cr.ecci.ci1221.util.algorithm.GraphAlgorithms;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyList;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyMatrix;
import ucr.ac.cr.ecci.ci1221.util.graph.EdgeList;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;

import java.util.Iterator;

public class GraphAlgorithmsTests {
    public void graphTest(Graph<Character> graph){
        graph.addNode('a');
        graph.addNode('b');
        graph.addNode('c');
        graph.addNode('d');
        graph.addNode('e');
        graph.addNode('f');
        graph.addNode('g');
        graph.addEdge('a', 'b', 2);
        graph.addEdge('a', 'd', 1);
        graph.addEdge('a', 'c', 4);
        graph.addEdge('b', 'd', 3);
        graph.addEdge('e', 'b', 10);
        graph.addEdge('c', 'd', 2);
        graph.addEdge('c', 'f', 5);
        graph.addEdge('d', 'e', 7);
        graph.addEdge('d', 'f', 8);
        graph.addEdge('d', 'g', 4);
        graph.addEdge('e', 'g', 6);
        graph.addEdge('f', 'g', 1);
        System.out.println("Adjacencies of the minimum spanning tree using Prim's algorithm");
        Graph<Character> prim = GraphAlgorithms.getMinimumSpanningTreePrim(graph);
        List<Character> l = prim.getValues();
        for(int i = 0; i < l.size(); i++){
            for(int j = 0; j < l.size(); j++){
                if(i != j && prim.areLinked(l.get(i), l.get(j))){
                    System.out.println(l.get(i) + " " + prim.getWeight(l.get(i), l.get(j)) + " "+ l.get(j));
                }
            }
        }
        System.out.println();

        System.out.println("The graph has cycles so isGraphAcyclic should be false: " + GraphAlgorithms.isGraphAcyclic(graph));
        System.out.println("Shortest paths from 'a' using Dijkstra");
        GraphAlgorithms.printShortestPath('a', GraphAlgorithms.getShortestPathDijkstra(graph, 'a'));
        System.out.println();

        System.out.println("All the shortest distances from every node to every node using Floyd");
        GraphAlgorithms.printAllShortestPaths(graph);
    }

    public static void main(String... args){
        GraphAlgorithmsTests t = new GraphAlgorithmsTests();
        System.out.println("Adjacency List");
        t.graphTest(new AdjacencyList<>(false, true));
        System.out.println();
        System.out.println();
        System.out.println("Adjacency Matrix");
        t.graphTest(new AdjacencyMatrix<>(false, true));
        System.out.println();
        System.out.println();
        System.out.println("Edge List");
        t.graphTest(new EdgeList<>(false, true));
    }
}
