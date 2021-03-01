package com.kp.practice;

import com.kp.practice.commontypes.ListNode;

import java.util.List;
import java.util.Map;

// 2: https://leetcode.com/problems/add-two-numbers/
public class AddTwoNumbers implements TestCase {
  public ListNode solution(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
      return new ListNode(0);
    } else if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    var currentSum = 0;
    var carryOver = 0;
    ListNode currentSumNode = null;
    // Traverse LLs one node at a time
    // Add each digit, with the sum appearing in the new list
    // if sum > 9, carry over 1 to next node
    ListNode[] currentNodes = new ListNode[] {l1.next, l2.next};
    while (currentNodes[0] != null || currentNodes[1] != null) {
      var l1Current = currentNodes[0];
      var l2Current = currentNodes[1];
      if (l1Current != null && l2Current != null) {
        currentSum = l1Current.val + l2Current.val + carryOver;
        currentNodes = new ListNode[]{l1Current.next, l2Current.next};
      } else if (l1Current != null) {
        currentSum = l1Current.val + carryOver;
        currentNodes = new ListNode[]{l1Current.next, null};
      } else {
        currentSum = l2Current.val + carryOver;
        currentNodes = new ListNode[]{null, l2Current.next};
      }
      carryOver = 0;
      if (currentSum > 9) {
        currentSum -= 10;
        carryOver = 1;
      }
      if (currentSumNode == null) {
        currentSumNode = new ListNode(currentSum);
      } else {
        currentSumNode.next = new ListNode(currentSum);
        currentSumNode = currentSumNode.next;
      }
    }
    if (carryOver == 1) {
      currentSumNode.next = new ListNode(1);
    }
    return currentSumNode;
  }

  public Map<String, List<ListNode>> getTestCases() {
    // 342 + 465 = 807
    var tc1Node1 = new ListNode(2);
    var tc1Node2 = new ListNode(4);
    var tc1Node3 = new ListNode(3);
    tc1Node1.next = tc1Node2;
    tc1Node3.next = tc1Node3;

    var tc1Node4 = new ListNode(5);
    var tc1Node5 = new ListNode(6);
    var tc1Node6 = new ListNode(4);
    tc1Node4.next = tc1Node5;
    tc1Node5.next = tc1Node6;

    // 17 + 6 = 23
    var tc2Node1 = new ListNode(6);
    var tc2Node2 = new ListNode(1);
    tc2Node1.next = tc2Node2;

    var tc2Node3 = new ListNode(6);

    // 87 + 21 = 108
    var tc3Node1 = new ListNode(7);
    var tc3Node2 = new ListNode(8);
    tc3Node1.next = tc3Node2;

    var tc3Node3 = new ListNode(1);
    var tc3Node4 = new ListNode(2);
    tc3Node3.next = tc3Node4;

    return Map.of(
        "1. Expected: 7 -> 0 -> 8", List.of(tc1Node1, tc1Node4),
        "2. Expected: 3 -> 2", List.of(tc2Node1, tc2Node3),
        "3. Expected: 8 -> 0 -> 1", List.of(tc3Node1, tc3Node3)
        );
  }
}
