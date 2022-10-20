package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.feature.Features;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * Abstract class that implements the Features interface that follows the command design.
 * Offers the operations that carry out transforming an image into its greyscale version.
 * This class helps minimize code between similar greyscale operations. Each operation
 * declares its calculation for the new greyscale pixel to be made in a helper that is then
 * used by the main apply() method to calculate the greyscale.
 */
public abstract class AbstractGreyscale implements Features {

  /**
   * Method that "applies" the greyscale to the input image. Uses a helper method unique to
   * each class to calculate the changes, then apply them.
   *
   * @param model the 2D Array of Pixels used to represent an image.
   * @return the new image after the greyscale modification has been completed.
   */
  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    // create a new 2D array that represents an image for the greyscale image
    Pixel[][] newPic = new Pixel[model.getHeight()][model.getWidth()];

    // iterate through the current image (2D Array)
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {

        // get the current pixel
        Pixel current = model.getPixelAt(row, col);

        newPic[row][col] = newPixelMaker(current);

      }
    }
    return newPic;
  }

  /**
   * Helps implement the changes done to the Pixel throughout each greyscale.
   *
   * @param p Input pixel to be changed through new calculations.
   * @return The new Pixel that was changed through the math.
   */
  protected abstract Pixel newPixelMaker(Pixel p);

}

