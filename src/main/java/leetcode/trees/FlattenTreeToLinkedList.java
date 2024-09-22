package leetcode.trees;

import java.util.LinkedList;
import java.util.List;

public class FlattenTreeToLinkedList {

    public void flattenInPlace(TreeNode root) {
        if (root == null)
            return;

        TreeNode cur = root;

        while (cur != null) {
            if (cur.left != null) {
                TreeNode last = cur.left;
                while (last.right != null) {
                    last = last.right;
                }

                last.right = cur.right; //переносим поддерево справа в конец правой части левого поддерева
                cur.right = cur.left;   //переносим поддерево вправов
                cur.left = null;
            }

            cur = cur.right; // после трансформации шагаем вправо
        }
    }

    public void flattenWithQueue(TreeNode root) {
        if (root == null)
            return;
        List<TreeNode> queue = new LinkedList<>();
        traversePreOrder(queue, root); //обходи в preOrder

        for (int i = 0; i < queue.size(); i++) {
            TreeNode tmp = queue.get(i);
            tmp.left = null;
            if (i < queue.size() - 1)
                tmp.right = queue.get(i + 1);

        }

    }

    private void traversePreOrder(List<TreeNode> queue, TreeNode node) {
        queue.add(node);
        if (node.left != null)
            traversePreOrder(queue, node.left);
        if (node.right != null)
            traversePreOrder(queue, node.right);
    }
}
