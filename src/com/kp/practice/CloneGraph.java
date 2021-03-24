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
    return Map.of("1. Expected [[2,4],[1,3],[2,4],[1,3]]", lcTestCase1());
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

    return n4;
  }
}

//Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//    Output: [[2,4],[1,3],[2,4],[1,3]]
//    Explanation: There are 4 nodes in the graph.
//    1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//    2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//    3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//    4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
