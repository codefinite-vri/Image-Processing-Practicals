package GUI;

import com.sun.tools.jconsole.JConsoleContext;
import org.jfree.chart.renderer.GrayPaintScale;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OptThresholding {
    BufferedImage img;
    float ft;
    int GrayScale(BufferedImage img, int i , int j){
        Color c= new Color(img.getRGB(j,i));
        int r = c.getRed(), g=c.getGreen(), b=c.getBlue();
        int gr=(r+g+b)/3;
        return gr;
    }
    BufferedImage OTAction(BufferedImage image){
        img=image;
        int width = img.getWidth(), height = img.getHeight();
        int k=0,  count1, count2;
        float t1, t2;
        float t[] = {0, 0, 0, 0, 0, 0, 0};
        double s= 0.0001;
        float gr;
        do{
            k++;
            t1 = t2= count1=count2=0;
            for(int i = 0; i<height; i++){
                for(int j = 0; j<width; j++){
                    gr = GrayScale(img, i, j);
                    if (gr>=t[k]) {
                        t1 += gr;
                        count1++;
                    }
                    else{
                        t2 += gr;
                        count2++;
                    }

                }
            }
            if(count1>0)
            t1/=count1;
            if(count2>0)
            t2/=count2;
            t[k]=(t1+t2)/2;
        } while(Math.abs(t[k]-t[k-1])>=s);
        ft=t[k-1];
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                gr = GrayScale(img, i, j);
                if (gr>=ft) {
                    Color n = new Color(255, 255, 255);
                    img.setRGB(j, i, n.getRGB());
                }
                else{
                    Color n = new Color(0, 0, 0);
                    img.setRGB(j, i, n.getRGB());
                }
            }
        }
        return img;
    }
    String ThresholdValue(){
        return Float.toString(ft);
    }
}
