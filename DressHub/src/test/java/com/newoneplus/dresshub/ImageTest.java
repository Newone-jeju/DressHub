//package com.newoneplus.dresshub;
//
//import org.junit.Test;
//
//import javax.imageio.ImageIO;
//import javax.imageio.stream.FileImageInputStream;
//import javax.imageio.stream.ImageInputStream;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class ImageTest extends Component{
//    String path = System.getProperty("user.dir") + "/src/test/java/com/newoneplus/dresshub/";
//
//    @Test
//    public void samllImageTest() throws IOException {
//        ImageProcesser imageProcesser = new ImageProcesser();
//        File imageFile = new File(path + "big_jean.jpg");
//        BufferedImage image = imageProcesser.getSmallImage(new FileInputStream(imageFile));
//        ImageIO.write(image,"jpg", new File(path + "smallSizeImage.jpg"));
//    }
//    @Test
//    public void mediumImageTest() throws IOException {
//        ImageProcesser imageProcesser = new ImageProcesser();
//        File imageFile = new File(path + "big_jean.jpg");
//        BufferedImage image = imageProcesser.getMediumImage(new FileInputStream(imageFile));
//        ImageIO.write(image,"jpg", new File(path + "mediumSizeImage.jpg"));
//    }
//    @Test
//    public void originImageTest() throws IOException {
//        ImageProcesser imageProcesser = new ImageProcesser();
//        File imageFile = new File(path + "big_jean.jpg");
//        BufferedImage image = imageProcesser.getOriginImage(new FileInputStream(imageFile));
//        ImageIO.write(image,"jpg", new File(path + "originSizeImage.jpg"));
//
//    }
//
//
//}
