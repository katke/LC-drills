package com.kp.practice;

import com.kp.practice.commontypes.ListNode;
import com.kp.practice.commontypes.Node;
import com.kp.practice.commontypes.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        var lcClass = new PhoneNumberLetterCombos();
        for (Map.Entry<String, ?> testCase : lcClass.getTestCases().entrySet()) {
            var description = testCase.getKey();
            String params = (String) testCase.getValue();
            System.out.println("-------------------");
            var actual = lcClass.solution(params);
            System.out.println(description + ", actual: " + actual.toString());
        }

    }
}
