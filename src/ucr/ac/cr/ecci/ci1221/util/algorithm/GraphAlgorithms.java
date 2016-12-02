package ucr.ac.cr.ecci.ci1221.util.algorithm;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.set.LinkedListSet;
import ucr.ac.cr.ecci.ci1221.util.collections.set.Set;
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
        if(graph.isDirected())
            throw new IllegalArgumentException();

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
                        if(currentWeight > 0 && currentWeight < minimumEdge){
                            index = i;
                            minimumEdge = currentWeight;
                            minimumEdgeValue =  adjacentNodes.get(j);
                        }
                    }
                }
            }
            if(minimumEdge < Double.MAX_VALUE && minimumEdgeValue != null){
                minimumTree.addNode(minimumEdgeValue);
                minimumTree.addEdge(minimumValues.get(index), minimumEdgeValue, minimumEdge);
            }else{
                //Graph has more than one component
                V toAdd = it.next();
                while(graph.contains(toAdd) && it.hasNext())
                    toAdd = it.next();

                minimumTree.addNode(toAdd);
            }
        }
        return minimumTree;
    }

    /**
     * Returns the minimum spanning tree of the given graph calculated using Kruskal's algorithm.
     */
    public static <V> Graph<V> getMinimumSpanningTreeKruskal(Graph<V> graph){
        if(graph.isDirected())
            throw new IllegalArgumentException();

        Graph<V> minimumTree = new AdjacencyList<>(false);
        List<V> values = graph.getValues();
        List<Edges<V>> edges = new LinkedList<>();
        for(int i = 0; i < values.size(); i++){
            for(int j = 0; j < values.size(); j++){
                if(i != j){
                    if(graph.areLinked(values.get(i), values.get(j))){
                        edges.add(new Edges<V>(values.get(i), values.get(j), graph.getWeight(values.get(i), values.get(j))));
                    }
                }
            }
        }
        SortingAlgorithms.mergeSort(edges);
        while(!edges.isEmpty()){
            Edges<V> e = edges.get(0);
            edges.remove(0);
            if(!minimumTree.contains(e.getFirstValue()) || !minimumTree.contains(e.getSecondValue())){
                if (!minimumTree.contains(e.getFirstValue()))
                    minimumTree.addNode(e.getFirstValue());

                if (!minimumTree.contains(e.getSecondValue()))
                    minimumTree.addNode(e.getSecondValue());

                minimumTree.addEdge(e.getFirstValue(), e.getSecondValue(), e.getWeight());
            }
        }
        return minimumTree;
    }

    /**
     * Returns whether the graph has cycles or not.
     *
     * @return false if the flag has cycles, true otherwise.
     */
    public static <V> boolean isGraphAcyclic(Graph<V> graph) {
        if(!graph.isDirected())
            throw new IllegalArgumentException();

        boolean hasCycles = false;
        if(!graph.isEmpty()){
            Iterator<V> i = graph.iterator();
            Set<V> visited = new LinkedListSet<>();
            hasCycles = dfs(i.next(), graph, visited);
        }
        return !hasCycles;
    }

    private static <V> boolean dfs(V node, Graph<V> graph, Set<V> visited){
        boolean hasCycle = false;
        visited.put(node);
        List<V> adjacentNodes = graph.getAdjacentNodes(node);
        for (int i = 0; i < adjacentNodes.size(); i++) {
            if (!visited.isMember(adjacentNodes.get(i)))
                hasCycle = dfs(adjacentNodes.get(i), graph, visited);

            else
                hasCycle = true;

        }
        return hasCycle;
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
        if(graph.isDirected())
            throw new IllegalArgumentException();

        List<V> values = graph.getValues();
        boolean visited[] = new boolean[values.size()];
        double distances[] = new double[values.size()];
        V precursors[] = (V[]) new Object[values.size()];

        //Step 1: Initialization
        for(int i = 0; i < values.size(); i++){
            if(values.get(i).equals(value)) {
                visited[i] = true;
                precursors[i] = value;
            }else{
                distances[i] = graph.getWeight(value, values.get(i));
                if(distances[i] != -1)
                    precursors[i] = values.get(i);
            }
        }
        while(!allSeen(visited)){
            //Step 2: Finding the Node with the minimal distance
            double minimalDistance = Double.MAX_VALUE;
            V minimalDistanceValue = null;
            int visitedIndex = -1;
            for(int i = 0; i < values.size(); i++){
                if(!visited[i] && distances[i] != -1 && distances[i] < minimalDistance){
                    minimalDistance = graph.getWeight(value, values.get(i));
                    minimalDistanceValue = values.get(i);
                    visitedIndex = i;
                }
            }
            if(visitedIndex != -1) {
                visited[visitedIndex] = true;
                //Step 3: Actualization
                for (int i = 0; i < values.size(); i++){
                    if(graph.areLinked(minimalDistanceValue, values.get(i))){
                        if(distances[visitedIndex] != -1){
                            if(distances[i] == -1 || distances[i] > graph.getWeight(minimalDistanceValue, values.get(i)) + distances[visitedIndex]){
                                distances[i] = graph.getWeight(minimalDistanceValue, values.get(i)) + distances[visitedIndex];
                                precursors[i] = minimalDistanceValue;
                            }
                        }
                    }
                }
            }
        }
        Dictionary<V, DijkstraResult<V>> shortestPaths = new Hashtable<>();
        for(int i = 0; i < values.size(); i++){
            shortestPaths.put(values.get(i), new DijkstraResult<V>(values.get(i), precursors[i], distances[i]));
        }
        return shortestPaths;
    }

    private static boolean allSeen(boolean seenList[]){
        boolean allSeen = true;
        for(int i = 0; i < seenList.length; i++){
            if(!seenList[i])
                allSeen = false;

        }
        return allSeen;
    }

    /**
     * Uses Floyd-Warshall's algorithm to print the shortest paths between all the
     * vertexes of the graph.
     *
     * @param graph the graph.
     * @param <V> the type of elements contained in the graph.
     */
    public static <V> void printAllShortestPaths(Graph<V> graph){
        List<V> values = graph.getValues();
        int size = graph.V();
        double matrix[][] = new double[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(i != j)
                    matrix[i][j] = graph.getWeight(values.get(i), values.get(j));
            }
        }

        for(int k = 0; k < size; k++){
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    if(matrix[i][j] == -1 && (matrix[i][k] != -1 && matrix[k][j] != -1) || (matrix[i][k] != -1 && matrix[k][j] != -1 && matrix[i][j] > matrix[i][k] + matrix[k][j])){
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
        for(int i = 0; i < size; i++){
            System.out.print("  " + values.get(i) + "  ");
        }
        System.out.println();
        for(int i = 0; i < size; i++) {
            System.out.print(values.get(i)+ " ");
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static <V> List<Graph<V>> getConnectedComponents(Graph<V> graph){
        return null;
    }

    /**
     * Private class that holds Node's values and the weight between them, used to simplify
     * the implementation of the Kruskal algorithm.
     * @param <V>
     */
    private static class Edges<V> implements Comparable<Edges<V>>{
        private V firstValue;
        private V secondValue;
        private double weight;

        public Edges(V firstValue, V secondValue, double weight){
            this.firstValue = firstValue;
            this.secondValue = secondValue;
            this.weight = weight;
        }

        public V getFirstValue() {
            return firstValue;
        }

        public V getSecondValue() {
            return secondValue;
        }

        public double getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edges<V> o) {
            int toReturn = 0;
            if(this.weight < o.getWeight())
                toReturn = -1;

            else if(this.weight > o.getWeight())
                toReturn = 1;

            return toReturn;
        }
    }
}
