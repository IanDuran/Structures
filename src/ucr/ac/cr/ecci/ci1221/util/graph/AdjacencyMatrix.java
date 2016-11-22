package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Student Name
 */
public class AdjacencyMatrix<V> implements Graph<V>{

    private final int INITIAL_SIZE = 10;
    private final int ENLARGING_SIZE = 5;
    private double[][] matrix;
    private V[] values;
    private boolean directed;
    private int vertexes;
    private int edges;

    public AdjacencyMatrix(boolean directed){
        this.directed = directed;
        matrix = new double[INITIAL_SIZE][INITIAL_SIZE];
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
        if(vertexes == values.length)
            this.enlarge();

        values[vertexes] = value;
        vertexes++;
    }

    @Override
    public void addNodes(V value1, V value2) {
        this.addNode(value1);
        this.addNode(value2);
        this.addEdge(value1, value2);
    }

    @Override
    public void addEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            matrix[firstIndex][secondIndex] = 1;
            if(!this.isDirected())
                matrix[secondIndex][firstIndex] = 1;

            edges++;
        }
    }

    @Override
    public void addEdge(V value1, V value2, double weight) {
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            matrix[firstIndex][secondIndex] = weight;
            if(!this.isDirected())
                matrix[secondIndex][firstIndex] = weight;

            edges++;
        }
    }

    @Override
    public boolean contains(V value) {
        return this.getIndex(value) != -1;
    }

    @Override
    public boolean areLinked(V value1, V value2) {
        boolean areLinked = false;
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            if(matrix[firstIndex][secondIndex] != 0)
                areLinked = true;

        }
        return areLinked;
    }

    @Override
    public double getWeight(V value1, V value2) {
        double weight = 0;
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            weight = matrix[firstIndex][secondIndex];
        }
        return weight;
    }

    @Override
    public void removeValue(V value) {
        if(this.contains(value)) {
            this.moveDown(this.getIndex(value));
            vertexes--;
        }
    }

    @Override
    public void removeEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2) && this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            matrix[firstIndex][secondIndex] = 0;
            if(!this.isDirected())
                matrix[secondIndex][firstIndex] = 0;

            edges--;
        }
    }

    @Override
    public List<V> getAdjacentNodes(V value) {
        List<V> adjacentNodes = new LinkedList<>();
        if(this.contains(value)){
            int index = this.getIndex(value);
            for(int i = 0; i < vertexes; i++){
                if(matrix[index][i] != 0)
                    adjacentNodes.add(values[i]);

            }
        }
        return adjacentNodes;
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
        matrix = new double[INITIAL_SIZE][INITIAL_SIZE];
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

    private void moveDown(int index){
        for(int i = index; i < vertexes - 1; i++){
            values[i] = values[i + 1];
            matrix[i] = matrix[i + 1];
        }
    }
}
