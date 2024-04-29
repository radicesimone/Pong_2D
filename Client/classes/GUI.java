import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class GUI {
    private static int WIDTH = 1500;
    private static int HEIGHT = 750;

    private JFrame finestra;
    private Listener keyListener;
    private Timer gameTimer;

    private char type; // W = waiting; G = game; E = ending
    private int second;
    private int result;

    /**
     * Costruttore
     * @param type tipo finestra (waiting, game, ending)
     * @param f eventuale campo da disegnare
     * @param result stato gioco (0 = in corso, 1 = vittoria, 2 = sconfitta)
     * @throws IOException
     */
    public GUI(char type, Field f, int result) throws IOException {
        this.type = type;
        this.second = 5;
        this.result = result;

        finestra = new JFrame("Pong");
        if (type == 'G') {
            this.keyListener = new Listener();
            finestra.addKeyListener(keyListener);
            finestra.setFocusable(true);

            gameTimer = new Timer(33, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    finestra.repaint();
                }
            });
            gameTimer.start();

        }

    }

    /**
     * Stop timer del disegno del campo e chiusura finestra
     */
    public void stopGame() {
        this.gameTimer.stop();
        this.finestra.dispose();
    }

    /**
     * Chiusura finestra
     */
    public void chiudiFinestra() {
        finestra.setVisible(false);
    }

    /**
     * Creazione finestra
     * @param f eventuale campo da disegnare
     */
    public void creaFinestra(Field f) {
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        finestra.getContentPane().setBackground(Color.BLACK);
        if (this.type == 'G') {
            finestra.add(new GamePanel(f));
        } else if (this.type == 'W') {
            finestra.add(new LoadingPanel());
        } else if (this.type == 'E') {
            finestra.add(new EndingPanel(this.result));
        }

        finestra.pack();
        finestra.setVisible(true);
    }

    /**
     * Get del keyListener
     * @return keyListener
     */
    public Listener getListener() {
        return this.keyListener;
    }

    /**
     * Get del frame (finestra)
     * @return finestra
     */
    public JFrame getFrame() {
        return this.finestra;
    }
}