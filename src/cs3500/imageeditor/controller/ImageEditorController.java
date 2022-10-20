package cs3500.imageeditor.controller;

import cs3500.imageeditor.model.ImageEditorModel;
import java.util.Map;

/**
 * This interface represents operations that can be used to load, edit, and save an image.
 */
public interface ImageEditorController {

  /**
   * This method runs the ImageEditor program.
   *
   * @throws IllegalStateException if the controller is unable to successfully read input
   */
  void run() throws IllegalStateException;

  /**
   * Loads the image in PPM format from the given path with the given name.
   *
   * @param path name of the path where the image is loaded from
   * @param name name of the image
   */
  void loadImage(String path, String name);

  /**
   * Saves the image in PPM format to the given path with the given name.
   *
   * @param path name of the path where the image will be saved
   * @param name name of the image
   * @param format name of the format of the image
   * @param stored Map which contains the ImageEditorModel corresponding to its given name
   */
  void saveImage(String path, String name, String format, Map<String, ImageEditorModel> stored);
}
