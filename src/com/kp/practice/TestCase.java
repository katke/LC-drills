package com.kp.practice;

import java.util.Map;

public interface TestCase {
  default Map<?, ?> getTestCases() {
    return Map.of();
  }
//  Object solution();
}
