package algorithm.json;

import java.util.HashSet;

/**
 * @author Roberto Anzaldua.
 */
public class JSONGraph {

  public final JSONEdge[] edges;
  public final JSONVertex[] nodes;

  public JSONGraph(JSONVertex[] nodes, JSONEdge[] edges) {
    this.edges = edges;
    this.nodes = nodes;
  }
}
