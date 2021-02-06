package com.kp.practice;

import com.kp.practice.commontypes.TreeNode;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        var minSubstring = new MinimumSubstring();
        for (Map.Entry<String, List<String>> testCase : minSubstring.getTestCases().entrySet()) {
            var description = testCase.getKey();
            var params = testCase.getValue();
            System.out.println("-------------------");
            var actual = minSubstring.solution(params.get(0), params.get(1));
            System.out.println(description + ", actual: " + actual);
        }

    }
}
