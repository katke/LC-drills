package com.kp.practice;

import java.util.List;
import java.util.Map;

// 159 https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
public class LongestSubstringTwoChars implements TestCase {

  public int solution(String t) {
    return 0;
  }

  public Map<String, List<String>> getTestCases() {
    return Map.of(
        "1. Expected 3", List.of("eceba"),
        "2. Expected 5", List.of("ccaabbb"),
        "3. Expected 0", List.of(""),
        "4. Expected 0", List.of("bbbbbbbbbbbbb"),
        "5. Expected 3", List.of("cccccccCabb"),
        "6. Expected 10", List.of("yyyyyyyyywyyyyyyyyalksdjfaaaaaaaa")
    );
  }
}
