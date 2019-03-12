package Game;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.TiledLayer;

public class Background {

    TiledLayer tl;

    public void init() {
        try {
            Image i = Image.createImage("/Img/Other/back.png");

//            tl = new TiledLayer(1, 1, i,240, 320);
//            tl.setCell(0, 0, 1);
            tl = new TiledLayer(10, 10, i, 24, 32);

            int p = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    p++;
                    tl.setCell(k, j, p);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TiledLayer getTl() {
        return tl;
    }

}
