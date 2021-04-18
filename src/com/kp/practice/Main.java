package com.kp.practice;

import com.kp.practice.commontypes.ListNode;
import com.kp.practice.commontypes.Node;
import com.kp.practice.commontypes.TreeNode;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        var lcClass = new MergeTwoSortedArrays();
        for (Map.Entry<String, List<ListNode>> testCase : lcClass.getTestCases().entrySet()) {
            var description = testCase.getKey();
            var params = testCase.getValue();
            System.out.println("-------------------");
            var actual = lcClass.solution(params.get(0), params.get(1));
            System.out.println(description + ", actual: " + actual.toStringRepresentation());
        }

    }
}
