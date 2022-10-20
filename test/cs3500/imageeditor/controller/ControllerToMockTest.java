//package cs3500.imageeditor.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.StringReader;
//
//import cs3500.imageeditor.model.ImageEditorModel;
//import cs3500.imageeditor.model.ImageEditorModelImp;
//import cs3500.imageeditor.model.Pixel;
//import cs3500.imageeditor.view.ImageEditorView;
//import cs3500.imageeditor.view.ImageEditorViewImp;
//import static org.junit.Assert.assertEquals;
//
///**
// * Test to see if the inputs given from the controller to the model are the correct ones.
// * This will determine if the controller is accurately distributing inputs.
// */
//
//public class ControllerToMockTest {
//
//  private StringBuilder log;
//  private MockFeaturesClass mock;
//  private ImageEditorModel ie;
//
//
//  @Before
//  public void init() {
//
//    Pixel[][] image = new Pixel[2][2];
//
//    image[0][0] = new Pixel(120,120,120);
//    image[0][1] = new Pixel(0,0,0);
//    image[1][0] = new Pixel(30,50,40);
//    image[1][1] = new Pixel(255,255,255);
//
//    ie = new ImageEditorModelImp(image);
//
//    log = new StringBuilder();
//
//    mock = new MockFeaturesClass(log);
//
//    StringBuilder output = new StringBuilder();
//
//    ImageEditorView view = new ImageEditorViewImp(output);
//
//    StringReader input = new StringReader("load res/Mikko.ppm mikko\n "
//            + "flip horizontal mikko mikko-h\n");
//
//    ImageEditorController controller = new ImageEditorControllerImpl(view, input);
//
//  }
//
//  @Test
//  public void testInputs() {
//
//    mock.modify(ie);
//
//    assertEquals("cs3500.imageeditor.model.ImageEditorModelImp@77ec78b9", log.toString());
//
//  }
//}
