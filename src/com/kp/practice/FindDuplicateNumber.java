package com.kp.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 287 https://leetcode.com/problems/find-the-duplicate-number/
public class FindDuplicateNumber implements TestCase {
  public int solution(int[] nums) {
    if (nums.length == 0) return 0;
    else if (nums.length == 1) return nums[0];
    return bruteForce(nums);
  }

  private int bruteForce(int[] nums) {
    Arrays.sort(nums);
    var prevInt = nums[0];
    var current = 0;
    for (int i = 1; i < nums.length; i++) {
      current = nums[i];
      if (current == prevInt) {
        break;
      }
      prevInt = current;
    }
    return current;
  }

  @Override
  public Map<String, List<int[]>> getTestCases() {
    return Map.of(
        "1. Expected 2", List.of(new int[] {1,3,4,2,2}),
        "2. Expected 3", List.of(new int[] {3,1,3,4,2}),
        "3. Expected 1", List.of(new int[] {1,1}),
        "4. Expected 1", List.of(new int[] {1,1,2})
    );
  }
}
