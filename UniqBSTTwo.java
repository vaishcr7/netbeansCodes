/*
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
*/

package leetcodeuniqstructbintrees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class LeetcodeUniqStructBinTrees {

    public static void main(String[] args) {
        List<TreeNode> anp = (new LeetcodeUniqStructBinTrees().generateTrees(6));
        anp.forEach((treeNode) -> {
            preorder(treeNode);
        });
    }

    static void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayDeque<TreeNode> aq = new ArrayDeque<>();
        aq.push(root);
        while (!aq.isEmpty()) {
            TreeNode p = aq.pop();
            System.out.print(p.val + " , ");
            if (p.right != null) {
                aq.push(p.right);
            }
            if (p.left != null) {
                aq.push(p.left);
            }
        }
        System.out.println("");
    }

    List<TreeNode> generateTrees(int n) {
        return work(1, n);
    }

    List<TreeNode> work(int st, int end) {
//        System.out.println("st= "+st+" and end= "+end);
        List<TreeNode> ans = new ArrayList<>();
        if (st > end) {
            return ans;
        }
        for (int i = st; i <= end; i++) {
            List<TreeNode> left = work(st, i - 1);
            List<TreeNode> right = work(i + 1, end);
            if (left.isEmpty() && right.isEmpty()) {
                TreeNode root = new TreeNode(i);
                ans.add(root);
            }
//            System.out.println("for i= "+i+" left-> "+left.size()+" and rght-> "+right.size());
            else if (!left.isEmpty() && !right.isEmpty()) {
                for (TreeNode treeNode : left) {
                    for (TreeNode treeNode1 : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = treeNode;
                        root.right = treeNode1;
                        ans.add(root);
                    }
                }
            } 
            else if(left.isEmpty() && !right.isEmpty()) {
                for (TreeNode treeNode : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = null;
                        root.right = treeNode;
                        ans.add(root);
                }
            }
            else{
                for (TreeNode treeNode : left) {
                        TreeNode root = new TreeNode(i);
                        root.right = null;
                        root.left = treeNode;
                        ans.add(root);
                }
            }
        }
        return ans;
    }
}

class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
