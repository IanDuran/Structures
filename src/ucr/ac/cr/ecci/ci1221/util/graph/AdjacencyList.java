package ucr.ac.cr.ecci.ci1221.util.graph;

import java.util.Iterator;
import java.util.List;

/**
 * @author Student Name
 */
public class AdjacencyList<V> implements Graph<V> {

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
    public int size() {
        return 0;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
