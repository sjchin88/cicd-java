package io.github.sjchin88.cs6510.f24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 *  Represents a binary tree handler which has a binary tree stored and can perform operations.
 *  such as check if the binary tree is a binary search tree (BST),
 *  get the maximum depth of the tree,
 *  get the maximum value of the tree
 *
 *  @author sjchin
 */
public class BinaryTreeHandler {

  /**
   * Common Delimiter.
   */
  public static final String DELIMITER = ",";

  /**
   * Common representation of null node.
   */
  public static final String NULLVALUE = "null";
  private BinaryTreeNode root;

  /**
   * Default constructor, initialize the root as null.
   */
  public BinaryTreeHandler() {
    this.root = null;
  }

  /**
   * Custom constructor 1, initialize the root as given BinaryTreeNode object.
   *
   * @param root passed on BinaryTreeNode object
   */
  public BinaryTreeHandler(BinaryTreeNode root) {
    this.root = root;
  }

  /**
   * Custom constructor 2, construct a new binary tree from a given list of integer.
   * using Breadth First Search Algorithm
   * note null in the list will indicate empty child node
   *
   * @param data String representation of a binary tree
   */
  public BinaryTreeHandler(String data) {
    this.root = deserialize(data);
  }

  /**
   * Method to check if the binary tree given is a binary search tree.
   * Time complexity is O(n), Space complexity is O(n)
   * where n is the number of nodes in the tree
   *
   * @param root of the binary tree to check
   *
   * @return boolean indicator
   */
  public static boolean isBst(BinaryTreeNode root) {
    if (root == null) {
      return true;
    }
    return isBstCheck(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  /**
   * Helper method to check if the binary tree given is a binary search tree.
   * Time complexity is O(n), Space complexity is O(n)
   * where n is the number of nodes in the tree
   *
   * @param root of the binary tree to check
   * @param leftLimit left boundary value for this node and all children nodes
   * @param rightLimit right boundary value for this node and all children nodes
   *
   * @return boolean indicator
   */
  private static boolean isBstCheck(BinaryTreeNode root, int leftLimit, int rightLimit) {
    if (root == null) {
      return true;
    }
    if (root.value <= leftLimit || root.value >= rightLimit) {
      return false;
    }
    if (!isBstCheck(root.leftChild, leftLimit, root.value)) {
      return false;
    }
    return isBstCheck(root.rightChild, root.value, rightLimit);
  }

  /**
   * Method to return the maximum depth of a given binary tree node.
   * Time complexity is O(n), Space complexity is O(n)
   * where n is the number of nodes in the tree
   *
   * @param root root of the binary tree node
   *
   * @return max depth as int
   */
  public static int getMaxDepth(BinaryTreeNode root) {
    if (root == null) {
      return 0;
    }
    return Integer.max(getMaxDepth(root.leftChild), getMaxDepth(root.rightChild)) + 1;
  }

  /**
   * Method to return the maximum value of the nodes of a given binary tree node.
   * if the given node is null will return Integer.MIN_VALUE
   * Time complexity is O(n), Space complexity is O(n)
   * where n is the number of nodes in the tree
   *
   * @param root root of the binary tree node
   *
   * @return max value as int
   */
  public static int getMaxValue(BinaryTreeNode root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    int max = Integer.max(root.value, getMaxValue(root.leftChild));
    max = Integer.max(max, getMaxValue(root.rightChild));
    return max;
  }

  /**
   * Construct a new binary tree based on given String representation.
   * Each node value is separated by the delimiter ','
   * Null node value is indicated by "null"
   * Empty tree is indicated by empty string
   * time complexity is O(n), space complexity is O(n)
   *
   * @param data String representation of a binary tree
   *
   * @return the root of the binary tree constructed
   */
  public static BinaryTreeNode deserialize(String data) {
    if (data == null || data.isEmpty()) {
      return null;
    }
    Queue<BinaryTreeNode> queue = new LinkedList<>();
    String[] values = data.split(DELIMITER);
    BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(values[0]));
    queue.add(root);
    for (int i = 1; i < values.length; i++) {
      BinaryTreeNode parentNode = queue.poll();
      if (parentNode == null) {
        continue;
      }
      if (!values[i].equals(NULLVALUE)) {
        BinaryTreeNode leftNode = new BinaryTreeNode(Integer.parseInt(values[i]));
        parentNode.leftChild = leftNode;
        queue.add(leftNode);
      }
      if (++i < values.length && !values[i].equals(NULLVALUE)) {
        BinaryTreeNode rightNode = new BinaryTreeNode(Integer.parseInt(values[i]));
        parentNode.rightChild = rightNode;
        queue.add(rightNode);
      }
    }
    return root;
  }

  /**
   * Return the String representation of the values of a given binary tree node.
   * Each node value is separated by the delimiter ','
   * Null node value is indicated by "null"
   * Empty tree is indicated by empty string
   * Algorithm used is BFS using Queue, time complexity is O(n), space complexity is O(n)
   * The list representation can be used by the deserialize method
   * to reconstruct the same binary tree
   *
   * @param root root of the binary tree
   *
   * @return String representation
   */
  public static String serialize(BinaryTreeNode root) {
    if (root == null) {
      return "";
    }

    List<String> resList = new ArrayList<>();
    Queue<BinaryTreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      BinaryTreeNode currNode = queue.poll();
      if (currNode == null) {
        resList.add(NULLVALUE);
        continue;
      }
      resList.add(String.valueOf(currNode.value));
      queue.add(currNode.leftChild);
      queue.add(currNode.rightChild);
    }
    while (!resList.isEmpty() && resList.get(resList.size() - 1).equals(NULLVALUE)) {
      resList.remove(resList.size() - 1);
    }
    return String.join(DELIMITER, resList);
  }

  /**
   * Getter for the root of binary tree stored in this handler.
   *
   * @return root of the binary tree
   */
  public BinaryTreeNode getRoot() {
    return root;
  }

  /**
   * Setter for the root of binary tree stored in this handler.
   *
   * @param root for new binary tree
   */
  public void setRoot(BinaryTreeNode root) {
    this.root = root;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinaryTreeHandler that = (BinaryTreeHandler) o;
    if (this.getRoot() == null && that.getRoot() == null) {
      return true;
    }
    if (this.getRoot() == null || that.getRoot() == null) {
      return false;
    }
    return this.getRoot().equals(that.getRoot());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.toString());
  }

  @Override
  public String toString() {
    return "BinaryTreeHandler:"
        + serialize(this.root);
  }
}