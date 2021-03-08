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
        "1. Expected 2 paths, [[0,1,3],[0,2,3]]", lcExample1()
    );
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
