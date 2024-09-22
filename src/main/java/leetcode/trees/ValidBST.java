package leetcode.trees;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidBST {
    long prev = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;

        if (!isValidBST(root.left))
            return false;

        if (prev >= root.val)
            return false;

        prev = root.val;

        if (!isValidBST(root.right))
            return false;

        return true;
    }

    public boolean isValidBSTUsingStack(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;

        while (!stack.isEmpty() || root != null) {
            // набриаем все слева
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }
}
