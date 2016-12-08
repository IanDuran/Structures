package ucr.ac.cr.ecci.ci1221.util.graph;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Ian Duran
 */
public class EdgeList<V> implements Graph<V> {

    private final int INITIAL_SIZE = 20;
    private final int ENLARGING_SIZE = 5;
    private LinkedList<Edge> edges;
    private V[] values;
    private boolean directed;
    private boolean weighted;
    private int vertexes;


    /**
     * Constructor for the EdgeList it takes a boolean that tells of the Graph will be directed or not
     * It initializes the list of values, the list of edges and the vertexes integer.
     * @param directed whether the graph will be directed or not.
     * @param weighted whether the graph will have weights or not.
     */
    public EdgeList(boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
        edges = new LinkedList<>();
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
    }

    /**
     * Returns true if the graph is directed, false otherwise.
     * @return true if the graph is directed, false otherwise.
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Adds a node to the Graph. If the values array is full, then it's enlarged.
     * The node is added as the last vertex in the array. It does not accept duplicates.
     * @param value the value to add.
     */
    @Override
    public void addNode(V value) {
        if(!this.contains(value)){
            if(vertexes == values.length)
                this.enlarge();

            values[vertexes] = value;
            vertexes++;
        }
    }

    /**
     * Inserts tow nodes in the graph and adds an edge between them if the graph is not weighted.
     * @param value1 the first value to add.
     * @param value2 the second value to add.
     */
    @Override
    public void addNodes(V value1, V value2) {
        if(!this.contains(value1) && !this.contains(value2) && !this.weighted){
            this.addNode(value1);
            this.addNode(value2);
            this.addEdge(value1, value2);
        }
    }

