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

public class greenHistogramPanel extends JPanel {
  private ImageEditorModel model;

  public greenHistogramPanel() {
    super();
    this.model = new ImageEditorModelImp();
    this.setBorder(BorderFactory.createTitledBorder("Green"));
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(300, 200));
  }

  // set model for Panel to take in
  public void setGreenHistogramModel(ImageEditorModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Map<Integer, Integer> greenMap = model.greenMap();

    int x = 0;

    for (Integer key: greenMap.keySet()) {

      int scale = Collections.max(greenMap.values()) / 250;

      int width = 1;
      int height = greenMap.get(key) / scale;

      int y = 250 - height;
      x += 1;

      g.setColor(Color.GREEN);
      g.fillRect(x, y, width, height);
    }
  }
}
