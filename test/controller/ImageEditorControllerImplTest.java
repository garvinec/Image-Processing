package controller;

import static cs3500.imageeditor.imagereader.Load.readConventional;
import static cs3500.imageeditor.imagereader.Load.readPPM;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import cs3500.imageeditor.controller.ImageEditorController;
import cs3500.imageeditor.controller.ImageEditorControllerImpl;
import cs3500.imageeditor.controller.InvalidAppendable;
import cs3500.imageeditor.controller.InvalidReadable;
import cs3500.imageeditor.view.ImageEditorView;
import cs3500.imageeditor.view.ImageEditorViewImp;
import java.io.StringReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link ImageEditorController} Test if the controller interacts with the model and the
 * view properly.
 */
// re-run the tests until all of them pass
public class ImageEditorControllerImplTest {

  private ImageEditorViewImp v1;


  // initial condition for tests
  @Before
  public void init() {
    Appendable output = new StringBuilder();

    this.v1 = new ImageEditorViewImp(output);
  }


  // test invalid constructor, when the given view is null
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullViewController() {
    Readable in = new StringReader("Test Exception");
    new ImageEditorControllerImpl(null, in);
  }

  // test invalid constructor, when the given readable is null
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullReadableController() {
    new ImageEditorControllerImpl(v1, null);
  }

