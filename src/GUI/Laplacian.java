package GUI;
import java.awt.image.BufferedImage;

public class Laplacian {
    public static int  getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;
        int gray = (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
        return gray;
    }
    BufferedImage LaplacianAction(BufferedImage image) {
        try {
            int x = image.getWidth();
            int y = image.getHeight();

            int[][] edgeColors = new int[x][y];
            int maxGradient = -1;

            for (int i = 1; i < x - 1; i++) {
                for (int j = 1; j < y - 1; j++) {

                    int val00 = getGrayScale(image.getRGB(i - 1, j - 1));
                    int val01 = getGrayScale(image.getRGB(i - 1, j));
                    int val02 = getGrayScale(image.getRGB(i - 1, j + 1));

                    int val10 = getGrayScale(image.getRGB(i, j - 1));
                    int val11 = getGrayScale(image.getRGB(i, j));
                    int val12 = getGrayScale(image.getRGB(i, j + 1));

                    int val20 = getGrayScale(image.getRGB(i + 1, j - 1));
                    int val21 = getGrayScale(image.getRGB(i + 1, j));
                    int val22 = getGrayScale(image.getRGB(i + 1, j + 1));

                    int gx =  ((1 * val00) + (1 * val01) + (1 * val02))
                            + ((1 * val10) + (-8 * val11) + (1 * val12))
                            + ((1 * val20) + (1 * val21) + (1 * val22));
                    int g = (int) gx;

                    if(maxGradient < g) {
                        maxGradient = g;
                    }

                    edgeColors[i][j] = g;
                }
            }

            double scale = 255.0 / maxGradient;

            for (int i = 1; i < x - 1; i++) {
                for (int j = 1; j < y - 1; j++) {
                    int edgeColor = edgeColors[i][j];
                    edgeColor = (int)(edgeColor * scale);
                    edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;

                    image.setRGB(i, j, edgeColor);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return image;
    }
}
