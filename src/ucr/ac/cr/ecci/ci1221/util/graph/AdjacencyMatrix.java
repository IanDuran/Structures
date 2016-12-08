package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Ian Duran
 */
public class AdjacencyMatrix<V> implements Graph<V>{

    private final int INITIAL_SIZE = 10;
    private final int ENLARGING_SIZE = 5;
    private double[][] matrix;
    private V[] values;
    private boolean directed;
    private boolean weighted;
    private int vertexes;
    private int edges;

    /**
     * Constructor of the AdjacencyMatrix.
     * It sets the directed and weighted variables.
     * @param directed if the graph will be directed or not.
     * @param weighted of the graph will have weights or not.
     */
    public AdjacencyMatrix(boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
        matrix = new double[INITIAL_SIZE][INITIAL_SIZE];
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(i != j)
                    matrix[i][j] = -1;
            }
        }
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
     * Adds a node to the graph if the node is not already contained in it.
     * If the values array is full, it is enlarged.
     * @param value the value to add.
     */
    @Override
    public void addNode(V value) {
        if (!this.contains(value)) {
            if(vertexes == values.length)
                this.enlarge();

            values[vertexes] = value;
            vertexes++;
        }
    }

    /**
     * Adds two nodes to the graph if they are not contained in it and an edge between them
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
     * Adds an edge without a weight between two given nodes, if they are already in the graph and if the
     * graph is not weighted.
     * @param value1 the first value.
     * @param value2 the second value.
     */
    @Override
    public void addEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2) && !this.weighted){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            matrix[firstIndex][secondIndex] = 1;
            if(!this.isDirected())
                matrix[secondIndex][firstIndex] = 1;

            edges++;
        }
    }

    /**
     * Adds and edge with a weight between two given nodes, if the nodes are already in the graph
     * and if the graph is weighted.
     * @param value1 the first value.
     * @param value2 the second value.
     * @param weight the weight
     */
    @Override
    public void addEdge(V value1, V value2, double weight) {
        if(this.contains(value1) && this.contains(value2) && this.weighted){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            matrix[firstIndex][secondIndex] = weight;
            if(!this.isDirected())
                matrix[secondIndex][firstIndex] = weight;

            edges++;
        }
    }

    /**
     * Tell is a given node is contained in the graph.
     * @param value the value to search.
     * @return true if the node is contained, false otherwise.
     */
    @Override
    public boolean contains(V value) {
        return this.getIndex(value) != -1;
    }

    /**
     * Tells if the two given nodes have an edge between them.
     * First it checks if the graph contains them, then it gets
     * the indexes of the nodes and gets the position of the matrix
     * pointed at by them. If its grates than zero, it means there
     * is an edge between them.
     * @param value1 the first value.
     * @param value2 the second value.
     * @return true if the nodes are linked, false otherwise.
     */
    @Override
    public boolean areLinked(V value1, V value2) {
        boolean areLinked = false;
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            if(matrix[firstIndex][secondIndex] > 0)
                areLinked = true;

        }
        return areLinked;
    }

    /**
     * Returns the weight between two given nodes if the
     * nodes are in the graph and if the graph is weighted.
     * If those conditions are not met, it returns a -1.
     * @param value1 the first value.
     * @param value2 the second value.
     * @return The weight of the edge between the two given nodes
     */
    @Override
    public double getWeight(V value1, V value2) {
        double weight = -1;
        if(this.contains(value1) && this.contains(value2) && this.weighted){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            weight = matrix[firstIndex][secondIndex];
        }
        return weight;
    }

    @Override
    public void removeValue(V value) {
    }

    /**
     * Removes an edge between the two given nodes, if they are in the graph and if they are linked.
     * First it checks that the above conditions are met, then it gets the indexes of the nodes
     * and sets the -1 the position pointed at by those indexes. If the graph is not directed,
     * the mirror position of the above is set to -1 as well.
     * @param value1 the first value.
     * @param value2 the second value.
     */
    @Override
    public void removeEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2) && this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            matrix[firstIndex][secondIndex] = -1;
            if(!this.isDirected())
                matrix[secondIndex][firstIndex] = -1;

            edges--;
        }
    }

    /**
     * Returns a List with the nodes adjacent to the given one, if its already in the
     * graph. It iterates over a row of the matrix, if the value is grater than 0 (0 being the same node
     * and -1 being no adjacency) it adds the value to the list.
     * @param value the value.
     * @return a list with the adjacencies of the given node.
     */
    @Override
    public List<V> getAdjacentNodes(V value) {
        List<V> adjacentNodes = new LinkedList<>();
        if(this.contains(value)){
            int index = this.getIndex(value);
            for(int i = 0; i < vertexes; i++){
                if(matrix[index][i] > 0)
                    adjacentNodes.add(values[i]);

            }
        }
        return adjacentNodes;
    }

    /**
     * Returns a list with all the values of the nodes.
     * @return a list with all the values of the nodes.
     */
    @Override
    public List<V> getValues() {
        List<V> valueList = new LinkedList<>();
        for(int i = 0; i < vertexes; i++)
            valueList.add(values[i]);

        return valueList;
    }

    /**
     * Returns the number of nodes.
     * @return  the number of nodes.
     */
    @Override
    public int V() {
        return vertexes;
    }

    /**
     * Returns the number of edges.
     * @return the number of edges.
     */
    @Override
    public int E() {
        return edges;
    }

    /**
     * Returns a new instance of the AdjacencyMatrixIterator class.
     * @return a new instance of the AdjacencyMatrixIterator class.
     */
    @Override
    public Iterator<V> iterator() {
        return new AdjacencyMatrixIterator(values, vertexes);
    }

    /**
     * Returns the total weight of the edges of the graph.
     * @return the total weight of the edges of the graph.
     */
    @Override
    public double getWeight() {
        double weight = 0;
        if(this.weighted){
            for (int i = 0; i < vertexes; i++) {
                for (int j = 0; j < vertexes; j++) {
                    weight += matrix[i][j];
                }
            }
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
     * Returns whether the graph is empty or not.
     * @return true if the graph is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return vertexes == 0;
    }

    /**
     * Clears the graph by setting all the variables to their default state.
     */
    @Override
    public void clear() {
        matrix = new double[INITIAL_SIZE][INITIAL_SIZE];
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

    /**
     * Returns an array with all the values of the nodes.
     * @return an array with all the values of the nodes.
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
     * Returns the matrix representation of the graph.
     * @return the matrix representation of the graph.
     */
    @Override
    public double[][] getGraphStructureAsMatrix() {
        double[][] newMatrix = new double[vertexes][vertexes];
        for(int i = 0; i < vertexes; i++){
            for(int j = 0; j < vertexes; j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    /**
     * Searches the values array for the node given as parameter.
     * If the node is in the graph, it returns the index of the node in the values array.
     * Otherwise it returns a -1.
     * @param value node being searched for.
     * @return the index of the node in the values array
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
     * Enlarges the values array and the weight matrix.
     */
    private void enlarge(){
        V[] newValues = (V[]) new Object[values.length + ENLARGING_SIZE];
        for(int i = 0; i < values.length; i++)
            newValues[i] = values[i];

        double[][] newMatrix = new double[matrix.length + ENLARGING_SIZE][matrix.length + ENLARGING_SIZE];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                newMatrix[i][j] = matrix[i][j];
            }
        }
        values = newValues;
        matrix = newMatrix;
    }

    /**
     * Moves down by one position all the elements in the values array and the weight matrix.
     * @param index point from which to start moving the elements.
     */
    private void moveDown(int index){
        for(int i = index; i < vertexes - 1; i++){
            values[i] = values[i + 1];
            matrix[i] = matrix[i + 1];
        }
    }

    /**
     * Iterator class for the AdjacencyMatrix.
     */
    private class AdjacencyMatrixIterator implements Iterator<V>{
        private int index;
        private int maxIndex;
        private V[] values;

        public AdjacencyMatrixIterator(V[] values, int maxIndex){
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
