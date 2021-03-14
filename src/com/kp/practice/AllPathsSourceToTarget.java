package com.kp.practice;

// 797 https://leetcode.com/problems/all-paths-from-source-to-target/
// status: Accepted! (and faster than 95% of the other submissions)
import java.util.*;

public class AllPathsSourceToTarget implements TestCase {

  public List<List<Integer>> solution(int[][] graph) {
    var allPaths = new ArrayList<List<Integer>>();
    findPaths(allPaths, new ArrayList<>(), 0, graph);
    return allPaths;
  }

  private void findPaths(List<List<Integer>> allPaths, List<Integer> currentPath, int currentNode, final int[][] graph) {
    currentPath.add(currentNode);
    var target = graph.length - 1;
    if (currentNode == target) {
      allPaths.add(currentPath);
      return;
    }
    var adjList = graph[currentNode];
    if (adjList.length == 0) return;
    for (int nextNode : adjList) {
      findPaths(allPaths, new ArrayList<>(currentPath), nextNode, graph);
    }
  }

  @Override
  public Map<String, int[][]> getTestCases() {
    return Map.of(
        "1. Expected 2 paths, [[0,1,3],[0,2,3]]", lcExample1(),
        "2. Expected 5 paths, [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]", lcExample2(),
        "3. Expected 1 path, [[0,1]]", lcExample3(),
        "4. Expected 3 paths, [[0,1,2,3],[0,2,3],[0,3]]", lcExample4(),
        "5. Expected 2 paths, [[0,1,2,3],[0,3]]", lcExample5(),
        "6. Expected 1 path, [[0,1,3,5,6]]", graphWithNonTargetDeadend()
    );
  }

  private int[][] graphWithNonTargetDeadend() {
    return new int[][] {
        {1, 2},
        {3},
        {4},
        {5},
        {},
        {6},
        {}
    };
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
