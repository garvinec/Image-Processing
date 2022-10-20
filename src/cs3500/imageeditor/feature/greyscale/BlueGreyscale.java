package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.model.Pixel;

/**
 * Represents the Blue Greyscale command of the command design. This class's purpose is to
 * create a blue emphasized greyscale. Once this object has been created, and the method
 * apply() is called, then it will apply the blue emphasized greyscale.
 */
public class BlueGreyscale extends AbstractGreyscale {

  /**
   * Constructor to initialize the BlueGreyscale object.
   */
  public BlueGreyscale() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Creates a new pixel that helps visualize the current pixel's blue component.
   * @param current Input pixel to be changed through new calculations.
   * @return a new Pixel that visualizes the blue component of the pixel.
   */
  @Override
  protected Pixel newPixelMaker(Pixel current) {
    return new Pixel(current.bValue(), current.bValue(), current.bValue(), current.aValue());
  }
}