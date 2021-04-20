package com.kp.practice;

import com.kp.practice.commontypes.ListNode;

import java.util.List;
import java.util.Map;

// 21 https://leetcode.com/problems/merge-two-sorted-lists/
// Status: ACCEPTED
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Merge Two Sorted Lists.
// Memory Usage: 37.7 MB, less than 99.98% of Java online submissions for Merge Two Sorted Lists.
// KOOLAID-MAN "OH YEAHHHH!"
public class MergeTwoSortedArrays implements TestCase {

  // pull out first node in both l1 and l2
  // if l1 is not null and smaller, create node with l1 val & point previous next to new one
  // // call .next on l1 node
  // else create node with l2 val (if not null) & point previous next to new one
  // // call .next on l2 node
  // runtime complexity: O(m + n) where m = l1 # of nodes/n = l2 # of nodes
  // space complexity: O(m + n)
  public ListNode solution(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    ListNode mergedRoot;
    ListNode mergedResult;
    ListNode l1Next = l1;
    ListNode l2Next = l2;
    if (l2Next.val > l1Next.val) {
      mergedRoot = new ListNode(l1Next.val);
      l1Next = l1Next.next;
    } else {
      mergedRoot = new ListNode(l2Next.val);
      l2Next = l2Next.next;
    }
    mergedResult = mergedRoot;
    while (l2Next != null || l1Next != null) {
      ListNode nextNode;
      if (l1Next == null) {
        nextNode = new ListNode(l2Next.val);
        l2Next = l2Next.next;
      } else if (l2Next == null) {
        nextNode = new ListNode(l1Next.val);
        l1Next = l1Next.next;
      } else if (l2Next.val > l1Next.val) {
        nextNode = new ListNode(l1Next.val);
        l1Next = l1Next.next;
      } else {
        nextNode = new ListNode(l2Next.val);
        l2Next = l2Next.next;
      }
      mergedResult.next = nextNode;
      mergedResult = mergedResult.next;
    }
    return mergedRoot;
  }

  @Override
  public Map<String, List<ListNode>> getTestCases() {
    return Map.of(
        "1. Expected: [1,1,2,3,4,4]", setupTestCase1(),
        "2. Expected: [0,0]", smallLists(),
        "3. Expected: [1,2,3,6,7,8]", mergeAtVeryEnd(),
        "4. Expected: [1,2,3,6,7,8]", mergeAtVeryBeginning(),
        "5. Expected: [1,4,5,6,7,8]", oneLongList1(),
        "6. Expected: [1,4,5,6,7,8]", oneLongList2(),
        "7. Expected: [1,1,1,1,1,3,3,3]", allListNodesHaveSameValue()
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
