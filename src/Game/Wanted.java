package Game;

import Game.Zombi.Zombi_1;
import Game.Zombi.Zombi_2;
import javax.microedition.lcdui.game.Sprite;

public class Wanted {

    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    
    public static final int ZOMBI1_TAP = 5;
    public static final int ZOMBI2_TAP = 5;

    public static Zombi_2 zombi_2;
    public static Zombi_1 zombi_1;

    public static boolean checkBoundry(Sprite sprite, int dir) {
        try {

            if (dir == LEFT) {

                if (!sprite.collidesWith(Game.boundry_left.getSprite(), true)) {
                    //not touch
                 
                    return true;
                }
            } else if (dir == RIGHT) {
                if (!sprite.collidesWith(Game.boundry_right.getSprite(), true)) {
                    //not touch
                   
                    return true;
                }
            } else if (dir == TOP) {
                if (!sprite.collidesWith(Game.boundry_top.getSprite(), true)) {
                    //not touch
                  
                    return true;
                }
            } else if (dir == BOTTOM) {
                if (!sprite.collidesWith(Game.boundry_bottom.getSprite(), true)) {
                    //not touch
                   
                    return true;
                }
            } else if (dir == ZOMBI1_TAP) {

                if (!sprite.collidesWith(zombi_1.getSprite(), true)) {
                    return true;
                }
            }else if (dir == ZOMBI2_TAP) {

                if (!sprite.collidesWith(zombi_2.getSprite(), true)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
