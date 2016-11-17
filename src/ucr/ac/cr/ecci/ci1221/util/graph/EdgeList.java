package ucr.ac.cr.ecci.ci1221.util.graph;

import ucr.ac.cr.ecci.ci1221.util.collections.list.LinkedList;

import java.util.Iterator;
import java.util.List;

/**
 * @author Student Name
 */
public class EdgeList<V> implements Graph<V> {

    private LinkedList<Edge> edges;

    @Override
    public boolean isDirected() {
        return false;
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

    private class Edge{
        private V first;
        private V second;
        private int weight;

        public Edge(V first, V second, int weight){
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

        public V getFirst() {
            return first;
        }

        public void setFirst(V first) {
            this.first = first;
        }

        public V getSecond() {
            return second;
        }

        public void setSecond(V second) {
            this.second = second;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
