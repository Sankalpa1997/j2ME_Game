package Game;

import Game.Zombi.Zombi_1;
import Game.Zombi.Zombi_2;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Player {
    
    private Sprite sprite;
    
    public static int playerHealth = 100;
    public static long speed = 20;
    private boolean isPlayerDead = false;
    private boolean isPlayerAttack = false;
    public static final int PLAYER_DEATHRATE = 1;
    public static final int PLAYER_BACKPOS = 2;
    
    public Player() {
        
        init();
        
    }
    
    public Sprite getSprite() {
        return sprite;
    }
    
    public void init() {
        
        try {
            Image i = Image.createImage("/Img/Player/Idle__000.png");
            i = ImageResizer.resizeImage(i, 15);
            sprite = new Sprite(i, i.getWidth(), i.getHeight());
            
            sprite.setPosition(10, 220);
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
    
    private boolean Iszombi_2colide = false;
    private boolean Iszombi_1colide = false;
    
    public void run() {
        new Thread(new Runnable() {
            
            public void run() {
                Iszombi_2colide = !sprite.collidesWith(Wanted.zombi_2.getSprite(), true);
                Iszombi_1colide = !sprite.collidesWith(Wanted.zombi_1.getSprite(), true);
                for (int i = 0; i <= 10; i++) {
                    try {
                        if (!isPlayerDead) {
                            
                            if (Wanted.checkBoundry(sprite, Wanted.RIGHT)) {
                                
                                if (Iszombi_2colide) {
                                    
                                    System.out.println("no tapped");
                                    Image im = Image.createImage("/Img/Player/Idle__000.png");
                                    if (i != 10) {
                                        sprite.move(1, 0);
                                        im = Image.createImage("/Img/Player/Run__00" + i + ".png");
                                        
                                    }
                                    im = ImageResizer.resizeImage(im, 15);
                                    sprite.setImage(im, im.getWidth(), im.getHeight());
                                    
                                    Thread.sleep(speed);
                                } else {
                                    System.out.println("z2 is " + Wanted.zombi_2.IsDead);
                                    System.out.println("z1 is " + Wanted.zombi_1.IsDead);
                                    
                                    if (!Wanted.zombi_2.IsDead) {
                                        System.out.println("z2 is alive");
                                        if (!isPlayerDead) {
                                            if (!isPlayerAttack) {
                                                playerHealth -= PLAYER_DEATHRATE;
                                                
                                                System.out.println("player health is " + playerHealth);
                                                
                                            }
                                            if (playerHealth <= 0) {
                                                //player dead
                                                isPlayerDead = true;
                                                death();
                                                break;
                                            } else {
                                                isPlayerAttack = false;
                                                sprite.setPosition(sprite.getX() - PLAYER_BACKPOS, sprite.getY());
                                                Image im = Image.createImage("/Img/Player/Idle__000.png");
                                                im = ImageResizer.resizeImage(im, 15);
                                                sprite.setImage(im, im.getWidth(), im.getHeight());
                                            }
                                        }
                                        
                                    } else {
                                        
                                        Iszombi_2colide = !Iszombi_2colide;
                                        
                                    }
                                    
                                    
                                }
                            } else {
                                Image i2 = Image.createImage("/Img/Player/Idle__000.png");
                                i2 = ImageResizer.resizeImage(i2, 15);
                                sprite.setImage(i2, i2.getWidth(), i2.getHeight());
                                sprite.setPosition(sprite.getX() - 5, sprite.getY());
                            }
                        } else {
                            System.out.println("p dead");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public void runBack() {
        new Thread(new Runnable() {
            
            public void run() {
                Iszombi_2colide = !sprite.collidesWith(Wanted.zombi_2.getSprite(), true);
                Iszombi_1colide = !sprite.collidesWith(Wanted.zombi_1.getSprite(), true);
                for (int i = 0; i <= 10; i++) {
                    try {
                        if (!isPlayerDead) {
                            if (Wanted.checkBoundry(sprite, Wanted.LEFT)) {
                                if (Iszombi_2colide) {
                                    Image im = Image.createImage("/Img/Player/IdleBack__000.png");
                                    if (i != 10) {
                                        sprite.move(-1, 0);
                                        im = Image.createImage("/Img/Player/RunBack__00" + i + ".png");
                                        
                                    }
                                    im = ImageResizer.resizeImage(im, 15);
                                    sprite.setImage(im, im.getWidth(), im.getHeight());
                                    
                                    Thread.sleep(speed);
                                } else {
                                    if (!Wanted.zombi_2.IsDead) {
                                        System.out.println("z2 is alive");
                                        if (!isPlayerDead) {
                                            if (!isPlayerAttack) {
                                                playerHealth -= PLAYER_DEATHRATE;
                                                
                                                System.out.println("player health is " + playerHealth);
                                                
                                            }
                                            if (playerHealth <= 0) {
                                                //player dead
                                                isPlayerDead = true;
                                                death();
                                                break;
                                            } else {
                                                isPlayerAttack = false;
                                                sprite.setPosition(sprite.getX() + PLAYER_BACKPOS, sprite.getY());
                                                Image im = Image.createImage("/Img/Player/Idle__000.png");
                                                im = ImageResizer.resizeImage(im, 15);
                                                sprite.setImage(im, im.getWidth(), im.getHeight());
                                            }
                                        }
                                        
                                    } else {
                                        
                                        Iszombi_2colide = !Iszombi_2colide;
                                        Iszombi_1colide = !Iszombi_1colide;
                                    }
                                }
                            } else {
                                Image i2 = Image.createImage("/Img/Player/Idle__000.png");
                                i2 = ImageResizer.resizeImage(i2, 15);
                                sprite.setImage(i2, i2.getWidth(), i2.getHeight());
                                sprite.setPosition(sprite.getX() + 5, sprite.getY());
                            }
                        } else {
                            System.out.println("p dead");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public void climb() {
        new Thread(new Runnable() {
            
            public void run() {
                Iszombi_2colide = !sprite.collidesWith(Wanted.zombi_2.getSprite(), true);
                Iszombi_1colide = !sprite.collidesWith(Wanted.zombi_1.getSprite(), true);
                for (int i = 0; i <= 10; i++) {
                    try {
                        if (!isPlayerDead) {
                            if (Wanted.checkBoundry(sprite, Wanted.TOP)) {
                                if (Iszombi_2colide) {
                                    Image im = Image.createImage("/Img/Player/Idle__000.png");
                                    if (i != 10) {
                                        sprite.move(0, -2);
                                        im = Image.createImage("/Img/Player/Climb_00" + i + ".png");
                                        
                                    }
                                    im = ImageResizer.resizeImage(im, 15);
                                    sprite.setImage(im, im.getWidth(), im.getHeight());
                                    
                                    Thread.sleep(speed);
                                } else {
                                    if (!Wanted.zombi_2.IsDead) {
                                        System.out.println("z2 is alive");
                                        if (!isPlayerDead) {
                                            if (!isPlayerAttack) {
                                                playerHealth -= PLAYER_DEATHRATE;
                                                
                                                System.out.println("player health is " + playerHealth);
                                                
                                            }
                                            if (playerHealth <= 0) {
                                                //player dead
                                                isPlayerDead = true;
                                                death();
                                                break;
                                            } else {
                                                isPlayerAttack = false;
                                                sprite.setPosition(sprite.getX(), sprite.getY() - PLAYER_BACKPOS);
                                                Image im = Image.createImage("/Img/Player/Idle__000.png");
                                                im = ImageResizer.resizeImage(im, 15);
                                                sprite.setImage(im, im.getWidth(), im.getHeight());
                                            }
                                        }
                                        
                                    } else {
                                        Iszombi_2colide = !Iszombi_2colide;
                                        Iszombi_1colide = !Iszombi_1colide;
                                    }
                                }
                            } else {
                                Image i2 = Image.createImage("/Img/Player/Idle__000.png");
                                i2 = ImageResizer.resizeImage(i2, 15);
                                sprite.setImage(i2, i2.getWidth(), i2.getHeight());
                                sprite.setPosition(sprite.getX(), sprite.getY() + 5);
                            }
                        } else {
                            System.out.println("p dead");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public void climbDown() {
        new Thread(new Runnable() {
            
            public void run() {
                Iszombi_2colide = !sprite.collidesWith(Wanted.zombi_2.getSprite(), true);
                Iszombi_1colide = !sprite.collidesWith(Wanted.zombi_1.getSprite(), true);
                for (int i = 9; i >= 0; i--) {
                    try {
                        if (!isPlayerDead) {
                            if (Wanted.checkBoundry(sprite, Wanted.BOTTOM)) {
                                if (Iszombi_2colide) {
                                    Image im = Image.createImage("/Img/Player/Idle__000.png");
                                    if (i != 0) {
                                        sprite.move(0, 2);
                                        im = Image.createImage("/Img/Player/Climb_00" + i + ".png");
                                        
                                    }
                                    im = ImageResizer.resizeImage(im, 15);
                                    sprite.setImage(im, im.getWidth(), im.getHeight());
                                    
                                    Thread.sleep(speed);
                                } else {
                                    if (!Wanted.zombi_2.IsDead) {
                                        System.out.println("z2 is alive");
                                        if (!isPlayerDead) {
                                            if (!isPlayerAttack) {
                                                playerHealth -= PLAYER_DEATHRATE;
                                                
                                                System.out.println("player health is " + playerHealth);
                                                
                                            }
                                            if (playerHealth <= 0) {
                                                //player dead
                                                isPlayerDead = true;
                                                death();
                                                break;
                                            } else {
                                                isPlayerAttack = false;
                                                sprite.setPosition(sprite.getX(), sprite.getY() + PLAYER_BACKPOS);
                                                Image im = Image.createImage("/Img/Player/Idle__000.png");
                                                im = ImageResizer.resizeImage(im, 15);
                                                sprite.setImage(im, im.getWidth(), im.getHeight());
                                            }
                                        }
                                        
                                    } else {
                                        Iszombi_2colide = !Iszombi_2colide;
                                        Iszombi_1colide = !Iszombi_1colide;
                                    }
                                }
                            } else {
                                Image i2 = Image.createImage("/Img/Player/Idle__000.png");
                                i2 = ImageResizer.resizeImage(i2, 15);
                                sprite.setImage(i2, i2.getWidth(), i2.getHeight());
                                sprite.setPosition(sprite.getX(), sprite.getY() - 10);
                            }
                        } else {
                            System.out.println("p dead");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    
    public void attack(final Zombi_2 zombi_2, final Zombi_1 zombi_1) {
        new Thread(new Runnable() {
            
            public void run() {
                if (!isPlayerDead) {
                    
                    for (int i = 0; i <= 10; i++) {
                        try {
                            isPlayerAttack = true;
                            Image im = Image.createImage("/Img/Player/Idle__000.png");
                            if (i != 10) {
                                
                                im = Image.createImage("/Img/Player/Attack__00" + i + ".png");
                                
                            }
                            im = ImageResizer.resizeImage(im, 15);
                            sprite.setImage(im, im.getWidth(), im.getHeight());
                            
                            Thread.sleep(speed);
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (sprite.collidesWith(zombi_2.getSprite(), false)) {
                        
                        if (!zombi_2.IsDead) {
                            zombi_2.zombiHealth -= 20;
                            
                            System.out.println("z2 healt is " + zombi_2.zombiHealth);
                            Game.score += 10;
                            
                        }
                        
                        if (zombi_2.zombiHealth <= 0) {
                            //zombi dead
                            zombi_2.IsDead = true;
                            System.out.println("z2 is dead now");
                            zombi_2.death();
                            
                        }
                        
                    } else if (sprite.collidesWith(zombi_1.getSprite(), false)) {
                        
                        if (!zombi_1.IsDead) {
                            zombi_1.zombiHealth -= 20;
                            
                            System.out.println("z1 healt is " + zombi_1.zombiHealth);
                            Game.score += 10;
                            
                        }
                        
                        if (zombi_1.zombiHealth <= 0) {
                            //zombi dead
                            zombi_1.IsDead = true;
                            zombi_1.death();
                            
                        }
                        
                    }
                    if (zombi_1.IsDead && zombi_2.IsDead) {
                        
                        Alert alert = new Alert("YOU WON");
                        Midlet.Midlet.display.setCurrent(alert);
                    }
                }
            }
        }).start();
    }
    
    public void death() {
        new Thread(new Runnable() {
            
            public void run() {
                if (isPlayerDead) {
                    
                    for (int i = 0; i < 10; i++) {
                        try {
                            System.out.println("player dead going on");
                            Image im = Image.createImage("/Img/Player/Dead__00" + i + ".png");
                            
                            im = ImageResizer.resizeImage(im, 15);
                            sprite.setImage(im, im.getWidth(), im.getHeight());
                            
                            Thread.sleep(speed);
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
    
}