    /**
     * Adds an edge between the two given nodes if they are in the graph and the graph is not weighted
     * @param value1 the first value.
     * @param value2 the second value.
     */
    @Override
    public void addEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2) && !this.weighted){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            edges.add(new Edge(firstIndex, secondIndex, 1));
            if(!this.isDirected())
                edges.add(new Edge(secondIndex, firstIndex, 1));

        }
    }

    /**
     * Adds an edge with the given weight between the two given nodes if they are in the graph and
     * the graph is weighted.
     * @param value1 the first value.
     * @param value2 the second value.
     * @param weight the weight
     */
    @Override
    public void addEdge(V value1, V value2, double weight) {
        if(this.contains(value1) && this.contains(value2) && this.weighted){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            edges.add(new Edge(firstIndex, secondIndex, weight));
            if(!this.isDirected())
                edges.add(new Edge(secondIndex, firstIndex, weight));
        }
    }

    /**
     * Tells if the given V type object is in the graph.
     * To do so it uses the private getIndex method.
     * @param value the value to search.
     * @return true if the object is in the graph, false otherwise.
     */
    @Override
    public boolean contains(V value) {
        return this.getIndex(value) != -1;
    }

    /**
     * Tells if there is an edge between the two given nodes.
     * First it checks if the nodes are contained int eh graph, then
     * it gets the indexes of the two nodes and it iterates over the list
     * of edges searching for an edge that has a from Integer equal to
     * the first index and a to integer that is equal to the second index
     * if such edge exists, it returns true.
     * @param value1 the first value.
     * @param value2 the second value.
     * @return true if the nodes are linked, false otherwise.
     */
    @Override
    public boolean areLinked(V value1, V value2) {
        boolean linked = false;
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            for(int i = 0; i < edges.size(); i++){
                Edge e = edges.get(i);
                if(e.getFrom() == firstIndex && e.getTo() == secondIndex)
                    linked = true;

            }
        }
        return linked;
    }

    /**
     * Returns the weight of the edge between the two given nodes or -1 if the graph is not weighted or the edge
     * does not exists. Firsts it checks if the given nodes are in the graph and if they are linked.
     * If the conditions are true, it iterates over the list of edges searching for an edge that has a
     * from value equal to the index of the first node, and a to value equal to the index of the second node.
     * Finally it returns the value of the found edge.
     * @param value1 the first value.
     * @param value2 the second value.
     * @return the weight of the edge between the two given nodes or -1 if either the graph is not weighted or the edge does not exists
     */
    @Override
    public double getWeight(V value1, V value2) {
        double weight = -1;
        if(this.contains(value1) && this.contains(value2) && this.areLinked(value1, value2) && this.weighted){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            for(int i = 0; i < edges.size() && weight == -1; i++){
                Edge e = edges.get(i);
                if(e.getFrom() == firstIndex && e.getTo() == secondIndex)
                    weight = e.getWeight();

            }
        }
        return weight;
    }

    /**
     * Removes a node from the graph with all it's edges.
     * First it checks if the node is indeed in the graph, it then gets its index.
     * The it goes in a loop, since the nodes are managed by their position in an array,
     * it searches every edge with a node in a position higher than the one of the
     * to-be-removed node and sets them to be a position lower. It also eliminates the edges
     * with a value equal to the position of the node. Finally it moves down by one position
     * every element of the array and decreases by one the number of nodes.
     * @param value the value to remove.
     */
    @Override
    public void removeValue(V value) {
        if(this.contains(value)){
            int index = this.getIndex(value);
            for(int i = 0; i < edges.size(); i++){
                if(edges.get(i).getFrom() == index || edges.get(i).getTo() == index) {
                    edges.remove(i);
                    i--;
                }else if(edges.get(i).getFrom() > index)
                    edges.get(i).setFrom(i - 1);

                else if(edges.get(i).getTo() > index)
                    edges.get(i).setTo(i - 1);

            }
            this.moveDown(index);
            vertexes--;
        }
    }

    /**
     * Moves down by one position all the nodes in the values array.
     * @param index position starting from which all the next nodes are moved down.
     */
    private void moveDown(int index){
        for(int i = index; i < vertexes; i++){
            values[i] = values[i + 1];
        }
    }

    /**
     * Removes and edge between the two given values, if the edge exists.
     * first it checks if the two nodes exist in the graph and if they are connected.
     * If the above conditions are met, a loop iterates in the list of edges.
     * If an edge is found between the two nodes, its eliminated from the list.
     * If the graph is not directed, it eliminates two edges.
     * @param value1 the first value.
     * @param value2 the second value.
     */
    @Override
    public void removeEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2) && this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            for(int i = 0; i < edges.size(); i++){
                Edge e = edges.get(i);
                if(e.getFrom() == firstIndex && e.getTo() == secondIndex){
                    edges.remove(i);
                    i--;
                }
                if(!this.isDirected()){
                    if(e.getFrom() == secondIndex && e.getTo() == firstIndex){
                        edges.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    /**
     * Returns a list with all the nodes adjacent to the given one, if the node
     * does not exist or does not have adjacencies, it returns an empty list.
     * @param value the value.
     * @return a list with all the adjacencies of the given node
     */
    @Override
    public List<V> getAdjacentNodes(V value) {
        List<V> adjacentNodes = new LinkedList<>();
        if(this.contains(value)){
            int index = this.getIndex(value);
            for(int i = 0; i < edges.size(); i++){
                if(edges.get(i).getFrom() == index)
                    adjacentNodes.add(values[edges.get(i).getTo()]);

            }
        }
        return adjacentNodes;
    }

    /**
     * Returns a list containing all the nodes of the Graph. It
     * iterates over the values array inserting the current value to the list
     * and then returns it.
     * @return a list with all the values of the node.
     */
    @Override
    public List<V> getValues() {
        List<V> valueList = new LinkedList<>();
        for(int i = 0; i < vertexes; i++)
            valueList.add(values[i]);

        return valueList;
    }

    /**
     * Returns the number of nodes in the graph.
     * @return the number of nodes in the graph.
     */
    @Override
    public int V() {
        return vertexes;
    }

    /**
     * Returns the number of edges in the graph.
     * To do this it gets the size of the list of edges. If the graph
     * is not directed, that number is divided by two, since there are no loops.
     * @return the number of edges.
     */
    @Override
    public int E() {
        int edgeNumber = edges.size();
        if(!this.isDirected())
            edgeNumber /= 2;
        return edgeNumber;
    }

    /**
     * Returns a new instance of the EdgeListIterator class.
     * @return a new instance of the EdgeListIterator class.
     */
    @Override
    public Iterator<V> iterator() {
        return new EdgeListIterator(values, vertexes);
    }

    /**
     * Iterates over the list of edges adding all the weights to the returned variable.
     * If the graph is not directed, the number is divided by two
     * @return the total weight of the graph.
     */
    @Override
    public double getWeight() {
        double weight = 0;
        if(this.weighted){
            for(int i = 0; i < edges.size(); i++)
                weight += edges.get(i).getWeight();

            if(!this.isDirected())
                weight /= 2;
        }
        return weight;
    }

    /**
     * Returns the size of the graph.
     * @return the size of the graph.
     */
    @Override
    public int size() {
        return vertexes;
    }

    /**
     * Tells if the graph is empty or not
     * @return true if the graph is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return vertexes == 0;
    }

    /**
     * Clears the graph by setting all of the class' variables to the default state.
     */
    @Override
    public void clear() {
        edges = new LinkedList<>();
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
    }

    /**
     * Returns an array containing all the nodes of the graph.
     * @return an array containing all the nodes of the graph.
     */
    @Override
    public V[] getValuesAsArray() {
        V[] returnedValues = (V[]) new Object[vertexes];
        for(int i = 0; i < vertexes; i++){
            returnedValues[i] = values[i];
        }
        return returnedValues;
    }

    /**
     * Returns a double matrix that represents all the adjacencies.
     * @return a double matrix that represents all the adjacencies.
     */
    @Override
    public double[][] getGraphStructureAsMatrix() {
        double[][] graphMatrix = new double[vertexes][vertexes];
        for(int i = 0; i < vertexes; i++){
            for(int j = 0; j < vertexes; j++){
                if(i != j){
                    graphMatrix[i][j] = this.getWeight(values[i], values[j]);
                }
            }
        }
        return graphMatrix;
    }

    /**
     * Searches the array of nodes for the given one and returns its index.
     * @param value searched node
     * @return the index in the array of the given node.
     */
    private int getIndex(V value){
        int index = -1;
        int counter = 0;
        while(index == -1 && counter < vertexes){
            if(values[counter].equals(value))
                index = counter;

            counter++;
        }
        return index;
    }

    /**
     * Enlarges the values array
     */
    private void enlarge(){
        V[] newValues = (V[]) new Object[values.length + ENLARGING_SIZE];
        for(int i = 0; i < values.length; i++)
            newValues[i] = values[i];

        values = newValues;
    }

    /**
     * Private class used to store the representation of an edge.
     * It has a from node, a to node and the weight between them.
     */
    private class Edge{
        private int from;
        private int to;
        private double weight;

        public Edge(int from, int to, double weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }

    /**
     * Iterator class for the EdgeList
     */
    private class EdgeListIterator implements Iterator<V>{
        private int index;
        private int maxIndex;
        private V[] values;

        public EdgeListIterator(V[] values, int maxIndex){
            this.values = values;
            this.maxIndex = maxIndex;
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return index < maxIndex;
        }

        @Override
        public V next() {
            V next = values[index];
            index++;
            return next;
        }
    }
}
