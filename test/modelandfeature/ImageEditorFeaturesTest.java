//package modelandfeature;
//
//import org.junit.Before;
//import org.junit.Test;
//import cs3500.imageeditor.feature.AdjustBrightness;
//import cs3500.imageeditor.feature.transformation.LumaTransformation;
//import cs3500.imageeditor.feature.filter.BlurFilter;
//import cs3500.imageeditor.feature.filter.SharpenFilter;
//import cs3500.imageeditor.feature.flip.FlipHorizontal;
//import cs3500.imageeditor.feature.flip.FlipVertical;
//import cs3500.imageeditor.feature.greyscale.BlueGreyscale;
//import cs3500.imageeditor.feature.greyscale.GreenGreyscale;
//import cs3500.imageeditor.feature.greyscale.IntensityGreyscale;
//import cs3500.imageeditor.feature.greyscale.LumaGreyscale;
//import cs3500.imageeditor.feature.greyscale.RedGreyscale;
//import cs3500.imageeditor.feature.greyscale.ValueGreyscale;
//import cs3500.imageeditor.feature.transformation.SepiaTransformation;
//import cs3500.imageeditor.model.ImageEditorModel;
//import cs3500.imageeditor.model.ImageEditorModelImp;
//import cs3500.imageeditor.model.Pixel;
//import static org.junit.Assert.assertEquals;
//
//
///**
// * Test to see if each of the features of the image editor work as intended.
// */
//
//public class ImageEditorFeaturesTest {
//
//  Pixel[][] image;
//  ImageEditorModel m;
//  RedGreyscale red;
//  GreenGreyscale green;
//  BlueGreyscale blue;
//  LumaGreyscale luma;
//  ValueGreyscale value;
//  IntensityGreyscale intensity;
//
//  AdjustBrightness addNormal;
//
//  AdjustBrightness addExtra;
//
//  AdjustBrightness removeNormal;
//
//  AdjustBrightness removeExtra;
//
//  FlipVertical vertical;
//
//  FlipHorizontal horizontal;
//
//  BlurFilter blur;
//
//  SharpenFilter sharpen;
//
//  LumaTransformation lumaTransformation;
//
//  SepiaTransformation sepiaTransformation;
//
//  @Before
//  public void init() {
//
//    image = new Pixel[2][2];
//
//    image[0][0] = new Pixel(130, 248, 41);
//    image[0][1] = new Pixel(42, 53, 140);
//    image[1][0] = new Pixel(75, 93, 123);
//    image[1][1] = new Pixel(98, 223, 94);
//
//    m = new ImageEditorModelImp(image);
//
//    red = new RedGreyscale();
//
//    green = new GreenGreyscale();
//
//    blue = new BlueGreyscale();
//
//    luma = new LumaGreyscale();
//
//    value = new ValueGreyscale();
//
//    intensity = new IntensityGreyscale();
//
//    addExtra = new AdjustBrightness(50);
//
//    addNormal = new AdjustBrightness(2);
//
//    removeExtra = new AdjustBrightness(-50);
//
//    removeNormal = new AdjustBrightness(-10);
//
//    vertical = new FlipVertical();
//
//    horizontal = new FlipHorizontal();
//
//    blur = new BlurFilter();
//
//    sharpen = new SharpenFilter();
//
//    lumaTransformation = new LumaTransformation();
//
//    sepiaTransformation = new SepiaTransformation();
//
//  }
//
//  @Test
//  public void testRedGreyscale() {
//
//    red.modify(m);
//
//    Pixel one = new Pixel(130, 130, 130);
//    Pixel two = new Pixel(42, 42, 42);
//    Pixel three = new Pixel(75, 75, 75);
//    Pixel four = new Pixel(98, 98, 98);
//
//    assertEquals(one, red.modify(m)[0][0]);
//    assertEquals(two, red.modify(m)[0][1]);
//    assertEquals(three, red.modify(m)[1][0]);
//    assertEquals(four, red.modify(m)[1][1]);
//  }
//
//  @Test
//  public void testGreenGreyscale() {
//
//    green.modify(m);
//
//    Pixel one = new Pixel(248, 248, 248);
//    Pixel two = new Pixel(53, 53, 53);
//    Pixel three = new Pixel(93, 93, 93);
//    Pixel four = new Pixel(223, 223, 223);
//
//
//    assertEquals(one, green.modify(m)[0][0]);
//    assertEquals(two, green.modify(m)[0][1]);
//    assertEquals(three, green.modify(m)[1][0]);
//    assertEquals(four, green.modify(m)[1][1]);
//  }
//
//  @Test
//  public void testBlueGreyscale() {
//
//    blue.modify(m);
//
//    Pixel one = new Pixel(41, 41, 41);
//    Pixel two = new Pixel(140, 140, 140);
//    Pixel three = new Pixel(123, 123, 123);
//    Pixel four = new Pixel(94, 94, 94);
//
//    assertEquals(one, blue.modify(m)[0][0]);
//    assertEquals(two, blue.modify(m)[0][1]);
//    assertEquals(three, blue.modify(m)[1][0]);
//    assertEquals(four, blue.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void testLumaGreyscale() {
//
//    luma.modify(m);
//
//    Pixel one = new Pixel(207, 207, 207);
//    Pixel two = new Pixel(56, 56, 56);
//    Pixel three = new Pixel(91, 91, 91);
//    Pixel four = new Pixel(187, 187, 187);
//
//    assertEquals(one, luma.modify(m)[0][0]);
//    assertEquals(two, luma.modify(m)[0][1]);
//    assertEquals(three, luma.modify(m)[1][0]);
//    assertEquals(four, luma.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void testValueGreyscale() {
//
//    value.modify(m);
//
//    Pixel one = new Pixel(248, 248, 248);
//    Pixel two = new Pixel(140, 140, 140);
//    Pixel three = new Pixel(123, 123, 123);
//    Pixel four = new Pixel(223, 223, 223);
//
//    assertEquals(one, value.modify(m)[0][0]);
//    assertEquals(two, value.modify(m)[0][1]);
//    assertEquals(three, value.modify(m)[1][0]);
//    assertEquals(four, value.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void testIntensityGreyscale() {
//
//    intensity.modify(m);
//
//    Pixel one = new Pixel(139, 139, 139);
//    Pixel two = new Pixel(78, 78, 78);
//    Pixel three = new Pixel(97, 97, 97);
//    Pixel four = new Pixel(138, 138, 138);
//
//    assertEquals(one, intensity.modify(m)[0][0]);
//    assertEquals(two, intensity.modify(m)[0][1]);
//    assertEquals(three, intensity.modify(m)[1][0]);
//    assertEquals(four, intensity.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void testIncreaseBrightness() {
//
//    addNormal.modify(m);
//
//    Pixel one = new Pixel(132, 250, 43);
//    Pixel two = new Pixel(44, 55, 142);
//    Pixel three = new Pixel(77, 95, 125);
//    Pixel four = new Pixel(100, 225, 96);
//
//    assertEquals(one, addNormal.modify(m)[0][0]);
//    assertEquals(two, addNormal.modify(m)[0][1]);
//    assertEquals(three, addNormal.modify(m)[1][0]);
//    assertEquals(four, addNormal.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void overIncreaseBrightness() {
//
//    image[0][0] = new Pixel(130, 248, 41);
//    image[0][1] = new Pixel(42, 53, 140);
//    image[1][0] = new Pixel(75, 93, 123);
//    image[1][1] = new Pixel(98, 223, 94);
//
//    addExtra.modify(m);
//
//    Pixel one = new Pixel(137, 255, 48);
//    Pixel two = new Pixel(92, 103, 190);
//    Pixel three = new Pixel(125, 143, 173);
//    Pixel four = new Pixel(130, 255, 126);
//
//    assertEquals(one, addExtra.modify(m)[0][0]);
//    assertEquals(two, addExtra.modify(m)[0][1]);
//    assertEquals(three, addExtra.modify(m)[1][0]);
//    assertEquals(four, addExtra.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void testDecreaseBrightness() {
//
//    removeNormal.modify(m);
//
//    Pixel one = new Pixel(120, 238, 31);
//    Pixel two = new Pixel(32, 43, 130);
//    Pixel three = new Pixel(65, 83, 113);
//    Pixel four = new Pixel(88, 213, 84);
//
//    assertEquals(one, removeNormal.modify(m)[0][0]);
//    assertEquals(two, removeNormal.modify(m)[0][1]);
//    assertEquals(three, removeNormal.modify(m)[1][0]);
//    assertEquals(four, removeNormal.modify(m)[1][1]);
//  }
//
//  @Test
//  public void overDecreaseBrightness() {
//
//    removeExtra.modify(m);
//
//    Pixel one = new Pixel(89, 207, 0);
//    Pixel two = new Pixel(0, 11, 98);
//    Pixel three = new Pixel(25, 43, 73);
//    Pixel four = new Pixel(48, 173, 44);
//
//    assertEquals(one, removeExtra.modify(m)[0][0]);
//    assertEquals(two, removeExtra.modify(m)[0][1]);
//    assertEquals(three, removeExtra.modify(m)[1][0]);
//    assertEquals(four, removeExtra.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void flipHorizontally() {
//
//    horizontal.modify(m);
//
//    Pixel one = new Pixel(130, 248, 41);
//    Pixel two = new Pixel(42, 53, 140);
//    Pixel three = new Pixel(75, 93, 123);
//    Pixel four = new Pixel(98, 223, 94);
//
//    assertEquals(three, horizontal.modify(m)[0][0]);
//    assertEquals(four, horizontal.modify(m)[0][1]);
//    assertEquals(one, horizontal.modify(m)[1][0]);
//    assertEquals(two, horizontal.modify(m)[1][1]);
//
//  }
//
//  @Test
//  public void flipVertically() {
//    vertical.modify(m);
//
//    Pixel one = new Pixel(130, 248, 41);
//    Pixel two = new Pixel(42, 53, 140);
//    Pixel three = new Pixel(75, 93, 123);
//    Pixel four = new Pixel(98, 223, 94);
//
//    assertEquals(two, vertical.modify(m)[0][0]);
//    assertEquals(one, vertical.modify(m)[0][1]);
//    assertEquals(four, vertical.modify(m)[1][0]);
//    assertEquals(three, vertical.modify(m)[1][1]);
//  }
//
//  @Test
//  public void blurFilter() {
//    blur.modify(m);
//
//    Pixel one = new Pixel(53, 94, 49);
//    Pixel two = new Pixel(44, 78, 60);
//    Pixel three = new Pixel(50, 85, 56);
//    Pixel four = new Pixel(47, 90, 59);
//
//    assertEquals(53, blur.modify(m)[0][0].rValue());
//    assertEquals(94, blur.modify(m)[0][0].gValue());
//    assertEquals(49, blur.modify(m)[0][0].bValue());
//
//    assertEquals(one, blur.modify(m)[0][0]);
//
//    assertEquals(44, blur.modify(m)[0][1].rValue());
//    assertEquals(78, blur.modify(m)[0][1].gValue());
//    assertEquals(60, blur.modify(m)[0][1].bValue());
//
//    assertEquals(two, blur.modify(m)[0][1]);
//
//    assertEquals(50, blur.modify(m)[1][0].rValue());
//    assertEquals(85, blur.modify(m)[1][0].gValue());
//    assertEquals(56, blur.modify(m)[1][0].bValue());
//
//    assertEquals(three, blur.modify(m)[1][0]);
//
//    assertEquals(47, blur.modify(m)[1][1].rValue());
//    assertEquals(90, blur.modify(m)[1][1].gValue());
//    assertEquals(59, blur.modify(m)[1][1].bValue());
//
//    assertEquals(four, blur.modify(m)[1][1]);
//  }
//
//  @Test
//  public void sharpenFilter() {
//    sharpen.modify(m);
//
//    Pixel one = new Pixel(184, 255, 130);
//    Pixel two = new Pixel(118, 194, 204);
//    Pixel three = new Pixel(142, 224, 192);
//    Pixel four = new Pixel(160, 255, 170);
//
//    assertEquals(one, sharpen.modify(m)[0][0]);
//    assertEquals(two, sharpen.modify(m)[0][1]);
//    assertEquals(three, sharpen.modify(m)[1][0]);
//    assertEquals(four, sharpen.modify(m)[1][1]);
//  }
//
//  @Test
//  public void lumaTransformation() {
//    lumaTransformation.modify(m);
//
//    Pixel one = new Pixel(208, 208, 208);
//    Pixel two = new Pixel(57, 57, 57);
//    Pixel three = new Pixel(91, 91, 91);
//    Pixel four = new Pixel(187, 187, 187);
//
//    assertEquals(one, lumaTransformation.modify(m)[0][0]);
//    assertEquals(two, lumaTransformation.modify(m)[0][1]);
//    assertEquals(three, lumaTransformation.modify(m)[1][0]);
//    assertEquals(four, lumaTransformation.modify(m)[1][1]);
//  }
//
//  @Test
//  public void sepiaTransformation() {
//
//    sepiaTransformation.modify(m);
//
//    Pixel one = new Pixel(250, 222, 173);
//    Pixel two = new Pixel(84, 75, 58);
//    Pixel three = new Pixel(124, 111, 86);
//    Pixel four = new Pixel(228, 203, 158);
//
//    assertEquals(one, sepiaTransformation.modify(m)[0][0]);
//    assertEquals(two, sepiaTransformation.modify(m)[0][1]);
//    assertEquals(three, sepiaTransformation.modify(m)[1][0]);
//    assertEquals(four, sepiaTransformation.modify(m)[1][1]);
//  }
//}
