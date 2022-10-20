package cs3500.imageeditor.controller.gui;

import cs3500.imageeditor.model.ImageEditorModel;
import java.util.Map;

/**
 * This interface represents operations that can be used to load, edit, and save an image through
 * GUI.
 */
public interface ImageEditorGUIController {

  /**
   * This method runs the ImageEditor program.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input
   */
  void run() throws IllegalStateException;

  /**
   * Loads the image in PPM format from the given path.
   *
   * @param path name of the path where the image is loaded from
   */
  void loadImage(String path);

  /**
   * Saves the image in PPM format to the given path.
   *
   * @param path name of the path where the image will be saved
   * @param format name of the format of the image
   */
  void saveImage(String path, String format);
}
