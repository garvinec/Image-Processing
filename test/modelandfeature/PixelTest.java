//package modelandfeature;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import cs3500.imageeditor.model.Pixel;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.fail;
//
///**
// * Test to ensure that the Pixel class stores and represents data correctly.
// */
//
//public class PixelTest {
//
//  private Pixel p1;
//  private Pixel p2;
//  private Pixel p3;
//  private Pixel p4;
//
//  @Before
//  public void init() {
//
//    p1 = new Pixel(255, 255,255);
//    p2 = new Pixel(0,0,0);
//    p3 = new Pixel(120,120,120);
//    p4 = new Pixel(120,120,120);
//
//  }
//
//  @Test
//  public void testInvalidInit() {
//    // try to make an invalid r value (too big)
//    try {
//      p1 = new Pixel(300, 10, 140);
//      fail("Cannot have a r value of above 255");
//    } catch (IllegalArgumentException e) {
//      // exception was thrown and caught successfully, let this test pass.
//    }
//
//    // try to make an invalid r value (below 0)
//    try {
//      p1 = new Pixel(-300, 10, 140);
//      fail("Cannot have a r value of below 0");
//    } catch (IllegalArgumentException e) {
//      // exception was thrown and caught successfully, let this test pass.
//    }
//
//    // try to make an invalid g value (too big)
//    try {
//      p2 = new Pixel(150, 500, 140);
//      fail("Cannot have a g value of above 255");
//    } catch (IllegalArgumentException e) {
//      // exception was thrown and caught successfully, let this test pass.
//    }
//
//    // try to make an invalid g value (below 0)
//    try {
//      p2 = new Pixel(150, -10, 140);
//      fail("Cannot have a g value of below 0");
//    } catch (IllegalArgumentException e) {
//      // exception was thrown and caught successfully, let this test pass.
//    }
//
//    // try to make an invalid b value (too big)
//    try {
//      p3 = new Pixel(150, 10, 1400);
//      fail("Cannot have a b value of above 255");
//    } catch (IllegalArgumentException e) {
//      // exception was thrown and caught successfully, let this test pass.
//    }
//
//    // try to make an invalid b value (below 0)
//    try {
//      p3 = new Pixel(150, 10, -140);
//      fail("Cannot have a b value of below 0");
//    } catch (IllegalArgumentException e) {
//      // exception was thrown and caught successfully, let this test pass.
//    }
//  }
//
//  @Test
//  public void testRValue() {
//
//    assertEquals(255, p1.rValue());
//    assertEquals(0, p2.rValue());
//    assertEquals(120, p3.rValue());
//
//  }
//
//  @Test
//  public void testGValue() {
//
//    assertEquals(255, p1.gValue());
//    assertEquals(0, p2.gValue());
//    assertEquals(120, p3.gValue());
//  }
//
//  @Test
//  public void testBValue() {
//
//    assertEquals(255, p1.bValue());
//    assertEquals(0, p2.bValue());
//    assertEquals(120, p3.bValue());
//
//  }
//
//  @Test
//  public void testEqualsOverride() {
//
//    assertEquals(p3, p4);
//    assertNotEquals(p1, p2);
//    assertNotEquals(p2, p4);
//  }
//
//  @Test
//  public void testHashcodeOverride() {
//    assertEquals(24120, p3.hashCode());
//    assertEquals(24120, p4.hashCode());
//    assertEquals(p3.hashCode(), p4.hashCode());
//    assertEquals(51255, p1.hashCode());
//    assertEquals(0, p2.hashCode());
//    assertNotEquals(p1.hashCode(), p2.hashCode());
//  }
//}
