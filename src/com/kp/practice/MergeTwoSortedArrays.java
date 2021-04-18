package com.kp.practice;

import com.kp.practice.commontypes.ListNode;

import java.util.List;
import java.util.Map;

// 21 https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedArrays implements TestCase {
  public void solution(ListNode l1, ListNode l2) {

  }

  @Override
  public Map<String, List<ListNode>> getTestCases() {
    return Map.of(
        "1. Expected: [1,1,2,3,4,4]", setupTestCase1()
    );
  }

  private List<ListNode> setupTestCase1() {
    var list1node1 = new ListNode(1);
    var list1node2 = new ListNode(2);
    var list1node3 = new ListNode(4);
    list1node1.next = list1node2;
    list1node2.next = list1node3;

    var list2node1 = new ListNode(1);
    var list2node2 = new ListNode(3);
    var list2node3 = new ListNode(4);
    list2node1.next = list2node2;
    list2node2.next = list2node3;
    return List.of(list1node1, list2node1);
  }
}
