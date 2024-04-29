import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private String response;

    /**
     * Costruttore con paramentri
     * @param ip indirizzo IP della macchina
     * @param por porta su cui si ascolta
     * @throws UnknownHostException
     * @throws IOException
     */
    public TcpClient(String ip, int port) throws UnknownHostException, IOException {
        this.clientSocket = new Socket(ip, port);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.response = "";
    }

    /**
     * Ricezione messaggio di avvio del gioco
     * @throws IOException
     */
    public int startGame() throws IOException {
        this.response = in.readLine();
        System.out.println("Messaggio ricevuto: " + response);
        clientSocket.close();
        String[] tmp = this.response.split(";");
        return Integer.parseInt(tmp[1]);
    }

    /**
     * Ricezione primo campo da gioco in XML
     * @return stringa che contiene le informazioni del campo in XML
     * @throws IOException
     */
    public String receiveFirstField() throws IOException{
        this.response = in.readLine();
        clientSocket.close();
        return this.response;
    }

    /**
     * Invio aggiornamenti campi in XML
     * @param xml messaggio da inviare
     * @return messaggio inviato
     * @throws IOException
     */
    public String updateField(String xml, int numPlayer) throws IOException {
        out.println(xml+";"+numPlayer);
        xml = in.readLine();
        clientSocket.close();
        return xml;
    }
}
