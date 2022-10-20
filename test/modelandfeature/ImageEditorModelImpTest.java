package modelandfeature;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.ImageEditorModelImp;
import cs3500.imageeditor.model.Pixel;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test to ensure that the Model of the Image Editor correctly calculates
 * and produces the desired representation of the image.
 */

public class ImageEditorModelImpTest {

  private ImageEditorModel im;

  private ImageEditorModel im1;

  private ImageEditorModel im2;

  @Before
  public void init() {

    Pixel[][] image = new Pixel[10][10];
    im = new ImageEditorModelImp(image);

    Pixel[][] image1 = new Pixel[2][2];
    image1[0][0] = new Pixel(150, 120,67,0);
    image1[0][1] = new Pixel(234, 13, 95,0);
    image1[1][0] = new Pixel(58, 140, 139,0);
    image1[1][1] = new Pixel(24, 14, 104,0);

    im1 = new ImageEditorModelImp(image1);

    Pixel[][] image2 = new Pixel[4][5];

    im2 = new ImageEditorModelImp(image2);
  }

  @Test
  public void testInitInvalid() {
    try {
      im = new ImageEditorModelImp(null);
      fail("Can't have a null image");
    } catch (IllegalArgumentException e) {
      // exception was thrown and caught successfully, let this test pass.
    }
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, im.getWidth());
    assertEquals(2, im1.getWidth());
    assertEquals(5, im2.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(10, im.getHeight());
    assertEquals(2, im1.getHeight());
    assertEquals(4, im2.getHeight());
  }

  @Test
  public void testGetPixelAt() {

    assertEquals(new Pixel(150,120,67,0), im1.getPixelAt(0,0));

    assertEquals(new Pixel(234,13,95,0), im1.getPixelAt(0,1));

    assertEquals(new Pixel(58, 140, 139,0), im1.getPixelAt(1,0));

    assertEquals(new Pixel(24,14,104,0), im1.getPixelAt(1,1));

  }

  @Test
  public void testInvalidGetPixelAt() {
    try {
      im1.getPixelAt(-1, 0);
      fail("Can't have negative coordinates");
    } catch (IllegalArgumentException e) {
      // exception was thrown correctly, let this test pass.
    }

    try {
      im1.getPixelAt(0, -16);
      fail("Can't have negative coordinates");
    } catch (IllegalArgumentException e) {
      // exception was thrown correctly, let this test pass.
    }

    try {
      im1.getPixelAt(-5, -4);
      fail("Can't have negative coordinates");
    } catch (IllegalArgumentException e) {
      // exception was thrown correctly, let this test pass.
    }

    try {
      im1.getPixelAt(10, 0);
      fail("Can't have negative coordinates");
    } catch (IllegalArgumentException e) {
      // exception was thrown correctly, let this test pass.
    }

    try {
      im1.getPixelAt(2, 15);
      fail("Can't have negative coordinates");
    } catch (IllegalArgumentException e) {
      // exception was thrown correctly, let this test pass.
    }

    try {
      im1.getPixelAt(100, 100);
      fail("Can't have negative coordinates");
    } catch (IllegalArgumentException e) {
      // exception was thrown correctly, let this test pass.
    }
  }

  @Test
  public void testRedMap() {
    assertEquals(4, im1.redMap().size());



  }
}
