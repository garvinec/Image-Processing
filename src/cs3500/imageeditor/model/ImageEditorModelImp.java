package cs3500.imageeditor.model;

import java.util.HashMap;

/**
 * Class that represents the model of ImageEditor. This class houses the information for the
 * commands to use and access. Stores all the information needed for the commands in the design
 * to execute correctly.
 */
public class ImageEditorModelImp implements ImageEditorModel {

  private final Pixel[][] image;

  private final int width;

  private final int height;

  /**
   * Constructor initializes the model by giving it the image in the form of a 2D Array.
   * @param image 2D Array of Pixels that represents the image being edited
   * @throws IllegalArgumentException if the representation of the image is null
   */

  public ImageEditorModelImp(Pixel[][] image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Can't have a null representation of an image");
    }

    this.image = image;
    this.width = image[0].length;
    this.height = image.length;
  }

  /**
   * Starting constructor for the main program.
   */
  public ImageEditorModelImp() {

    this.image = null;
    this.width = 0;
    this.height = 0;
  }

  /**
   * Gets the Pixel at the given location (row, col).
   * @param row the row number of the pixel
   * @param col the column number of the pixel
   * @return Pixel that is at the coordinates given.
   * @throws IllegalArgumentException if the coordinates are outside the 2D Array.
   */
  @Override
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= this.height || col >= this.width) {
      throw new IllegalArgumentException("Can't have out of bounds Pixel");
    }

    return image[row][col];
  }

  /**
   * Obtains the height of the 2D Array that represents the image.
   * @return int that represents the height of the 2D Array of Pixels.
   */
  @Override
  public int getHeight() {

    return this.height;
  }

  /**
   * Obtains the width of the 2D Array that represents the image.
   * @return int that represents the width of the 2D Array of Pixels.
   */
  @Override
  public int getWidth() {

    return this.width;
  }

  /**
   * Obtains a hashmap filled with all the values of the r channel and how often they appear.
   * @return hashmap of the red frequencies of the image.
   */
  @Override
  public HashMap<Integer, Integer> redMap() {

    // create a new hashmap
    HashMap<Integer, Integer> redMap = new HashMap<>();

    // iterate through the image pixel by pixel
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel
        Pixel current = this.getPixelAt(row, col);

        // get the r channel value of the current pixel
        int curR = current.rValue();

        redMap.put(curR, redMap.getOrDefault(curR, 0) + 1);
      }
    }
    return redMap;
  }


  /**
   * Obtains a hashmap filled with all the values of the g channel and how often they appear.
   * @return hashmap of the green frequencies of the image.
   */
  @Override
  public HashMap<Integer, Integer> greenMap() {
    // create a new hashmap
    HashMap<Integer, Integer> greenMap = new HashMap<>();

    // iterate through the image pixel by pixel
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel
        Pixel current = this.getPixelAt(row, col);

        // get the r channel value of the current pixel
        int curG = current.gValue();

        greenMap.put(curG, greenMap.getOrDefault(curG, 0) + 1);
      }
    }
    return greenMap;
  }

  /**
   * Obtains a hashmap filled with all the values of the b channel and how often they appear.
   * @return hashmap of the blue frequencies of the image.
   */
  @Override
  public HashMap<Integer, Integer> blueMap() {
    // create a new hashmap
    HashMap<Integer, Integer> blueMap = new HashMap<>();

    // iterate through the image pixel by pixel
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel
        Pixel current = this.getPixelAt(row, col);

        // get the r channel value of the current pixel
        int curB = current.bValue();

        blueMap.put(curB, blueMap.getOrDefault(curB, 0) + 1);
      }
    }
    return blueMap;
  }

  @Override
  public HashMap<Integer, Integer> intensityMap() {
    // create a new hashmap
    HashMap<Integer, Integer> intensityMap = new HashMap<>();

    // iterate through the image pixel by pixel
    for (int row = 0; row < this.getHeight(); row++) {
      for (int col = 0; col < this.getWidth(); col++) {

        // current pixel
        Pixel current = this.getPixelAt(row, col);

        // get the r channel value of the current pixel
        int curI = (current.rValue() + current.bValue() + current.gValue()) / 3;

        intensityMap.put(curI, intensityMap.getOrDefault(curI, 0) + 1);
      }
    }
    return intensityMap;
  }
}
