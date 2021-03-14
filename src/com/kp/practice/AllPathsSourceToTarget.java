package com.kp.practice;

// 797 https://leetcode.com/problems/all-paths-from-source-to-target/
import java.util.*;

public class AllPathsSourceToTarget implements TestCase {

  public List<List<Integer>> solution(int[][] graph) {
    var allPaths = new ArrayList<List<Integer>>();
    findPaths(allPaths, new ArrayList<Integer>(), 0, graph);

    return allPaths;
  }

  //[ [4,3,1], [3,2,4], [3], [4], [] ]

  private void findPaths(List<List<Integer>> allPaths, List<Integer> currentPath, int nextNode, final int[][] graph) {
    var target = graph.length - 1;
    if (currentPath == null) {
      currentPath = new ArrayList<>();
    }
    if (nextNode == target) {
      currentPath.add(nextNode);
      allPaths.add(currentPath);
      return;
    }
    currentPath.add(nextNode);
    var adjList = graph[nextNode];
    findPaths(allPaths, currentPath, nextNode, graph);


    }
  }

  @Override
  public Map<String, int[][]> getTestCases() {
    return Map.of(
//        "1. Expected 2 paths, [[0,1,3],[0,2,3]]", lcExample1(),
        "2. Expected 5 paths, [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]", lcExample2()
//        "3. Expected 1 path, [[0,1]]", lcExample3(),
//        "4. Expected 3 paths, [[0,1,2,3],[0,2,3],[0,3]]", lcExample4(),
//        "5. Expected 2 paths, [[0,1,2,3],[0,3]]", lcExample5()
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
