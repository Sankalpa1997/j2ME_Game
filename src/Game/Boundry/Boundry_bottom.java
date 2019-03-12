package Game;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Boundry_bottom {

    private Sprite sprite;

    public Boundry_bottom() {
        init();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void init() {

        try {
            Image i = Image.createImage("/Img/Boundry/bottom.png");
            sprite = new Sprite(i, i.getWidth(), i.getHeight());

            sprite.setPosition(0, -15);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
