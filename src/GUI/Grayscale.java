// Java program to demonstrate colored to greyscale conversion
package GUI;

import java.awt.image.BufferedImage;
import java.io.File;

public class Grayscale
{
    public BufferedImage GrayScaleAction(BufferedImage image){
        BufferedImage img= image;
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);
                int alpha = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int gray = (int) (0.216 * r + 0.7152 * g + 0.0722 * b);
                p = (y << 24) | (gray << 16) | (gray << 8) | gray;
                img.setRGB(x, y, p);
            }
        }
        return img;
    }
}
