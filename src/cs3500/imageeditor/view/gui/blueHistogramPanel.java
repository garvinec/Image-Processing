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

/**
 * This panel represents the histogram of the loaded image.
 */
public class blueHistogramPanel extends JPanel {

  private ImageEditorModel model;

  public blueHistogramPanel() {
    super();
    this.model = new ImageEditorModelImp();
    this.setBorder(BorderFactory.createTitledBorder("Blue"));
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(300, 200));
  }

  // set model for Panel to take in
  public void setBlueHistogramModel(ImageEditorModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Map<Integer, Integer> blueMap = model.blueMap();

    int x = 0;

    for (Integer key: blueMap.keySet()) {

      int scale = Collections.max(blueMap.values()) / 200;

      int width = 1;
      int height = blueMap.get(key) / scale;

      int y = 200 - height;
      x += 1;

      g.setColor(Color.BLUE);
      g.fillRect(x, y, width, height);
    }
  }
}
