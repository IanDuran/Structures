package ucr.ac.cr.ecci.ci1221.util.graph;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;

import java.util.Iterator;
import ucr.ac.cr.ecci.ci1221.util.collections.list.List;

/**
 * @author Student Name
 */
public class EdgeList<V> implements Graph<V> {

    private LinkedList<Edge> edges;
    private boolean directed;
    private int counter;

    public EdgeList(boolean directed){
        edges = new LinkedList<>();
        this.directed = directed;
        counter = 0;
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
        return null;
    }

    @Override
    public int V() {
        return 0;
    }

    @Override
    public int E() {
        return 0;
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    private class Edge<T>{
        private T first;
        private T second;
        private int firstNumber;
        private int secondNumber;
        private int weight;

        public Edge(T first, T second){
            this.first = first;
            this.second = second;
        }

        public Edge(T first){
            this.first = first;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public T getSecond() {
            return second;
        }

        public void setSecond(T second) {
            this.second = second;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getFirstNumber() {
            return firstNumber;
        }

        public void setFirstNumber(int firstNumber) {
            this.firstNumber = firstNumber;
        }

        public int getSecondNumber() {
            return secondNumber;
        }

        public void setSecondNumber(int secondNumber) {
            this.secondNumber = secondNumber;
        }
    }
}
