package cs3500.imageeditor.feature.transformation;

/**
 * Represents the Sepia Transformation command of the command design. This class is a feature
 * that allows for the image to be greyscale according to a sepia matrix transformation.
 * In other words, it allows for a weighted greyscale. The matrix for this greyscale is a
 * 3 by 3 matrix with weighted R, G, and B values.
 */
public class SepiaTransformation extends AbstractColorTransformation {

  /**
   * Constructor initializes the command Sepia Transformation so that it can be called on an
   * image and greyscale that image.
   */
  public SepiaTransformation() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Generates the color transformation matrix the operation uses to apply to the image.
   * @return 2D Array of values that represent the color transformation matrix.
   */
  @Override
  protected double[][] color() {

    double[][] sepia = new double[3][3];

    sepia[0][0] = .393;
    sepia[0][1] = .769;
    sepia[0][2] = .189;
    sepia[1][0] = .349;
    sepia[1][1] = .686;
    sepia[1][2] = .168;
    sepia[2][0] = .272;
    sepia[2][1] = .534;
    sepia[2][2] = .131;

    return sepia;
  }
}
