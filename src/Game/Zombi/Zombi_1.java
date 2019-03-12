package Game.Zombi;

import Game.ImageResizer;
import Game.Wanted;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Zombi_1 {

    private Sprite sprite;
    private boolean IsTouchRight = false;
    private boolean IsTouchLeft = false;
    public boolean IsDead = false;
    public  int zombiHealth = 40;

    public static final int SPEED = 200;

    
    public Zombi_1() {
        init();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void init() {

        try {
            System.out.println("z1 created");
            Image i = Image.createImage("/Img/Zombi/Idle (1).png");
            i = ImageResizer.resizeImage(i, 15);
            sprite = new Sprite(i, i.getWidth(), i.getHeight());

            sprite.setPosition(20, 110);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void attack() {
        new Thread(new Runnable() {

            public void run() {
                if (!IsDead) {
                   

                    if (!IsTouchRight) {

                        for (int i = 1; i <= 10; i++) {
                            if (Wanted.checkBoundry(sprite, Wanted.RIGHT)) {
                                try {

                                    Image im = Image.createImage("/Img/Zombi/Idle (1).png");
                                    if (i != 10) {
                                        sprite.move(1, 0);
                                        im = Image.createImage("/Img/Zombi/Walk (" + i + ").png");

                                    }
                                    im = ImageResizer.resizeImage(im, 15);
                                    sprite.setImage(im, im.getWidth(), im.getHeight());

                                    Thread.sleep(SPEED);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                IsTouchRight = true;
                                IsTouchLeft = false;

                            }
                        }
                    } else {
                        if (!IsTouchLeft) {

                            for (int i1 = 1; i1 <= 10; i1++) {
                                try {
                                    if (Wanted.checkBoundry(sprite, Wanted.LEFT)) {

                                        Image im = Image.createImage("/Img/Zombi/Idle (1).png");
                                        if (i1 != 1) {
                                            sprite.move(-1, 0);
                                            im = Image.createImage("/Img/Zombi/WalkBack (" + i1 + ").png");

                                        }
                                        im = ImageResizer.resizeImage(im, 15);
                                        sprite.setImage(im, im.getWidth(), im.getHeight());

                                        Thread.sleep(SPEED);

                                    } else {
                                        IsTouchLeft = true;

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            IsTouchRight = false;
                        }
                    }
                }

            }
        }).start();

    }

    public void death() {
        if (IsDead) {
            new Thread(new Runnable() {

                public void run() {
                    for (int i1 = 1; i1 <= 10; i1++) {
                        try {
                            System.out.println("death....");

                            Image im = Image.createImage("/Img/Zombi/Dead (" + i1 + ").png");

                            im = ImageResizer.resizeImage(im, 15);
                            sprite.setImage(im, im.getWidth(), im.getHeight());

                            Thread.sleep(100);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        System.out.println("last dead");
                        Image im = Image.createImage("/Img/Zombi/Dead (10).png");

                        im = ImageResizer.resizeImage(im, 15);
                        sprite.setImage(im, im.getWidth(), im.getHeight());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
