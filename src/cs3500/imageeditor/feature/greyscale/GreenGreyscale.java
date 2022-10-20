package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.model.Pixel;

/**
 * Represents the Green Greyscale command of the command design. This class's purpose is to
 * create a green emphasized greyscale. Once this object has been created, and the method
 * apply() is called, then it will apply the green emphasized greyscale.
 */
public class GreenGreyscale extends AbstractGreyscale {

  /**
   * Constructor to initialize the GreenGreyscale object.
   */
  public GreenGreyscale() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Creates a new pixel that helps visualize the current pixel's green component.
   * @param current Input pixel to be changed through new calculations.
   * @return a new Pixel that visualizes the green component of the pixel.
   */
  @Override
  protected Pixel newPixelMaker(Pixel current) {
    return new Pixel(current.gValue(), current.gValue(), current.gValue(), current.aValue());
  }
}