package cs3500.imageeditor.view.gui;

import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.ImageEditorModelImp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This panel represents the histogram of the loaded image.
 */
public class redHistogramPanel extends JPanel {

  private ImageEditorModel model;

  public redHistogramPanel() {
    super();
    this.model = new ImageEditorModelImp();
    this.setBorder(BorderFactory.createTitledBorder("Red"));
    this.setLayout(new BorderLayout());
    this.setBackground(Color.black);
    this.setOpaque(false);
    this.setPreferredSize(new Dimension(300, 200));
  }

  // set model for Panel to take in
  public void setRedHistogramModel(ImageEditorModel model) {
    this.model = model;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Map<Integer, Integer> redMap = model.redMap();

    int x = 0;

    for (Integer key: redMap.keySet()) {

      int scale = Collections.max(redMap.values()) / 200;

      int width = 1;
      int height = redMap.get(key) / scale;

      int y = 200 - height;
      x += 1;

      g.setColor(Color.RED);
      g.fillRect(x, y, width, height);
    }
  }
}
