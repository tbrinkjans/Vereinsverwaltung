package application.util;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    public static Image getImage(String path) {
        Image img;
        try {
            img = ImageIO.read(ImageLoader.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException ex) {
            img = null;
        }
        return img;
    }

}
