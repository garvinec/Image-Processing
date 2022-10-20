package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.model.Pixel;

/**
 * Represents the Red Greyscale command of the command design. This class's purpose is to
 * create a red emphasized greyscale. Once this object has been created, and the method
 * apply() is called, then it will apply the red emphasized greyscale.
 */
public class RedGreyscale extends AbstractGreyscale {

  /**
   * Constructor initializes the RedGreyscale object.
   */
  public RedGreyscale() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Creates a new pixel that helps visualize the current pixel's red component.
   * @param current Input pixel to be changed through new calculations.
   * @return a new Pixel that visualizes the red component of the pixel.
   */
  @Override
  protected Pixel newPixelMaker(Pixel current) {
    return new Pixel(current.rValue(), current.rValue(), current.rValue(), current.aValue());
  }
}
