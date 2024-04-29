public class Player {
    private Paddle paddle;
    private int score;
    private int sets;
    private PowerUp currentPowerUp;

    /**
     * Costruttore di default
     */
    public Player() {
        this.paddle = new Paddle();
        this.score = 0;
        this.sets = 0;
        this.currentPowerUp = new PowerUp();
    }

    /**
     * Costruttore con la coordinata x per il playerTwo
     * @param x valore della x in cui posizionarsi
     */
    public Player(int x) {
        this.paddle = new Paddle(x);
        this.score = 0;
        this.sets = 0;
    }

    /**
     * Set del PowerUp che si ha a disposizione
     * @param pUp nuovo valore
     */
    public void setCurrentPowerUp(PowerUp pUp){
        this.currentPowerUp = pUp;
    }

    /**
     * Get del PowerUp che si ha a disposizione
     * @return currentPowerUp
     */
    public PowerUp getCurrentPowerUp(){
        return this.currentPowerUp;
    }

    /**
     * Get della racchetta
     * @return paddle
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Get del punteggio
     * @return score
     */
    public int getScore() {
        return this.score;
    }

   /**
    * Incremento punteggio
    */
    public void increaseScore() {
        this.score++;
    }

    /**
     * Get dei set vinti dal giocatore
     * @return set vinti
     */
    public int getSets(){
        return this.sets;
    }

    /**
     * Incremento set vinti
     */
    public void increaseSets(){
        this.sets++;
    }

    /**
     * Reset punteggio
     */
    public void resetScore(){
        this.score = 0;
    }

    /**
     * Set della racchetta
     * @param p nuovo valore
     */
    public void setPaddle(Paddle p){
        this.paddle = p;
    }

    /**
     * Set del punteggio
     * @param score nuovo valore
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set dei set vinti
     * @param sets nuovo valore
     */
    public void setSets(int sets) {
        this.sets = sets;
    }
}
