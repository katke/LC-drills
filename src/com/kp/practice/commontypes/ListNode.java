package com.kp.practice.commontypes;

// LeetCode given type definition for singly-linked list
public class ListNode {
     public int val;
     public ListNode next;
     public ListNode() {}
     public ListNode(int val) { this.val = val; }
     public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

     public String toLLStringRepresentation() {
          var strBuilder = new StringBuilder();
          strBuilder.append(this.val);
          var nextNode = this.next;
          if (nextNode == null) return strBuilder.toString();
          while (nextNode != null) {
               strBuilder
                   .append(" -> ")
                   .append(nextNode.val);
               nextNode = nextNode.next;
          }
          return strBuilder.toString();
     }
}