package cs3500.imageeditor.feature.filter;

/**
 * Blur Filter is a class that represents the command of the command design. This class is a feature
 * that allows for the image to be blurred by applying a kernel and changing the pixel with a
 * weighted blur. The kernel is a 3 by 3 that has values to help apply a weighted blur when
 * calculated correctly.
 */
public class BlurFilter extends AbstractFilter {

  /**
   * Constructor that initializes the command Blur so that it can be called on an image
   * and blur it as the user would like.
   */
  public BlurFilter() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Produces the kernel that is to be used to apply the blur.
   * @return a 2D Array of double that represent the kernel.
   */
  @Override
  protected double[][] kernel() {

    double[][] blur = new double[3][3];

    blur[0][0] = 0.0625;
    blur[0][1] = 0.125;
    blur[0][2] = 0.0625;
    blur[1][0] = 0.125;
    blur[1][1] = 0.25;
    blur[1][2] = 0.125;
    blur[2][0] = 0.0625;
    blur[2][1] = 0.125;
    blur[2][2] = 0.0625;

    return blur;
  }

}
