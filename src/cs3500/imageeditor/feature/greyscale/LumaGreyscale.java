package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.model.Pixel;

/**
 * Represents the Luma Greyscale command of the command design. This class's purpose is to
 * create a luma emphasized greyscale. Luma value is calculated by multiplying the three components
 * by different values to create a weighted greyscale.
 * Once this object has been created, and the method apply() is called,
 * then it will apply the luma emphasized greyscale.
 */
public class LumaGreyscale extends AbstractGreyscale {

  /**
   * Constructor initializes the LumaGreyscale object.
   */
  public LumaGreyscale() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Creates a new pixel that helps visualize the current pixel's luma value.
   * The luma value is a weighted greyscale that multiplies the 3 components of the pixel
   * by different numbers to generate this weighted greyscale.
   * @param current Input pixel to be changed through new calculations.
   * @return a new Pixel that visualizes the luma component of the pixel.
   */
  @Override
  protected Pixel newPixelMaker(Pixel current) {

    int luma = (int) (.2126 * current.rValue() +
            .7152 * current.gValue() +
            .0722 * current.bValue());

    return new Pixel(luma, luma, luma, current.aValue());
  }
}
