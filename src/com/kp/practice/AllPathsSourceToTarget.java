package com.kp.practice;

// 797 https://leetcode.com/problems/all-paths-from-source-to-target/
import java.util.*;

public class AllPathsSourceToTarget implements TestCase {

  public List<List<Integer>> solution(int[][] graph) {
    var startingPoints = graph[0];
    var allPaths = new ArrayList<List<Integer>>();
    for (int node : startingPoints) {
      var path = new ArrayList<Integer>();
      path.add(0);
      allPaths.add(findPath(path, node, graph));
    }
    return allPaths;
  }

  private List<Integer> findPath(List<Integer> currentPath, int nextNode, int[][] graph) {
    currentPath.add(nextNode);
    var target = graph.length;
    var adjList = graph[nextNode];
    for (int nextNodeToCheck : adjList) {
      if (nextNodeToCheck == target) {
        currentPath.add(nextNodeToCheck);
        return currentPath;
      } else {
        return findPath(currentPath, nextNodeToCheck, graph);
      }
    }
    return currentPath;
  }

  @Override
  public Map<String, int[][]> getTestCases() {
    return Map.of(
        "1. Expected 2 paths, [[0,1,3],[0,2,3]]", lcExample1(),
        "2. Expected 5 paths, [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]", lcExample2(),
        "3. Expected 1 path, [[0,1]]", lcExample3(),
        "4. Expected 3 paths, [[0,1,2,3],[0,2,3],[0,3]]", lcExample4(),
        "5. Expected 2 paths, [[0,1,2,3],[0,3]]", lcExample5()
    );
  }

  private int[][] lcExample5() {
    return new int[][] {
        {1, 3},
        {2},
        {3},
        {}
    };
  }

  private int[][] lcExample4() {
    return new int[][] {
        {1, 2, 3},
        {2},
        {3},
        {}
    };
  }

  private int[][] lcExample3() {
    return new int[][] {
        {1},
        {}
    };
  }

  private int[][] lcExample2() {
    return new int[][] {
        {4, 3, 1},
        {3, 2, 4},
        {3},
        {4},
        {}
    };
  }

  private int[][] lcExample1() {
    return new int[][] {
        {1, 2},
        {3},
        {3},
        {}
    };
  }
}
