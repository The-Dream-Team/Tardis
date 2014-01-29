package me.dreamteam.tardis;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class SpriteStore {

    private static SpriteStore single = new SpriteStore();

    public static SpriteStore get() {
        return single;
    }

    private HashMap sprites = new HashMap();

    public Sprite getSprite(String ref) {
        if (sprites.get(ref) != null) {
            return (Sprite) sprites.get(ref);
        }

        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(ref);

            if (url == null) {
                fail("DEBUG: [WARN] Can't find sprite or resource: " + ref);
            }

            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            fail("DEBUG: [ERROR] Failed to load: " + ref);
        }

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);

        image.getGraphics().drawImage(sourceImage, 0, 0, null);

        Sprite sprite = new Sprite(image);
        sprites.put(ref, sprite);

        return sprite;
    }

    private void fail(String message) {
        System.err.println(message);
        System.exit(0);
    }
}
