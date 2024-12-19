package com.example;

public class DemandBST {
    static class Node {
        String time;
        int demand;
        Node left, right;

        public Node(String time, int demand) {
            this.time = time;
            this.demand = demand;
            this.left = this.right = null;
        }
    }

    private Node root;

    //insert a new node with a specific time and demand into the BST
    public void insert(String time, int demand) {
        root = insertRec(root, time, demand);
    }
    //helper method to implement the insert method recursively
    private Node insertRec(Node root, String time, int demand) {
        if (root == null) {
            root = new Node(time, demand);
            return root;
        }
        //if the time is less than the root time, insert it in the left subtree
        //if the time is greater than the root time, insert it in the right subtree
        if (time.compareTo(root.time) < 0)
            root.left = insertRec(root.left, time, demand);
        else if (time.compareTo(root.time) > 0)
            root.right = insertRec(root.right, time, demand);

        return root;
    }
    //searches for a node with the specidi time in the BST
    public int search(String time) {
        Node result = searchRec(root, time);
        return result != null ? result.demand : -1; // Return -1 if not found
    }
    //helper method to implement the search method recursively
    private Node searchRec(Node root, String time) {
        if (root == null || root.time.equals(time))
            return root;
        if (time.compareTo(root.time) < 0)
            return searchRec(root.left, time);
        return searchRec(root.right, time);
    }
    //transverses the BST in inorder
    public void inorder() {
        inorderRec(root);
    }
    //helper method to implement the inorder method recursively
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println("Time: " + root.time + ", Demand: " + root.demand);
            inorderRec(root.right);
        }
    }
}
