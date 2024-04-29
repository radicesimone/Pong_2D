import java.awt.*;
import javax.swing.*;

class GamePanel extends JPanel {

    private Field field;

    /**
     * Costruttore
     * @param field campo da disegnare
     */
    public GamePanel(Field field) {
        this.field = field;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Get della dimensione della finestra
     */
    public Dimension getPreferredSize() {
        return new Dimension(1500,750);
    }

    /**
     * Disegno finestra
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //sfondo
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //campo
        field.drawField(g);
    }  
}
