package com.kp.practice;

import com.kp.practice.commontypes.ListNode;

import java.util.List;
import java.util.Map;

import static java.util.Objects.nonNull;

// 21 https://leetcode.com/problems/merge-two-sorted-lists/
public class MergeTwoSortedArrays implements TestCase {
  public ListNode solution(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    ListNode mergedRoot = cloneInputL1(l1);
    ListNode merged = mergedRoot;
    var l2Next = l2;
    var currentMergedNode = merged;
    while (l2Next != null) {
      // save existing currentMergedNode to refer back to once we find the insertion point
      var prevMergedNode = currentMergedNode;
      while (currentMergedNode != null && currentMergedNode.val < l2Next.val) {
        // traverse merged list until we find the point where currentl2Val is greater than
        // currentMergedNode value
        prevMergedNode = currentMergedNode;
        currentMergedNode = currentMergedNode.next;
      }
      if (prevMergedNode.val > l2.val) {
        currentMergedNode = setNewMergedLLRoot(prevMergedNode, l2Next);
        if (currentMergedNode.val < l2Next.val) {
          mergedRoot = currentMergedNode;
        }
      } else {
        currentMergedNode = mergeInNewNode(prevMergedNode, l2Next);
      }
      l2Next = l2Next.next;
    }
    return mergedRoot;
  }

  private ListNode setNewMergedLLRoot(ListNode mergeInsertionPoint, ListNode l2NewRoot) {
    return new ListNode(l2NewRoot.val, mergeInsertionPoint);
  }

  private ListNode mergeInNewNode(ListNode mergeInsertionPoint, ListNode l2Node) {
    ListNode nextNode;
    // if null, then we're appending to end of l1
    if (mergeInsertionPoint.next == null) {
      nextNode = null;
    } else {
      // extract the current mergeInsertionPoint.next so we can point the new node to it
      nextNode = mergeInsertionPoint.next;
    }
    // point mergeInsertionPoint.next to a new node with l2 val
    mergeInsertionPoint.next = new ListNode(l2Node.val, nextNode);
    return mergeInsertionPoint;
  }

  private ListNode cloneInputL1(ListNode l1) {
    var currentNode = l1;
    ListNode cloneRoot = new ListNode(l1.val);
    ListNode clone = cloneRoot;
    boolean isRoot = true;
    while (currentNode != null) {
      if (isRoot) {
        isRoot = false;
      } else {
        clone.next = new ListNode(currentNode.val);
        clone = clone.next;
      }
      currentNode = currentNode.next;
    }
    return cloneRoot;
  }

  @Override
  public Map<String, List<ListNode>> getTestCases() {
    return Map.of(
//        "1. Expected: [1,1,2,3,4,4]", setupTestCase1(),
//        "2. Expected: [0,0]", smallLists(),
//        "3. Expected: [1,2,3,6,7,8]", mergeAtVeryEnd(),
        "4. Expected: [1,2,3,6,7,8]", mergeAtVeryBeginning()
//        "5. Expected: [1,2,3,6,7,8]", oneLongList1(),
//        "6. Expected: [1,2,3,6,7,8]", oneLongList2(),
//        "7. Expected: [1,2,3,6,7,8]", allListNodesHaveSameValue()
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

  private List<ListNode> smallLists() {
    var list1node1 = new ListNode(0);
    var list2node1 = new ListNode(0);
    return List.of(list1node1, list2node1);
  }

  private List<ListNode> mergeAtVeryEnd() {
    var list1node1 = new ListNode(1);
    var list1node2 = new ListNode(2);
    var list1node3 = new ListNode(3);
    list1node1.next = list1node2;
    list1node2.next = list1node3;

    var list2node1 = new ListNode(6);
    var list2node2 = new ListNode(7);
    var list2node3 = new ListNode(8);
    list2node1.next = list2node2;
    list2node2.next = list2node3;
    return List.of(list1node1, list2node1);
  }

  private List<ListNode> mergeAtVeryBeginning() {
    var list1node1 = new ListNode(6);
    var list1node2 = new ListNode(7);
    var list1node3 = new ListNode(8);
    list1node1.next = list1node2;
    list1node2.next = list1node3;

    var list2node1 = new ListNode(1);
    var list2node2 = new ListNode(2);
    var list2node3 = new ListNode(3);
    list2node1.next = list2node2;
    list2node2.next = list2node3;
    return List.of(list1node1, list2node1);
  }

  private List<ListNode> oneLongList1() {
    var list1node1 = new ListNode(4);

    var list2node1 = new ListNode(1);
    var list2node2 = new ListNode(5);
    var list2node3 = new ListNode(6);
    var list2node4 = new ListNode(7);
    var list2node5 = new ListNode(8);
    list2node1.next = list2node2;
    list2node2.next = list2node3;
    list2node3.next = list2node4;
    list2node4.next = list2node5;
    return List.of(list1node1, list2node1);
  }

  private List<ListNode> oneLongList2() {
    var list1node1 = new ListNode(1);
    var list1node2 = new ListNode(5);
    var list1node3 = new ListNode(6);
    var list1node4 = new ListNode(7);
    var list1node5 = new ListNode(8);
    list1node1.next = list1node2;
    list1node2.next = list1node3;
    list1node3.next = list1node4;
    list1node4.next = list1node5;

    var list2node1 = new ListNode(4);
    return List.of(list1node1, list2node1);
  }

  private List<ListNode> allListNodesHaveSameValue() {
    var list1node1 = new ListNode(1);
    var list1node2 = new ListNode(1);
    var list1node3 = new ListNode(1);
    var list1node4 = new ListNode(1);
    var list1node5 = new ListNode(1);
    list1node1.next = list1node2;
    list1node2.next = list1node3;
    list1node3.next = list1node4;
    list1node4.next = list1node5;

    var list2node1 = new ListNode(3);
    var list2node2 = new ListNode(3);
    var list2node3 = new ListNode(3);
    list2node1.next = list2node2;
    list2node2.next = list2node3;
    return List.of(list1node1, list2node1);
  }
}
