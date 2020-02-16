package algorithm;

import datastructures.ArrayBasedGraph;
import datastructures.IntDoubleBinaryHeap;

/**
 * @author Roberto Anzaldua.
 * Implements Dijkstra shortest path algorithm using a binary heap.
 * g is the graph used to run the algorithm

 * w is an array containing the weight of the shortest path for each vertex.
 * p is an array containing the predecesor for each vertex.
 *
 * s is the starting index of the vertex that the algorithm uses
 *
 * Both w and p are primitive int arrays with default
 * initialization (all elements are initialized to 0)
 * This implies that in order for this approach to work, the weights need to be greater than  0.
 * This is because when we interpret the results, we need to take into account that any unreachable
 * vertex v != s will have a p[v] = 0.
 */
public class DijkstraShortestPaths {

  /**
   * SimpleGraph were the algorithm is run
   */
  private ArrayBasedGraph g;
  /**
   * Starting vertex
   */
  private int s;

  /**
   * Weight to all vertices from starting vertex s
   */
  public int[] w;

  /**
   * Predecesor graph
   */
  public int[] p;

  public DijkstraShortestPaths(ArrayBasedGraph g) {
    this.g = g;
  }

  public void shortestPaths(int s) {
    this.s = s;
    initialization();

    w[s] = 0;
    p[s] = s;

    final IntDoubleBinaryHeap binaryHeap = new IntDoubleBinaryHeap(g.vertexNeighborsPositions.length);
    binaryHeap.insert_(0, s);

    while(!binaryHeap.isEmpty()) {
      final int u = binaryHeap.poll_element();
      final int startPosition = g.vertexNeighborsPositions[u];
      final int endPosition = g.vertexNeighborsPositions[u + 1];

      for(int i = startPosition; i < endPosition; i++){
        if(g.neighbors[i] != s){
          weightRelax(u, g.neighbors[i], g.weights[i], binaryHeap);
        }
      }
    }
  }

  private void initialization() {
    w = new int[g.vertexNeighborsPositions.length - 1];
    p = new int[g.vertexNeighborsPositions.length - 1];
  }

  private void weightRelax(int u, int v, int uvWeight, IntDoubleBinaryHeap binaryHeap) {
    final int weightToV = w[u] + uvWeight;
    //If w is 0, we haven't found any route, so this is the shortest one
    if(w[v] == 0){
      update(u, v, weightToV);
      binaryHeap.insert_(w[v], v);
    }else {
      if(weightToV < w[v]){
        update(u, v, weightToV);
        binaryHeap.update_(w[v], v);
      }
    }
  }

  private void update(int u, int v, int alternativeRouteTime){
    w[v] = alternativeRouteTime;
    p[v] = u;
  }
}
