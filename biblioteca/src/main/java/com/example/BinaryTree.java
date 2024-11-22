package com.example;

// Define the Node class for each node in the tree
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class Book {
    int ISBN;
    String Name;
}

public class BinaryTree {

    // Root of the binary tree
    private Node root;

    // Constructor to create a new binary tree with no nodes
    public BinaryTree() {
        this.root = null;
    }

    // Method to insert a node into the binary tree
    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            _insert(data, root);
        }
    }

    // Method to delete a node from the binary tree (in-order)
    public void deleteNode(int data) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        _deleteNode(data, root);
    }

    // Helper method for deleting a node recursively
    private boolean _deleteNode(int data, Node currentNode) {
        if (currentNode == null) {
            return false; // Node not found
        } else if (data < currentNode.data) {
            return _deleteNode(data, currentNode.left);
        } else if (data > currentNode.data) {
            return _deleteNode(data, currentNode.right);
        }

        // Node found, delete it recursively
        if (currentNode.left == null && currentNode.right == null) {
            currentNode = null;
            return true;
        } else if (currentNode.left == null) {
            currentNode = currentNode.right;
            return true;
        } else if (currentNode.right == null) {
            currentNode = currentNode.left;
            return true;
        }

        // Node has two children, find the minimum in the right subtree and replace the
        // node's value with it
        int minVal = _findMinValue(currentNode.right);
        currentNode.data = minVal;
        deleteNode(minVal); // Recursively call the delete method on the child tree with the new data value
        return true;
    }

    private int _findMinValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    // Helper method for inserting a node recursively
    private void _insert(int data, Node currentNode) {
        if (data < currentNode.data) {
            if (currentNode.left == null) {
                currentNode.left = new Node(data);
            } else {
                _insert(data, currentNode.left);
            }
        } else if (data > currentNode.data) {
            if (currentNode.right == null) {
                currentNode.right = new Node(data);
            } else {
                _insert(data, currentNode.right);
            }
        }
    }

    // Method to print the binary tree in-order
    public void printInOrder() {
        System.out.print("InOrder: ");
        if (root != null) {
            _printInOrder(root);
        }
        System.out.println();
    }

    // Helper method for printing the binary tree in-order recursively
    private void _printInOrder(Node currentNode) {
        if (currentNode != null) {
            _printInOrder(currentNode.left);
            System.out.print(currentNode.data + " ");
            _printInOrder(currentNode.right);
        }
    }

    // Method to print the binary tree pre-order
    public void printPreOrder() {
        System.out.print("PreOrder: ");
        if (root != null) {
            _printPreOrder(root);
        }
        System.out.println();
    }

    // Helper method for printing the binary tree pre-order recursively
    private void _printPreOrder(Node currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.data + " ");
            _printPreOrder(currentNode.left);
            _printPreOrder(currentNode.right);
        }
    }

    // Method to print the binary tree post-order
    public void printPostOrder() {
        System.out.print("PostOrder: ");
        if (root != null) {
            _printPostOrder(root);
        }
        System.out.println();
    }

    // Helper method for printing the binary tree post-order recursively
    private void _printPostOrder(Node currentNode) {
        if (currentNode != null) {
            _printPostOrder(currentNode.left);
            _printPostOrder(currentNode.right);
            System.out.print(currentNode.data + " ");
        }
    }

    // Method to get the height of the binary tree
    public int getHeight() {
        return _getHeight(root);
    }

    // Helper method for getting the height of the binary tree recursively
    private int _getHeight(Node currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            int leftHeight = _getHeight(currentNode.left);
            int rightHeight = _getHeight(currentNode.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Method to check if the binary tree is balanced
    public boolean isBalanced() {
        return _isBalanced(root) != -1;
    }

    // Helper method for checking if the binary tree is balanced recursively
    private int _isBalanced(Node currentNode) {
        if (currentNode == null) {
            return 0;
        } else {
            int leftHeight = _getHeight(currentNode.left);
            int rightHeight = _getHeight(currentNode.right);
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1; // Unbalanced
            }
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        System.out.println("In-order: ");
        tree.printInOrder();

        System.out.println("\nPre-order: ");
        tree.printPreOrder();

        System.out.println("\nPost-order: ");
        tree.printPostOrder();

        System.out.println("\nHeight: " + tree.getHeight());
        System.out.println("Is Balanced: " + tree.isBalanced());
    }
}