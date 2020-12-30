package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayscaleDilation {
    BufferedImage GDAction(BufferedImage image){
        int width = image.getWidth(), height = image.getHeight();
        BufferedImage im1 = new
                BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        int mask[][]={{0,1,0}, {1,1,1}, {0,1,0}};
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                int val;
                int max = 0;
                for (int x=-1; x<2; x++){
                    for (int y=-1; y<2; y++){
                        try{
                            Color p = new Color(image.getRGB(j+x, i+y));
                            int r=p.getRed(), g=p.getGreen(), b=p.getBlue();
                            int grayscale = (r+g+b)/3;
                            if(grayscale==255){
                                max=255;
                            } else{ val =
                                    grayscale +
                                            mask[x+1][y+1];
                                if(val>max){
                                    max=val;
                                }}
                        } catch(Exception e){
                            continue;
                        }
                    }
                } System.out.println(max);
                Color n= new Color(max, max, max);
                im1.setRGB(j, i, n.getRGB());
            }
        }
        return im1;
    }
}
