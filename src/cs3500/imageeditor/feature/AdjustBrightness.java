package cs3500.imageeditor.feature;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * A command in the command design pattern that allows for the process of making the brightness
 * change possible. It takes in a model object and the level of brightness to change. It only
 * implements the one method of the interface it implements (features or the commands). The one
 * method produces the level of brightness the command is set to make in the form of a 2D Array of
 * Pixels that represent the image.
 */
public class AdjustBrightness implements Features {
  private final int level;

  /**
   * Constructor initializes the level of brightness to be changed by.
   * @param level int that represents how much to increase the components of the pixel by
   */
  public AdjustBrightness(int level) {

    this.level = level;
  }

  /**
   * Executes the change of brightness given to the command.
   * @param model 2D Array of Pixels that represent the image that is going to be modified.
   * @return 2D Array of Pixels that represent the new image built.
   */
  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    // create a new 2D array that represents an image for the flipped image
    Pixel[][] newPic = new Pixel[model.getHeight()][model.getWidth()];

    // values to be used to create the new pixel
    int r;
    int g;
    int b;

    // iterate through the 2D Array of Pixels or the "image"
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {

        // get the current pixel
        Pixel current = model.getPixelAt(row, col);

        // see the hypothetical results of changing the brightness value
        int rResult = current.rValue() + this.level;
        int gResult = current.gValue() + this.level;
        int bResult = current.bValue() + this.level;

        // clamp value to 255 as max and 0 as min
        if (rResult > 255 || gResult > 255 || bResult > 255) {

          // calculate the most you can add to each component before going over
          int maxCanAdd = 255 - Math.max(current.rValue(),
                  Math.max(current.gValue(), current.bValue()));

          // once the value is calculated set the new pixel component values to add the increment
          r = current.rValue() + maxCanAdd;
          g = current.gValue() + maxCanAdd;
          b = current.bValue() + maxCanAdd;


        } else if (rResult < 0 || gResult < 0 || bResult < 0) {

          // calculate the most you can subtract to each component before going under
          int maxCanRemove = Math.min(current.rValue(),
              Math.min(current.gValue(), current.bValue()));

          // once the value is calculated set the new pixel component values to add the decrement
          r = current.rValue() - maxCanRemove;
          g = current.gValue() - maxCanRemove;
          b = current.bValue() - maxCanRemove;

        } else {

          // if there are no issues of going over the clamp values, then just add it normally
          r = rResult;
          g = gResult;
          b = bResult;

        }

        // create a new pixel based on new calculated component values (brighten or darken)
        Pixel newPixel = new Pixel(r, g, b, current.aValue());

        // put the new pixel on the new "image" produced
        newPic[row][col] = newPixel;
      }
    }
    return newPic;
  }
}
