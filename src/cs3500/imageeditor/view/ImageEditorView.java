package cs3500.imageeditor.view;

import java.io.IOException;

/**
 * Interface to represent the output/view of the Image Editor. So far it only produces
 * or renders helpful messages.
 */

public interface ImageEditorView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}