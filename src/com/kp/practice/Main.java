package com.kp.practice;

import com.kp.practice.commontypes.ListNode;
import com.kp.practice.commontypes.Node;
import com.kp.practice.commontypes.TreeNode;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        var lcClass = new MaxProfit();
        for (Map.Entry<String, ?> testCase : lcClass.getTestCases().entrySet()) {
            var description = testCase.getKey();
            var params = (List<int[]>) testCase.getValue();
            System.out.println("-------------------");
            var actual = lcClass.solution(params.get(0), params.get(1), params.get(2));
            System.out.println(description + ", actual: " + actual);
        }

    }
}
