import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Field {
    Player playerOne;
    Player playerTwo;
    Ball ball;
    ArrayList<PowerUp> listPowerUp;

    /*
     * Costruttore
     */
    public Field() {
        this.playerOne = new Player();
        this.playerTwo = new Player(1500 - (this.playerOne.getPaddle().getX()) - 25);
        this.ball = new Ball();
        this.listPowerUp = new ArrayList<PowerUp>();
        // riempi il vettore di powerUp
        // this.fillListPowerUp();
    }

    /*
     * riempi il vettore con tutti e 4 i possibili power up
     */
    public void fillListPowerUp() {
        // aggiugni powerup a sinistra
        PowerUp pA = new PowerUp(75, 500);
        PowerUp pB = new PowerUp(250, 500);
        PowerUp pC = new PowerUp(425, 500);
        PowerUp pD = new PowerUp(600, 500);
        this.listPowerUp.add(pA);
        this.listPowerUp.add(pB);
        this.listPowerUp.add(pC);
        this.listPowerUp.add(pD);

        // power up a destra
        PowerUp pA1 = new PowerUp(75, 900);
        PowerUp pB2 = new PowerUp(250, 900);
        PowerUp pC3 = new PowerUp(425, 900);
        PowerUp pD4 = new PowerUp(600, 900);
        this.listPowerUp.add(pA1);
        this.listPowerUp.add(pB2);
        this.listPowerUp.add(pC3);
        this.listPowerUp.add(pD4);
    }

    /**
     * Disegno campo da gioco
     * 
     * @param g graphics
     */
    public void drawField(Graphics g) {
        g.setColor(Color.WHITE);
        // racchette
        this.playerOne.getPaddle().drawPaddle(g);
        this.playerTwo.getPaddle().drawPaddle(g);

        // palla
        this.ball.drawBall(g, ' ');

        // punteggio
        // crea una nuova fonte con dimensione personalizzata
        Font customFont = new Font("Arial", Font.BOLD, 50);
        g.setFont(customFont);
        g.drawString(this.playerOne.getScore() + "", (1500 / 2) - 55, 50);
        g.drawString(this.playerTwo.getScore() + "", (1500 / 2) + 30, 50);
        // set
        g.setColor(Color.RED);
        g.drawString(this.playerOne.getSets() + "", (1500 / 2) - 85, 50);
        g.drawString(this.playerTwo.getSets() + "", (1500 / 2) + 65, 50);

        // metà campo
        g.setColor(Color.white);
        for (int i = 0; i < 750; i += 20) {
            g.drawLine(1500 / 2, i, 1500 / 2, i + 10);
        }

        // disegna vettore powerup
        // controllo nella funzione
        for (PowerUp powerUp : this.listPowerUp) {
            powerUp.drawPowerUp(g);
        }

        // disegna vettore palline powerup
        // controllo nella funzione
        for (int i = 0; i < this.listPowerUp.size(); i++) {
            this.listPowerUp.get(i).drawBallPowerUp(g);
        }
    }

    /**
     * Get del giocatore uno
     * 
     * @return playerOne
     */
    public Player getPlayerOne() {
        return this.playerOne;
    }

    /**
     * Get del giocatore due
     * 
     * @return playerTwo
     */
    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    /**
     * Get della pallina
     * 
     * @return ball
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * Get della lista di PowerUp
     * 
     * @return listPowerUp
     */
    public ArrayList<PowerUp> getListPowerUp() {
        return this.listPowerUp;
    }

    /**
     * Controllo del rimbalzo sulle pareti del campo
     * 
     * @return true se la pallina ha colpito una parete verticale (punto al
     *         giocatore), false se non lo ha fatto
     */
    public boolean checkWallHit() {
        // controllo pareti orizzontali
        if (this.ball.getY() <= 0 || this.ball.getY() + (this.ball.getRadius() * 2) >= 750) {
            if (this.ball.getDirectionY() == 'u') {
                this.ball.setDirectionY('d');
            } else if (this.ball.getDirectionY() == 'd') {
                this.ball.setDirectionY('u');
            }
        }

        // controllo pareti verticali
        if (this.ball.getX() <= 0 || this.ball.getX() + (this.ball.getRadius() * 2) >= 1500) {
            if (this.ball.getX() <= 0) {
                this.playerTwo.increaseScore();
                // resetto last touch della pallina
                this.ball.setLastTouch(0);
            } else {
                this.playerOne.increaseScore();
                // resetto last touch della pallina
                this.ball.setLastTouch(0);
            }
            return true;
        }
        return false;
    }

    /**
     * Controllo del colpo della pallina del PowerUp
     */
    public void checkPowerUpBallHit() {
        if (this.ball.getLastTouch() == 1) {

        }
    }

    /**
     * Controllo del rimbalzo delle racchette
     * 
     * @return true se la pallina ha colpito una racchetta, false se non lo ha fatto
     */
    public Boolean checkPaddleHit() {
        // controllo paddle avversaria
        if ((this.ball.getX() + (this.ball.getRadius() * 2) >= this.playerTwo.getPaddle().getX())
                && (this.ball.getY() >= this.playerTwo.getPaddle().getY() && this.ball
                        .getY() <= this.playerTwo.getPaddle().getY() + this.playerTwo.getPaddle().getHeight())) {
            // arriva dal basso
            if (this.ball.getDirectionY() == 'u') {
                this.ball.setAngle(this.ball.getAngle() + 90);
            }
            // arriva dall'alto
            else if (this.ball.getDirectionY() == 'd') {
                this.ball.setAngle(this.ball.getAngle() - 90);
            }
            // inverte percorso sul piano x
            this.ball.setDirectionX('l');
            // imposto chi ha fatto l'ultimo tocco
            this.ball.setLastTouch(2);
            return true;
        }
        // controllo paddle utente
        else if ((this.ball.getX() >= this.playerOne.getPaddle().getX()
                && this.ball.getX() <= this.playerOne.getPaddle().getX() + this.playerOne.getPaddle().getWidth())
                && (this.ball.getY() >= this.playerOne.getPaddle().getY() && this.ball
                        .getY() <= this.playerOne.getPaddle().getY() + this.playerOne.getPaddle().getHeight())) {
            // arriva dal basso
            if (this.ball.getDirectionY() == 'u') {
                this.ball.setAngle(this.ball.getAngle() + 90);
            }
            // arriva dall'alto
            else if (this.ball.getDirectionY() == 'd') {
                this.ball.setAngle(this.ball.getAngle() - 90);
            }
            // inverte percorso sul piano x
            this.ball.setDirectionX('r');
            // imposto nella pallina chi ha fatto l'ultimo tocco
            this.ball.setLastTouch(1);
            return true;
        }
        return false;
    }

    /**
     * Controllo sul tocco della pallina a un PowerUp
     */
    public void checkPowerUpBlockHit() {
        // per tutti i power up
        for (int i = 0; i < this.listPowerUp.size(); i++) {
            // controllo della collisione tra pallina e quadrato del power up
            if ((this.ball.getX() + this.ball.getRadius() >= this.listPowerUp.get(i).getX() &&
                    this.ball.getX() + this.ball.getRadius() <= this.listPowerUp.get(i).getX()
                            + this.listPowerUp.get(i).getWidth())
                    && (this.ball.getY() + this.ball.getRadius() >= this.listPowerUp.get(i).getY() &&
                            this.ball.getY() + this.ball.getRadius() <= this.listPowerUp.get(i).getY()
                                    + this.listPowerUp.get(i).getWidth())) {
                // se la pallina non è stata toccata da nessun giocatore e tocca un power non
                // succede nulla
                // quindi
                // se last touch != 0
                if (this.ball.getLastTouch() != 0) {
                    
                    // il blocco è stato rotto quindi imposto le coordinate dela pallina
                    // lo rendo attivo
                    /*
                     * this.listPowerUp.get(i).setIsBallPowerUpActivate(true);
                     * this.listPowerUp.get(i).getBallPowerUp().setDirectionXPowerUp(this.ball.
                     * getLastTouch());
                     * this.listPowerUp.get(i).setBallPowerUpCoordinates(this.listPowerUp.get(i).
                     * getX(), this.listPowerUp.get(i).getY());
                     */
                    //in base al tipo di power up
                    if(this.listPowerUp.get(i).getType() == 'A'){
                        if (this.ball.getLastTouch() == 1)
                            this.playerOne.increaseScore();
                        else
                            this.playerTwo.increaseScore();
                    }
                    else if(this.listPowerUp.get(i).getType() == 'B'){
                        if (this.ball.getLastTouch() == 1)
                            this.playerOne.decreasesScore();
                        else
                            this.playerTwo.decreasesScore();
                    }
                    //genero un altro powerup se lo tocca
                    this.generatePowerUp(this.listPowerUp.get(i).getType());
                    //lo elimino dalla lista
                    this.listPowerUp.remove(i);
                }
            }
        }
    }

    /**
     * Controllo del tocco della paddle sulla parete alta
     * 
     * @return true se la paddle si può spostare in alto senza uscire dal campo,
     *         false se non può
     */
    public boolean checkTop() {
        // controllo alto
        if (this.playerOne.getPaddle().getY() - 20 < 0) {
            return false;
        }
        return true;
    }

    /**
     * Controllo del tocco della paddle sulla parete bassa
     * 
     * @return true se la paddle si può spostare in basso senza uscire dal campo,
     *         false se non può
     */
    public boolean checkDown() {
        // controllo basso
        if (this.playerOne.getPaddle().getY() + 20 > 550) {
            return false;
        }
        return true;
    }

    /**
     * Controllo punteggio: assegnazione punti e set e eventuale determinazione
     * vincitore
     * 
     * @return
     */
    public int checkScores() {
        boolean tmp = false;

        // controllo set
        // assegna set al giocatore 1
        if (this.playerOne.getScore() == 10) {
            this.playerOne.increaseSets();
            tmp = true;
        }
        // assegna set al giocatore 2
        else if (this.playerTwo.getScore() == 10) {
            this.playerTwo.increaseSets();
            tmp = true;
        }

        // se è finito un set resetta il punteggio
        if (tmp) {
            this.playerOne.resetScore();
            this.playerTwo.resetScore();
        }

        // controllo fine gioco
        if (playerOne.getSets() == 3) {
            return 1;
        } else if (playerTwo.getSets() == 3) {
            return 2;
        }
        return 0;
    }

    /**
     * Generazione PowerUp
     */
    public void generatePowerUp(char type) {
        Random random = new Random();
        // genero la posizione x e y
        int posX = random.nextInt(900) + 200;
        int posY = random.nextInt(550) + 50;
        
        PowerUp p = new PowerUp(posY, posX, type);
        this.listPowerUp.add(p);
    }

    /**
     * Set del giocatore uno
     * 
     * @param p nuovo valore
     */
    public void setPlayerOne(Player p) {
        this.playerOne = p;
    }

    /**
     * Set del giocatore due
     * 
     * @param p nuovo valore
     */
    public void setPlayerTwo(Player p) {
        this.playerTwo = p;
    }

    /**
     * Set della pallina
     * 
     * @param b nuovo valore
     */
    public void setBall(Ball b) {
        this.ball = b;
    }

    /**
     * Set della lista di power up
     * 
     * @param list nuovo valore
     */
    public void setListPowerUp(ArrayList<PowerUp> list) {
        this.listPowerUp = list;
    }
}