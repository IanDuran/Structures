package ucr.ac.cr.ecci.ci1221.util.algorithm;

import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Dictionary;
import ucr.ac.cr.ecci.ci1221.util.collections.dictionary.Hashtable;
import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.LinkedListQueue;
import ucr.ac.cr.ecci.ci1221.util.collections.queue.Queue;
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

        //Graph<V> minimumTree = new AdjacencyList<>(false);
        List<Graph<V>> forest = new LinkedList<>();
        List<V> values = graph.getValues();
        List<Edges<V>> edges = new LinkedList<>();

        //Getting all the edges
        for(int i = 0; i < values.size(); i++){
            for(int j = 0; j < values.size(); j++){
                if(i != j){
                    if(graph.areLinked(values.get(i), values.get(j))){
                        edges.add(new Edges<>(values.get(i), values.get(j), graph.getWeight(values.get(i), values.get(j))));
                    }
                }
            }
        }
        SortingAlgorithms.mergeSort(edges);

        //Initializing the forest

            /*if(!minimumTree.contains(e.getFirstValue()) || !minimumTree.contains(e.getSecondValue())){
                if (!minimumTree.contains(e.getFirstValue()))
                    minimumTree.addNode(e.getFirstValue());

                if (!minimumTree.contains(e.getSecondValue()))
                    minimumTree.addNode(e.getSecondValue());

                minimumTree.addEdge(e.getFirstValue(), e.getSecondValue(), e.getWeight());
            }*/

        return null;
    }

    /**
     * Returns whether the graph has cycles or not.
     * First it checks if the graph is not empty, then , is it is not empty, it creates an Iterator,
     * a Set of visited nodes and calls the private method dfsCycles with the first node, a null value for the
     * precursor, the graph and the visited set. Then it returns the opposite answer of the dfsCycles method
     * @return false if the flag has cycles, true otherwise.
     */
    public static <V> boolean isGraphAcyclic(Graph<V> graph) {
        boolean hasCycles = false;
        if(!graph.isEmpty()){
            Iterator<V> i = graph.iterator();
            Set<V> visited = new LinkedListSet<>();
            hasCycles = dfsCycles(i.next(), null, graph, visited);
        }
        return !hasCycles;
    }

    /**
     * Private recursive method that tells if a graph has cycles using Depth First Search. It first creates the boolean value for the return,
     * then adds the current node to the set of visited nodes, after that, it gets the nodes adjacent to the
     * current one and for every one of them it checks if they are not visited, if they aren't, it makes the recursive
     * call with the corresponding adjacent node, the current node as the precursor, the graph and the visited set.
     * If the adjacent node was already visited, it checks if it wasn't the precursor, if it wasn't, it means the
     * algorithm ha reached a previously visited node, and thus, it has a cycle, so it returns true.
     * @param node current node being visited
     * @param precursor node visited immediately prior to the current one
     * @param graph graph that contains the nodes
     * @param visited set that contains the visited nodes
     * @param <V> class of the nodes
     * @return true if the graph has cycles, false otherwise
     */
    private static <V> boolean dfsCycles(V node, V precursor, Graph<V> graph, Set<V> visited){
        boolean hasCycle = false;
        visited.put(node);
        List<V> adjacentNodes = graph.getAdjacentNodes(node);
        for (int i = 0; i < adjacentNodes.size(); i++) {
            if (!visited.isMember(adjacentNodes.get(i))){
                hasCycle = dfsCycles(adjacentNodes.get(i), node, graph, visited);
            }else if(!adjacentNodes.get(i).equals(precursor)){
                hasCycle = true;
            }
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
     * The first thing the algorithm does is to initialize the variables needed for it's completion:
     * the list of nodes, a boolean array that represents the visited nodes and the node array that are the precursors.
     * It then sets the initial values of the previously created objects. The it goes into a cycle that iterates until
     * all the nodes are visited, condition checked using the private allSeen method. Inside the cycle a double is created
     * and it's value set to be Double.MAX_VALUE and a V type is created to be the next node. After that, it finds the
     * non-visited node with the shortest total distance to it and it's index in the list, if there's such node.
     * If there is such node, it is set to visited and then all the distances from the non-visited nodes are updated
     * and if there's a new shortest distance for a node, the chosen node is set as a precursor. Finally a Dictionary is
     * filled with the node, the precursor and the distance.
     * @param graph the graph
     * @param value the initial vertex
     * @param <V> the type of the elements in the graph.
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
            shortestPaths.put(values.get(i), new DijkstraResult<>(values.get(i), precursors[i], distances[i]));
        }
        return shortestPaths;
    }

    /**
     * Private method that tells if every boolean in a boolean array is set to true.
     * @param seenList boolean array that will be evaluated
     * @return true if every member of the array is true, false otherwise.
     */
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
     * First thing done is the creation of a double matrix that is filled with the distances of every
     * adjacency in the graph, because of the implementation of the Graphs, if there is no edge between
     * two nodes, the weight is -1, and the diagonal is filled with zeroes. After that it uses a triple for loop
     * to store the shortest distances from every node to every node. Finally the double matrix is printed.
     * @param graph the graph.
     * @param <V> the type of elements contained in the graph.
     */
    public static <V> void printAllShortestPaths(Graph<V> graph){
        List<V> values = graph.getValues();
        int size = graph.V();
        double[][] matrix = new double[size][size];
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

    /**
     * First it creates a list of graphs that will contain one graph per component.
     * Then it gets an array of the nodes of the given graph and creates a set of visited nodes.
     * Then iterates over every node, and if the current node is not visited, it calls the
     * bfsComponents method to get a graph with all the nodes connected to the current one and
     * inserts it in the list. Finally the list is returned.
     * @param graph graph that holds all the components
     * @param <V> type of the nodes
     * @return a list with all the connected components
     */
    public static <V> List<Graph<V>> getConnectedComponents(Graph<V> graph) {
        List<Graph<V>> components = new LinkedList<>();
        V[] values = graph.getValuesAsArray();
        Set<V> visited = new LinkedListSet<>();
        for(int i = 0; i < values.length; i++){
            if(!visited.isMember(values[i])){
                components.add(bfsComponents(graph, values, visited, i));
            }
        }
        return components;
    }

    /**
     * Private recursive method used to return a graph with all the nodes that are connected to the one passed as a parameter.
     * It creates a graph of a single component from a given graph using Breadth First Search. The first thing done is to
     * create a graph that will hold the component, then it creates the queue needed for the search and enqueues
     * the node in the node array in the position pointed by index. Then, while the queue is not empty, dequeues a node,
     * adds it the the visited set and adds it to the new graph, until all the nodes in the component are added.
     * After that, an array of nodes is created from the nodes of the new graph. Finally, for every two nodes in the
     * new array, if they are connected on the given array, the edge is added in the new array and it is returned.
     * @param graph the graph that contains the nodes.
     * @param values array containing the nodes of the graph.
     * @param visited set of visited nodes
     * @param index index in the list that corresponds to the node passed as parameter.
     * @param <V> type of the Node.
     * @return a graph with all the modes connected to the one passed as a parameter.
     */
    private static <V> Graph<V> bfsComponents(Graph<V> graph, V values[], Set<V> visited, int index){
        Graph<V> currentComponent = new AdjacencyList<>(false);
        Queue<V> queue = new LinkedListQueue<>();
        queue.enqueue(values[index]);
        while(!queue.isEmpty()){
            V currentValue = queue.dequeue();
            visited.put(currentValue);
            currentComponent.addNode(currentValue);
            List<V> adjacencies = graph.getAdjacentNodes(currentValue);
            for(int i = 0; i < adjacencies.size(); i++){
                if(!visited.isMember(adjacencies.get(i))) {
                    queue.enqueue(adjacencies.get(i));
                }
            }
        }
        V[] componentValues = currentComponent.getValuesAsArray();
        for(int i = 0; i < componentValues.length; i++){
            for(int j = 0; j < componentValues.length; j++){
                if(i != j && graph.areLinked(componentValues[i], componentValues[j])){
                    currentComponent.addEdge(componentValues[i], componentValues[j], graph.getWeight(componentValues[i], componentValues[j]));
                }
            }
        }
        return currentComponent;
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
