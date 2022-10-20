package cs3500.imageeditor.feature.filter;

import cs3500.imageeditor.feature.Features;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * Abstract class to remove duplicate code from any filter that uses a kernel. The class
 * contains abstracted code that applies the filter to any image (2D Array of Pixels) that
 * is given as an argument. All you have to do is provide the kernel to overlay,
 * and the function will apply this.
 */
public abstract class AbstractFilter implements Features {

  /**
   * Applies the kernel of choice to the image and changes the pixels accordingly.
   *
   * @param model 2D Array of Pixels that represents the image being edited
   * @return a new 2D Array of Pixels that contains the changed Pixels that have the kernel applied.
   */
  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    double[][] kernel = kernel();

    int length = kernel.length / 2;
    int width = kernel[0].length / 2;

    // creating a new image for the output
    Pixel[][] newPic = new Pixel[model.getHeight()][model.getWidth()];

    // iterating through the 2D Array and doing something to each pixel
    for (int row = 0; row < model.getHeight(); row++) {
      for (int col = 0; col < model.getWidth(); col++) {

        // get the current pixel of the image
        Pixel currPixel = model.getPixelAt(row, col);

        // local variables to store values to be added
        double totalRed = 0;

        double totalGreen = 0;

        double totalBlue = 0;

        // iterate through the kernel and multiply corresponding parts
        // new position keeps on being 0,0 for each row
        for (int kernelr = row - length; kernelr <= row + length; kernelr++) {
          for (int kernelc = col - width; kernelc <= col + width; kernelc++) {

            // if there are any pixels on the edge, only include calculations of neighboring pixels
            if ((kernelc >= 0 && kernelr >= 0
                    && kernelr < model.getHeight() && kernelc < model.getWidth())) {

              // get the "current" pixel you are on
              Pixel current = model.getPixelAt(kernelr, kernelc);

              double kernelValue =
                      kernel[kernelr - (row - kernel.length / 2)]
                              [kernelc - (col - kernel[0].length / 2)];

              totalRed += current.rValue() * kernelValue;
              totalGreen += current.gValue() * kernelValue;
              totalBlue += current.bValue() * kernelValue;
            }
          }
        }

        // clamp and round each new value for the pixel to a range of 0-255 and as an int
        int newRed = (int) Math.rint(Math.max(0, Math.min(totalRed, 255)));
        int newGreen = (int) Math.rint(Math.max(0, Math.min(totalGreen, 255)));
        int newBlue = (int) Math.rint(Math.max(0, Math.min(totalBlue, 255)));

        // create a new pixel based off the new totals for the components
        Pixel newPixel = new Pixel(newRed, newGreen, newBlue, currPixel.aValue());

        // add the new pixel to the correct position on the new image
        newPic[row][col] = newPixel;
      }
    }
    // return the new 2D Array that represents the image
    return newPic;
  }

  /**
   * Abstract method that returns the kernel to be applied as a 2D array of double values.
   * This is used to set the kernel that will later be used to modify the image input.
   *
   * @return a 2D Array of double that represent the kernel desired.
   */
  protected abstract double[][] kernel();

}
