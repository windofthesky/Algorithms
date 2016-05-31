package algorithms;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> 
{

    private static final boolean RED   = true;
    private static final boolean BLACK = false;
    private Node root;     // root of the BST

    // BST helper node data type
    private class Node 
    {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int N;             // subtree count

        public Node(Key key, Value val, boolean color, int N) 
        {
            this.key = key;
            this.val = val;
            this.color = color;
            this.N = N;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public RedBlackBST() 
    {
    }
}
