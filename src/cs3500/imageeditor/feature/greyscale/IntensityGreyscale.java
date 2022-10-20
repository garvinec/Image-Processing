package cs3500.imageeditor.feature.greyscale;

import cs3500.imageeditor.model.Pixel;

/**
 * Represents the Intensity Greyscale command of the command design. This class's purpose is to
 * create an intensity emphasized greyscale. Intensity value is just the average of all 3 components
 * Once this object has been created, and the method apply() is called,
 * then it will apply the intensity emphasized greyscale.
 */
public class IntensityGreyscale extends AbstractGreyscale {

  /**
   * Constructor to initialize the IntensityGreyscale object.
   */
  public IntensityGreyscale() {
    // Constructor should be empty because it doesn't need to take in any arguments to execute
    // the edit correctly, and acts as a command in the command design.
  }

  /**
   * Creates a new pixel that helps visualize the current pixel's intensity value.
   * The intensity value is the average of the 3 components.
   * @param current Input pixel to be changed through new calculations.
   * @return a new Pixel that visualizes the intensity component of the pixel.
   */
  @Override
  protected Pixel newPixelMaker(Pixel current) {

    int intensity = (current.rValue() + current.gValue() + current.bValue()) / 3;

    return new Pixel(intensity, intensity, intensity, current.aValue());
  }
}
