package com.kp.practice;

import com.kp.practice.commontypes.ListNode;

import java.util.List;
import java.util.Map;

// 21 https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedArrays implements TestCase {
  public ListNode solution(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    ListNode merged = l1;
    var l2Next = l2;
    var currentMergedNode = merged;
    while (l2Next != null) {
      // save existing currentMergedNode to refer back to once we find the insertion point
      var prevMergedNode = currentMergedNode;
      while (currentMergedNode != null && currentMergedNode.val < l2Next.val) {
        // traverse merged until we find the point where currentl2Val is greater than
        // currentMergedNode value
        prevMergedNode = currentMergedNode;
        currentMergedNode = currentMergedNode.next;
      }
      mergeInNewNode(prevMergedNode, l2Next);
      l2Next = l2Next.next;
    }
    return merged;
  }

  private void mergeInNewNode(ListNode mergedInsertionPoint, ListNode l2Node) {
    // extract the current mergedInsertionPoint.next
    var existingNextNode = mergedInsertionPoint.next;
    // point mergedInsertionPoint.next to a node with l2 val
    // point that new node next to the prev mergedInsertionPoint.next
    // set mergedInsertionPoint to mergedInsertionPoint.next
    mergedInsertionPoint.next = new ListNode(l2Node.val, existingNextNode);;
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
