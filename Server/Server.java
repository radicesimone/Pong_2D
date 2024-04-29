import java.io.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Server {
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {
        //avvio
        TcpServer tcpService = new TcpServer();
        String[] arrayIPs = tcpService.startGame();

        //gioco

        //campo iniziale
        Field field = new Field();
        field.getBall().generateBall();
        //genero il primo powerUp di tipo A
        field.generatePowerUp('A');
        //genero il primo powerUp di tipo B
        field.generatePowerUp('B');
        tcpService = new TcpServer();
        XML xmlService = new XML("tmp");
        tcpService.sendGeneratedField(xmlService.fieldToXML(field), xmlService);

        //loop gioco
        int game = 0;
        while(game == 0){
            //1. campi con scambi e controlli
            tcpService = new TcpServer();
            
            tcpService.updateFields(arrayIPs, xmlService);
            //2. invio stato partita (0: continua, 1 win playerUno e 2 viceversa)
        }
        
       // while(game == 0){
       /*  tcpService = new TCP_SERVER();
        String[] array = tcpService.receiveFields();

        //suddivisione ip e campi
        XML_SERVER xmlService = new XML_SERVER("tmp");
        String[] arrayIPs = new String[2];
        String[] arrayXMLFields = new String[2];
        Field[] fields = new Field[2];

        for(int i = 0; i < 2; i++){
            String[] tmp = array[i].split(";");
            arrayIPs[i] = tmp[0];
            arrayXMLFields[i] = tmp[1];
            fields[i] = xmlService.fromXML(arrayXMLFields[i]);
        }
        */
        System.out.println();
    }
}