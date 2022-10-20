package cs3500.imageeditor;

import cs3500.imageeditor.controller.ImageEditorController;
import cs3500.imageeditor.controller.ImageEditorControllerImpl;
import cs3500.imageeditor.view.ImageEditorView;
import cs3500.imageeditor.view.ImageEditorViewImp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * The ImageProcessing program.
 */
public final class ImageProcessingProgram {

  /**
   * The main method that takes in user inputs.
   *
   * @param args user inputs
   */
  public static void main(String[] args) {

    Readable rd;

    if (args.length != 0) {
      try {
        rd = new FileReader(args[0]);

      } catch (FileNotFoundException e) {

        rd = new InputStreamReader(System.in);
      }
    } else {
      rd = new InputStreamReader(System.in);
    }


    Appendable ap = System.out;

    ImageEditorView view = new ImageEditorViewImp(ap);

    ImageEditorController controller = new ImageEditorControllerImpl(view, rd);

    controller.run();
  }
}
