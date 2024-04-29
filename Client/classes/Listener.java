import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Listener implements KeyListener{
    //ultimo tasto premuto
    private int lastPressedKeyCode = -1;

    /**
     * Controllo della pressione di un tasto
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //aggiorna ultimo tasto premuto
        this.lastPressedKeyCode = keyCode;
    }

    /**
     * Controllo del rilascio di un tasto
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == lastPressedKeyCode) {
            //resetta variabile quando il tasto viene lasciato
            this.lastPressedKeyCode = -1;
        }
    }

    /**
     * Controllo della scrittura di un tasto
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Get dell'ultimo tasto premuto
     * @return lastPressedKeyCode
     */
    public int getLastKeyPressed(){
        return this.lastPressedKeyCode;
    }

}
