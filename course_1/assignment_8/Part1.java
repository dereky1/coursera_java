
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part1 {
    public void gray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        String outImageName = "copy-"+inImage.getFileName();
        outImage.setFileName(outImageName);
        for(Pixel pixel : inImage.pixels()){
           int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
           Pixel outPixel = new Pixel(pixel);
           outPixel.setRed(avg);
           outPixel.setGreen(avg);
           outPixel.setBlue(avg);
           outImage.setPixel(pixel.getX(), pixel.getY(), outPixel);
        }
        outImage.save();
    }
    public void inverted(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        String outImageName = "inverted-"+inImage.getFileName();
        outImage.setFileName(outImageName);
        for(Pixel pixel : inImage.pixels()){
           int red = 255 - pixel.getRed();
           int green = 255 - pixel.getGreen();
           int blue = 255- pixel.getBlue();
           Pixel outPixel = new Pixel(pixel);
           outPixel.setRed(red);
           outPixel.setGreen(green);
           outPixel.setBlue(blue);
           outImage.setPixel(pixel.getX(), pixel.getY(), outPixel);
        }
        outImage.save();
    }
    public void batch(String method){
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            ImageResource image = new ImageResource(f);
            if(method.toLowerCase().equals("gray"))
                gray(image);
            else if(method.toLowerCase().equals("inverted"))
                inverted(image);
        }
    }
    
}
