package io.github.sjchin88.cs6510.f24;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryTreeHandlerTest {
  private BinaryTreeHandler testHandler;
  private String testString;
  private String testBstString;
  private BinaryTreeNode testRoot;

  @BeforeEach
  void setUp() {
    testHandler = new BinaryTreeHandler();
    testString = "1,2,3,null,null,4,5";
    testBstString = "2,1,4,null,null,3,6";
    testRoot = BinaryTreeHandler.deserialize(testString);
  }

  @Test
  void isBst() {
    assertFalse(BinaryTreeHandler.isBst(testRoot));
    assertTrue(BinaryTreeHandler.isBst(testHandler.getRoot()));
    BinaryTreeNode testBstRoot = BinaryTreeHandler.deserialize(testBstString);
    assertTrue(BinaryTreeHandler.isBst(testBstRoot));
    String testString2 = "3,1,2,null,null,4,5";
    assertFalse(BinaryTreeHandler.isBst(BinaryTreeHandler.deserialize(testString2)));
  }

  @Test
  void getMaxDepth() {
    assertEquals(3, BinaryTreeHandler.getMaxDepth(testRoot));
  }

  @Test
  void getMaxValue() {
    assertEquals(5, BinaryTreeHandler.getMaxValue(testRoot));
  }

  @Test
  void serializeDeserialize() {
    BinaryTreeNode root = BinaryTreeHandler.deserialize(testString);
    String serialize = BinaryTreeHandler.serialize(root);
    assertEquals(testString, serialize);
    assertNull(BinaryTreeHandler.deserialize(null));
    assertNull(BinaryTreeHandler.deserialize(""));
  }

  @Test
  void getRoot() {
    assertNull(testHandler.getRoot());
    testHandler = new BinaryTreeHandler(testRoot);
    assertEquals(testRoot, testHandler.getRoot());
    testHandler = new BinaryTreeHandler(testString);
    assertEquals(testRoot, testHandler.getRoot());
  }

  @Test
  void setRoot() {
    BinaryTreeNode newRoot = BinaryTreeHandler.deserialize(testBstString);
    testHandler.setRoot(newRoot);
    assertEquals(newRoot, testHandler.getRoot());
  }

  @Test
  void testEquals() {
    assertEquals(testHandler, testHandler);
    assertNotEquals(testHandler, null);
    assertNotEquals(testHandler, new Object());
    assertEquals(testHandler, new BinaryTreeHandler());
    assertNotEquals(testHandler, new BinaryTreeHandler(testBstString));
    assertNotEquals(new BinaryTreeHandler(testBstString), testHandler);
    assertNotEquals(new BinaryTreeHandler(testString), new BinaryTreeHandler(testBstString));
    assertEquals(new BinaryTreeHandler(testString), new BinaryTreeHandler(testString));
  }

  @Test
  void testHashCode() {
    String expectedString = "BinaryTreeHandler:";
    assertEquals(Objects.hashCode(expectedString), testHandler.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "BinaryTreeHandler:";
    assertEquals(expectedString, testHandler.toString());
  }
}