package com.kp.practice;

import com.kp.practice.commontypes.TreeNode;

import java.util.*;


// 662: https://leetcode.com/problems/maximum-width-of-binary-tree/
// Status: Passes tests but isn't optimized for space yet, 'memory limit exceeded'
public class MaxWidthBinaryTree implements TestCase {

  //  public int solution(TreeNode root) {
//    if (root == null) {
//      return 0;
//    }
//    int result = 0;
//    Deque<Optional<TreeNode>> queue = new ArrayDeque<>();
//    queue.add(Optional.of(root));
//    int index = 1;
//    int currentLevel = 1;
//
//    while (!queue.isEmpty()) {
//      var prevLevel = currentLevel;
//      // In balanced btrees, knowing which level you're on means you know how many items are present
//      // with base 2 raised to N power, where N is the level
//      // 2^0 = 1, 2^1 = 2, 2^3 = 4, etc
//      currentLevel = (int) (Math.log(index) / Math.log(2));
//      boolean isEndOfRow = prevLevel != currentLevel;
//      if (isEndOfRow) {
//        if (isEmptyRow(queue)) {
//          break;
//        }
//        int actualWidth = getActualWidth(queue);
//        if (actualWidth > result) {
//          result = actualWidth;
//        }
//      }
//
//      var currentOpt = queue.remove();
//      if (currentOpt.isPresent()) {
//        var current = currentOpt.get();
//        if (current.left != null) {
//          queue.add(Optional.of(current.left));
//        } else {
//          queue.add(Optional.ofNullable(null));
//        }
//        if (current.right != null) {
//          queue.add(Optional.of(current.right));
//        } else {
//          queue.add(Optional.ofNullable(null));
//        }
//      } else {
//        queue.add(Optional.ofNullable(null));
//        queue.add(Optional.ofNullable(null));
//      }
//      index++;
//
//    }
//    return result;
//  }
  public int solution(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int result = 0;
    Deque<PsuedoTuple> queue = new ArrayDeque<>();
    queue.add(new PsuedoTuple(root, 1));
    int rowEndIndex = 1;
    while (!queue.isEmpty()) {
      PsuedoTuple currentNode = queue.remove();
      if (currentNode.node.left != null) {
        queue.add(new PsuedoTuple(currentNode.node.left, leftNodeIndex(currentNode.index)));
      }
      if (currentNode.node.right != null) {
        queue.add(new PsuedoTuple(currentNode.node.right, rightNodeIndex(currentNode.index)));
      }

      PsuedoTuple firstItem = queue.peekFirst();
      if (firstItem == null) {
        break;
      } else if (firstItem.index > rowEndIndex) { // Indicates we've started a new row
        rowEndIndex = rightNodeIndex(rowEndIndex);
        PsuedoTuple lastItem = queue.getLast();
        result = Math.max(result, lastItem.index - firstItem.index + 1);
      }

    }
    return result;
  }

  private int leftNodeIndex(int parentIndex) {
    return parentIndex * 2;
  }

  private int rightNodeIndex(int parentIndex) {
    return parentIndex * 2 + 1;
  }

  private boolean isEmptyRow(Deque<PsuedoTuple> queue) {
    return queue
        .stream()
        .allMatch(tuple -> tuple.node == null);
  }

  private int getActualWidth(Deque<PsuedoTuple> nodes) {
    boolean hasNodeAtStart = false;
    int width = 0;
    int nullAccum = 0;
    for (PsuedoTuple tuple : nodes) {
      if (!hasNodeAtStart && tuple.node == null) {
        continue;
      } else if (hasNodeAtStart && tuple.node == null) {
        nullAccum++;
        continue;
      } else {
        width += nullAccum;
        nullAccum = 0;
      }
      width++;
      hasNodeAtStart = true;
    }
    return width;
  }

  public Map<String, List<TreeNode>> getTestCases() {
    return Map.of(
        "1. Expect 8", List.of(outerNodesOnly()),
        "2. Expect 4", List.of(mixOfInnerOuterNodes()),
        "3. Expect 4", List.of(moreInnerNodes()),
        "4. Expect 1", List.of(oneSidedTree()));
  }

  private TreeNode outerNodesOnly() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(3);
    root.right = new TreeNode(2);

    root.left.left = new TreeNode(5);
    root.right.right = new TreeNode(9);

    root.left.left.left = new TreeNode(6);
    root.right.right.right = new TreeNode(7);
    return root;
  }

  private TreeNode mixOfInnerOuterNodes() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(3);
    root.right = new TreeNode(2);

    root.left.left = new TreeNode(5);
    root.left.right = new TreeNode(4);

    root.right.right = new TreeNode(9);

    return root;
  }

  private TreeNode moreInnerNodes() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(8);

    root.right = new TreeNode(3);
    root.right.right = new TreeNode(6);
//    root.right.right.right = new TreeNode(11);
    root.right.left = new TreeNode(5);
    root.right.left.left = new TreeNode(9);
    root.right.left.right = new TreeNode(10);

    return root;
  }

  private TreeNode oneSidedTree() {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.left.left = new TreeNode(4);
    root.left.left.left.left = new TreeNode(5);
    return root;
  }
}

class PsuedoTuple {
  public TreeNode node;
  public int index;
  public PsuedoTuple(TreeNode node, int index) {
    this.node = node;
    this.index = index;
  }
}
