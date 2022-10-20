package cs3500.imageeditor.controller;

import cs3500.imageeditor.feature.Features;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.Pixel;

/**
 * Mock class to represent any feature/command that is given input by the controller.
 * This will help test to see if the features/commands are receiving correct information.
 */
public class MockFeaturesClass implements Features {

  private final StringBuilder log;


  /**
   * Mock class.
   *
   * @param log the log of user inputs
   */
  public MockFeaturesClass(StringBuilder log) {
    this.log = log;
  }

  @Override
  public Pixel[][] modify(ImageEditorModel model) {

    log.append(model);

    return new Pixel[0][];
  }
}
