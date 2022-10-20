package cs3500.imageeditor.imagereader;

import cs3500.imageeditor.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This class contains the method to read an image with conventional formats (jpg, png, and bmp) and
 * prints its contents.
 */
public class LoadGUI {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path name of the image.
   * @return the content of the image in the form of a 2D Array of Pixels
   */
  public static BufferedImage readPPM(String filename) {
    Scanner sc;

    BufferedImage img;

    Color pixelColor;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalStateException("File not found");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pixelColor = new Color(r, g, b);

        img.setRGB(j, i, pixelColor.getRGB());
      }
    }
    return img;
  }

  /**
   * Read an image file in conventional formats (e.g., jpg, png, bmp, etc.) and prints its contents.
   *
   * @param filename the path name of the image
   * @return the content of the image in the form of a 2D Array of Pixels
   */
  public static BufferedImage readConventional(String filename) {

    BufferedImage img;

    Color pixelColor;

    try {
      img = ImageIO.read(new File(filename));
    } catch (IOException e) {
      System.out.println("File " + filename + " not found!");
      throw new IllegalStateException("File not found");
    }

    int width = img.getWidth();
    int height = img.getHeight();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        pixelColor = new Color(img.getRGB(j, i));

        int r = pixelColor.getRed();
        int g = pixelColor.getGreen();
        int b = pixelColor.getBlue();
        int a = pixelColor.getAlpha();

       pixelColor = new Color(r, g, b, a);

       img.setRGB(j, i, pixelColor.getRGB());
      }
    }
    return img;
  }
}