import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {

        // Where both nodes are null (the parents were valid)
        if(n1 == null && n2 == null)
            return true;

        // If the nodes are different return false (not the same)
        if(n1 == null || n2 == null)
            return false;

        // Check if the nodes are different
        if(!n1.equals(n2))
            return false;

        // We've found a pair of nodes that are valid and the same, let's check their children
        if (isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild))
            return true;
        if (isIsomorphic(n1.leftChild, n2.rightChild) && isIsomorphic(n1.rightChild, n2.leftChild))
            return true;

        return false;
    }
}
