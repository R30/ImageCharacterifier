import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class ImageCharacterisationExample {
    public static void main(String[] args){
        if(args.length<1){
            System.out.println("You need to specify a URL to the image you wish to characterify!");
            return;
        }
        try {
            String image_url = args[0];
            //read image
            BufferedImage img_to_be_characterified = ImageIO.read(new URL(image_url));
            //Characterify image
            Image_characterifier characterifier = new Image_characterifier("&<|>(?) ");
            System.out.println(characterifier.characterify(img_to_be_characterified).toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
