package leetcode.trees;

import java.util.*;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        Queue<TreeNode> currentLevel = new LinkedList<>();
        Queue<TreeNode> childrenLevel = new LinkedList<>();

        currentLevel.offer(root);

        while (!currentLevel.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            while (!currentLevel.isEmpty()) {
                TreeNode node = currentLevel.poll();
                level.add(node.val);

                if (node.left != null)
                    childrenLevel.add(node.left);
                if (node.right != null)
                    childrenLevel.add(node.right);
            }

            if (!childrenLevel.isEmpty()) {
                currentLevel.addAll(childrenLevel);
                childrenLevel.clear();
            }
            res.add(level);
        }

        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        Queue<TreeNode> treeNodes = new ArrayDeque<>();

        Queue<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.offer(root);

        while (!currentLevel.isEmpty()) {

            List<Integer> level = new ArrayList<>();
            int levels = currentLevel.size(); //изначальный размер очереди
            for (int i = 0; i < levels; i++) {              //пошли с начала
                TreeNode currNode = currentLevel.poll();    //Забрали элемент
                level.add(currNode.val);
                if (currNode.left != null) {
                    currentLevel.offer(currNode.left);      // положили в конец
                }
                if (currNode.right != null) {
                    currentLevel.offer(currNode.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}
