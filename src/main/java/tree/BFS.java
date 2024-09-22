package tree;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BFS {

    // Метод для обхода дерева в ширину (BFS)
    public static void breadthFirstSearch(TreeNode root) {
        if (root == null) {
            return; // Если дерево пустое, ничего не делаем
        }

        Queue<TreeNode> queue = new LinkedList<>(); // Очередь для хранения узлов
        queue.add(root); // Добавляем корень в очередь

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll(); // Извлекаем первый узел из очереди

            // Обрабатываем текущий узел (можем выводить его значение или делать другую обработку)
            System.out.print(currentNode.val + " ");

            // Добавляем дочерние узлы текущего узла в очередь, если они существуют
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        // Создаем простое дерево для тестирования
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);


        // Обход дерева в ширину и вывод на экран
        System.out.println("Обход дерева в ширину:");
        breadthFirstSearch(root);
    }
}

