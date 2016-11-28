package ucr.ac.cr.ecci.ci1221.util.algorithm;

import java.util.Dictionary;
import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.graph.AdjacencyList;
import ucr.ac.cr.ecci.ci1221.util.graph.Graph;

/**
 * @author Rodrigo A. Bartels
 */
public class GraphAlgorithms {

    /**
     * Returns the minimum spanning tree of the given graph calculated using Prim's algorithm.
     */
    public static <V> Graph<V> getMinimumSpanningTreePrim(Graph<V> graph){
        Graph<V> minimumTree = new AdjacencyList<>(false);
        Iterator<V> it = graph.iterator();
        minimumTree.addNode(it.next());
        while(minimumTree.V() < graph.V()){
            List<V> minimumValues = minimumTree.getValues();
            double minimumEdge = Double.MAX_VALUE;
            V minimumEdgeValue = null;
            int index = 0;
            for(int i = 0; i < minimumValues.size(); i++){
                List<V> adjacentNodes = graph.getAdjacentNodes(minimumValues.get(i));
                for(int j = 0; j < adjacentNodes.size(); j++){
                    if(!minimumTree.contains(adjacentNodes.get(j))){
                        double currentWeight = graph.getWeight(minimumValues.get(i), adjacentNodes.get(j));
                        if(currentWeight < minimumEdge){
                            index = i;
                            minimumEdge = currentWeight;
                            minimumEdgeValue =  adjacentNodes.get(j);
                        }
                    }
                }
            }
            minimumTree.addNode(minimumEdgeValue);
            minimumTree.addEdge(minimumValues.get(index), minimumEdgeValue, minimumEdge);
        }
        return minimumTree;
    }

    /**
     * Returns the minimum spanning tree of the given graph calculated using Kruskal's algorithm.
     */
    public static <V> Graph<V> getMinimumSpanningTreeKruskal(Graph<V> graph){
        return null;
    }

    private static <V> Graph<V> kruskal(){
        return null;
    }

    /**
     * Returns whether the graph has cycles or not.
     *
     * @return false if the flag has cycles, false otherwise.
     */
    public static <V> boolean isGraphAcyclic(Graph<V> graph){
        return false;
    }

    /**
     * Prints the shortest path from the given value using the result from Dikjstra's algorithm.
     *
     * @param value the value (vertex) in which the path starts.
     * @param paths the result of Dikjstra's algorithm.
     * @param <V> the type of elements stored in the original graph.
     */
    public <V> void printShortestPath(V value, Dictionary<V, DijkstraResult<V>> paths){

    }

    /**
     * Calculates the distance
     *
     * @param graph the graph
     * @param value the initial vertex
     *
     * @param <V> the type of the elements in the graph.
     *
     * @return a dictionary containing the predecessors of each node and the distance.
     */
    public static <V> Dictionary<V, DijkstraResult<V>> getShortestPathDijkstra(Graph<V> graph, V value){
        return null;
    }

    /**
     * Uses Floyd-Warshall's algorithm to print the shortest paths between all the
     * vertexes of the graph.
     *
     * @param graph the graph.
     * @param <V> the type of elements contained in the graph.
     */
    public static <V> void printAllShortestPaths(Graph<V> graph){

    }

    public static <V> List<Graph<V>> getConnectedComponents(Graph<V> graph){
        return null;
    }
}
