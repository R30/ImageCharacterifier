package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

//An image characterifier characterifies an image
class Image_characterifier {

    //the characters from which the characterified flag will be constructed
    private String chars_to_use;
    private double flagwidth = 500.0;
    private int threshold = 128; //threshold for the RGB value to be considered present
    private boolean thresholdSet = false;

    public Image_characterifier(String chars_to_use){
        this.flagwidth = 500.0;
        this.chars_to_use = chars_to_use;
        thresholdSet = false;
    }

    public Image_characterifier(String chars_to_use, double width){
        this.chars_to_use = chars_to_use;
        this.flagwidth = width;
        thresholdSet = false;
    }

    public Image_characterifier(String chars_to_use, double width, int threshold){
        this.chars_to_use = chars_to_use;
        this.flagwidth = width;
        this.threshold = threshold;
        thresholdSet = true;
    }

    private int getThreshold(BufferedImage img){
        //returns median value of all RGB of entire image, which should be threshold for image
        ArrayList<Integer> allRGB = new ArrayList<Integer>();
        for(int y=0; y<img.getHeight(); y++){
            for(int x=0; x<img.getWidth(); x++){
                Color pixel = new Color(img.getRGB(x, y));
                allRGB.add(pixel.getRed());
                allRGB.add(pixel.getGreen());
                allRGB.add(pixel.getBlue());
            }
        }
        Collections.sort(allRGB);
        return allRGB.get(allRGB.size()/2);
    }

    public void setWidth(int width){
        this.flagwidth = width;
    }

    //given an image, it returns the characterified version
    Characterified_image characterify(BufferedImage img_to_characterify){
        if(!thresholdSet) {
            //automatically set threshold, if not already set
            this.threshold = getThreshold(img_to_characterify);
        }
        ArrayList<String> result = new ArrayList<String>();

        //the width of the flag is equal to constant flagwidth, while the height scales to an appropriate height
        double flagwidth = this.flagwidth, flagheight = img_to_characterify.getHeight()/(img_to_characterify.getWidth()/flagwidth);

        for(double y=0; y<img_to_characterify.getHeight(); y+=img_to_characterify.getHeight()/flagheight){
            String line = "";
            for(double x=0; x<img_to_characterify.getWidth(); x+=img_to_characterify.getWidth()/flagwidth) {
                Color pixel_color = new Color(img_to_characterify.getRGB((int) x, (int) y));
                //indx gives the index of the character that we will use for this coordinate
                //if there is a lot of red -> add 1
                //if there is a lot of green -> add 2
                //if there is a lot of blue -> add 4
                //if the pixel is invisible -> multiply by 0, so it equals 0
                int indx = 0;
                if (pixel_color.getRed() >= threshold) {
                    indx++;
                }
                if (pixel_color.getGreen() >= threshold) {
                    indx += 2;
                }
                if (pixel_color.getBlue() >= threshold) {
                    indx += 4;
                }
                if (pixel_color.getAlpha() < 0) {
                    indx *= 0;
                }
                char p = chars_to_use.charAt(indx);
                line += p;
            }
            result.add(line);
        }
        return new Characterified_image(result);
    }
}