  // test exception when the given Readable is not valid
  @Test(expected = IllegalStateException.class)
  public void testReadableException() {
    Readable in = new InvalidReadable();
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);
    controller.run();
  }

  // test exception when the appendable is not valid
  @Test(expected = IllegalStateException.class)
  public void testAppendableException() {
    Readable in = new StringReader("Test invalid appendable");
    Appendable out = new InvalidAppendable();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);
    controller.run();
  }

  // when the given path of the image to load does not exist
  @Test
  public void nonExistentLoadImage() {
    Readable in = new StringReader("load testImages/abc.ppm abc\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("File not found! Please enter an existing file path.", outputs[4]);
  }

  // when the given path of the image to load is not of a compatible format
  @Test
  public void invalidFormatLoadImage() {
    Readable in = new StringReader("load testImages/testLoadImage.txt test\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Image format not supported! Please input a compatible format type.",
        outputs[4]);
  }

  // when the given image to save does not exist
  @Test
  public void nonExistentSaveImage() {
    Readable in = new StringReader("save testImages/abc.ppm abc\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.loadImage("testImages/koala.ppm", "koala");
    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("File not found! Please enter an existing file to save.", outputs[5]);
  }

  // when the user tries to save the image in an unsupported format
  @Test
  public void invalidFormatSaveImage() {
    Readable in = new StringReader("save testImages/koala.txt koala\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.loadImage("testImages/koala.ppm", "koala");
    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Image format not supported", outputs[5]);
  }

  // when the given number for adjusting brightness is not an integer
  @Test(expected = InputMismatchException.class)
  public void invalidBrightness() {
    Readable in = new StringReader("brighten 2.2 koala koala-invalidBrightness\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.loadImage("testImages/koala.ppm", "koala");
    controller.run();
  }

  // when the given direction for flipping is not valid
  @Test
  public void invalidFlip() {
    Readable in = new StringReader("flip-invalidDirection koala koala-invalidDirection\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.loadImage("testImages/koala.ppm", "koala");
    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Please give a valid input", outputs[5]);
  }

  // when the given component for greyscale is not valid
  @Test
  public void invalidGreyscale() {
    Readable in = new StringReader("greyscale-random koala koala-invalidGreyscale\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.loadImage("testImages/koala.ppm", "koala");
    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Please give a valid input", outputs[5]);
  }

  // when the given color transformation command is invalid
  @Test
  public void invalidColorT() {
    Readable in = new StringReader("color-random koala koala-invalidColorTransformation\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, in);

    controller.loadImage("testImages/koala.png", "koala");
    controller.run();

    String[] outputs = out.toString().split(System.lineSeparator());

    assertEquals("Please give a valid input", outputs[5]);
  }

  // test if loadImage loads the image correctly and overwrite the name it is associated with when
  // done so by the user.
  @Test
  public void testLoadImage() {
    Readable load = new StringReader("load testImages/koala.ppm koala\n"
                                     + "save testImages/koalaTest.ppm koala\n"
                                     + "brighten 100 koala koala-brighten100\n"
                                     + "save testImages/koala-brighten100.ppm "
                                     + "koala-brighten100\n"
                                     + "load testImages/koala.png koala-png\n"
                                     + "load testImages/koala.jpg koala-jpg\n"
                                     + "load testImages/koala.bmp koala-bmp\n"
                                     + "save testImages/loadPng.png koala-png\n"
                                     + "save testImages/loadJpg.jpg koala-jpg\n"
                                     + "save testImages/loadBmp.bmp koala-bmp\nq\n");

    Readable overwrite = new StringReader("load testImages/koala-brighten100.ppm koala\n"
                                          + "save testImages/test-koala-load-overwritten.ppm "
                                          + "koala\nq\n");

    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, load);
    ImageEditorController controller1 = new ImageEditorControllerImpl(view, overwrite);

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
        readPPM("testImages/koalaTest.ppm"));

    assertArrayEquals(readConventional("testImages/loadPng.png"),
        readConventional("testImages/koala.png"));

    // due to jpg lossy compression
    assertFalse(Arrays.deepEquals(readConventional("testImages/loadJpg.jpg"),
        readConventional("testImages/koala.jpg")));

    assertArrayEquals(readConventional("testImages/loadBmp.bmp"),
        readConventional("testImages/koala.bmp"));

    // test overwrite image-name
    controller1.run();

    assertArrayEquals(readPPM("testImages/test-koala-load-overwritten.ppm"),
        readPPM("testImages/koala-brighten100.ppm"));
  }

  // test if saveImage saves the image correctly and to the correct path. Also, test if it
  // overwrites an existing file when the user save a different image but with the same path.
  // re-run the test if it fails the first time.
  @Test
  public void testSaveImage() {
    Readable save = new StringReader("save testImages/testSaveKoala.ppm saveKoalaTest\n"
                                     + "save testImages/testSaveKoalaBrighten.ppm "
                                     + "saveKoalaBrightenTest\n"
                                     + "save testImages/savePng.png koala-png\n"
                                     + "save testImages/saveJpg.jpg koala-jpg\n"
                                     + "save testImages/saveBmp.bmp koala-bmp\nq\n");

    Readable overwrite = new StringReader(
        "save testImages/testSaveKoala.ppm saveKoalaBrightenTest\nq\n");

    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, save);
    ImageEditorController controller1 = new ImageEditorControllerImpl(view, overwrite);

    controller.loadImage("testImages/koala.ppm", "saveKoalaTest");
    controller.loadImage("testImages/koala-brighten100.ppm", "saveKoalaBrightenTest");
    controller.loadImage("testImages/koala.png", "koala-png");
    controller.loadImage("testImages/koala.jpg", "koala-jpg");
    controller.loadImage("testImages/koala.bmp", "koala-bmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
        readPPM("testImages/testSaveKoala.ppm"));

    assertArrayEquals(readPPM("testImages/testSaveKoalaBrighten.ppm"),
        readPPM("testImages/koala-brighten100.ppm"));

    assertArrayEquals(readConventional("testImages/savePng.png"),
        readConventional("testImages/koala.png"));

    // due to jpg lossy compression
    assertFalse(Arrays.deepEquals(readConventional("testImages/saveJpg.jpg"),
        readConventional("testImages/koala.jpg")));

    assertArrayEquals(readConventional("testImages/saveBmp.bmp"),
        readConventional("testImages/koala.bmp"));

    // test overwriting existing files
    controller1.loadImage("testImages/koala-brighten100.ppm", "saveKoalaBrightenTest");

    controller1.run();

    assertArrayEquals(readPPM("testImages/testSaveKoala.ppm"),
        readPPM("testImages/koala-brighten100.ppm"));
  }

  // test if saveImage could save an image different from its original format
  @Test
  public void testSaveImageFormats() {
    Readable save = new StringReader("save testImages/ppmToPng.png koala-ppm\n"
                                     + "save testImages/ppmToJpg.jpg koala-ppm\n"
                                     + "save testImages/ppmToBmp.bmp koala-ppm\n"
                                     + "save testImages/pngToPpm.ppm koala-png\n"
                                     + "save testImages/pngToJpg.jpg koala-png\n"
                                     + "save testImages/pngToBmp.bmp koala-png\n"
                                     + "save testImages/jpgToPpm.ppm koala-jpg\n"
                                     + "save testImages/jpgToPng.png koala-jpg\n"
                                     + "save testImages/jpgToBmp.bmp koala-jpg\n"
                                     + "save testImages/bmpToJpg.jpg koala-bmp\n"
                                     + "save testImages/bmpToPng.png koala-bmp\n"
                                     + "save testImages/bmpToPpm.ppm koala-bmp\nq\n");

    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, save);

    controller.loadImage("testImages/koala.ppm", "koala-ppm");
    controller.loadImage("testImages/koala.png", "koala-png");
    controller.loadImage("testImages/koala.jpg", "koala-jpg");
    controller.loadImage("testImages/koala.bmp", "koala-bmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/pngToPpm.ppm"),
        readPPM("testImages/koala.ppm"));

    // due to jpg lossy compression
    assertFalse(Arrays.deepEquals(readPPM("testImages/jpgToPpm.ppm"),
        readPPM("testImages/koala.ppm")));

    assertArrayEquals(readPPM("testImages/bmpToPpm.ppm"),
        readPPM("testImages/koala.ppm"));

    assertArrayEquals(readConventional("testImages/ppmToPng.png"),
        readConventional("testImages/koala.png"));

    // due to jpg lossy compression
    assertFalse(Arrays.deepEquals(readConventional("testImages/jpgToPng.png"),
        readConventional("testImages/koala.png")));

    assertArrayEquals(readConventional("testImages/bmpToPng.png"),
        readConventional("testImages/koala.png"));

    assertArrayEquals(readConventional("testImages/ppmToJpg.jpg"),
        readConventional("testImages/koala.jpg"));

    assertArrayEquals(readConventional("testImages/pngToJpg.jpg"),
        readConventional("testImages/koala.jpg"));

    assertArrayEquals(readConventional("testImages/bmpToJpg.jpg"),
        readConventional("testImages/koala.jpg"));

    assertArrayEquals(readConventional("testImages/ppmToBmp.bmp"),
        readConventional("testImages/koala.bmp"));

    // due to jpg lossy compression
    assertFalse(Arrays.deepEquals(readConventional("testImages/jpgToBmp.bmp"),
        readConventional("testImages/koala.bmp")));

    assertArrayEquals(readConventional("testImages/pngToBmp.bmp"),
        readConventional("testImages/koala.bmp"));
  }

  // test if the images brighten and darken properly
  @Test
  public void testBrightenDarkenImage() {
    Readable brighten = new StringReader("brighten 30 koalaPpm testKoalaBrightenPpm\n"
                                         + "brighten 30 koalaJpg testKoalaBrightenJpg\n"
                                         + "brighten 30 koalaPng testKoalaBrightenPng\n"
                                         + "brighten 30 koalaBmp testKoalaBrightenBmp\n"
                                         + "brighten -30 koalaPpm testKoalaDarkenPpm\n"
                                         + "brighten -30 koalaJpg testKoalaDarkenJpg\n"
                                         + "brighten -30 koalaPng testKoalaDarkenPng\n"
                                         + "brighten -30 koalaBmp testKoalaDarkenBmp\n"
                                         + "brighten 30 testKoalaBrightenPng "
                                         + "testKoalaBrightenTwice\n"
                                         + "brighten -30 testKoalaDarkenBmp "
                                         + "testKoalaDarkenTwice\n"
                                         + "save testImages/testKoalaBrightenPpm.ppm "
                                         + "testKoalaBrightenPpm\n"
                                         + "save testImages/testKoalaBrightenJpg.jpg "
                                         + "testKoalaBrightenJpg\n"
                                         + "save testImages/testKoalaBrightenPng.png "
                                         + "testKoalaBrightenPng\n"
                                         + "save testImages/testKoalaBrightenBmp.bmp "
                                         + "testKoalaBrightenBmp\n"
                                         + "save testImages/testKoalaDarkenPpm.ppm "
                                         + "testKoalaDarkenPpm\n"
                                         + "save testImages/testKoalaDarkenJpg.jpg "
                                         + "testKoalaDarkenJpg\n"
                                         + "save testImages/testKoalaDarkenPng.png "
                                         + "testKoalaDarkenPng\n"
                                         + "save testImages/testKoalaDarkenBmp.bmp "
                                         + "testKoalaDarkenBmp\n"
                                         + "save testImages/testKoalaBrightenTwice.png "
                                         + "testKoalaBrightenTwice\n"
                                         + "save testImages/testKoalaDarkenTwice.bmp "
                                         + "testKoalaDarkenTwice\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, brighten);

    controller.loadImage("testImages/koala.ppm", "koalaPpm");
    controller.loadImage("testImages/koala.jpg", "koalaJpg");
    controller.loadImage("testImages/koala.png", "koalaPng");
    controller.loadImage("testImages/koala.bmp", "koalaBmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
            readPPM("testImages/koalaTest.ppm"));
  }

  // test if it creates a greyscale image with the specified component correctly.
  @Test
  public void testGreyscaleImage() {
    Readable greyscale = new StringReader("greyscale-red koalaPpm testGsRed\n"
                                          + "greyscale-green koalaJpg testGsGreen\n"
                                          + "greyscale-blue koalaPng testGsBlue\n"
                                          + "greyscale-luma koalaBmp testGsLuma\n"
                                          + "greyscale-intensity koalaPpm testGsIntensity\n"
                                          + "greyscale-value koalaPng testGsValue\n"
                                          + "save testImages/testGsRed.ppm testGsRed\n"
                                          + "save testImages/testGsGreen.jpg testGsGreen\n"
                                          + "save testImages/testGsBlue.png testGsBlue\n"
                                          + "save testImages/testGsLuma.bmp testGsLuma\n"
                                          + "save testImages/testGsIntensity.ppm testGsIntensity\n"
                                          + "save testImages/testGsValue.png testGsValue\n\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, greyscale);

    controller.loadImage("testImages/koala.ppm", "koalaPpm");
    controller.loadImage("testImages/koala.jpg", "koalaJpg");
    controller.loadImage("testImages/koala.png", "koalaPng");
    controller.loadImage("testImages/koala.bmp", "koalaBmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
            readPPM("testImages/koalaTest.ppm"));
  }

  // test if the image flips (in the specified direction) correctly
  @Test
  public void testFlipImage() {
    Readable flip = new StringReader("flip-vertical koalaPpm testKoalaFlipVPpm\n"
                                     + "flip-horizontal koalaJpg testKoalaFlipHJpg\n"
                                     + "flip-vertical koalaPng testKoalaFlipVPng\n"
                                     + "flip-horizontal koalaBmp testKoalaFlipHBmp\n"
                                     + "flip-vertical testKoalaFlipHBmp testKoalaFlipHFlipV\n"
                                     + "save testImages/testKoalaFlipV.ppm testKoalaFlipVPpm\n"
                                     + "save testImages/testKoalaFlipH.jpg testKoalaFlipHJpg\n"
                                     + "save testImages/testKoalaFlipV.png testKoalaFlipVPng\n"
                                     + "save testImages/testKoalaFlipH.bmp testKoalaFlipHBmp\n"
                                     + "save testImages/testKoalaFlipHFlipV.bmp "
                                     + "testKoalaFlipHFlipV\nq\n");
    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, flip);

    controller.loadImage("testImages/koala.ppm", "koalaPpm");
    controller.loadImage("testImages/koala.jpg", "koalaJpg");
    controller.loadImage("testImages/koala.png", "koalaPng");
    controller.loadImage("testImages/koala.bmp", "koalaBmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
            readPPM("testImages/koalaTest.ppm"));
  }

  // test if the filters work correctly (blur and sharpen)
  @Test
  public void testFilter() {
    Readable filter = new StringReader("blur koalaPpm testKoalaBlurPpm\n"
                                       + "blur koalaJpg testKoalaBlurJpg\n"
                                       + "sharpen koalaPng testKoalaSharpenPng\n"
                                       + "sharpen koalaBmp testKoalaSharpenBmp\n"
                                       + "save testImages/testBlurPpm.ppm testKoalaBlurPpm\n"
                                       + "save testImages/testBlurJpg.jpg testKoalaBlurJpg\n"
                                       + "save testImages/testSharpenPng.png testKoalaSharpenPng\n"
                                       + "save testImages/testSharpenBmp.bmp "
                                       + "testKoalaSharpenBmp\nq\n");

    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, filter);

    controller.loadImage("testImages/koala.ppm", "koalaPpm");
    controller.loadImage("testImages/koala.jpg", "koalaJpg");
    controller.loadImage("testImages/koala.png", "koalaPng");
    controller.loadImage("testImages/koala.bmp", "koalaBmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
            readPPM("testImages/koalaTest.ppm"));
  }

  // test if the color transformations work properly
  @Test
  public void testColorTransformation() {
    Readable colorTransformation = new StringReader("color-luma koalaPpm testKoalaLumaPpm\n"
                                                    + "color-luma koalaPng testKoalaLumaPng\n"
                                                    + "color-sepia koalaJpg testKoalaSepiaJpg\n"
                                                    + "color-sepia koalaBmp testKoalaSepiaBmp\n"
                                                    + "save testImages/testLumaPpm.ppm "
                                                    + "testKoalaLumaPpm\n"
                                                    + "save testImages/testLumaPng.png "
                                                    + "testKoalaLumaPng\n"
                                                    + "save testImages/testSepiaJpg.jpg "
                                                    + "testKoalaSepiaJpg\n"
                                                    + "save testImages/testSepiaBmp.bmp "
                                                    + "testKoalaSepiaBmp\nq\n");

    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);
    ImageEditorController controller = new ImageEditorControllerImpl(view, colorTransformation);

    controller.loadImage("testImages/koala.ppm", "koalaPpm");
    controller.loadImage("testImages/koala.jpg", "koalaJpg");
    controller.loadImage("testImages/koala.png", "koalaPng");
    controller.loadImage("testImages/koala.bmp", "koalaBmp");

    controller.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
            readPPM("testImages/koalaTest.ppm"));
  }

  // test if the image will come out as the desired outcome after multiple edits
  // (e.g., flip an image then brighten it)
  @Test
  public void testMultipleEdits() {
    Readable flipAndGs = new StringReader("load testImages/koala.ppm koalaPpm\n"
                                          + "flip-vertical koalaPpm koalaFlipV\n"
                                          + "greyscale-luma koalaFlipV koalaFlipGs\n"
                                          + "save testImages/testKoalaFlipGs.ppm koalaFlipGs\nq\n");

    Readable brightnessAndGs = new StringReader("load testImages/koala.png koalaPng\n"
                                                + "brighten -30 koalaPng koalaBrightness\n"
                                                + "greyscale-green koalaBrightness "
                                                + "koalaBrightnessGs\n"
                                                + "save testImages/testKoalaBrightnessGs.png "
                                                + "koalaBrightnessGs\nq\n");

    Readable flipAndBrightness = new StringReader("load testImages/koala.jpg koalaJpg\n"
                                                  + "flip-horizontal koalaJpg koalaFlipH\n"
                                                  + "brighten 45 koalaFlipH koalaFlipBrightness\n"
                                                  + "save testImages/testKoalaFlipBrightness.jpg "
                                                  + "koalaFlipBrightness\nq\n");

    Readable filterAndColorTransformation = new StringReader("load testImages/koala.bmp "
                                                             + "koalaBmp\n"
                                                             + "sharpen koalaBmp koalaBmpSharpen\n"
                                                             + "color-sepia koalaBmpSharpen "
                                                             + "koalaBmpSharpenSepia\n"
                                                             + "save "
                                                             + "testImages/testKoalaSharpenSepia"
                                                             + ".bmp koalaBmpSharpenSepia\nq\n");

    StringBuilder out = new StringBuilder();
    ImageEditorView view = new ImageEditorViewImp(out);

    ImageEditorController controller1 = new ImageEditorControllerImpl(view, flipAndGs);
    ImageEditorController controller2 = new ImageEditorControllerImpl(view,
        brightnessAndGs);
    ImageEditorController controller3 = new ImageEditorControllerImpl(view,
        flipAndBrightness);
    ImageEditorController controller4 = new ImageEditorControllerImpl(view,
        filterAndColorTransformation);

    controller1.run();

    controller2.run();

    controller3.run();

    controller4.run();

    assertArrayEquals(readPPM("testImages/koala.ppm"),
            readPPM("testImages/koalaTest.ppm"));
  }
}