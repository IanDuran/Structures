package ucr.ac.cr.ecci.ci1221.util.graph;

import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

import ucr.ac.cr.ecci.ci1221.util.collections.Collection;

/**
 * @author Rodrigo A. Bartels
 */
public interface Graph<V> extends Collection, Iterable<V>{

    /**
     * Returns true if this graph is directed or false if it is undirected.
     *
     * @return true or false
     */
    boolean isDirected();

    /**
     * Adds a node to the graph with the given value.
     *
     * @param value the value to add.
     */
    void addNode(V value);

    /**
     * Adds a node for each of the given values and adds an edge between them.
     *
     * @param value1 the first value to add.
     * @param value2 the second value to add.
     */
    void addNodes(V value1, V value2);

    /**
     * Adds an edge between the nodes with the given values, if they belong to the graph and if an
     * edge doesn't exist yet.
     *
     * @param value1 the first value.
     * @param value2 the second value.
     */
    void addEdge(V value1, V value2);

    /**
     * Adds an edge with the given weight between the nodes with the given values,
     * if they belong to the graph and if an edge doesn't exist yet.
     *
     * @param value1 the first value.
     * @param value2 the second value.
     * @param weight the weight
     */
    void addEdge(V value1, V value2, double weight);

    /**
     * Checks if the graph contains the given value.
     *
     * @param value the value to search.
     *
     * @return true if the value is within the graph.
     */
    boolean contains(V value);

    /**
     * Returns true if there is an edge within the given values.
     *
     * @param value1 the first value.
     * @param value2 the second value.
     *
     * @return true if there is an edge within the given values.
     */
    boolean areLinked(V value1, V value2);

    /**
     * If there is an edge within the given values it returns the weight of that edge. It returns
     * 0 if the graph is unweighted.
     *
     * @param value1 the first value.
     * @param value2 the second value.
     *
     * @return true if there is an edge within the given values.
     */
    double getWeight(V value1, V value2);

    /**
     * Removes the given value from the graph.
     *
     * @param value the value to remove.
     */
    void removeValue(V value);

    /**
     * If there is an edge between the given values, this method removes it from the graph.
     *
     * @param value1 the first value.
     * @param value2 the second value.
     */
    void removeEdge(V value1, V value2);

    /**
     * Returns a list with all the values that are adyacent to the given value.
     *
     * @param value the value.
     *
     * @return list of adyacent nodes.
     */
    List<V> getAdjacentNodes(V value);

    /**
     * Returns all the values contained in the graph.
     *
     * @return the graph's values.
     */
    List<V> getValues();

    /**
     * Returns the number of vertexes (values) in the graph.
     *
     * @return number of values.
     */
    int V();

    /**
     * Returns the number of edges in the current graph.
     *
     * @return the number of edges.
     */
    int E();

    /**
     * Returns the sum of the weights of all the edges in the tree.
     *
     * @return graph weight
     */
    double getWeight();
}
