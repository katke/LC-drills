package com.kp.practice;

import java.util.Map;

public interface TestCase {
  default Map<?, ?> getTestCases() {
    return Map.of();
  }
  default Object solution() {
    throw new RuntimeException("No solution method implemented");
  }
}
