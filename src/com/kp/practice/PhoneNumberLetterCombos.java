package com.kp.practice;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.List;
import java.util.Map;

public class PhoneNumberLetterCombos implements TestCase {
  public List<String> solution(String digits) {
    return List.of();
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
