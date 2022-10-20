package cs3500.imageeditor.controller;


import static cs3500.imageeditor.imagereader.Load.readConventional;
import static cs3500.imageeditor.imagereader.Load.readPPM;
import static cs3500.imageeditor.imagereader.Save.saveConventional;
import static cs3500.imageeditor.imagereader.Save.savePPM;

import cs3500.imageeditor.feature.AdjustBrightness;
import cs3500.imageeditor.feature.Features;
import cs3500.imageeditor.feature.filter.BlurFilter;
import cs3500.imageeditor.feature.filter.SharpenFilter;
import cs3500.imageeditor.feature.flip.FlipHorizontal;
import cs3500.imageeditor.feature.flip.FlipVertical;
import cs3500.imageeditor.feature.greyscale.BlueGreyscale;
import cs3500.imageeditor.feature.greyscale.GreenGreyscale;
import cs3500.imageeditor.feature.greyscale.IntensityGreyscale;
import cs3500.imageeditor.feature.greyscale.LumaGreyscale;
import cs3500.imageeditor.feature.greyscale.RedGreyscale;
import cs3500.imageeditor.feature.greyscale.ValueGreyscale;
import cs3500.imageeditor.feature.transformation.LumaTransformation;
import cs3500.imageeditor.feature.transformation.SepiaTransformation;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.ImageEditorModelImp;
import cs3500.imageeditor.view.ImageEditorView;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

/**
 * This is the controller implementation class. It takes in inputs from users and updates the model
 * and view accordingly.
 */
public class ImageEditorControllerImpl implements ImageEditorController {

  private final ImageEditorView view;
  private final Readable in;
  private final Map<String, ImageEditorModel> stored;
  private final Map<String, Function<Scanner, Features>> features;

  /**
   * This constructor is for the controller of the Image Editor.
   *
   * @param view the view of Image Editor
   * @param in   input given by the user
   * @throws IllegalArgumentException if either the model, view, or in is null
   */
  public ImageEditorControllerImpl(ImageEditorView view, Readable in)
      throws IllegalArgumentException {
    if (view == null || in == null) {
      throw new IllegalArgumentException("view and the input must not be empty");
    }

    this.view = view;
    this.in = in;

    this.stored = new HashMap<>();
    this.features = new HashMap<>();

    addFeatures();
  }

  /**
   * Helper method to add all the commands/features.
   */
  private void addFeatures() {
    features.put("flip-horizontal", s -> new FlipHorizontal());
    features.put("flip-vertical", s -> new FlipVertical());
    features.put("greyscale-red", s -> new RedGreyscale());
    features.put("greyscale-green", s -> new GreenGreyscale());
    features.put("greyscale-blue", s -> new BlueGreyscale());
    features.put("greyscale-value", s -> new ValueGreyscale());
    features.put("greyscale-intensity", s -> new IntensityGreyscale());
    features.put("greyscale-luma", s -> new LumaGreyscale());
    features.put("brighten", s -> new AdjustBrightness(s.nextInt()));
    features.put("blur", s -> new BlurFilter());
    features.put("sharpen", s -> new SharpenFilter());
    features.put("color-sepia", s -> new SepiaTransformation());
    features.put("color-luma", s -> new LumaTransformation());
  }

  // gets the format name of the image (e.g., jpg, png, etc.)
  private String getFormatName(String path) {
    return path.substring(path.lastIndexOf(".") + 1);
  }

  /**
   * Converts the image into a 2D array and saves it in a stored location until further use.
   *
   * @param path name of the path where the image is loaded from
   * @param name name of the image
   */
  @Override
  public void loadImage(String path, String name) {

    ImageEditorModel model;

    switch (getFormatName(path).toLowerCase()) {
      case "ppm":
        model = new ImageEditorModelImp(readPPM(path));
        stored.put(name, model);
        renderMessageHelper("You've loaded the image\n");
        break;

      case "jpg":
      case "jpeg":
      case "png":
      case "bmp":
      case "wbmp":
        model = new ImageEditorModelImp(readConventional(path));
        stored.put(name, model);
        renderMessageHelper("You've loaded the image\n");
        break;

      default:
        renderMessageHelper("Image format not supported! Please input a compatible format type.\n");
        break;
    }
  }

  /**
   * Saves the edited image from the program at the desired file location.
   *
   * @param path   name of the path where the image will be saved
   * @param name   name of the image
   * @param format name of the image format (e.g., jpg)
   * @param stored map of the stored content of existing images
   */
  @Override
  public void saveImage(String path, String name, String format,
      Map<String, ImageEditorModel> stored) {

    switch (format.toLowerCase()) {
      case "ppm":
        savePPM(path, name, stored);
        renderMessageHelper("You've saved the image\n");
        break;
      case "jpg":
      case "jpeg":
      case "png":
      case "bmp":
      case "wbmp":
        saveConventional(path, name, format, stored);
        renderMessageHelper("You've saved the image\n");
        break;
      default:
        renderMessageHelper("Image format not supported\n");
        break;
    }
  }

