package com.newoneplus.dresshub;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


//getBuffredImage시점에서 imageInputStream이 close됨
public class ImageProcesser {

    public BufferedImage getSmallImage(InputStream inputStream) throws IOException {
        int smallXSize = 200;
        int smallYSize = 200;

        BufferedImage newImage = getSizedImage(inputStream, smallXSize, smallYSize);
        return newImage;

    }

    public BufferedImage getMediumImage(InputStream inputStream) throws IOException {
        int mediumXSize = 500;
        int mediumYSize = 500;
        
        BufferedImage newImage = getSizedImage(inputStream, mediumXSize, mediumYSize);;
        return newImage;
    }

    public BufferedImage getOriginImage(InputStream inputStream) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        return image;
    }






    private BufferedImage getSizedImage(InputStream inputStream, int X, int Y) throws IOException {
        BufferedImage image= ImageIO.read(inputStream);
        Image resizeImage = image.getScaledInstance(X, Y, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(X, Y, BufferedImage.TYPE_INT_RGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(resizeImage, 0, 0, null);
        g.dispose();
        return newImage;
    }


//            출처: http://jang8584.tistory.com/240 [개발자의 길]

}
