package algorithm;

import datastructures.ArrayBasedGraph;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

public class DijkstraShortestPathsTest {

  @Test
  public void shouldTraverse10VertexGraph() {
    final int[] vertexNeighborsPositions = {0, 2, 3, 5, 6, 7, 8, 8, 9, 10, 11};
    final int[] neighbors = {1, 4, 2, 3, 5, 5, 5, 7, 7, 9, 1};
    final int[] weights =   {4, 5, 3, 5, 7, 11, 3, 3, 11, 5, 1};


    ArrayBasedGraph graph = new ArrayBasedGraph(vertexNeighborsPositions, neighbors, weights);

    DijkstraShortestPaths dijkstraShortestPaths =
        new DijkstraShortestPaths(graph);

    dijkstraShortestPaths.shortestPaths(0);
    System.out.println("Weights to vertices: " + Arrays.toString(dijkstraShortestPaths.w));
    System.out.println("Predecesors: " + Arrays.toString(dijkstraShortestPaths.p));

    Assert.assertArrayEquals(new int[]{0, 4, 7, 12, 5, 8, 0, 11, 0, 0}, dijkstraShortestPaths.w);
    Assert.assertArrayEquals(new int[]{0, 0, 1, 2,  0, 4, 0,  5, 0, 0}, dijkstraShortestPaths.p);
  }


  @Test
  public void shouldTraverseMinimalNetwork() {
    final int[] vertexNeighborsPositions = {0, 0, 1, 3};
    final int[] neighbors = {2, 1, 0};
    final int[] weights =   {3, 1, 3};

    ArrayBasedGraph graph = new ArrayBasedGraph(vertexNeighborsPositions, neighbors, weights);

    DijkstraShortestPaths dijkstraShortestPaths =
        new DijkstraShortestPaths(graph);

    dijkstraShortestPaths.shortestPaths(1);
    System.out.println("Weights to vertices: " + Arrays.toString(dijkstraShortestPaths.w));
    System.out.println("Predecesors: " + Arrays.toString(dijkstraShortestPaths.p));
    Assert.assertArrayEquals(new int[]{6, 0, 3}, dijkstraShortestPaths.w);
    Assert.assertArrayEquals(new int[]{2, 1, 1}, dijkstraShortestPaths.p);
  }

  @Test
  public void testOnGraph() {

    ArrayBasedGraph graph = JSONGraphUtils.deserialize("array_sample.json");

    DijkstraShortestPaths dijkstraShortestPaths =
        new DijkstraShortestPaths(graph);

    int numberOfTests = 1000;

    int[] testVertices = getTestVertices(numberOfTests, graph.vertexNeighborsPositions.length - 1);
    testPerformance(dijkstraShortestPaths, testVertices);
  }

  private int[] testPerformance(DijkstraShortestPaths dijkstraShortestPaths,
  int[] testVertices){

    Instant start = Instant.now();

    for (int i = 0; i < testVertices.length; i++) {
      dijkstraShortestPaths.shortestPaths(testVertices[i]);
    }

    Instant end = Instant.now();

    Duration between = Duration.between(start, end);
    System.out.println("Seconds: " + between.getSeconds());
    System.out.println("Average per query: " + (double)between.getSeconds() / testVertices.length);
    return dijkstraShortestPaths.w;
  }

  private int[] getTestVertices(int numberOfTests, int numberOfVertices) {
    int[] testVertices = new int[numberOfTests];

    for(int i = 0; i < numberOfTests; i++){
      testVertices[i] = randInt(numberOfVertices);
    }

    return testVertices;
  }

  public static int randInt(int max) {
    return new Random().nextInt(max);
  }
}
