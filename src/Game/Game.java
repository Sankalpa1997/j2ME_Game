package Game;

import Game.Zombi.Zombi_1;
import Game.Zombi.Zombi_2;
import Midlet.Midlet;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;

public class Game extends GameCanvas implements CommandListener {

    int keyState;
    public static int score = 0;
    LayerManager layerManager;
    Graphics graphics;
    public static Boundry_top boundry_top;
    public static Boundry_bottom boundry_bottom;
    public static Boundry_left boundry_left;
    public static Boundry_right boundry_right;

    private Player player;

    private Zombi_1 zombi_1;
    private Zombi_2 zombi_2;

    private Command restart;

    Background background;

    public Game() {
        super(true);
        init();
        initCompo();
    }

    private void initCompo() {

        restart = new Command("Restart", Command.OK, 1);
        addCommand(restart);
        setCommandListener(this);

    }

    private void init() {

        Player.playerHealth = 100;
        score = 0;

        layerManager = new LayerManager();
        graphics = getGraphics();

        //boundry top
        boundry_top = new Boundry_top();
        boundry_top.init();
        layerManager.append(boundry_top.getSprite());

        //boundry bottom
        boundry_bottom = new Boundry_bottom();
        boundry_bottom.init();
        layerManager.append(boundry_bottom.getSprite());

        //boundry left
        boundry_left = new Boundry_left();
        boundry_left.init();
        layerManager.append(boundry_left.getSprite());

        //boundry right
        boundry_right = new Boundry_right();
        boundry_right.init();
        layerManager.append(boundry_right.getSprite());

        //player
        player = new Player();
        player.init();
        layerManager.append(player.getSprite());

        //zombi 1
        zombi_1 = new Zombi_1();
        zombi_1.init();
        layerManager.append(zombi_1.getSprite());

        Wanted.zombi_1 = zombi_1;

        //zombi 2
        zombi_2 = new Zombi_2();
        zombi_2.init();
        layerManager.append(zombi_2.getSprite());

        Wanted.zombi_2 = zombi_2;

        //back
        background = new Background();
        background.init();
        layerManager.append(background.getTl());

        layerManager.paint(graphics, 0, 0);

        flushGraphics();

    }

    public void loop() {

        new Thread(new Runnable() {

            public void run() {
                while (true) {
                    try {

                        keyState = getKeyStates();
                        if ((keyState & RIGHT_PRESSED) != 0) {

                            System.out.println("man go right");
                            player.run();

                        } else if ((keyState & LEFT_PRESSED) != 0) {

                            System.out.println("man go left");
                            player.runBack();

                        } else if ((keyState & UP_PRESSED) != 0) {

                            System.out.println("man go up");
                            player.climb();

                        } else if ((keyState & DOWN_PRESSED) != 0) {

                            System.out.println("man go down");
                            player.climbDown();

                        }
                        zombiAttack();
                        graphics.setColor(0, 0, 0);
                        graphics.fillRect(0, 0, getWidth(), getHeight());

                        graphics.setColor(255, 255, 255);
                        graphics.drawString("SCORE : " + score, 10, 0, 0);

                        graphics.setColor(255, 255, 255);
                        graphics.drawString("HEALTH : " + Player.playerHealth, 100, 0, 0);

                        layerManager.paint(graphics, 0, 0);
                        flushGraphics();

                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    private void zombiAttack() {

        try {

            zombi_1.attack();
            zombi_2.attack();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void keyReleased(int keyCode) {
        if (keyCode == KEY_NUM5) {
            System.out.println("attack");
            player.attack(zombi_2, zombi_1);
        }
    }

    public void commandAction(Command c, Displayable d) {

        if (c == restart) {
            System.out.println("restart...");
            init();
        }
    }

}
