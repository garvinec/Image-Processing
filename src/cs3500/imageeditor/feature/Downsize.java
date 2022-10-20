package cs3500.imageeditor.feature;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * A command in the command design pattern that allows for an image to be downsized. It takes in
 * the dimensions of the new image size, and the corresponding method takes in the image to be
 * downsized. The method then produces the new image with its downsized pixels and the calculations
 * to create this new smaller image.
 */
public class Downsize implements Features {

  private final int newHeight;
  // INVARIANT: has to be > 0 & Greater than or equal to current model's height

  private final int newWidth;
  // INVARIANT: has to be > 0 & Greater than or equal to current model's width

  /**
   * Constructor to initialize the dimensions of the new image as fields and the command.
   * @param newHeight int that represents the new height of the image
   * @param newWidth int that represents the new width of the image
   * @throws IllegalArgumentException when the height or width of the new image is negative.
   */
  public Downsize(int newHeight, int newWidth) throws IllegalArgumentException {
    if (newHeight < 0 || newWidth < 0) {
      throw new IllegalArgumentException("Can't have a negative dimension");

    }

    this.newHeight = newHeight;
    this.newWidth = newWidth;

  }

  /**
   * Calculates and maps the corresponding downsized image to the original image to produce a
   * smaller version of the image to the user's desired size.
   * @param model 2D Array of Pixels that represent the image that is going to be modified.
   * @return 2D Array of Pixels that represent the new downsized image and the calculations it took.
   */

  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    // Check to see if new dimensions are at least the same if not less than the current
    if (model.getHeight() > this.newHeight || model.getWidth() > this.newWidth) {
      throw new IllegalArgumentException("This new dimension won't downsize!");
    }

    // create a new image to be produced and added to
    Pixel[][] newPic = new Pixel[this.newHeight][this.newWidth];

    // iterate through the downsized 2D Array and get the corresponding Pixel from the original
    // image and calculate new values based off that mapped pixel
    for (int row = 0; row < this.newHeight; row++) {
      for (int col = 0; col < this.newWidth; col++) {

        // calculations to get corresponding coordinates of the current image
        // TO DO: change this to get the right math
        double r = 1 / newHeight;
        double c = 1 / newWidth;


        // Find and establish the 4 floating points
        Pixel mappedA = model.getPixelAt((int) Math.floor(r), (int) Math.floor(c));

        Pixel mappedB = model.getPixelAt((int) Math.ceil(r), (int) Math.floor(c));

        Pixel mappedC = model.getPixelAt((int) Math.floor(r), (int) Math.ceil(c));

        Pixel mappedD = model.getPixelAt((int) Math.ceil(r), (int) Math.ceil(c));

        // Calculate the new R value
        double mR = mappedB.rValue() * (c - Math.floor(c)) + mappedA.rValue() * (Math.ceil(c) - c);

        double nR = mappedD.rValue() * (c - Math.floor(c)) + mappedC.rValue() * (Math.ceil(c) - c);

        int newR = (int) Math.rint(nR * (r - Math.floor(r)) + mR * (Math.ceil(r) - r));

        // Calculate the new G value
        double mG = mappedB.gValue() * (c - Math.floor(c)) + mappedA.gValue() * (Math.ceil(c) - c);

        double nG = mappedD.gValue() * (c - Math.floor(c)) + mappedC.gValue() * (Math.ceil(c) - c);

        int newG = (int) Math.rint(nG * (r - Math.floor(r)) + mG * (Math.ceil(r) - r));

        // Calculate the new B value
        double mB = mappedB.bValue() * (c - Math.floor(c)) + mappedA.bValue() * (Math.ceil(c) - c);

        double nB = mappedD.bValue() * (c - Math.floor(c)) + mappedC.bValue() * (Math.ceil(c) - c);

        int newB = (int) Math.rint(nB * (r - Math.floor(r)) + mB * (Math.ceil(r) - r));

        // make the new Pixel for the location of the new downsized image
        Pixel newPixel = new Pixel(newR,newG,newB,0);

        newPic[row][col] = newPixel;
      }
    }
    return newPic;
  }
}
