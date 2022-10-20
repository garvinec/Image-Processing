package cs3500.imageeditor.feature.transformation;

/**
 * Represents the Luma Transformation command of the command design. This class is a feature
 * that allows for the image to be greyscale according to a luma matrix transformation.
 * In other words, it allows for a weighted greyscale. The matrix for this greyscale is a
 * 3 by 3 matrix with weighted R, G, and B values.
 */
public class LumaTransformation extends AbstractColorTransformation {


  /**
   * Constructor initializes the command Luma Transformation so that it can be called on an
   * image and greyscale that image.
   */
  public LumaTransformation() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }


  /**
   * Generates the color transformation matrix the operation uses to apply to the image.
   * @return 2D Array of values that represent the color transformation matrix.
   */
  @Override
  protected double[][] color() {

    double[][] luma = new double[3][3];

    luma[0][0] = .2126;
    luma[0][1] = .7152;
    luma[0][2] = .0722;
    luma[1][0] = .2126;
    luma[1][1] = .7152;
    luma[1][2] = .0722;
    luma[2][0] = .2126;
    luma[2][1] = .7152;
    luma[2][2] = .0722;

    return luma;
  }
}
