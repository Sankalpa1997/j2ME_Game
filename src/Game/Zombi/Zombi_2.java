package Game.Zombi;

import Game.ImageResizer;
import Game.Wanted;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Zombi_2 {

    private Sprite sprite;
    private boolean IsTouchRight = false;
    private boolean IsTouchLeft = false;
    public int zombiHealth = 40;
    public boolean IsDead = false;

    public Zombi_2() {
        init();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void init() {

        try {
            Image i = Image.createImage("/Img/Zombi/Idle (1).png");
            i = ImageResizer.resizeImage(i, 15);
            sprite = new Sprite(i, i.getWidth(), i.getHeight());
            sprite.setTransform(Sprite.TRANS_MIRROR);

            sprite.setPosition(140, 180);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void attack() {
        new Thread(new Runnable() {

            public void run() {
                if (!IsDead) {

                    for (int i = 1; i <= 8; i++) {

                        try {

                            Image im = Image.createImage("/Img/Zombi/Attack (" + i + ").png");

                            im = ImageResizer.resizeImage(im, 15);
                            sprite.setImage(im, im.getWidth(), im.getHeight());

                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

    }

    public void death() {

        new Thread(new Runnable() {

            public void run() {
                for (int i1 = 1; i1 <= 10; i1++) {
                    try {
                        System.out.println("death....");
                        Image im = Image.createImage("/Img/Zombi/Idle (1).png");
                        if (i1 != 1) {
                            im = Image.createImage("/Img/Zombi/Dead (" + i1 + ").png");

                        }
                        im = ImageResizer.resizeImage(im, 15);
                        sprite.setImage(im, im.getWidth(), im.getHeight());

                        Thread.sleep(100);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
