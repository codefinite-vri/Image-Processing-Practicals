package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IP_APP {
    private JPanel panel;
    private JLabel image1;
    private JLabel imagef;
    private JLabel subtitle;
    private JLabel title;
    private JButton upload;
    private JButton save;
    private JButton NEGATIVEButton;
    private JButton GRAYSCALEButton;
    private JButton INTENSITYSLICINGButton;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField2;
    private JButton BITPLANEButton;
    private JTextField textField4;
    private JButton CONTRASTSTRETCHINGButton;
    private JButton WAVGFILTER;
    private JButton MEDIANFILTER;
    private JButton BOXFILTER;
    private JButton LAPLACIAN;
    private JButton SOBEL;
    private JButton PREWITT;
    private JButton Histogram;
    private JButton HISTEQ;
    private JButton OptimalThresholding;
    private JTextField ThresholdValue;
    private JButton GrayscaleErosion;
    private JButton GrayscaleDilation;

    BufferedImage image = null;
    BufferedImage imageo = null;
    File f = null;

    public IP_APP() {

        title.setFont(new Font("Adobe Gothic Std B", Font.PLAIN, 25));
        subtitle.setFont(new Font("Verdana", Font.PLAIN, 15));
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadActionPerformed(e);
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveActionPerformed(e);
            }
        });


        GRAYSCALEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grayscale g = new Grayscale();
                imageo=g.GrayScaleAction(image);
                displayImage();
            }
        });
        NEGATIVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Negative n = new Negative();
                imageo=n.NegativeAction(e, f, image);
                displayImage();
            }
        });
        INTENSITYSLICINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a= Integer.parseInt(textField3.getText()),d=Integer.parseInt(textField1.getText()), v=Integer.parseInt(textField2.getText());
                IntensitySlicing i = new IntensitySlicing();
                imageo=i.IntensitySlicing(image,a,d,v);
                displayImage();
            }
        });
        BITPLANEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grayscale gImg = new Grayscale();
                imageo=gImg.GrayScaleAction(image);
                BitPlanes b=new BitPlanes();
                imageo=b.BitPlanes(imageo, Integer.parseInt(textField4.getText()));
                displayImage();
            }
        });
        CONTRASTSTRETCHINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContrastStretching n = new ContrastStretching();
                imageo=n.ContrastStretchingAction(image);
                displayImage();
            }
        });
        BOXFILTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BoxFilter b = new BoxFilter();
                imageo=b.BoxFilterAction(image);
                displayImage();
            }
        });
        WAVGFILTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WAvgFilter w = new WAvgFilter();
                imageo=w.WAvgFilterAction(image);
                displayImage();
            }
        });
        MEDIANFILTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MedianFilter m = new MedianFilter();
                imageo=m.MedianFilterAction(image);
                displayImage();
            }
        });
        LAPLACIAN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Laplacian l = new Laplacian();
                imageo=l.LaplacianAction(image);
                displayImage();
            }
        });
        SOBEL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sobel s = new Sobel();
                imageo=s.SobelAction(image);
                displayImage();
            }
        });
        PREWITT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Prewitt p = new Prewitt();
                imageo=p.PrewittAction(image);
                displayImage();
            }
        });
        Histogram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Histogram h = new Histogram();
                imageo=h.HistogramAction(image);
                displayImage();
            }
        });
        HISTEQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistogramEqualization h = new HistogramEqualization();
                imageo=h.HistEqAction(image,imagef.getWidth()*imagef.getHeight() );
                displayImage();
            }
        });
        OptimalThresholding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptThresholding o = new OptThresholding();
                imageo=o.OTAction(image);
                ThresholdValue.setText(o.ThresholdValue());
                displayImage();
            }
        });
        GrayscaleErosion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrayscaleErosion ge = new GrayscaleErosion();
                imageo=ge.GEAction(image);
                displayImage();
            }
        });
        GrayscaleDilation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrayscaleDilation gd = new GrayscaleDilation();
                imageo=gd.GDAction(image);
                displayImage();
            }
        });
    }

    private void uploadActionPerformed(java.awt.event.ActionEvent evt){
        int width = 500;
        int height = 300;
        //Read Image
        JFileChooser j =  new JFileChooser();
        //Open the Save Dialog
        j.showSaveDialog(null);
        f = new File(j.getSelectedFile().getAbsolutePath());
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        try{
            image = ImageIO.read(f);
        }
        catch(IOException ex){
            Logger.getLogger(IP_APP.class.getName()).log(Level.SEVERE, null, ex);
        }

        Image img = image.getScaledInstance(image1.getWidth(), image1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(img);
        image1.setIcon(icon);
        System.out.println("Reading Complete");
    }

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //Open the Save Dialog
            j.showSaveDialog(null);
            ImageIO.write(image, "PNG", new File(j.getSelectedFile().getAbsolutePath()));
        } catch (IOException ex) {
            Logger.getLogger(IP_APP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayImage(){
        Image ig = imageo.getScaledInstance(imagef.getWidth(), imagef.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(ig);
        imagef.setIcon(icon);
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("IP_APP");
        frame.setContentPane(new IP_APP().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1480,680);
        frame.setVisible(true);
    }
}