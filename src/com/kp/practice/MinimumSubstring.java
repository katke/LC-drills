package com.kp.practice;

import java.util.*;

// 76 https://leetcode.com/problems/minimum-window-substring/
// Status: ARGH 256/266 tests passing but getting time limit exceeded on final one
public class MinimumSubstring implements TestCase {

  public String solution(String s, String t) {
    if (s == null || t == null) return "";
    if (s.length() < t.length()
        || s.length() == 0
        || t.length() == 0) return "";
    if (s.equals(t)) return s;

    var l = 0;
    var r = t.length();
    var result = "";

    while (r <= s.length()) {
      var currentWindowDesirable = allCharsAccountedFor(t, s, l, r);
      if (!currentWindowDesirable) {
        r++;
      } else {
        if (r - l < result.length() || result.length() == 0) {
          result = s.substring(l, r);
        }
        l++;
      }
    }
    return result;
  }

  private boolean allCharsAccountedFor(String t, String s, int left, int right) {
    var tList = setTList(t);
    var window = s.substring(left, right);
    for (var i = 0; i < window.length(); i++) {
      Character character = window.charAt(i);
      tList.remove(character);
      if (tList.isEmpty()) break;
    }
    return tList.isEmpty();
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