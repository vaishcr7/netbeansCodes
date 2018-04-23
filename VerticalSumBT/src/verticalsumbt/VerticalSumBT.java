package verticalsumbt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerticalSumBT {

    public static void main(String[] args) {
        BinaryTree root = new BinaryTree(0);
        BinaryTree node1 = new BinaryTree(1);
        BinaryTree node2 = new BinaryTree(2);
        BinaryTree node3 = new BinaryTree(3);
        BinaryTree node4 = new BinaryTree(4);
        BinaryTree node5 = new BinaryTree(5);
        BinaryTree node6 = new BinaryTree(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(vertSum(root));
    }

    public static ArrayList<Integer> vertSum(BinaryTree root) {
        ArrayList<Integer> sum = new ArrayList<>();
        Map<Integer, ArrayList<BinaryTree>> mp = new HashMap<>();
        int ind = 0;
        if (root == null) {
            return sum;
        }
        mp = getMap(mp, root, ind);
        for( ArrayList<BinaryTree> list: mp.values())
        {
            int sumN=0;
            for(BinaryTree node: list)
                sumN+=node.data;
            sum.add(sumN);
        }        
        return sum;
    }

    public static Map<Integer, ArrayList<BinaryTree>> getMap(Map<Integer, ArrayList<BinaryTree>> mp, BinaryTree root, int ind) {
        if (!mp.containsKey(ind)) {
            ArrayList<BinaryTree> l = new ArrayList<>();
            l.add(root);
            mp.put(ind, l);
        } 
        else {
            ArrayList<BinaryTree> l = mp.get(ind);
            l.add(root);
            mp.put(ind, l);
        }
        if (root.left != null) {
            mp = getMap(mp, root.left, ind - 1);
        }
        if (root.right != null) {
            mp = getMap(mp, root.right, ind + 1);
        }
        return mp;
    }
}

class BinaryTree {

    public int data;
    public BinaryTree left, right;

    public BinaryTree(int data) {
        this.data = data;
        left = null;
        right = null;
    }

}
