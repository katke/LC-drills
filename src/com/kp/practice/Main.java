package com.kp.practice;

import com.kp.practice.commontypes.ListNode;
import com.kp.practice.commontypes.Node;
import com.kp.practice.commontypes.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        var lcClass = new Search2DMatrix();
        for (Map.Entry<String, ?> testCase : lcClass.getTestCases().entrySet()) {
            var description = testCase.getKey();
            List<?> params = (List<?>) testCase.getValue();
            System.out.println("-------------------");
            var actual = lcClass.solution((int[][]) params.get(0), (int) params.get(1));
            System.out.println(description + ", actual: " + actual);
        }

    }
}
