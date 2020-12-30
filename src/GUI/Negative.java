// Java program to demonstrate colored to negative conversion
package GUI;

import java.awt.image.BufferedImage;
import java.io.File;

public class Negative
{
    BufferedImage NegativeAction(java.awt.event.ActionEvent evt, File f, BufferedImage image){
        BufferedImage img= image;
        int width = img.getWidth();
        int height = img.getHeight();
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                int p = img.getRGB(x,y);
                int alpha = (p>>24)&0xff;
                int r = (p>>16)&0xff;
                int g = (p>>8)&0xff;
                int b = p&0xff;

                //subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                //set new RGB value
                p = (alpha<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(x, y, p);
            }
        }
        return img;
    }
}
