package view;

import cs3500.imageeditor.view.ImageEditorView;
import cs3500.imageeditor.view.ImageEditorViewImp;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test to ensure that the Image Editor Implementation works correctly.
 */
public class ImageEditorViewImpTest {

  private ImageEditorView iv;
  private Appendable output;

  @Before
  public void init() {

    output = new StringBuilder();
    iv = new ImageEditorViewImp(output);

  }

  @Test
  public void initInvalid() {
    try {
      iv = new ImageEditorViewImp(null);
      fail("Can't have null Appendable output");
    } catch (IllegalArgumentException e) {
      // exception was thrown and caught successfully, let this test pass.
    }
  }

  @Test
  public void testRenderMessage() {
    try {
      iv.renderMessage("Hello there.");
    } catch (IOException e) {
      fail("Cannot render message");
    }

    assertEquals("Hello there.", output.toString());

    try {
      iv.renderMessage(" What's up");
    } catch (IOException e) {
      fail("Cannot render message");
    }

    assertEquals("Hello there. What's up", output.toString());
  }
}