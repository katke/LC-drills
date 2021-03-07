package com.kp.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 287 https://leetcode.com/problems/find-the-duplicate-number/
// Status: Accepted
public class FindDuplicateNumber implements TestCase {
  public int solution(int[] nums) {
    if (nums.length == 0) return 0;
    else if (nums.length == 1) return nums[0];
    return bruteForce(nums);
  }

  private int bruteForce(int[] nums) {
    // nlogn time, constant space
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
//
//  private int binarySearch(int[] nums) {
//    var numsCopy = Arrays.copyOf(nums, nums.length);
//    Arrays.sort(numsCopy);
//    var result = findSubArrayWithDup(numsCopy);
//    return result[0];
//  }
//
//  private int[] findSubArrayWithDup(int[] nums) {
//    if (nums.length <= 2) {
//      return nums;
//    }
//    var midpoint = nums.length / 2;
//
//    var subarray1 = Arrays.copyOfRange(nums, 0, midpoint);
//    var subarray2 = Arrays.copyOfRange(nums, midpoint, nums.length);
//    var subarr1LastInt = subarray1[subarray1.length - 1];
//    if (subarr1LastInt == subarray2[0]) { // catch scenarios where the duplicate is split between 1 & 2
//      return new int[] {subarray2[0]};
//    }
//    var subarr1FirstInt = subarray1[0];
//    var diff = subarray1.length - (subarr1LastInt - subarr1FirstInt);
//
//    if (diff == 2) {
//      return findSubArrayWithDup(subarray1);
//    } else {
//      return findSubArrayWithDup(subarray2);
//    }
//  }

  @Override
  public Map<String, List<int[]>> getTestCases() {
    return Map.of(
        "1. Expected 2", List.of(new int[] {1,3,4,2,2}),
        "2. Expected 3", List.of(new int[] {3,1,3,4,2}),
        "3. Expected 1", List.of(new int[] {1,1}),
        "4. Expected 1", List.of(new int[] {1,1,2}),
        "5. Expected 0", List.of(new int[] {}),
        "6. Expected 3", List.of(new int[] {1,3,10,2,5,3,4,8,6,9,7}),
        "7. Expected 10", List.of(new int[] {1,3,10,2,5,10,4,8,6,9,7})
    );
  }
}
