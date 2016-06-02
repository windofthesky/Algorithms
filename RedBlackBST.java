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
    
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    //number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) 
    {
        if (x == null) 
        {
            return 0;
        }
        
        return x.N;
    } 

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() 
    {
        return size(root);
    }

   /**
     * Is this symbol table empty?
     * @return true if this symbol table is empty and false otherwise
     */
    public boolean isEmpty() 
    {
        return (root == null);
    }
    
}
