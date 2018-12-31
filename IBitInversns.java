package ibitinversns;

public class IBitInversns {

    public static void main(String[] args) {
        AvlTree tree = new AvlTree(); 
  
        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 2); 
        tree.root = tree.insert(tree.root, 4); 
        tree.root = tree.insert(tree.root, 1); 
        tree.root = tree.insert(tree.root, 3); 
        tree.root = tree.insert(tree.root, 5); 
        tree.root = tree.insert(tree.root, 6); 
        tree.root = tree.insert(tree.root, 0); 
//        tree.root = tree.insert(tree.root, 25); 
  
        /* The constructed AVL Tree would be 
             30 
            /  \ 
          20   40 
         /  \     \ 
        10  25    50 
        */
//        System.out.println("Preorder traversal" + 
//                        " of constructed tree is : "); 
//        tree.preOrder(tree.root); 
        System.out.println("\n inversion count= "+tree.count);
    }
}
class AvlTree
{
    AvlTreeNode root;
    int count=0;
    int height(AvlTreeNode N) { 
        if (N == null) 
            return 0; 
  
        return N.height; 
    } 
  
    // A utility function to get maximum of two integers 
    int max(int a, int b) { 
        return (a > b) ? a : b; 
    } 
  
    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
    AvlTreeNode rightRotate(AvlTreeNode y) { 
        AvlTreeNode x = y.left; 
        AvlTreeNode T2 = x.right; 
  
        // Perform rotation 
        x.right = y; 
        y.left = T2; 
  
        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
  
        // Return new root 
        return x; 
    } 
  
    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
    AvlTreeNode leftRotate(AvlTreeNode x) { 
        AvlTreeNode y = x.right; 
        AvlTreeNode T2 = y.left; 
  
        // Perform rotation 
        y.left = x; 
        x.right = T2; 
  
        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        // Return new root 
        return y; 
    } 
  
    // Get Balance factor of node N 
    int getBalance(AvlTreeNode N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
  
    AvlTreeNode insert(AvlTreeNode node, int key) { 
//        System.out.println("key= "+key);
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new AvlTreeNode(key)); 
  
        if (key < node.key) 
        {
            count+=1;
            count+=node.rightNodesSize;
            System.out.println("count increased for "+node.key+" for key= "+key);
            node.leftNodesSize+=1;
            node.left = insert(node.left, key);
        } 
        else if (key > node.key) 
        {
            node.right = insert(node.right, key);
            node.rightNodesSize+=1;
        } 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && key < node.left.key) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && key > node.right.key) 
            return leftRotate(node);
  
        // Left Right Case 
        if (balance > 1 && key > node.left.key) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && key < node.right.key) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    } 
    public static  void preOrder(AvlTreeNode node) { 
        if (node != null) { 
            System.out.print(node.key + " "); 
            preOrder(node.left); 
            preOrder(node.right); 
        }
    } 
}
class AvlTreeNode
{
    public int key,height;
    public AvlTreeNode left,right;
    int leftNodesSize,rightNodesSize;
    public AvlTreeNode(int key) {
        this.key=key;
        height=1;
        leftNodesSize=0;
        rightNodesSize=0;
    }
    
}