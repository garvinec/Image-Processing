package cs3500.imageeditor.imagereader;

import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

import cs3500.imageeditor.model.ImageEditorModel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * This class has the method to read a PPM image from file and simply print its contents.
 * Produces a physical image at the desired path with the desired name that can be viewed.
 */
public class SaveGUI {

  /**
   * Saves ppm file to the specified path.
   *
   * @param path   name of the path where the file will be saved
   * @param model the most recent model
   */
  public static void savePPM(String path, ImageEditorModel model) {

    OutputStream os = null;

    StringBuilder imageData = new StringBuilder();

    imageData.append("P3\n");
    imageData.append(model.getWidth());
    imageData.append(" ").append(model.getHeight()).append("\n");
    imageData.append("255\n");

    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        imageData.append(model.getPixelAt(i, j).rValue()).append("\n");
        imageData.append(model.getPixelAt(i, j).gValue()).append("\n");
        imageData.append(model.getPixelAt(i, j).bValue()).append("\n");
      }
    }

    try {
      String output = imageData.toString();
      byte[] byteData = output.getBytes();
      os = new FileOutputStream(path);
      for (int i = 0; i < byteData.length; i++) {
        os.write(byteData[i]);
      }
      System.out.println("Image saved successfully");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        os.close();
      } catch (IOException e) {
        // throws IOException
      }
    }
  }

  /**
   * Saves image with conventional format to the specified path.
   *
   * @param path   name of the path where the file will be saved
   * @param format name of the format (e.g., jpg)
   * @param model the most recent model
   */
  public static void saveConventional(String path, String format, ImageEditorModel model) {

    BufferedImage img = null;

    Color pixelColor;

    switch (format.toLowerCase()) {
      case "jpg":
      case "jpeg":
        img = new BufferedImage(model.getWidth(), model.getHeight(),
            TYPE_INT_RGB);
        break;

      case "png":
        img = new BufferedImage(model.getWidth(), model.getHeight(),
            TYPE_INT_ARGB);
        break;

      default:
        // do nothing, other formats should be ignored
        break;
    }

    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        int r = model.getPixelAt(i, j).rValue();
        int g = model.getPixelAt(i, j).gValue();
        int b = model.getPixelAt(i, j).bValue();

        pixelColor = new Color(r, g, b);

        img.setRGB(j, i, pixelColor.getRGB());
      }
    }

    try {
      File output = new File(path);

      ImageIO.write(img, format.toLowerCase(), output);
      System.out.println("Image saved successfully");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
