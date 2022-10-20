package cs3500.imageeditor.view.gui;

import cs3500.imageeditor.model.ImageEditorModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * Represents the text view of the image editor model and implements the interface's methods.
 */
public class ImageEditorGUI extends JFrame implements ImageEditorUIView {

  private JPanel mainPanel;
  private JScrollPane mainScrollPanel;
  private redHistogramPanel redHistogramPanel;
  private blueHistogramPanel blueHistogramPanel;
  private greenHistogramPanel greenHistogramPanel;
  private intensityHistogramPanel intensityHistogramPanel;
  private JPanel histogramPanel;
  private JLabel imageLabel;

  private JLabel checkboxDisplay;
  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JComboBox<String> previewMode;
  private JButton flipMode;
  private JButton greyscaleMode;
  private JButton colorMode;
  private JButton filterMode;
  private JSlider brightnessSlider;
  private JLabel flipModeDisplay;
  private JLabel greyscaleModeDisplay;
  private JLabel colorModeDisplay;
  private JLabel filterModeDisplay;
  private JLabel fileOpenDisplay;
  private JLabel fileSaveDisplay;

  public ImageEditorGUI() {
    super();
    setTitle("Image Editor Program");
    setSize(1500, 900);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
    //scroll bars around this main panel
    mainScrollPanel = new JScrollPane(mainPanel);
    add(mainScrollPanel);

    // view image panel, includes the panel that shows image and choose view option
    JPanel viewPanel = new JPanel();
    viewPanel.setBorder(BorderFactory.createTitledBorder("View Panel"));
    viewPanel.setLayout(new BorderLayout());
    viewPanel.setPreferredSize(new Dimension(600, 50));
    mainPanel.add(viewPanel, BorderLayout.WEST);

    // panel that shows the image
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Preview"));
    imagePanel.setLayout(new BorderLayout());
    imagePanel.setMaximumSize(new Dimension(600, 600));
    //imagePanel.setMaximumSize(null);
    viewPanel.add(imagePanel, BorderLayout.CENTER);

    JScrollPane imageScrollPanel;

    imageLabel = new JLabel();
    imageScrollPanel = new JScrollPane(imageLabel);
    imageLabel.setVerticalAlignment(JLabel.CENTER);
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imagePanel.add(imageScrollPanel, BorderLayout.CENTER);


    // editor panel
    JPanel editorPanel = new JPanel();
    editorPanel.setBorder(BorderFactory.createTitledBorder("Editor Panel"));
    editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.LINE_AXIS));
    editorPanel.setPreferredSize(new Dimension(800, 800));
    mainPanel.add(editorPanel, BorderLayout.EAST);

    // histogram panel
    histogramPanel = new JPanel();
    histogramPanel.setBorder(BorderFactory.createTitledBorder("RGB Histogram"));
    histogramPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    histogramPanel.setPreferredSize(new Dimension(310, 800));

    // red histogram panel
    redHistogramPanel = new redHistogramPanel();
    blueHistogramPanel = new blueHistogramPanel();
    greenHistogramPanel = new greenHistogramPanel();
    histogramPanel.add(redHistogramPanel);
    histogramPanel.add(blueHistogramPanel);
    histogramPanel.add(greenHistogramPanel);

    editorPanel.add(histogramPanel, BorderLayout.NORTH);

    //command panel
    JPanel commandPanel = new JPanel();
    commandPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
    commandPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    commandPanel.setPreferredSize(new Dimension(390, 480));
    editorPanel.add(commandPanel, BorderLayout.SOUTH);

    // add intensity histogram
    intensityHistogramPanel = new intensityHistogramPanel();
    commandPanel.add(intensityHistogramPanel, BorderLayout.SOUTH);

    // flip direction options panel
    JPanel flipPanel = new JPanel();
    flipPanel.setBorder(BorderFactory.createEmptyBorder());
    flipPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 15));
    flipPanel.setPreferredSize(new Dimension(350, 50));
    commandPanel.add(flipPanel, BorderLayout.NORTH);

    flipModeDisplay = new JLabel("Flip");
    flipPanel.add(flipModeDisplay);

    flipMode = new JButton("Flip");

    //the event listener when an option is selected
    flipMode.setActionCommand("Flip options");
    flipMode.setPreferredSize(new Dimension(125, 30));

    flipPanel.add(flipMode);

    // filter options panel
    JPanel filterPanel = new JPanel();
    filterPanel.setBorder(BorderFactory.createEmptyBorder());
    filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 46, 15));
    filterPanel.setPreferredSize(new Dimension(350, 50));
    commandPanel.add(filterPanel, BorderLayout.NORTH);

    filterModeDisplay = new JLabel("Filter");
    filterPanel.add(filterModeDisplay);

    filterMode = new JButton("Filter");

    //the event listener when an option is selected
    filterMode.setActionCommand("Filter options");
    filterMode.setPreferredSize(new Dimension(125, 30));

    filterPanel.add(filterMode);

    // greyscale options panel
    JPanel greyscalePanel = new JPanel();
    greyscalePanel.setBorder(BorderFactory.createEmptyBorder());
    greyscalePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 32, 15));
    greyscalePanel.setPreferredSize(new Dimension(350, 50));
    commandPanel.add(greyscalePanel, BorderLayout.NORTH);

    greyscaleModeDisplay = new JLabel("Greyscale");
    greyscalePanel.add(greyscaleModeDisplay);

    greyscaleMode = new JButton("Greyscale");

    //the event listener when an option is selected
    greyscaleMode.setActionCommand("Greyscale options");
    greyscaleMode.setPreferredSize(new Dimension(125, 30));

    greyscalePanel.add(greyscaleMode);

    // color transformation options panel
    JPanel colorPanel = new JPanel();
    colorPanel.setBorder(BorderFactory.createEmptyBorder());
    colorPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 26, 15));
    colorPanel.setPreferredSize(new Dimension(350, 50));
    commandPanel.add(colorPanel, BorderLayout.NORTH);

    colorModeDisplay = new JLabel("Color Mode");
    colorPanel.add(colorModeDisplay);

    String[] color = {"-", "Luma", "Sepia"};

    colorMode = new JButton("Color Mode");

    //the event listener when an option is selected
    colorMode.setActionCommand("Color options");
    colorMode.setPreferredSize(new Dimension(125, 30));

    colorPanel.add(colorMode);

    // brighten adjustment slider
    JPanel brightnessPanel = new JPanel();
    JLabel brightnessDisplay = new JLabel("Brightness");
    brightnessSlider = new JSlider(-255, 255, 0);

    brightnessPanel.add(brightnessDisplay);
    brightnessPanel.add(brightnessSlider);

    brightnessSlider.setPreferredSize(new Dimension(350, 50));
    brightnessSlider.setPaintTicks(true);
    brightnessSlider.setMinorTickSpacing(17);
    brightnessSlider.setPaintTrack(true);
    brightnessSlider.setMajorTickSpacing(85);
    brightnessSlider.setPaintLabels(true);

    brightnessPanel.setBorder(BorderFactory.createEmptyBorder());
    brightnessPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 26, 25));
    brightnessPanel.setPreferredSize(new Dimension(380, 120));
    commandPanel.add(brightnessPanel, BorderLayout.NORTH);

    //file open
    JPanel fileopenPanel = new JPanel();
    fileopenPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
    commandPanel.add(fileopenPanel);
    fileOpenDisplay = new JLabel("Open a file");
    fileopenPanel.add(fileOpenDisplay);
    fileOpenButton = new JButton("Load Image");
    fileOpenButton.setActionCommand("Open file");
    fileopenPanel.add(fileOpenButton);

    //file save
    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
    commandPanel.add(filesavePanel);
    fileSaveDisplay = new JLabel("Save Project");
    filesavePanel.add(fileSaveDisplay);
    fileSaveButton = new JButton("Save Image");
    fileSaveButton.setActionCommand("Save file");
    filesavePanel.add(fileSaveButton);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setLoadButtonListener(ActionListener actionEvent) {
    fileOpenButton.addActionListener(actionEvent);
  }

  @Override
  public void setSaveButtonListener(ActionListener actionEvent) {
    fileSaveButton.addActionListener(actionEvent);
  }

  @Override
  public void setFlipListener(ActionListener actionEvent) {
    flipMode.addActionListener(actionEvent);
  }

  @Override
  public void setFilterListener(ActionListener actionEvent) {
    filterMode.addActionListener(actionEvent);
  }

  @Override
  public void setGreyscaleListener(ActionListener actionEvent) {
    greyscaleMode.addActionListener(actionEvent);
  }

  @Override
  public void setColorListener(ActionListener actionEvent) {
    colorMode.addActionListener(actionEvent);
  }

  @Override
  public void setBrightnessListener(ChangeListener changeListenerEvent) {
    brightnessSlider.addChangeListener(changeListenerEvent);
  }

  @Override
  public void setRedHistogramPanel(ImageEditorModel model) {
    redHistogramPanel.setRedHistogramModel(model);
  }

  @Override
  public void setBlueHistogramPanel(ImageEditorModel model) {
    blueHistogramPanel.setBlueHistogramModel(model);
  }

  @Override
  public void setGreenHistogramPanel(ImageEditorModel model) {
    greenHistogramPanel.setGreenHistogramModel(model);
  }

  @Override
  public void setIntensityHistogramPanel(ImageEditorModel model) {
    intensityHistogramPanel.setIntensityHistogramModel(model);
  }


  @Override
  public void setImageLabel(ImageIcon image) {
    imageLabel.setIcon(image);
  }

  @Override
  public void setPreviewListener(ActionListener actionEvent) {
    previewMode.addActionListener(actionEvent);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void renderErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);
  }
}
