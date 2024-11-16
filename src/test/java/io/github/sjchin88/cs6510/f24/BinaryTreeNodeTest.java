package io.github.sjchin88.cs6510.f24;

import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryTreeNodeTest {
  private BinaryTreeNode btreeNode;
  private final int rootVal = 1;

  @BeforeEach
  void setUp() {
    this.btreeNode = new BinaryTreeNode(rootVal);
  }

  @Test
  void testToString() {
    String result = btreeNode.toString();
    Assertions.assertEquals("1", btreeNode.toString());
  }

  @Test
  void testEquals() {
    // Test equal to itself
    assert btreeNode.equals(btreeNode);
    // Test equal to other node
    BinaryTreeNode newBtreeNode = new BinaryTreeNode(rootVal);
    assert btreeNode.equals(newBtreeNode);
    // Test not equal to other node
    BinaryTreeNode newBtreeNode2 = new BinaryTreeNode(rootVal + 1);
    assert !btreeNode.equals(newBtreeNode2);
    // Test not equal to other object
    assert !btreeNode.equals(rootVal);
    // Test not equal with single left children
    this.btreeNode.leftChild = newBtreeNode2;
    assert !btreeNode.equals(newBtreeNode);
    assert !newBtreeNode.equals(btreeNode);
    // Test equal with single left child
    newBtreeNode.leftChild = newBtreeNode2;
    assert btreeNode.equals(newBtreeNode);
    // Test not equal with right child
    BinaryTreeNode newBtreeNode3 = new BinaryTreeNode(rootVal + 2);
    this.btreeNode.rightChild = newBtreeNode3;
    assert !btreeNode.equals(newBtreeNode);
    assert !newBtreeNode.equals(btreeNode);
    // Test equal with right child
    newBtreeNode = new BinaryTreeNode(rootVal, newBtreeNode2, newBtreeNode3);
    assert btreeNode.equals(newBtreeNode);
  }

  @Test
  void testHashCode() {
    int hashValue = btreeNode.hashCode();
    assert hashValue == Objects.hash('1');
  }
}