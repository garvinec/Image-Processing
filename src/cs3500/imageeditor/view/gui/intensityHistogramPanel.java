package cs3500.imageeditor.view.gui;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.ImageEditorModelImp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collections;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class intensityHistogramPanel extends JPanel {

  private ImageEditorModel model;

  public intensityHistogramPanel() {
    super();
    this.model = new ImageEditorModelImp();
    this.setBorder(BorderFactory.createTitledBorder("Intensity"));
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(300, 200));
  }

  // set model for Panel to take in
  public void setIntensityHistogramModel(ImageEditorModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Map<Integer, Integer> intensityMap = model.intensityMap();

    int x = 0;

    for (Integer key: intensityMap.keySet()) {

      int scale = Collections.max(intensityMap.values()) / 200;

      int width = 1;
      int height = intensityMap.get(key) / scale;

      int y = 200 - height;
      x += 1;

      g.setColor(Color.yellow);
      g.fillRect(x, y, width, height);
    }
  }
}
