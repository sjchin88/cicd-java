package io.github.sjchin88.cs6510.f24;

import java.util.Objects;

/**
 * Represents a Binary Tree Node which contains the node value.
 * left and right child Binary Tree Node if exist
 * All methods are safe for concurrency operation
 *
 * @author sjchin
 */
public class BinaryTreeNode {

  /**
   * value of the binary tree node.
   */
  protected int value;

  /**
   * left child of the binary tree node.
   */
  protected BinaryTreeNode leftChild;

  /**
   * right child of the binary tree node.
   */
  protected BinaryTreeNode rightChild;

  /**
   * Create a new BinaryTreeNode with the given value.
   *
   * @param value integer value stored by the node
   */
  public BinaryTreeNode(int value) {
    this.value = value;
    this.leftChild = null;
    this.rightChild = null;
  }

  /**
   * Create a new BinaryTreeNode with the given value, leftChild and rightChild.
   *
   * @param value integer value stored by the node
   * @param leftChild left child node
   * @param rightChild right child node
   */
  public BinaryTreeNode(int value, BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
    this.value = value;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
  }

  /**
   * Override default to string method.
   * Time complexity is O(n) and space complexity is O(n) as it will
   * go through every child
   *
   * @return string representation
   */
  @Override
  public String toString() {
    return BinaryTreeHandler.serialize(this);
  }

  /**
   * Override default equals method.
   * Time complexity is O(n) and space complexity is O(n) as it will
   * go through every child
   *
   * @param o other object to compare against
   *
   * @return boolean indicator
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BinaryTreeNode that = (BinaryTreeNode) o;

    if (this.value != that.value) {
      return false;
    }

    boolean leftChildEqual;
    boolean rightChildEqual;

    if (this.leftChild == null && that.leftChild == null) {
      leftChildEqual = true;
    } else if (this.leftChild == null || that.leftChild == null) {
      leftChildEqual = false;
    } else {
      leftChildEqual = this.leftChild.equals(that.leftChild);
    }

    if (!leftChildEqual) {
      return false;
    }

    if (this.rightChild == null && that.rightChild == null) {
      rightChildEqual = true;
    } else if (this.rightChild == null || that.rightChild == null) {
      rightChildEqual = false;
    } else {
      rightChildEqual = this.rightChild.equals(that.rightChild);
    }

    return rightChildEqual;
  }

  /**
   * Override the default hash method.
   *
   * @return hash value
   */
  @Override
  public int hashCode() {
    return Objects.hash(BinaryTreeHandler.serialize(this));
  }
}
