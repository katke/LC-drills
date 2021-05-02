package com.kp.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// 4. https://leetcode.com/problems/median-of-two-sorted-arrays/
// Status: ACCEPTED
public class MedianTwoSortedArrays implements TestCase {

    public double solution(int[] nums1, int[] nums2) {
      if (nums1 == null || nums1.length == 0) {
        return findMedianValue(nums2);
      } else if (nums2 == null || nums2.length == 0) {
        return findMedianValue(nums1);
      }

      int mergedLength = nums1.length + nums2.length;
      int[] mergedArray = new int[mergedLength];
      int mergedIndex = 0;
      int nums1Index = 0;
      int nums2Index = 0;
      while (nums1Index + nums2Index < mergedLength) {
        int nextNum;
        if (nums1Index == nums1.length) {
          nextNum = nums2[nums2Index];
          nums2Index++;
        } else if (nums2Index == nums2.length) {
          nextNum = nums1[nums1Index];
          nums1Index++;
        } else if (nums1[nums1Index] < nums2[nums2Index]) {
          nextNum = nums1[nums1Index];
          nums1Index++;
        } else {
          nextNum = nums2[nums2Index];
          nums2Index++;
        }
        mergedArray[mergedIndex] = nextNum;
        mergedIndex++;
      }
      return findMedianValue(mergedArray);
    }

    private double findMedianValue(int[] nums) {
      int midpointIndex = nums.length / 2;
      if (nums.length % 2 == 0) {
        int secondaryMidpoint = midpointIndex - 1;
        return (double) (nums[midpointIndex] + nums[secondaryMidpoint]) / 2;
      } else {
        return nums[midpointIndex];
      }
    }

  @Override
  public Map<String, List<int[]>> getTestCases() {
    return Map.of(
        "1. Expected: 2.0", List.of(new int[]{1,3}, new int[]{2}),
        "2. Expected: 2.5", List.of(new int[]{1,2}, new int[]{3,4}),
        "3. Expected: 0.0", List.of(new int[]{0,0}, new int[]{0,0}),
        "4. Expected: 1.0", List.of(new int[]{1}, new int[]{}),
        "5. Expected: 2.0", List.of(new int[]{}, new int[]{2}),
        "6. Expected: 8.0", List.of(new int[]{2,4,6,8,10}, new int[]{11,12}),
        "7. Expected: 9.0", List.of(new int[]{11,12,13}, new int[]{2,4,6,8,10})
    );
  }
}
