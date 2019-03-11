import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public void inOrderTraversalRecursive(TreeNode<T> node, ArrayList<T> list) {
        if(node != null){
            inOrderTraversalRecursive(node.leftChild, list);
            list.add(node.key);
            inOrderTraversalRecursive(node.rightChild, list);
        }

    }

    public List<T> inOrderTraversal() {
        ArrayList<T> outputs = new ArrayList<T>();
        inOrderTraversalRecursive(root, outputs);
        return outputs;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        else {
            // Case 3: two children
            replacement = findPredecessor(n);
            delete(replacement);
            replacement.moveChildrenFrom(n);

            //replacement = n.leftChild;
            //replacement = null;
        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the predecessor TreeNode by calling the function you will implement below
            TreeNode<T> predecessor = findPredecessor(n);
            // return the key of predecessor TreeNode
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        // finds and returns the TreeNode with key = key if such a TreeNode exists in the tree
        TreeNode<T> n = find(root, key);
        if (n != null) {
            // get the successor TreeNode by calling the function you will implement below
            TreeNode<T> successor = findSuccessor(n);
            // return the key of successor TreeNode
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // logn
        TreeNode<T> current_node;
        // Node has the next neighbor over
        if(n.hasLeftChild()){
            current_node = n.leftChild;
            while(current_node.hasRightChild()){
                current_node = current_node.rightChild;
            }
            return current_node;
        } else {
            current_node = n.parent;
            // go up until the first parent is a left child
            while (n.isLeftChild() && current_node != null) {
                n = current_node;
                current_node = current_node.parent;
            }
            return current_node;
        }
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        // logn
        TreeNode<T> current_node;
        // Node has the next neighbor over
        if(n.hasRightChild()){
            current_node = n.rightChild;
            while(current_node.hasLeftChild()){
                current_node = current_node.leftChild;
            }
            return current_node;
        } else {
            current_node = n.parent;
            // go up until the first parent is a left child
            while(n.isRightChild() && current_node != null){
                n = current_node;
                current_node = current_node.parent;
            }
            return current_node;
        }
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
