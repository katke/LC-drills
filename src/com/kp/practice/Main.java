package com.kp.practice;

import com.kp.practice.commontypes.TreeNode;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        var maxWithBinaryTree = new MaxWidthBinaryTree();
        for (Map.Entry<String, List<TreeNode>> testCase : maxWithBinaryTree.getTestCases().entrySet()) {
            var description = testCase.getKey();
            var params = testCase.getValue();
            System.out.println("-------------------");
            System.out.println(description);
            System.out.println(maxWithBinaryTree.solution(params.get(0)));
        }

    }
}
