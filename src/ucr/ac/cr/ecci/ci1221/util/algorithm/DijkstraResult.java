package ucr.ac.cr.ecci.ci1221.util.algorithm;

/**
 * @author Rodrigo A. Bartels
 */
public final class DijkstraResult<V> {

    private V vertex;

    private V precursor;

    private Double distance;

    DijkstraResult(V vertex, V precursor, double distance){
        this.vertex = vertex;
        this.precursor = precursor;
        this.distance = distance;
    }

    public V getVertex() {
        return vertex;
    }

    public void setVertex(V vertex) {
        this.vertex = vertex;
    }

    public V getPrecursor() {
        return precursor;
    }

    public void setPrecursor(V precursor) {
        this.precursor = precursor;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
