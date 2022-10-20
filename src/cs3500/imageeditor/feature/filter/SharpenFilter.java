package cs3500.imageeditor.feature.filter;

/**
 * Sharpen Filter is a class that represents the command of the command design. This class is
 * a feature that allows for the image to be sharpened by applying a kernel and changing the pixel
 * with a weighted sharpen. The kernel is a 3 by 3 that has values to help apply a weighted sharpen
 * when calculated correctly.
 */
public class SharpenFilter extends AbstractFilter {

  /**
   * Constructor that initializes the command Sharpen so that it can be called on an image
   * and sharpen it as the user would like.
   */
  public SharpenFilter() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Produces the kernel that is to be used to sharpen the image.
   * @return a 2D Array of double that represent the kernel.
   */
  @Override
  protected double[][] kernel() {

    double[][] sharpen = new double[5][5];

    sharpen[0][0] = -.125;
    sharpen[0][1] = -.125;
    sharpen[0][2] = -.125;
    sharpen[0][3] = -.125;
    sharpen[0][4] = -.125;
    sharpen[1][0] = -.125;
    sharpen[1][1] = .25;
    sharpen[1][2] = .25;
    sharpen[1][3] = .25;
    sharpen[1][4] = -.125;
    sharpen[2][0] = -.125;
    sharpen[2][1] = .25;
    sharpen[2][2] = 1;
    sharpen[2][3] = .25;
    sharpen[2][4] = -.125;
    sharpen[3][0] = -.125;
    sharpen[3][1] = .25;
    sharpen[3][2] = .25;
    sharpen[3][3] = .25;
    sharpen[3][4] = -.125;
    sharpen[4][0] = -.125;
    sharpen[4][1] = -.125;
    sharpen[4][2] = -.125;
    sharpen[4][3] = -.125;
    sharpen[4][4] = -.125;

    return sharpen;
  }
}
