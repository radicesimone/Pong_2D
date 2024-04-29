public class Player {
    private Paddle paddle;
    private int score;
    private int sets;
    private PowerUp currentPowerUp;

    /*
     * Costruttore (default per il player locale)
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
     * Set del powerUp che si ha a disposizione
     * @param pUp nuovo valore
     */
    public void setCurrentPowerUp(PowerUp pUp){
        this.currentPowerUp = pUp;
    }

    /**
     * Get del powerUp che si ha a disposizione
     * @return powerUp a disposizione
     */
    public PowerUp getCurrentPowerUp(){
        return this.currentPowerUp;
    }

    /**
     * Get racchetta
     * @return racchetta giocatore
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Get del punteggio
     * @return punteggio
     */
    public int getScore() {
        return this.score;
    }

    /*
     * Incremento punteggio
     */
    public void increaseScore() {
        this.score++;
    }

    /*
     * Decremento punteggio
     */
    public void decreasesScore() {
        if(this.score > 0)
            this.score--;
    }

    /**
     * Get dei set vinti dal giocatore
     * @return set vinti
     */
    public int getSets(){
        return this.sets;
    }

    /**
     * Incremeto set
     */
    public void increaseSets(){
        this.sets++;
    }

    /**
     * reset del punteggio
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
     * Set dei punti
     * @param score nuovo valore
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set dei set
     * @param sets nuovo valore
     */
    public void setSets(int sets) {
        this.sets = sets;
    }

    

}
