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
    var visitedNodes = new ArrayList<Node>();
    stack.push(node);
    var cloneMap = new HashMap<Integer, Node>();
    // Creating hashmap with base new nodes, no neighbors yet
    // TODO combine stack iterations into one in refactor
    while (!stack.isEmpty()) {
      var currentNode = stack.pop();
//      visitedNodes.add(currentNode);
      if (!cloneMap.containsKey(currentNode.val)) {
        cloneMap.put(currentNode.val, new Node(currentNode.val));
      }

      for (Node neighbor : currentNode.neighbors) {
        if (!cloneMap.containsKey(neighbor.val)) {
          stack.push(neighbor);
//          cloneMap.put(neighbor.val, new Node(neighbor.val));
        }
      }
    }
    System.out.println(cloneMap.keySet());
    // Now that we've created all the new nodes, simply add the neighbors
    // via the node clone objects in the hashmap
    // TODO combine stack iterations into one in refactor
    stack.push(node);
    visitedNodes = new ArrayList<>();
    while (!stack.isEmpty()) {
      var currentNode = stack.pop();
      System.out.println(currentNode.val);
      if (!visitedNodes.contains(currentNode)) {
        var currentNodeClone = cloneMap.get(currentNode.val);
        var cloneNeighbors = new ArrayList<Node>();
        for (Node neighbor : currentNode.neighbors) {
          cloneNeighbors.add(cloneMap.get(neighbor.val));
          if (!visitedNodes.contains(neighbor)) {
            stack.push(neighbor);
          }
        }
        currentNodeClone.neighbors = cloneNeighbors;
        visitedNodes.add(currentNode);
      }
    }

    return cloneMap.get(node.val);
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
