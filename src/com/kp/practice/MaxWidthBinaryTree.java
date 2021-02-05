package com.kp.practice;

import com.kp.practice.commontypes.TreeNode;

import java.util.*;


// 662: https://leetcode.com/problems/maximum-width-of-binary-tree/
// Status: Passes tests but isn't optimized for space yet, 'memory limit exceeded'
public class MaxWidthBinaryTree implements TestCase {

  public int solution(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int result = 0;
    Deque<Optional<TreeNode>> queue = new ArrayDeque<>();
    queue.add(Optional.of(root));
    int index = 1;
    int currentLevel = 1;

    while (!queue.isEmpty()) {
      var prevLevel = currentLevel;
      currentLevel = (int) (Math.log(index) / Math.log(2));
      boolean isEndOfRow = prevLevel != currentLevel;
      if (isEndOfRow) {
        if (isEmptyRow(queue)) {
          break;
        }
        int actualWidth = getActualWidth(queue);
        if (actualWidth > result) {
          result = actualWidth;
        }
      }

      var currentOpt = queue.remove();
      if (currentOpt.isPresent()) {
        var current = currentOpt.get();
        if (current.left != null) {
          queue.add(Optional.of(current.left));
        } else {
          queue.add(Optional.ofNullable(null));
        }
        if (current.right != null) {
          queue.add(Optional.of(current.right));
        } else {
          queue.add(Optional.ofNullable(null));
        }
      } else {
        queue.add(Optional.ofNullable(null));
        queue.add(Optional.ofNullable(null));
      }
      index++;

    }
    return result;
  }

  private boolean isEmptyRow(Deque<Optional<TreeNode>> queue) {
    return queue
        .stream()
        .allMatch(node -> !node.isPresent());
  }

  private int getActualWidth(Deque<Optional<TreeNode>> nodes) {
    boolean hasNodeAtStart = false;
    int width = 0;
    int nullAccum = 0;
    for (Optional<TreeNode> node : nodes) {
      if (!hasNodeAtStart && !node.isPresent()) {
        continue;
      } else if (hasNodeAtStart && !node.isPresent()) {
        nullAccum++;
        continue;
      } else if (nullAccum > 0 && node.isPresent()) {
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
