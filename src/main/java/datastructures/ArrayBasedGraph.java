package datastructures;

/**
 * @author Roberto Anzaldua.
 */
public class ArrayBasedGraph {
    public final int[] vertexNeighborsPositions;
    public final int[] neighbors;
    public final int[] weights;

    public ArrayBasedGraph(int[] vertexNeighborsPositions, int[] neighbors, int[] weights) {
        this.vertexNeighborsPositions = vertexNeighborsPositions;
        this.neighbors = neighbors;
        this.weights = weights;
    }
}
