package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Student Name
 */
public class AdjacencyMatrix<V> implements Graph<V>{

    private final int INITIAL_SIZE = 10;
    private int[][] matrix;
    private V[] values;
    private boolean directed;
    private int vertexes;
    private int edges;

    public AdjacencyMatrix(boolean directed){
        this.directed = directed;
        matrix = new int[INITIAL_SIZE][INITIAL_SIZE];
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addNode(V value) {

    }

    @Override
    public void addNodes(V value1, V value2) {

    }

    @Override
    public void addEdge(V value1, V value2) {

    }

    @Override
    public void addEdge(V value1, V value2, double weight) {

    }

    @Override
    public boolean contains(V value) {
        return false;
    }

    @Override
    public boolean areLinked(V value1, V value2) {
        return false;
    }

    @Override
    public double getWeight(V value1, V value2) {
        return 0;
    }

    @Override
    public void removeValue(V value) {

    }

    @Override
    public void removeEdge(V value1, V value2) {

    }

    @Override
    public List<V> getAdjacentNodes(V value) {
        return null;
    }

    @Override
    public List<V> getValues() {
        List<V> valueList = new LinkedList<>();
        for(int i = 0; i < vertexes; i++)
            valueList.add(values[i]);

        return valueList;
    }

    @Override
    public int V() {
        return vertexes;
    }

    @Override
    public int E() {
        return edges;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public double getWeight() {
        double weight = 0;
        for (int i = 0; i < vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                weight += matrix[i][j];
            }
        }
        if(this.isDirected())
            weight /= 2;

        return weight;
    }

    @Override
    public int size() {
        return vertexes;
    }

    @Override
    public boolean isEmpty() {
        return vertexes == 0;
    }

    @Override
    public void clear() {
        matrix = new int[INITIAL_SIZE][INITIAL_SIZE];
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

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
}