  // Helps render the message and catches the error if it cannot be rendered.
  private void renderMessageHelper(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Cannot render message");
    }
  }

  /**
   * Runs the program by taking in inputs and executing the correct commands.
   *
   * @throws IllegalStateException if there isn't anything else to read
   */
  @Override
  public void run() throws IllegalStateException {

    // Creates a scanner to take in inputs
    Scanner sc = new Scanner(this.in);

    // status of the Image Editor
    boolean isRunning = true;

    // reads the next user input to give to the mode
    String userInstruction;

    Function<Scanner, Features> cmd;

    Features feature;

    // path of the image file
    String path;

    // name of the image
    String name;

    // displays the welcome message
    welcomeMessage();

    while (isRunning) {

      try {
        userInstruction = sc.next();
      } catch (NoSuchElementException e) {
        throw new IllegalStateException("There isn't anything else to read");
      }

      // do something like
      // if (features.containsKey(userInstruction) &&

      if (features.containsKey(userInstruction)) {
        try {
          cmd = features.get(userInstruction);
          feature = cmd.apply(sc);
          ImageEditorModel model = new ImageEditorModelImp(feature.modify(stored.get(sc.next())));
          stored.put(sc.next(), model);
          renderMessageHelper("Image edited!\n");
        } catch (Exception e) {
          renderMessageHelper("Please type a valid operation.\n");
        }
      } else {
        switch (userInstruction.toLowerCase()) {
          case "q":
          case "quit":
            isRunning = false;
            return;

          case "h":
          case "help":
            commandMenu();
            break;

          case "load":
            path = sc.next();
            name = sc.next();

            try {
              loadImage(path, name);
            } catch (Exception e) {
              renderMessageHelper("File not found! Please enter an existing file path.\n");
            }
            break;

          case "save":
            path = sc.next();
            name = sc.next();

            try {
              saveImage(path, name, getFormatName(path), this.stored);
            } catch (Exception e) {
              renderMessageHelper("File not found! Please enter an existing file to save.\n");
            }
            break;

          default:
            renderMessageHelper("Please give a valid input.\n");
            break;
        }
      }
    }

    displayQuit();

  }

  // produces the welcome message, the first message the user sees
  private void welcomeMessage() {
    renderMessageHelper("Welcome to Image Editor!\n");
    renderMessageHelper("Enter 'h' or 'help' for command menu\n");
    renderMessageHelper("Enter 'q' or 'quit' to quit the Editor\n");
    renderMessageHelper("Load an image first (enter 'h' or 'help' to see how)\n");
  }

  // produces a command menu which shows the user how to use the commands
  private void commandMenu() {

    renderMessageHelper("Here are the commands you could use:\n"
                        + "\n"
                        + "<1> Load an image.\n"
                        + "Use this command to load an image. "
                        + "This command should be used before any operations below in order\n"
                        + "to have the content of the image saved in the program.\n"
                        + "Command: blur <destination image name> <source image name>\n"
                        + "Example: blur testImages/image.png image\n"
                        + "\n"
                        + "<2> Flip an image.\n"
                        + "Use this command to flip an image horizontally or vertically.\n"
                        + "Command: flip-<direction> <destination image name> <source image name>\n"
                        + "Example: flip-vertical testImages/image.png image\n"
                        + "\n"
                        + "<3> Brighten/Darken an image.\n"
                        + "Use this command to make an image brighter "
                        + "or darker by a specified amount.\n"
                        + "Command: brighten <level of brightness/darkness> "
                        + "<destination image name> <source image name>\n"
                        + "Note: Use a negative number if you want to darken the image instead of "
                        + "brightening it.\n"
                        + "Example: brighten 10 testImages/image.png image\n"
                        + "\n"
                        + "<4> Create a greyscale image of an image.\n"
                        + "Use this command to create a greyscale image with the specified "
                        + "component.\n"
                        + "Command: greyscale-<the component to greyscale> <destination image name>"
                        + " <source image name>\n"
                        + "Note: This program allows user to create an greyscale image with 7 "
                        + "different components - red,\n"
                        + "green, blue, intensity, luma, value, and sepia.\n"
                        + "Example: greyscale-red testImages/image.png image\n"
                        + "\n"
                        + "<5> Blur an image.\n"
                        + "Use this command to blur an image.\n"
                        + "Command: blur <destination image name> <source image name>\n"
                        + "Example: blur testImages/image.png image\n"
                        + "\n"
                        + "<6> Sharpen an image.\n"
                        + "Use this command to sharpen an image.\n"
                        + "Command: sharpen <destination image name> <source image name>\n"
                        + "Example: sharpen testImages/image.png image\n"
                        + "\n"
                        + "<7> Color Transformation.\n"
                        + "Use this command to transform the color tone of the image, "
                        + "luma to create greyscale and sepia to\n"
                        + "create a reddish brown tone.\n"
                        + "Command (Greyscale): color-luma <destination image name> "
                        + "<source image name>\n"
                        + "Note: This command creates the same result as using the command:\n"
                        + "greyscale-luma <destination image name> <source image name>\n"
                        + "Example: color-luma testImages/image.png image\n"
                        + "Command (Sepia): color-sepia <destination image name> "
                        + "<source image name>\n"
                        + "Example: color-sepia testImages/image.png image\n"
                        + "\n"
                        + "<8> Save an image.\n"
                        + "Use this command to save an image at the specified location.\n"
                        + "Command: save <destination image name> <source image name>\n"
                        + "Example: save testImages/image.png image\n");
  }

  // display a quit message to show the user that they quit the program
  private void displayQuit() {
    renderMessageHelper("Image Editor Quit");
    renderMessageHelper("Any images saved will appear in the folder the image destination");
  }
}