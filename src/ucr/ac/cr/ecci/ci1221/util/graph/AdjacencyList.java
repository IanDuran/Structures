package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Ian Duran
 */
public class AdjacencyList<V> implements Graph<V> {

    private final int INITIAL_SIZE = 20;
    private final int ENLARGING_SIZE = 5;
    private boolean directed;
    private V[] values;
    private List<Node>[] adjacencies;
    private int vertexes;
    private int edges;


    public AdjacencyList(boolean directed){
        this.directed = directed;
        values = (V[]) new Object[INITIAL_SIZE];
        adjacencies = new LinkedList[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addNode(V value) {
        if(vertexes >= values.length)
            this.enlarge();

        adjacencies[vertexes] = new LinkedList<>();
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
        if(!this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            if(firstIndex != -1 && secondIndex != -1){
                adjacencies[firstIndex].add(new Node(secondIndex, 1));
                if(!this.isDirected())
                    adjacencies[secondIndex].add(new Node(firstIndex, 1));
            }
            edges++;
        }
    }

    @Override
    public void addEdge(V value1, V value2, double weight) {
        if(!this.areLinked(value1, value2)) {
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            if (firstIndex != -1 && secondIndex != -1) {
                adjacencies[firstIndex].add(new Node(secondIndex, weight));
                if (!this.isDirected())
                    adjacencies[secondIndex].add(new Node(firstIndex, weight));
            }
            edges++;
        }
    }

    @Override
    public boolean contains(V value) {
        return this.getIndex(value) != -1;
    }

    @Override
    public boolean areLinked(V value1, V value2) {
        return this.getWeight(value1, value2) != -1 || this.getWeight(value2, value1) != -1;
    }

    @Override
    public double getWeight(V value1, V value2) {
        double weight = -1;
        int firstIndex = this.getIndex(value1);
        int secondIndex = this.getIndex(value2);
        if(firstIndex != -1 && secondIndex != -1){
            int counter = 0;
            while(weight == -1 && counter < adjacencies[firstIndex].size()){
                if(adjacencies[firstIndex].get(counter).getValue() == secondIndex)
                    weight = adjacencies[firstIndex].get(counter).getWeight();

                counter++;
            }
        }
        return weight;
    }

    @Override
    public void removeValue(V value) {
        int toRemove = -1;
        int counter = 0;
        while(toRemove == -1 && counter < vertexes){
            if(values[counter].equals(value))
                toRemove = counter;

            counter++;
        }
        this.moveDown(counter);
        vertexes--;
    }

    @Override
    public void removeEdge(V value1, V value2) {
        int firstIndex = this.getIndex(value1);
        int secondIndex = this.getIndex(value2);
        if(firstIndex != -1 && secondIndex != -1){
            int counter = 0;
            boolean eliminated = false;
            while(!eliminated && counter < adjacencies[firstIndex].size()){
                if(adjacencies[firstIndex].get(counter).getValue() == secondIndex){
                    adjacencies[firstIndex].remove(counter);
                    eliminated = true;
                }
                counter++;
            }
            if(!isDirected()){
                counter = 0;
                eliminated = false;
                while(!eliminated && counter < adjacencies[secondIndex].size()){
                    if(adjacencies[secondIndex].get(counter).getValue() == firstIndex){
                        adjacencies[secondIndex].remove(counter);
                        eliminated = true;
                    }
                    counter++;
                }
            }
            edges--;
        }

    }

    @Override
    public List<V> getAdjacentNodes(V value) {
        List<Node> adjacentNodes = null;
        List<V> adjacentNodesValues = new LinkedList<>();
        int counter = 0;
        while(adjacentNodes == null && counter < vertexes){
            if(values[counter].equals(value))
                adjacentNodes = adjacencies[counter];

            counter++;
        }
        if(adjacentNodes != null){
            for(int i = 0; i < adjacentNodes.size(); i++){
                adjacentNodesValues.add(values[adjacentNodes.get(i).getValue()]);
            }
        }
        return adjacentNodesValues;
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
        return new AdjacencyListIterator(values, vertexes);
    }

    @Override
    public int size() {
        return vertexes;
    }

    @Override
    public double getWeight() {
        double totalWeight = 0;
        for(int i = 0; i < vertexes; i++){
            for(int j = 0; j < adjacencies[i].size(); j++){
                totalWeight += adjacencies[i].get(j).getWeight();
            }
        }
        if(!this.isDirected())
            totalWeight /= 2;

        return totalWeight;
    }

    @Override
    public boolean isEmpty() {
        return vertexes == 0;
    }

    @Override
    public void clear() {
        values = (V[]) new Object[INITIAL_SIZE];
        adjacencies = new LinkedList[INITIAL_SIZE];
        vertexes = 0;
        edges = 0;
    }

    @Override
    public V[] getValuesAsArray() {
        return values;
    }

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

    private void enlarge(){
        V[] newArray = (V[]) new Object[values.length + ENLARGING_SIZE];
        List<Node> newAdjacencies[] = new LinkedList[adjacencies.length + ENLARGING_SIZE];
        for(int i = 0; i < newArray.length; i++) {
            newArray[i] = values[i];
            newAdjacencies[i] = adjacencies[i];
        }
        values = newArray;
        adjacencies = newAdjacencies;
    }

    private void moveDown(int index){
        for(int i = index; i < vertexes - 1; i++) {
            values[i] = values[i + 1];
            adjacencies[i] = adjacencies[i + 1];
        }
        values[vertexes] = null;
        adjacencies[vertexes] = null;
    }

    private int getIndex(V value){
        int index = -1;
        int counter = 0;
        while(counter < vertexes && index == -1){
            if(values[counter].equals(value))
                index = counter;

            counter++;
        }
        return index;
    }

    private class Node{
        private int value;
        private double weight;

        public Node(int value, double weight){
            this.value = value;
            this.weight = weight;
        }

        public Node(int value){
            this.value = value;
            this.weight = 0;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    private class AdjacencyListIterator implements Iterator<V>{
        private int index;
        private int maxIndex;
        private V[] values;

        public AdjacencyListIterator(V[] values, int maxIndex){
            this.values = values;
            this.maxIndex = maxIndex;
            this.index = 0;
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
