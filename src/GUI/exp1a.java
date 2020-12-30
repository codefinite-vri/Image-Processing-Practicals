package GUI;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
public class exp1a {
    public static void main(String[] args) {
        int width = 963;
        int height = 640;
        BufferedImage image = null;
        File f = null;
        try { //read image
            f = new File("j2.jpg");
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        try { //write image
            f = new File("Output.jpg");
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
}
