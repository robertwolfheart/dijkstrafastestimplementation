package algorithm;

import algorithm.json.JSONGraph;
import com.google.gson.Gson;
import datastructures.ArrayBasedGraph;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Roberto Anzaldua.
 */
public class JSONGraphUtils {
  public static JSONGraph getFromFilePath(String filepath) {

    Gson gson = new Gson();

    JSONGraph response = gson.fromJson(getJSONObject(filepath), JSONGraph.class);

    return response;
  }

  private static String getJSONObject(String path) {
    String content = null;

    try {
      content = new String(Files.readAllBytes(Paths.get(path)));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return content;
  }

  public static ArrayBasedGraph deserialize(String filePath) {
    String content = null;
    try {
      content = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return new Gson().fromJson(content, ArrayBasedGraph.class);
  }

  private static void serialize(ArrayBasedGraph graph, String fileName) {

    Gson gson = new Gson();
    String json = gson.toJson(graph);

    try(  PrintWriter out = new PrintWriter( fileName )  ){
      out.println( json );
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

}
