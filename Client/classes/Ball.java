import java.awt.*;
import java.util.Random;

public class Ball {
    private double x;
    private double y;
    private int radius;
    private char directionX;
    private char directionY;
    private int angle;
    //se 0 = nessuno
    //se 1 = player 1
    //se 2 = player 2
    private int lastTouch;

    /**
     * Costruttore
     */
    Ball() {
        this.x = 0;
        this.y = 0;
        this.radius = 25;
        this.directionX = ' ';
        this.directionY = ' ';
        this.angle = 0;
        this.lastTouch = 0;
    }

    /**
     * Disegno pallina
     * @param g graphics
     * @param flagColore flag colore
     */
    public void drawBall(Graphics g, char flagColore) {
        //se devo colorare di blu altrimenti colora bianco
        if(flagColore == 'b')
            g.setColor(Color.BLUE);
        g.fillOval((int)this.x, (int)this.y, this.radius, this.radius);
        //ritrono al colore bianco
        g.setColor(Color.WHITE);

    }

    /**
     * Generazione pallina
     */
    public void generateBall() {
        Random random = new Random();
        // left o right
        int gen = random.nextInt(0, 2);

        if (gen == 0) { // left
            this.directionX = 'l';
        } else { // right
            this.directionX = 'r';
        }

        // up o down
        gen = random.nextInt(0, 2);

        if (gen == 0) { // left
            this.directionY = 'u';
        } else { // right
            this.directionY = 'd';
        }

        // starting x
        if (this.directionX == 'l') {
            this.x = (1500 / 2) - this.radius;
        } else if (this.directionX == 'r') {
            this.x = (1500 / 2) + this.radius;
        }
        // starting y
        this.y = 750 / 2;

        // starting angle

        // up left (135-180)
        if (this.directionY == 'u' && this.directionX == 'l') {
            gen = random.nextInt(46) + 135;
        }
        // up right (0-45)
        else if (this.directionY == 'u' && this.directionX == 'r') {
            gen = random.nextInt(46);
        }
        // down left (180-225)
        else if (this.directionY == 'd' && this.directionX == 'l') {
            gen = random.nextInt(46) + 180;
        }
        // down right (315-360)
        else if (this.directionY == 'd' && this.directionX == 'r') {
            gen = random.nextInt(46) + 315;
        }

        this.angle = gen;
    }

    /**
     * Aggiornamento coordinate pallina
     */
    public void updateBallCoordinates() {
        double val1 = Math.abs(Math.cos(this.angle));
        double val2 = Math.abs(Math.sin(this.angle));
        // up left
        if (this.directionY == 'u' && this.directionX == 'l') {
            this.x = this.x - val1*10;
            this.y = this.y - val2*10;
        }
        // up right
        else if (this.directionY == 'u' && this.directionX == 'r') {
            this.x = this.x + val1*10;
            this.y = this.y - val2*10;
        }
        // down left
        else if (this.directionY == 'd' && this.directionX == 'l') {
            this.x = this.x - val1*10;
            this.y = this.y + val2*10;
        }
        // down right
        else if (this.directionY == 'd' && this.directionX == 'r') {
            this.x = this.x + val1*10;
            this.y = this.y + val2*10;
        }
    }

    /**
     * Aggiornamento coordinate PowerUp
     */
    public void updateBallCoordinatesPowerUp() {
        //left
        if (this.directionX == 'l')
            this.x = this.x - 4;
        //right
        else if (this.directionX == 'r') {
            this.x = this.x + 4;
        }
    }

    /**
     * Set della direzione della pallina del PowerUp
     * @param value
     */
    public void setDirectionXPowerUp(int value) {
        if(value == 1)
            this.directionX = 'l';
        else if(value == 2)
            this.directionX = 'r';
    }

    /**
     * Generazione pallina PowerUp
     */
    public void generateBallPowerUp() {
        // left o right in base all'ultimo touch della pallina
        if (this.lastTouch == 1) { // left
            this.directionX = 'l';
        } else if (this.lastTouch == 2) { // right
            this.directionX = 'r';
        }
    }

    /**
     * Get della x della pallina
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get della y della pallina
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Get dell'ultimo tocco della pallina (per PowerUp)
     * @return lastTouch
     */
    public int getLastTouch() {
        return this.lastTouch;
    }

    /**
     * Get del raggio della pallina
     * @return radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Get della direzione orizzontale della pallina
     * @return directionX
     */
    public char getDirectionX() {
        return this.directionX;
    }

    /**
     * Get della direzione verticale della pallina
     * @return directionY
     */
    public char getDirectionY() {
        return this.directionY;
    }

    /**
     * Get dell'angolo della pallina
     * @return angle
     */
    public int getAngle() {
        return this.angle;
    }

    /**
     * Set della direzione orizzontale della pallina
     * @param value nuovo valore
     */
    public void setDirectionX(char value) {
        this.directionX = value;
    }

    /**
     * Set della direzione verticale della pallina
     * @param value nuovo valore
     */
    public void setDirectionY(char value) {
        this.directionY = value;
    }

    /**
     * Set dell'angolo della pallina
     * @param value nuovo valore
     */
    public void setAngle(int value) {
        this.angle = value;
    }

    /**
     * Set dell'ultimo tocco della (per PowerUp)
     * @param value nuovo valore
     */
    public void setLastTouch(int touch) {
        this.lastTouch = touch;
    }

    /**
     * Set della x della pallina
     * @param value nuovo valore
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set della y della pallina
     * @param value nuovo valore
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set del raggio della pallina
     * @param value nuovo valore
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    
}
