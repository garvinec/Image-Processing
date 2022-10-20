package cs3500.imageeditor;

import cs3500.imageeditor.controller.ImageEditorController;
import cs3500.imageeditor.controller.ImageEditorControllerImpl;
import cs3500.imageeditor.controller.gui.ImageEditorGUIController;
import cs3500.imageeditor.controller.gui.ImageEditorGUIControllerImpl;
import cs3500.imageeditor.model.ImageEditorModel;
import cs3500.imageeditor.model.ImageEditorModelImp;
import cs3500.imageeditor.view.gui.ImageEditorGUI;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The ImageEditor Program with GUI.
 */
public class ImageEditorProgram {

  /**
   * The main method that takes in user inputs.
   *
   * @param args user inputs
   */
  public static void main(String[] args) {

    ImageEditorModel model = new ImageEditorModelImp();
    ImageEditorGUI.setDefaultLookAndFeelDecorated(false);
    ImageEditorGUI view = new ImageEditorGUI();

    view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    view.setVisible(true);

    try {
      // Set cross-platform Java L&F (also called "Metal")
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

      //UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());

      //   UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
      //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      //    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
      //    {
      //       if ("Nimbus".equals(info.getName())) {
      //          UIManager.setLookAndFeel(info.getClassName());
      //         break;
      //    }
      // }
    } catch (UnsupportedLookAndFeelException e) {
      // handle exception
    } catch (ClassNotFoundException e) {
      // handle exception
    } catch (InstantiationException e) {
      // handle exception
    } catch (IllegalAccessException e) {
      // handle exception
    } catch (Exception e) {
    }

    ImageEditorGUIController controller = new ImageEditorGUIControllerImpl(model, view);
    controller.run();
  }
}


