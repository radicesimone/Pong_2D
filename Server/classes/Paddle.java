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
     * Costruttore con parametri
     * @param x valore della x dove posizionarsi
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
        g.fillRect(this.x, this.y, this.width, this.height);// DrawRectangle(this.x, this.y, this.width, this.height, White, true);
    }

    /**
     * Disegno racchetta
     * @param g graphics
     */
    public void setY(char direction) {
        if (direction == 'W') { // alto
            this.y -= 10;
        } else if (direction == 'S') { // basso
            this.y += 10;
        }
    }

    /**
     * Get della coordinata x
     * @return coordinata x 
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get della coordinata y
     * @return coordinata y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get larghezza
     * @return largezza
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get altezza
     * @return altezza
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
     * Set coordinata y
     * @param y nuovo valore
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set larghezza
     * @param width nuovo valore
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set altezza
     * @param height nuovo valore
     */
    public void setHeight(int height) {
        this.height = height;
    }

    

}
