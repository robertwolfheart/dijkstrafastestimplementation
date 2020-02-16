package algorithm.json;

/**
 * @author Roberto Anzaldua.
 */
public class JSONVertex {
  public final int node_id;
  public final String[] postcodes;
  public final String road_type;

  public JSONVertex(int nodeId, String[] postcodes, String roadtype) {
    this.node_id = nodeId;
    this.postcodes = postcodes;
    this.road_type = roadtype;
  }
}
