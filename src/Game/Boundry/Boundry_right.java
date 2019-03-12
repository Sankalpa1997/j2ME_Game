package Game;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Boundry_right {

    private Sprite sprite;

    public Boundry_right() {
        init();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void init() {

        try {
            Image i = Image.createImage("/Img/Boundry/right.png");
            sprite = new Sprite(i, i.getWidth(), i.getHeight());

            sprite.setPosition(0, 0);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
