package com.kp.practice;

import java.util.List;
import java.util.Map;

// 74 https://leetcode.com/problems/search-a-2d-matrix/
public class Search2DMatrix implements TestCase {
  public boolean solution(int[][] matrix, int target) {

    return false;
  }

  public Map<String, List<?>> getTestCases() {
    return Map.of(
        "1. Expected: true", lcTestCase1(),
        "2. Expected: true", onlyOneRow(),
        "3. Expected: false", targetExceedsAllRows(),
        "4. Expected: true", targetIsLastInt(),
        "5. Expected: true", targetIsLFirstInt(),
        "6. Expected: false", noMatch()
    );
  }

  private List<?> lcTestCase1() {
    int[] row1 = new int[]{1,3,5,7};
    int[] row2 = new int[]{10,11,16,20};
    int[] row3 = new int[]{23,30,34,60};
    int[][] matrix = new int[][] {
        row1,
        row2,
        row3
    };
    return List.of(matrix, 3);
  }

  private List<?> onlyOneRow() {
    int[] row1 = new int[]{1,30,31,37};
    int[][] matrix = new int[][] {
        row1
    };
    return List.of(matrix, 31);
  }

  private List<?> targetExceedsAllRows() {
    int[] row1 = new int[]{1,2,3,4};
    int[] row2 = new int[]{19,20,30,60};
    int[] row3 = new int[]{61,62,64,70};
    int[][] matrix = new int[][] {
        row1,
        row2,
        row3
    };
    return List.of(matrix, 71);
  }

  private List<?> targetIsLastInt() {
    int[] row1 = new int[]{100};
    int[] row2 = new int[]{101};
    int[] row3 = new int[]{150};
    int[][] matrix = new int[][] {
        row1,
        row2,
        row3
    };
    return List.of(matrix, 150);
  }

  private List<?> targetIsLFirstInt() {
    int[] row1 = new int[]{21,25,31,33,37,40};
    int[] row2 = new int[]{50,51,55,60,61,65};
    int[] row3 = new int[]{78,79,80,90,100,102};
    int[][] matrix = new int[][] {
        row1,
        row2,
        row3
    };
    return List.of(matrix, 21);
  }

  private List<?> noMatch() {
    int[] row1 = new int[]{1,2,3,4,5,6};
    int[] row2 = new int[]{15,17,20,60,61,65};
    int[] row3 = new int[]{78,79,90,97,100,102};
    int[][] matrix = new int[][] {
        row1,
        row2,
        row3
    };
    return List.of(matrix, 66);
  }
}
