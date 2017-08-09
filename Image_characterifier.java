import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

//An image characterifier characterifies an image
class Image_characterifier {

    //the characters from which the characterified flag will be constructed
    private String chars_to_use;
    private final double flagwidth = 500.0;

    public Image_characterifier(String chars_to_use){
        this.chars_to_use = chars_to_use;
    }

    //given an image, it returns the characterified version
    Characterified_image characterify(BufferedImage img_to_characterify){

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
                if (pixel_color.getRed() >= 128) {
                    indx++;
                }
                if (pixel_color.getGreen() >= 128) {
                    indx += 2;
                }
                if (pixel_color.getBlue() >= 128) {
                    indx += 4;
                }
                if (pixel_color.getAlpha() < 128) {
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