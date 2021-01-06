# Image Processing Practicals

These are the set of practicals done under the course of Image Processing at colleges affiliated to the Goa University. These are done using a GUI (Graphical User Interface) which helps to observe the results in a more efficient way. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Java Editor (Intellij Idea is most preferred)

## Experiment Names
<ul>
  <li>Read and Write an Image in Java</li>
  <li>Create a GUI using Java</li>
  <li>Grayscale Conversion</li>
  <li>Gray Level Slicing</li>
  <li>Bit Plane Slicing</li>
  <li>Contrast Stretching</li>
  <li>Image Smoothing</li>
  <li>Image Sharpening</li>
  <li>Histogram of an Image</li>
  <li>Histogram Equalization</li>
  <li>Optimal Thresholding</li>
  <li>Grayscale Erosion</li>
  <li>Virtual Laboratory - Image Segmentation</li>
</ul>

## EXPERIMENT 1
The BufferedImage and ImageIO Libraries are used to carry out this section of the experiment. <br/>
<pre>int width = 963;  
int height = 640;  
BufferedImage image = null;  
File f = null; 
f = new File("j2.jpg"); 
 
//read image
image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);  
image = ImageIO.read(f);  
System.out.println("Reading complete.");  

//write image  
f = new File("Output.jpg");  
ImageIO.write(image, "jpg", f); 
System.out.println("Writing complete.");  
</pre>

<b>OUTPUT</b><br/>
<img src="images/ip1.png">
