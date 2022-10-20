package cs3500.imageeditor.view;

import java.io.IOException;

/**
 * Represents the text view of the image editor model and implements the interface's methods.
 */
public class ImageEditorViewImp implements ImageEditorView {

  private final Appendable output;

  /**
   * Constructor initializes the view or the output that the view produces.
   *
   * @param output Appendable that allows for modification
   *
   * @throws IllegalArgumentException if the output is null
   */
  public ImageEditorViewImp(Appendable output) throws IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException("Can't have null model or output");
    }
    this.output = output;
  }


  /**
   * Renders the specific message given as an output.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission to the terminal fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.output.append(message);
  }
}