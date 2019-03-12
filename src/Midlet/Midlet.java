

package Midlet;

import Game.Game;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;


public class Midlet extends MIDlet {

    public static Display display;
    public static Game game;
    private void init(){
    
        display = Display.getDisplay(this);
        game = new Game();
    }
    
    
    
    public void startApp() {
        init();
        display.setCurrent(game);
        game.loop();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
