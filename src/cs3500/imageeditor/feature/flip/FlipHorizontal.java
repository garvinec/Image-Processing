package cs3500.imageeditor.feature.flip;

import cs3500.imageeditor.feature.Features;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * A command in the command design pattern that allows for the process of making the flip
 * change possible. It only implements the one method of the interface it implements.
 * The one method produces the type of flip the command is set to make in the form of a
 * 2D Array of Pixels that represent the image.
 */
public class FlipHorizontal implements Features {

  /**
   * Constructor that initializes the command Flip Horizontal so that it can be called
   * on am image and flip it as the user would like.
   */
  public FlipHorizontal() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Applies the horizontal flip to the image as the user intends.
   * @param model 2D Array of Pixels that represents an image
   * @return the image now flipped horizontally.
   */
  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    // create a new 2D array that represents an image for the flipped image
    Pixel[][] newPic = new Pixel[model.getHeight()][model.getWidth()];

    // iterate through the 2D array of pixels, get the pixel from its current location
    // and move it to the new image at the flipped location for that pixel
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {

        // get the current pixel
        Pixel current = model.getPixelAt(row, col);

        newPic[model.getHeight() - row - 1][col] = current;

      }
    }
    return newPic;
  }
}
