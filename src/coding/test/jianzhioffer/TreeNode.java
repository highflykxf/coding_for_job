package coding.test.jianzhioffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }

    public static TreeNode createBinaryTree(int[] arr) {
        List<TreeNode> nodeList = new LinkedList<TreeNode>();
        // 将数组的值转换为node
        for (int node_index = 0; node_index < arr.length; node_index++) {
            nodeList.add(new TreeNode(arr[node_index]));
        }
        // 对除最后一个父节点按照父节点和孩子节点的数字关系建立二叉树
        for (int parent_index = 0; parent_index < arr.length / 2 - 1; parent_index++) {
            nodeList.get(parent_index).left = nodeList.get(parent_index * 2 + 1);
            nodeList.get(parent_index).right = nodeList.get(parent_index * 2 + 2);
        }
        int last_parent_index = arr.length / 2 - 1;
        nodeList.get(last_parent_index).left = nodeList.get(last_parent_index * 2 + 1);
        if (arr.length % 2 == 1)
            nodeList.get(last_parent_index).right = nodeList.get(last_parent_index * 2 + 2);
        return nodeList.get(0);
    }

    public static void preOrderTraverse(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public static void inOrderTraverse(TreeNode root) {
        if (root == null)
            return;
        inOrderTraverse(root.left);
        System.out.print(root.val + " ");
        inOrderTraverse(root.right);
    }

    public static void postOrderTraverse(TreeNode root) {
        if (root == null)
            return;
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.val + " ");
    }

    public static ArrayList<ArrayList<Integer>> levelOrderTraverse(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            list.add(node.val);
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
            res.add(list);
        }
        return res;
    }

    public static ArrayList<ArrayList<Integer>> zigzagOrderTraverse(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        ArrayList<Integer> level_list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(null);// 层分隔符
        queue.addLast(root);
        boolean leftToRight = true;
        while (queue.size() != 1) {
            TreeNode cur_node = queue.removeFirst();
            if (cur_node == null) {
                Iterator<TreeNode> iter = null;
                if (leftToRight)
                    iter = queue.iterator();
                else
                    iter = queue.descendingIterator();
                leftToRight = !leftToRight;
                while (iter.hasNext()) {
                    TreeNode tmp = (TreeNode) iter.next();
                    level_list.add(tmp.val);
                }
                res.add(new ArrayList<Integer>(level_list));
                level_list.clear();
                queue.addLast(null);
                continue;
            }
            if (root.left != null)
                queue.addLast(root.left);
            if (root.right != null)
                queue.addLast(root.right);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        TreeNode binary_tree = createBinaryTree(arr);
        preOrderTraverse(binary_tree);
        System.out.println();
        inOrderTraverse(binary_tree);
        System.out.println();
        postOrderTraverse(binary_tree);
        System.out.println();
        ArrayList<ArrayList<Integer>> level_traverse_res = levelOrderTraverse(binary_tree);
        System.out.println();
    }
}
