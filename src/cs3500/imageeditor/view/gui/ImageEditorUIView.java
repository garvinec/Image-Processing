package cs3500.imageeditor.view.gui;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;

/**
 * Interface to represent the output/view of the Image Editor. So far it only produces or renders
 * helpful messages.
 */

public interface ImageEditorUIView {

  void makeVisible();

  void setLoadButtonListener(ActionListener actionEvent);

  void setSaveButtonListener(ActionListener actionEvent);

  void setFlipListener(ActionListener actionEvent);

  void setFilterListener(ActionListener actionEvent);

  void setGreyscaleListener(ActionListener actionEvent);

  void setColorListener(ActionListener actionEvent);

  void setRedHistogramPanel(ImageEditorModel model);

  void setBlueHistogramPanel(ImageEditorModel model);

  void setGreenHistogramPanel(ImageEditorModel model);
  void setIntensityHistogramPanel(ImageEditorModel model);

  void setImageLabel(ImageIcon image);

  void setPreviewListener(ActionListener actionEvent);

  void setBrightnessListener(ChangeListener changeEvent);

  void refresh();

  void renderErrorMessage(String error);

  void renderMessage(String message);
}
