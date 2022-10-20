package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.model.Pixel;

/**
 * Represents the Value Greyscale command of the command design. This class's purpose is to
 * create a value emphasized greyscale. Value is calculated by taking the component of the pixel
 * with the max value and setting all other components equal to this value.
 * Once this object has been created, and the method apply() is called,
 * then it will apply the intensity emphasized greyscale.
 */
public class ValueGreyscale extends AbstractGreyscale {

  /**
   * Constructor initializes the ValueGreyscale object.
   */
  public ValueGreyscale() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Creates a new pixel that helps visualize the current pixel's "value".
   * The value is calculated by taking the current pixel's max value component and setting
   * the remaining components equal to the component with that max value.
   * @param current Input pixel to be changed through new calculations.
   * @return a new Pixel that visualizes the value component of the pixel.
   */
  @Override
  protected Pixel newPixelMaker(Pixel current) {

    int value = Math.max(current.rValue(), Math.max(current.gValue(), current.bValue()));

    return new Pixel(value, value, value, current.aValue());
  }
}