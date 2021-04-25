package com.kp.practice;

// 17. https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PhoneNumberLetterCombos implements TestCase {
  private static final Map<String, List<String>> digitMap = Map.of(
      "2", List.of("a", "b", "c"),
      "3", List.of("d", "e", "f"),
      "4", List.of("g", "h", "i"),
      "5", List.of("j", "k", "l"),
      "6", List.of("m", "n", "o"),
      "7", List.of("p", "q", "r", "s"),
      "8", List.of("t", "u", "v"),
      "9", List.of("w", "x", "y", "z")
      );
  public List<String> solution(String digits) {
    if (digits.length() == 0) return List.of();
    var digitArr = digits.split("");
    List<String> results = new ArrayList<>();
    // Add empty string required to ensure it iterates in addCombinations
    results.add("");
    for (var digit : digitArr) {
      results = addCombinations(digit, results);
    }
    return results;
  }

  // [a, a, a, b, b, b, c, c, c]
  // [ad, ae, af, bd, be, bf, cd, ce, cf]

  private List<String> addCombinations(String digit, List<String> currentResults) {
    var letters = digitMap.get(digit);
      List<String> updatedResults = new ArrayList<>();
      for (var letter : letters) {
        // for each letter corresponding to the digit, iterate through all existing options
        for (var result : currentResults) {
          // for each existing result, append our current letter to it
          // in first iteration, just appends letter to empty string
          updatedResults.add(result + letter);
        }
      }
      return updatedResults;
  }

  public Map<String, String> getTestCases() {
    return Map.of(
        "1. Expected: [ad,ae,af,bd,be,bf,cd,ce,cf]", "23",
        "2. Expected: []", "",
        "3. Expected: [a,b,c]", "2",
        "4. Expected: [jpm, jpn, jpo, jqm, jqn, jqo, jrm, jrn, jro, jsm, jsn, jso, kpm, kpn, kpo, kqm, kqn, kqo, krm, krn, kro, ksm, ksn, kso, lpm, lpn, lpo, lqm, lqn, lqo, lrm, lrn, lro, lsm, lsn, lso]", "576"
    );
  }

}
