package com.newoneplus.dresshub;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageProcesser {

    public BufferedImage getSmallImage(ImageInputStream imageInputStream) throws IOException {
        int smallXSize = 200;
        int smallYSize = 200;

        BufferedImage newImage = getBufferedImage(imageInputStream, smallXSize, smallYSize);

        return newImage;

    }

    public BufferedImage getMediumImage(ImageInputStream imageInputStream) throws IOException {
        int mediumXSize = 500;
        int mediumYSize = 500;

        BufferedImage newImage = getBufferedImage(imageInputStream, mediumXSize, mediumYSize);

        return newImage;

    }


    private BufferedImage getBufferedImage(ImageInputStream imageInputStream, int smallXSize, int smallYSize) throws IOException {
        BufferedImage image= ImageIO.read(imageInputStream);

        Image resizeImage = image.getScaledInstance(smallXSize, smallYSize, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(smallXSize, smallYSize, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(resizeImage, 0, 0, null);
        g.dispose();
        return newImage;
    }


//            출처: http://jang8584.tistory.com/240 [개발자의 길]

}
