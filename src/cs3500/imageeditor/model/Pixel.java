package cs3500.imageeditor.model;

/**
 * Represents a set of 3 R, G, B values that combine to make a "pixel" of a certain color.
 */

public class Pixel {

  private final int r;
  // INVARIANT: r has to be a value from 0-255
  private final int g;
  // INVARIANT: g has to be a value from 0-255
  private final int b;
  // INVARIANT: b has to be a value from 0-255
  private final int a;
  // INVARIANT a has to be a value from 0-255

  /**
   * Constructor initializes a Pixel that has an r, g, b, and a value.
   *
   * @param r value of the red component of the pixel
   * @param g value of the green component of the pixel
   * @param b value of the blue component of the pixel
   * @param a value of the alpha component of the pixel
   * @throws IllegalArgumentException when the values are out of the range of 0-255.
   */

  public Pixel(int r, int g, int b, int a) throws IllegalArgumentException {
    if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255 || a < 0 || a > 255) {
      throw new IllegalArgumentException("Invalid value for r, g, b, or a");
    }
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }

  /**
   * New definition of equality to accurately compare two pixels and their components.
   *
   * @param o object that the current object is being compared to
   * @return boolean either true or false if the current object is "equal" to the other.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pixel)) {
      return false;
    }
    Pixel other = (Pixel) o;

    return other.rValue() == this.rValue()
           && other.gValue() == this.gValue()
           && other.bValue() == this.bValue();
  }

  /**
   * New hashcode calculations to determine equal objects.
   *
   * @return int that represents the hashcode of the object.
   */
  @Override
  public int hashCode() {
    return this.gValue() * 200 + rValue();
  }

  /**
   * Obtains the value of the R component of the pixel.
   *
   * @return int, the value of the R component of the pixel.
   */
  public int rValue() {

    return this.r;

  }

  /**
   * Obtains the value of the G component of the pixel.
   *
   * @return int, the value of the G component of the pixel.
   */

  public int gValue() {

    return this.g;

  }

  /**
   * Obtains the value of the B component of the pixel.
   *
   * @return int, the value of the B component of the pixel.
   */

  public int bValue() {

    return this.b;
  }

  /**
   * Obtains the value of the alpha component of the pixel.
   *
   * @return int, the value of the alpha component of the pixel.
   */
  public int aValue() {

    return this.a;
  }
}
