package com.kp.practice;

// 797 https://leetcode.com/problems/all-paths-from-source-to-target/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllPathsSourceToTarget implements TestCase {

  public List<List<Integer>> solution(int[][] graph) {
    return new ArrayList<>();
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
