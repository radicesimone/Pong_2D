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
        //aggiorna la variabile di stato con l'ultimo tasto premuto
        this.lastPressedKeyCode = keyCode;
    }

    @Override
    /**
     * Controllo del rilascio di un tasto
     */
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == lastPressedKeyCode) {
            //resetta la variabile quando il tasto viene lasciato
            this.lastPressedKeyCode = -1;
        }
    }

    @Override
    /**
     * Controllo della scrittura di un tasto
     */
    public void keyTyped(KeyEvent e) {
        //metodo che si attiva quando si digita un tasto
    }

    /**
     * Get ultimo tasto premuto
     * @return ultimo tasto premuto
     */
    public int getLastKeyPressed(){
        return this.lastPressedKeyCode;
    }

}
