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
public class Save {


  /**
   * Saves ppm file to the specified path.
   *
   * @param path   name of the path where the file will be saved
   * @param name   name of the image
   * @param stored stored contents of the modified images
   */
  public static void savePPM(String path, String name, Map<String, ImageEditorModel> stored) {

    OutputStream os = null;

    StringBuilder imageData = new StringBuilder();

    imageData.append("P3\n");
    imageData.append(stored.get(name).getWidth());
    imageData.append(" ").append(stored.get(name).getHeight()).append("\n");
    imageData.append("255\n");

    for (int i = 0; i < stored.get(name).getHeight(); i++) {
      for (int j = 0; j < stored.get(name).getWidth(); j++) {
        imageData.append(stored.get(name).getPixelAt(i, j).rValue()).append("\n");
        imageData.append(stored.get(name).getPixelAt(i, j).gValue()).append("\n");
        imageData.append(stored.get(name).getPixelAt(i, j).bValue()).append("\n");
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
   * @param name   name of the image
   * @param format name of the format (e.g., jpg)
   * @param stored contents of the modified images
   */
  public static void saveConventional(String path, String name, String format,
      Map<String, ImageEditorModel> stored) {

    BufferedImage img = null;

    Color pixelColor = null;

    for (int i = 0; i < stored.get(name).getHeight(); i++) {
      for (int j = 0; j < stored.get(name).getWidth(); j++) {
        int r = stored.get(name).getPixelAt(i, j).rValue();
        int g = stored.get(name).getPixelAt(i, j).gValue();
        int b = stored.get(name).getPixelAt(i, j).bValue();
        int a = stored.get(name).getPixelAt(i, j).aValue();

        switch (format) {
          case "jpg":
          case "jpeg":
            img = new BufferedImage(stored.get(name).getWidth(), stored.get(name).getHeight(),
                TYPE_INT_RGB);
            pixelColor = new Color(r, g, b);
            break;

          case "png":
            img = new BufferedImage(stored.get(name).getWidth(), stored.get(name).getHeight(),
                TYPE_INT_ARGB);
            pixelColor = new Color(r, g, b, a);
            break;

          case "bmp":
          case "wbmp":
            img = new BufferedImage(stored.get(name).getWidth(), stored.get(name).getHeight(),
                TYPE_3BYTE_BGR);
            pixelColor = new Color(r, g, b);
            break;

          default:
            // do nothing, other formats should be ignored
            break;
        }

        img.setRGB(j, i, pixelColor.getRGB());
      }
    }

    try {
      File output = new File(path);

      ImageIO.write(img, format, output);
      System.out.println("Image saved successfully");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}