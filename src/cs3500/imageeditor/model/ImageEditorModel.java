package cs3500.imageeditor.model;

import java.util.HashMap;

/**
 * This interface represents the operation offered by the ImageEditor model.
 */
public interface ImageEditorModel {

  /**
   * returns the pixel at the specified location.
   *
   * @param row the row number of the pixel
   * @param col the column number of the pixel
   * @return a pixel which has the RGB components
   */
  Pixel getPixelAt(int col, int row);

  /**
   * returns the height of the image.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * returns the width of the image.
   *
   * @return width of the image
   */
  int getWidth();

  /**
   * returns the red component frequencies of every pixel in the image.
   * @return hashmap of all the red component values (key) and the amount (value)
   */
  HashMap<Integer, Integer> redMap();

  /**
   * returns the green component frequencies of every pixel in the image.
   * @return hashmap of all the green component value (key) and the amount (value)
   */
  HashMap<Integer, Integer> greenMap();

  /**
   * returns the blue component frequencies of every pixel in the image.
   * @return hashmap of all the green component value (key) and the amount (value)
   */
  HashMap<Integer, Integer> blueMap();

  /**
   * returns the intensity component frequencies of every pixel in the image.
   * @return hashmap of all the intensity component value (key) and the amount (value)
   */
  HashMap<Integer, Integer> intensityMap();
}
