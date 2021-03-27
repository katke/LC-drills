package com.kp.practice;

import com.kp.practice.commontypes.TreeNode;

import java.util.Map;

// 104 https://leetcode.com/problems/maximum-depth-of-binary-tree/
public class MaximumDepthBinaryTree implements TestCase {
  public int solution(TreeNode root) {
    return 0;
  }

  @Override
  public Map<String, TreeNode> getTestCases() {
    return Map.of(
        "1. LC test case", lcTestCase1(),
        "2. Expected 1", singleNode()
    );
  }

  private TreeNode lcTestCase1() {
    var root = new TreeNode(3);

    root.right = new TreeNode(20);
    root.left = new TreeNode(9);

    root.right.right = new TreeNode(7);
    root.right.left = new TreeNode(15);
    return root;
  }

  private TreeNode singleNode() {
    return new TreeNode(1);
  }


}
