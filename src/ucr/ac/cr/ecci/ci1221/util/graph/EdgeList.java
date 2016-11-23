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
    private int vertexes;


    public EdgeList(boolean directed){
        this.directed = directed;
        edges = new LinkedList<>();
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public void addNode(V value) {
        if(!this.contains(value)){
            if(vertexes == values.length)
                this.enlarge();

            values[vertexes] = value;
            vertexes++;
        }
    }

    @Override
    public void addNodes(V value1, V value2) {
        if(!this.contains(value1) && !this.contains(value2)){
            this.addNode(value1);
            this.addNode(value2);
            this.addEdge(value1, value2);
        }
    }

    @Override
    public void addEdge(V value1, V value2) {
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            edges.add(new Edge(firstIndex, secondIndex, 1));
            if(!this.isDirected())
                edges.add(new Edge(secondIndex, firstIndex, 1));

        }
    }

    @Override
    public void addEdge(V value1, V value2, double weight) {
        if(this.contains(value1) && this.contains(value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            edges.add(new Edge(firstIndex, secondIndex, weight));
            if(!this.isDirected())
                edges.add(new Edge(secondIndex, firstIndex, weight));
        }
    }

    @Override
    public boolean contains(V value) {
        return this.getIndex(value) != -1;
    }

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

    @Override
    public double getWeight(V value1, V value2) {
        double weight = 0;
        if(this.contains(value1) && this.contains(value2) && this.areLinked(value1, value2)){
            int firstIndex = this.getIndex(value1);
            int secondIndex = this.getIndex(value2);
            for(int i = 0; i < edges.size(); i++){
                Edge e = edges.get(i);
                if(e.getFrom() == firstIndex && e.getTo() == secondIndex)
                    weight = e.getWeight();

            }
        }
        return weight;
    }

    @Override
    public void removeValue(V value) {
    }

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
        return edges.size();
    }

    @Override
    public Iterator<V> iterator() {
        return null;
    }

    @Override
    public double getWeight() {
        double weight = 0;
        for(int i = 0; i < edges.size(); i++)
            weight += edges.get(i).getWeight();

        if(!this.isDirected())
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
        edges = new LinkedList<>();
        values = (V[]) new Object[INITIAL_SIZE];
        vertexes = 0;
    }

    private int getIndex(V value){
        int index = -1;
        int counter = 0;
        while(index == -1 && counter < vertexes){
            if(values[counter].equals(value))
                index = counter;
        }
        return index;
    }

    private void enlarge(){
        V[] newValues = (V[]) new Object[values.length + ENLARGING_SIZE];
        for(int i = 0; i < values.length; i++)
            newValues[i] = values[i];

        values = newValues;
    }

    private class Edge{
        private int from;
        private int to;
        private double weight;

        public Edge(int from, int to, double weight){
            this.from = from;
            this.to = to;
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
}
