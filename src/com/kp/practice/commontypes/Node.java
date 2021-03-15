package com.kp.practice.commontypes;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Node {
  public int val;
  public List<Node> neighbors;
  public Node() {
    val = 0;
    neighbors = new ArrayList<>();
  }
  public Node(int _val) {
    val = _val;
    neighbors = new ArrayList<>();
  }
  public Node(int _val, ArrayList<Node> _neighbors) {
    val = _val;
    neighbors = _neighbors;
  }

  @Override
  public String toString() {
    var strBuilder = new StringBuilder();
    strBuilder.append("\n");
    var stack = new ArrayDeque<Node>();
    stack.add(this);
    var visited = new ArrayList<Node>();
    while (!stack.isEmpty()) {
      var currentNode = stack.pop();
      if (!visited.contains(currentNode)) {
        strBuilder.append("Node: ")
            .append(currentNode.val);
        visited.add(currentNode);
        strBuilder.append(", neighbors: [ ");
        for (Node neighbor : currentNode.neighbors) {
          strBuilder.append(neighbor.val).append(" ");
          if (!visited.contains(neighbor)) {
            stack.push(neighbor);
          }
        }
        strBuilder.append("]\n");
      }
    }
    return strBuilder.toString();
  }
}
