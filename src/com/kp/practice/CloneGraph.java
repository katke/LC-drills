package com.kp.practice;

import com.kp.practice.commontypes.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// 133 https://leetcode.com/problems/clone-graph/
public class CloneGraph implements TestCase {
  public Node solution(Node node) {
    if (node == null) {
      return node;
    }
    var stack = new ArrayDeque<Node>();
    stack.push(node);
    var cloneNodeMap = new HashMap<Integer, Node>();
    // cloneNodeMap as source of truth for new node clone objects
    while (!stack.isEmpty()) {
      var ogCurrentNode = stack.pop();
      if (!cloneNodeMap.containsKey(ogCurrentNode.val)) {
        cloneNodeMap.put(ogCurrentNode.val, new Node(ogCurrentNode.val));
      }
      var cloneNeighbors = new ArrayList<Node>();
      for (Node neighbor : ogCurrentNode.neighbors) {
        if (!cloneNodeMap.containsKey(neighbor.val)) {
          cloneNodeMap.put(neighbor.val, new Node(neighbor.val));
        }
        var neighborCloneNode = cloneNodeMap.get(neighbor.val);
        if (neighborCloneNode.neighbors == null || neighborCloneNode.neighbors.isEmpty()) {
          // We haven't visited this node yet
          stack.push(neighbor);
        }
        cloneNeighbors.add(cloneNodeMap.get(neighbor.val));
      }
      cloneNodeMap.get(ogCurrentNode.val).neighbors = cloneNeighbors;
    }
    return cloneNodeMap.get(node.val);
  }

  public Map<String, Node> getTestCases() {
    return Map.of("1. Expected [[2,4],[1,3],[2,4],[1,3]]", lcTestCase1(),
        "2. Expected [[3,5],[3],[1,2,4],[3],[1]]", testCase2(),
        "3. Expected [[2],[1,3],[2,4,5,6,7],[3],[3],[3],[3]]", testCase3());
  }

  private Node lcTestCase1() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);

    n1.neighbors.add(n2);
    n1.neighbors.add(n4);

    n2.neighbors.add(n1);
    n2.neighbors.add(n3);

    n3.neighbors.add(n2);
    n3.neighbors.add(n4);

    n4.neighbors.add(n1);
    n4.neighbors.add(n3);

    return n1;
  }

  private Node testCase2() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);

    n1.neighbors.add(n5);
    n1.neighbors.add(n3);

    n2.neighbors.add(n3);

    n3.neighbors.add(n1);
    n3.neighbors.add(n2);
    n3.neighbors.add(n4);

    n4.neighbors.add(n3);

    n5.neighbors.add(n1);

    return n1;
  }

  private Node testCase3() {
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);
    Node n6 = new Node(6);
    Node n7 = new Node(7);

    n1.neighbors.add(n2);

    n2.neighbors.add(n1);
    n2.neighbors.add(n3);

    n3.neighbors.add(n2);
    n3.neighbors.add(n4);
    n3.neighbors.add(n5);
    n3.neighbors.add(n6);
    n3.neighbors.add(n7);

    n4.neighbors.add(n3);

    n5.neighbors.add(n3);

    n6.neighbors.add(n3);

    n7.neighbors.add(n3);

    return n1;
  }
}
