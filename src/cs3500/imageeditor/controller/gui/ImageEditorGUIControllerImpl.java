package cs3500.imageeditor.controller.gui;

import static cs3500.imageeditor.imagereader.Load.readConventional;
import static cs3500.imageeditor.imagereader.Load.readPPM;
import static cs3500.imageeditor.imagereader.SaveGUI.saveConventional;
import static cs3500.imageeditor.imagereader.SaveGUI.savePPM;
import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;

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
import cs3500.imageeditor.view.gui.ImageEditorGUI;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageEditorGUIControllerImpl implements ImageEditorGUIController,
    ActionListener, ChangeListener {

  private ImageEditorModel model;
  private final ImageEditorGUI view;
  private final Map<String, Features> features;
  private String loadPath;
  private String fileFormat;

  public ImageEditorGUIControllerImpl(ImageEditorModel model, ImageEditorGUI view) {

    this.model = model;
    this.view = view;

    this.loadPath = null;
    this.fileFormat = null;

    this.features = new HashMap<>();

    addFeatures();
  }

  /**
   * Helper method to add all the commands/features.
   */
  private void addFeatures() {
    features.put("Horizontal", new FlipHorizontal());
    features.put("Vertical", new FlipVertical());
    features.put("Red", new RedGreyscale());
    features.put("Green", new GreenGreyscale());
    features.put("Blue", new BlueGreyscale());
    features.put("Value", new ValueGreyscale());
    features.put("Intensity", new IntensityGreyscale());
    features.put("Luma", new LumaGreyscale());
// fix brightness later
    //    features.put("brighten", new AdjustBrightness(level);
    features.put("Blur", new BlurFilter());
    features.put("Sharpen", new SharpenFilter());
    features.put("Sepia Mode", new SepiaTransformation());
    features.put("Luma Mode", new LumaTransformation());
  }

  @Override
  public void run() throws IllegalStateException {
    this.view.setLoadButtonListener(this);
    this.view.setSaveButtonListener(this);
    this.view.setColorListener(this);
    this.view.setFilterListener(this);
    this.view.setFlipListener(this);
    this.view.setGreyscaleListener(this);
    this.view.setPreviewListener(this);
    this.view.setBrightnessListener(this);
    this.view.makeVisible();
  }

  // Helps render the message
  private void renderMessageHelper(String message) {
    this.view.renderMessage(message);
  }

  // helps render the error message
  private void renderErrorMessageHelper(String error) {
    this.view.renderErrorMessage(error);
  }

  // helper method to convert a model into a buffered image
  private BufferedImage convertModel(ImageEditorModel model, String format) {

    // initialize the image
    BufferedImage img = null;

    // determine the type of buffered image to create depending on the file type
    switch (format.toLowerCase()) {
      case "jpg":
      case "jpeg":
      case "ppm":
        img = new BufferedImage(model.getWidth(), model.getHeight(), TYPE_INT_RGB);
        break;
      case "png":
        img = new BufferedImage(model.getWidth(), model.getHeight(), TYPE_INT_ARGB);
        break;
      case "bmp":
      case "wbmp":
        img = new BufferedImage(model.getWidth(), model.getHeight(), TYPE_3BYTE_BGR);
        break;
    }

    // initialize the color of the pixel
    Color pixelColor;

    // iterate through the model and get the channel values at each pixel of the model
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {

        // set the channel values of each pixel to a local variable to be used later
        int r = model.getPixelAt(i, j).rValue();
        int g = model.getPixelAt(i, j).gValue();
        int b = model.getPixelAt(i, j).bValue();

        // create the "pixel" for the buffered image
        pixelColor = new Color(r, g, b);

        img.setRGB(j, i, pixelColor.getRGB());
      }
    }
    return img;
  }

  // gets the format name of the image (e.g., jpg, png, etc.)
  private String getFormatName(String path) {
    return path.substring(path.lastIndexOf(".") + 1);
  }

  @Override
  public void loadImage(String path) {

    switch (getFormatName(path).toLowerCase()) {
      case "ppm":
        this.model = new ImageEditorModelImp(readPPM(path));
        fileFormat = "ppm";
        break;

      case "jpg":
        model = new ImageEditorModelImp(readConventional(path));
        fileFormat = "jpg";
        break;
      case "png":
        model = new ImageEditorModelImp(readConventional(path));
        fileFormat = "png";
        break;
      case "bmp":
        model = new ImageEditorModelImp(readConventional(path));
        fileFormat = "bmp";
        break;

      default:
        renderErrorMessageHelper(
            "Image format not supported! Please input a compatible format type.\n");
        break;
    }
  }

  @Override
  public void saveImage(String path, String format) {
    switch (format.toLowerCase()) {
      case "ppm":
        savePPM(path, model);
        renderMessageHelper("You've saved the image\n");
        break;
      case "jpg":
      case "png":
        saveConventional(path, format, model);
        renderMessageHelper("You've saved the image\n");
        break;
      default:
        renderErrorMessageHelper("Image format not supported\n");
        break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    Features feature = null;

    switch (e.getActionCommand()) {
      case "Open file": {
        final JFileChooser fchooser = new JFileChooser(".");
        fchooser.setAcceptAllFileFilterUsed(true);
        int retvalue = fchooser.showOpenDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          loadPath = f.getAbsolutePath();
          loadImage(loadPath);
          view.setImageLabel(new ImageIcon(loadPath));
          view.setRedHistogramPanel(model);
          view.setGreenHistogramPanel(model);
          view.setBlueHistogramPanel(model);
          view.setIntensityHistogramPanel(model);
        }
        break;
      }
      case "Save file": {
        final JFileChooser fchooser = new JFileChooser(".");
        fchooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(
            "JPG", "jpg");
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(
            "PNG", "png");
        FileNameExtensionFilter bmpFilter = new FileNameExtensionFilter(
            "BMP", "bmp");
        FileNameExtensionFilter ppmFilter = new FileNameExtensionFilter(
            "PPM", "ppm");
        fchooser.setFileFilter(ppmFilter);
        fchooser.setFileFilter(bmpFilter);
        fchooser.setFileFilter(jpgFilter);
        fchooser.setFileFilter(pngFilter);

        int retvalue = fchooser.showSaveDialog(view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          saveImage(f.getAbsolutePath(), fchooser.getFileFilter().getDescription());
        }
      }
      break;
      case "Flip options": {

        if (model.getWidth() == 0 || model.getHeight() == 0) {
          renderErrorMessageHelper("Open a file before editing");
        } else {
          String[] options = {"Horizontal", "Vertical"};
          int retvalue = JOptionPane.showOptionDialog(view, "Please choose a direction",
              "Flip", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
              options[1]);

          if (retvalue == 0) {
            feature = new FlipVertical();
          }

          if (retvalue == 1) {
            feature = new FlipHorizontal();
          }

          ImageEditorModel newModel = new ImageEditorModelImp(feature.modify(this.model));
          BufferedImage img = convertModel(newModel, fileFormat);
          model = newModel;
          view.setImageLabel(new ImageIcon(img));
          view.setRedHistogramPanel(model);
          view.setGreenHistogramPanel(model);
          view.setBlueHistogramPanel(model);
          view.setIntensityHistogramPanel(model);
        }
      }
      break;
      case "Filter options": {

        if (model.getWidth() == 0 || model.getHeight() == 0) {
          renderErrorMessageHelper("Open a file before editing");
        } else {
          String[] options = {"Blur", "Sharpen"};
          int retvalue = JOptionPane.showOptionDialog(view, "Please choose a filter",
              "Filter", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
              options[1]);

          if (retvalue == 0) {
            feature = new BlurFilter();
          }

          if (retvalue == 1) {
            feature = new SharpenFilter();
          }

          ImageEditorModel newModel = new ImageEditorModelImp(feature.modify(this.model));
          BufferedImage img = convertModel(newModel, fileFormat);
          model = newModel;
          view.setImageLabel(new ImageIcon(img));
          view.setRedHistogramPanel(model);
          view.setGreenHistogramPanel(model);
          view.setBlueHistogramPanel(model);
          view.setIntensityHistogramPanel(model);
        }
      }
      break;
      case "Greyscale options": {

        if (model.getWidth() == 0 || model.getHeight() == 0) {
          renderErrorMessageHelper("Open a file before editing");
        } else {
          String[] options = {"Red", "Green", "Blue", "Value", "Intensity", "Luma"};
          int retvalue = JOptionPane.showOptionDialog(view, "Please choose a greyscale mode.",
              "Greyscale", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
              options[5]);

          feature = features.get(options[retvalue]);

          ImageEditorModel newModel = new ImageEditorModelImp(feature.modify(this.model));
          BufferedImage img = convertModel(newModel, fileFormat);
          model = newModel;
          view.setImageLabel(new ImageIcon(img));
          view.setRedHistogramPanel(model);
          view.setGreenHistogramPanel(model);
          view.setBlueHistogramPanel(model);
          view.setIntensityHistogramPanel(model);
        }
      }
      break;
      case "Color options": {

        if (model.getWidth() == 0 || model.getHeight() == 0) {
          renderErrorMessageHelper("Open a file before editing");
        } else {
          String[] options = {"Sepia", "Luma"};
          int retvalue = JOptionPane.showOptionDialog(view, "Please choose a greyscale mode.",
              "Greyscale", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
              options[1]);

          if (retvalue == 0) {
            feature = new SepiaTransformation();
          }

          if (retvalue == 1) {
            feature = new LumaTransformation();
          }

          ImageEditorModel newModel = new ImageEditorModelImp(feature.modify(this.model));
          BufferedImage img = convertModel(newModel, fileFormat);
          model = newModel;
          view.setImageLabel(new ImageIcon(img));
          view.setRedHistogramPanel(model);
          view.setGreenHistogramPanel(model);
          view.setBlueHistogramPanel(model);
          view.setIntensityHistogramPanel(model);
        }
      }
      break;
    }

    view.refresh();
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    Features feature = new AdjustBrightness(source.getValue());
    ImageEditorModel newModel = new ImageEditorModelImp(feature.modify(this.model));
    BufferedImage img = convertModel(newModel, fileFormat);
    model = newModel;
    view.setImageLabel(new ImageIcon(img));
    view.refresh();
  }
}
