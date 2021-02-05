package com.kp.practice;
import com.kp.practice.commontypes.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;


// 662: https://leetcode.com/problems/maximum-width-of-binary-tree/
// Status: Passes tests but isn't optimized for space yet
public class MaxWidthBinaryTree {

    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int result = 0;
            Deque<Optional<TreeNode>> queue = new ArrayDeque<>();
            queue.add(Optional.of(root));
            int index = 1;
            int currentLevel = 1;

            while (!queue.isEmpty()) {
                var prevLevel = currentLevel;
                currentLevel = (int)(Math.log(index) / Math.log(2));
                boolean isEndOfRow = prevLevel != currentLevel;
                if (isEndOfRow) {
                    if (isEmptyRow(queue)) {
                        break;
                    }
                    int actualWidth = getActualWidth(queue);
                    if (actualWidth > result) {
                        result = actualWidth;
                    }
                }

                var currentOpt = queue.remove();
                if (currentOpt.isPresent()) {
                    var current = currentOpt.get();
                    if (current.left != null) {
                        queue.add(Optional.of(current.left));
                    } else {
                        queue.add(Optional.ofNullable(null));
                    }
                    if (current.right != null) {
                        queue.add(Optional.of(current.right));
                    } else {
                        queue.add(Optional.ofNullable(null));
                    }
                } else {
                    queue.add(Optional.ofNullable(null));
                    queue.add(Optional.ofNullable(null));
                }
                index++;

            }
            return result;
        }

        private boolean isEmptyRow(Deque<Optional<TreeNode>> queue) {
            return queue
                    .stream()
                    .allMatch(node -> !node.isPresent());
        }

        private int getActualWidth(Deque<Optional<TreeNode>> nodes) {
            boolean hasNodeAtStart = false;
            int width = 0;
            int nullAccum = 0;
            for (Optional<TreeNode> node : nodes) {
                if (!hasNodeAtStart && !node.isPresent()) {
                    continue;
                } else if (hasNodeAtStart && !node.isPresent()) {
                    nullAccum++;
                    continue;
                } else if (nullAccum > 0 && node.isPresent()) {
                    width += nullAccum;
                    nullAccum = 0;
                }
                width++;
                hasNodeAtStart = true;
            }
            return width;
        }
    }
}
