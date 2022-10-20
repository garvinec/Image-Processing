package cs3500.imageeditor.feature.transformation;

import cs3500.imageeditor.feature.Features;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * Abstract class that implements the Features interface that follows the command design.
 * Offers operations that carry out transforming an image into a color transformation version.
 * This class helps minimize the code between similar color transformation operations. Each
 * operation will declare the 2D array to calculate the new Pixel from the old, and then create
 * a new image using these new values calculated.
 */

public abstract class AbstractColorTransformation implements Features {


  /**
   * Produces a new image with the color transformation applied.
   *
   * @param model 2D Array of Pixels that represent the image
   * @return A new image that has the color transformation done.
   */
  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    // set the transformation matrix you want to use
    double[][] color = color();

    // creating a new image for the output
    Pixel[][] newPic = new Pixel[model.getHeight()][model.getWidth()];

    // iterating through the 2D Array and doing something to each pixel
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {

        // get the "current" pixel you are on
        Pixel current = model.getPixelAt(row, col);

        // local variables to store values to be added
        double totalRed = 0;

        double totalGreen = 0;

        double totalBlue = 0;

        // iterate through the color transformation and multiply corresponding parts
        for (int r = 0; r < color.length; r++) {
          if (r == 0) {
            totalRed += current.rValue() * color[0][0]
                    + current.gValue() * color[0][1]
                    + current.bValue() * color[0][2];
          } else if (r == 1) {
            totalGreen += current.rValue() * color[1][0]
                    + current.gValue() * color[1][1]
                    + current.bValue() * color[1][2];
          } else if (r == 2) {
            totalBlue += current.rValue() * color[2][0]
                    + current.gValue() * color[2][1]
                    + current.bValue() * color[2][2];
          }
        }

        // clamp and round each new value for the pixel to a range of 0-255 and as an int
        int newRed = (int) Math.rint(Math.max(0, Math.min(totalRed, 255)));
        int newGreen = (int) Math.rint(Math.max(0, Math.min(totalGreen, 255)));
        int newBlue = (int) Math.rint(Math.max(0, Math.min(totalBlue, 255)));

        // create a new pixel based off the new totals for the components
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue, current.aValue());

        // add the new pixel to the correct position on the new image
        newPic[row][col] = newPixel;
      }
    }
    // return the new 2D Array that represents the image
    return newPic;
  }

  /**
   * Helper method that contains the correct transformation matrix to apply to the image.
   *
   * @return 2D Array of doubles that represent the matrix.
   */
  protected abstract double[][] color();
}
