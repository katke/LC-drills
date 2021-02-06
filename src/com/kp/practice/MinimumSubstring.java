package com.kp.practice;

import java.util.*;
// 76 https://leetcode.com/problems/minimum-window-substring/
// Status: brute force, not optimized
public class MinimumSubstring implements TestCase {

  public String solution(String s, String t) {
    if (s == null || t == null) {
      return "";
    }
    if (s.length() < t.length()
        || s.length() == 0
        || t.length() == 0) return "";

    var l = 0;
    var r = t.length();
    var result = "";
//    int tCodepointsTotal = t.codePoints().sum();
//    Map<Character, Integer> tMap = setTMap(t);
    List<Character> tList = setTList(t);
//    System.out.println("tCodepointsTotal: " + tCodepointsTotal);
//    Set<Integer> tSet = getTCodepointsSet(t);
    while (r < s.length()) {
      var currentWindow = s.substring(l, r + 1);
      var newChar = s.charAt(r);
      tList.remove((Character) newChar);
//      System.out.println("Current window: " + currentWindow);
//      System.out.println("l: " + s.charAt(l) + ", r: " + s.charAt(r));
//      System.out.println("tList: " + tList.toString());

      if (tList.isEmpty()) {
//        System.out.println("All chars accounted for");
        tList = setTList(t);
        if (result.length() == 0 || currentWindow.length() < result.length()) {
//          System.out.println("Setting result to new window");
          result = currentWindow;
        }
        l++;
        r = l;
      } else {
        r++;
      }
      // keep incrementing r until matching window is found
      // once it is, increment l until the window is no longer desirable
      // then increment r, and repeat
    }
    return result;
  }

  private boolean allCharsAccountedFor(Map<Character, Integer> tMap) {
    var remaining = tMap
        .values()
        .stream()
        .reduce(0, (subtotal, next) -> subtotal + next);
    return remaining == 0;
  }

  private Map<Character, Integer> setTMap(String t) {
    Map<Character, Integer> tMap = new HashMap<>();
    for (var i = 0; i < t.length(); i++) {
      var charKey = t.charAt(i);
      tMap.compute(charKey, (k, v) -> v == null ? 1 : v + 1);
    }
    return tMap;
  }

  private List<Character> setTList(String t) {
    List<Character> tList = new ArrayList<>();
    for (var i = 0; i < t.length(); i++) {
      var currentChar = t.charAt(i);
      tList.add(currentChar);
    }
    return tList;
  }

  public Map<String, List<String>> getTestCases() {
    return Map.of(
        "Expected: BANC", List.of("ADOBECODEBANC", "ABC"),
        "Expected: \"\"", List.of("ABC", "ABCD"),
        "Expected: \" \"", List.of("a", "b"),
        "Expected: a", List.of("a", "a"),
        "Expected: bCBA", List.of("ABCODEBaNbCBA", "AbC"),
        "Expected: HEr", List.of("talkeHandHErRzJrARRHNDeoRRRRzalkje", "Hr")
    );
  }
}
