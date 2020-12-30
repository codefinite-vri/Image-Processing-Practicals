package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;
public class HistogramEqualization {
    void chartNew(String title, String x, String y, DefaultCategoryDataset dataset){
        JFreeChart p1 = ChartFactory.createBarChart(title,x,y,dataset);
        CategoryPlot plot1 = p1.getCategoryPlot();
        plot1.setRangeGridlinePaint(Color.black);
        ChartFrame barFrame2 = new ChartFrame(title,p1);
        barFrame2.setVisible(true);
        barFrame2.setSize(450,400);

    }

    BufferedImage HistAction(BufferedImage image) {
        int intensity1[] = new int[256];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int avg = (r + g + b) / 3;
                intensity1[avg]++;
            }
        }
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        for(int i=0;i<256;i++) {
            dataset1.setValue(intensity1[i],"intensity",String.valueOf(i));
        }
        chartNew("Original Histogram","Intensity Range", "Frequency", dataset1);
        return image;
    }

    BufferedImage HistEqAction(BufferedImage image, int anzpixel)
    {
        image = HistAction(image);
        int intensity[] = new int[256];
        for(int y=0;y<image.getHeight();y++){
            for(int x=0;x<image.getWidth();x++){
                int p= image.getRGB(x,y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int valueBefore = (r+g+b)/3;
                intensity[valueBefore]++;
            }
        } int sum=0;
        float[] lut = new float[anzpixel];
        for(int i=0;i<255;i++){
            sum+=intensity[i];
            lut[i]=sum*255/anzpixel;
        }
        for(int y=0;y< image.getHeight();y++){
            for(int x=0;x<image.getWidth();x++){
                Color c = new Color(image.getRGB(x, y));
                int p= image.getRGB(x,y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int valueBefore = (r+g+b)/3;
                int valueAfter = (int) lut[valueBefore];
                intensity[valueAfter]++;
                Color n = new Color(valueAfter,valueAfter,valueAfter);
                image.setRGB(x, y, n.getRGB());
            }
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<256;i++) {
            dataset.setValue(intensity[i],"intensity",String.valueOf(i));
        }
        chartNew("Equalized Histogram","Intensity Range", "Frequency", dataset);
        return image;
    }
}