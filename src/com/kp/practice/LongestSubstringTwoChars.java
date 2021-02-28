package com.kp.practice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
// Status: Accepted!
public class LongestSubstringTwoChars implements TestCase {

  public int solution(String t) {
    if (t == null) {
      return 0;
    } else if (t.length() < 2) {
      return t.length();
    }
    Map<Character, Integer> tMap = new HashMap<>();
    var l = 0;
    var r = 1;
    updateCountMap(t.charAt(l), tMap, true);
    var max = 2;
    while (r <= t.length()) {
      if (tMap.keySet().size() > 2) {
        // condition is now broken
        // increment left
        updateCountMap(t.charAt(l), tMap, false);
        l++;
      } else {
        // keep incrementing right
        var possibleNewMax = tMap.values().stream().reduce(0, (subtotal, next) -> subtotal + next);
        max = Math.max(max, possibleNewMax);
        if (r == t.length()) break;
        updateCountMap(t.charAt(r), tMap, true);
        r++;
      }
    }
    return max;
  }

  private void updateCountMap(Character c, Map<Character, Integer> tMap, boolean add) {
    if (add) {
      tMap.compute(c, (k, v) -> v == null ? 1 : v + 1);
    } else {
      var currentVal = tMap.getOrDefault(c, -1);
      if (currentVal > 1) {
        tMap.put(c, currentVal - 1);
      } else {
        tMap.remove(c);
      }
    }
  }

  public Map<String, List<String>> getTestCases() {
    return Map.of(
        "1. Expected 3", List.of("eceba"),
        "2. Expected 5", List.of("ccaabbb"),
        "3. Expected 0", List.of(""),
        "4. Expected 13", List.of("bbbbbbbbbbbbb"),
        "5. Expected 9", List.of("cccccccCcabbbbbbBbb"),
        "6. Expected 18", List.of("yyyyyyyyywyyyyyyyyalksdjfaaaaaaaa"),
        "7. Expected 1", List.of("y"),
        "8. Expected 7", List.of("yyyyaaab")
    );
  }
}
