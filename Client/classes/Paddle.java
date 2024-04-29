import java.awt.Graphics;

public class Paddle {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Costruttore di default
     */
    public Paddle() {
        this.width = 25;
        this.height = 150;
        this.x = 25;
        this.y = (500/2)-(this.height/2);
    }

    /**
     * Costruttore con parametro x (per playerTwo)
     * @param x valore della x in cui posizionarsi
     */
    public Paddle(int x) {
        this.width = 25;
        this.height = 150;
        this.x = x;
        this.y = (500/2)-(this.height/2);
    }

    /**
     * Disegno racchetta
     * @param g graphics
     */
    public void drawPaddle(Graphics g) {
       // g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

    /**
     * Spostamento pallina
     * @param direction direzione
     */
    public void setY(char direction) {
        if (direction == 'W') { // alto
            this.y -= 20;
        } else if (direction == 'S') { // basso
            this.y += 20;
        }
    }

    /**
     * Get della coordinata x
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get della coordinata y
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get della lunghezza
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get dell'altezza
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Set della coordinata x
     * @param x nuovo valore
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set della coordinata y
     * @param y nuovo valore
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set della lunghezza
     * @param width nuovo valore
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set dell'altezza
     * @param height nuovo valore
     */
    public void setHeight(int height) {
        this.height = height;
    }

    

}
