package cs3500.imageeditor.feature;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * Part of the command design pattern, acts as the interface where all the "commands" or in
 * this case features go. It only contains one method that executes the changes or commands
 * that the subclasses are supposed to carry.
 */
public interface Features {

  /**
   * Executes the change/edit that each subclass carries.
   * @param model 2D Array of Pixels that represent the image that is going to be modified.
   * @return new 2D Array of Pixels that has the editing changes.
   */
  Pixel[][] modify(ImageEditorModel model);
}
