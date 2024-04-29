import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class ThreadUpdate implements Runnable{
    private Field field;
    private int playerNumber;
    /**
     * Costruttore di default
     * @param f campo da gioco che subisce gli aggiornamenti
     * @param n numero del giocatore
     */
    public ThreadUpdate(Field f, int n){
        this.field = f;
        this.playerNumber = n;
    }

    /*
     * Operazione che esegue il thread
     * Socket in ascolto per inviare e ricevere gli aggiornamenti
     */
    @Override
    public void run() {
        while (true) {
            try { 
                // Codice per la connessione e l'aggiornamento del campo
                TcpClient tcpService = new TcpClient(Main.SERVER_IP, Main.PORT);
                XML xmlService = new XML("tmp");
                String newXmlField = tcpService.updateField(xmlService.fieldToXML(field), this.playerNumber);
                Field newField = xmlService.fromXML(newXmlField);
                field.updateField(newField);
                // Aggiungi un ritardo per evitare l'overhead eccessivo
                Thread.sleep(33);
            } catch (InterruptedException | IOException | TransformerException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }
}
