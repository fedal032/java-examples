package leetcode.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) { //time complexity O(N)
        if (root == null)
            return true;

        Queue<TreeNode> queue = new LinkedList<>();     // space complexity O(N)
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null)
                continue;

            if (left == null || right == null)
                return false;

            if (left.val != right.val)
                return false;

            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;

    }
}
