package algorithm.json;

/**
 * @author Roberto Anzaldua.
 */
public class JSONEdge {
  public final int source_node_id;
  public final int target_node_id;
  public final int travel_distance;
  public final int travel_time;

  public JSONEdge(int source_node_id, int targetNodeId, int travelDistance, int travelTime) {
    this.source_node_id = source_node_id;
    this.target_node_id = targetNodeId;
    this.travel_distance = travelDistance;
    this.travel_time = travelTime;
  }
}
