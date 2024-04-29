import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XML {
    private String tmpFileName;

    /**
     * Costruttore con parametri
     * @param name nome file XML
     */
    public XML(String name){
        this.tmpFileName = name;
    }

    /**
     * Da una stringa XML tira fuori un oggetto campo
     * @param xml stringa xml
     * @return nuovo campo ricevuto
     */
    public Field fromXML(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));

            Element rootElement = document.getDocumentElement();
            Field field = new Field();

            // Recupera i valori degli elementi principali
            field.setPlayerOne(parsePlayerElement(rootElement, "playerOne"));
            field.setPlayerTwo(parsePlayerElement(rootElement, "playerTwo"));
            field.setBall(parseBallElement(rootElement, "ball"));
            field.setListPowerUp(parsePowerUps(rootElement, "powerUps"));

            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Crea un oggetto giocatore partendo da XML
     * @param parentElement elemento padre da cui prendere il figlio
     * @param elementName nome elemento
     * @return oggetto Player
     */
    public Player parsePlayerElement(Element parentElement, String elementName) {
        Element playerElement = getChildElement(parentElement, elementName);

        if (playerElement != null) {
            Player player = new Player();
            player.setPaddle(parsePaddleElement(playerElement, "paddle"));
            player.setScore(getChildElementValue(playerElement, "score", 0));
            player.setSets(getChildElementValue(playerElement, "sets", 0));
            player.setCurrentPowerUp(parsePowerUpElement(playerElement, "currentPowerUp"));

            return player;
        }

        return null;
    }

    /**
     * Crea un oggetto Paddle partendo da XML
     * @param parentElement elemento padre da cui prendere il figlio
     * @param elementName nome elemento
     * @return oggetto Paddle
     */
    public Paddle parsePaddleElement(Element parentElement, String elementName) {
        Element paddleElement = getChildElement(parentElement, elementName);

        if (paddleElement != null) {
            Paddle paddle = new Paddle();
            paddle.setX(getChildElementValue(paddleElement, "x", 0));
            paddle.setY(getChildElementValue(paddleElement, "y", 0));
            paddle.setWidth(getChildElementValue(paddleElement, "width", 0));
            paddle.setHeight(getChildElementValue(paddleElement, "height", 0));

            return paddle;
        }

        return null;
    }

    /**
     * Crea un oggetto Ball partendo da XML
     * @param parentElement elemento padre da cui prendere il figlio
     * @param elementName nome elemento
     * @return oggetto Ball
     */
    public Ball parseBallElement(Element parentElement, String elementName) {
        Element ballElement = getChildElement(parentElement, elementName);

        if (ballElement != null) {
            Ball ball = new Ball();
            ball.setX(getChildElementValue(ballElement, "x", 0.0));
            ball.setY(getChildElementValue(ballElement, "y", 0.0));
            ball.setRadius(getChildElementValue(ballElement, "radius", 0));
            ball.setDirectionX(getChildElementValue(ballElement, "directionX", '\0'));
            ball.setDirectionY(getChildElementValue(ballElement, "directionY", '\0'));
            ball.setAngle(getChildElementValue(ballElement, "angle", 0));
            ball.setLastTouch(getChildElementValue(ballElement, "lastTouch", 0));

            return ball;
        }

        return null;
    }

    /**
     * Crea una lista di power up partendo da XML
     * @param parentElement elemento padre da cui prendere il figlio
     * @param elementName nome elemento
     * @return lista di oggetti di power up
     */
    public ArrayList<PowerUp> parsePowerUps(Element parentElement, String elementName) {
        Element powerUpsElement = parentElement;//getChildElement(parentElement, elementName);

        if (powerUpsElement != null) {
            NodeList powerUpNodes = powerUpsElement.getElementsByTagName("powerUp");
            ArrayList<PowerUp> powerUps = new ArrayList<>();

            for (int i = 0; i < powerUpNodes.getLength(); i++) {
                Element powerUpElement = (Element) powerUpNodes.item(i);
                PowerUp powerUp = parsePowerUpElement(powerUpElement, "powerUp");
                if (powerUp != null) {
                    powerUps.add(powerUp);
                }
            }

            return powerUps;
        }

        return null;
    }

    /**
     * Crea un oggetto PowerUp partendo da XML
     * @param parentElement elemento padre da cui prendere il figlio
     * @param elementName nome elemento
     * @return oggetto Paddle
     */
    public PowerUp parsePowerUpElement(Element parentElement, String elementName) {
        Element powerUpElement = parentElement;//getChildElement(parentElement, elementName);


        if (powerUpElement != null) {
            PowerUp powerUp = new PowerUp();
            powerUp.setX(getChildElementValue(powerUpElement, "x", 0));
            powerUp.setY(getChildElementValue(powerUpElement, "y", 0));
            powerUp.setWidth(getChildElementValue(powerUpElement, "width", 0));
            powerUp.setType(getChildElementValue(powerUpElement, "type", '\0'));
            powerUp.setTimeCreate(getChildElementValue(powerUpElement, "timeCreate", 0L));
            return powerUp;
        }

        return null;
    }

    /**
     * Prendi un nodo figlio
     * @param parentElement elemento padre da cui prendere il figlio
     * @param elementName nome elemento
     * @return elemento identificato dal nome dalla lista
     */
    public Element getChildElement(Element parentElement, String childElementName) {
        NodeList nodeList = parentElement.getElementsByTagName(childElementName);

        if (nodeList.getLength() > 0) {
            return (Element) nodeList.item(0);
        }

        return null;
    }

    /**
     * Metodo che prende valore del nodo figlio
     * @param <T> tipo valore
     * @param parentElement elemento padre da cui prendere il figlio
     * @param childElementName nome elemento 
     * @param defaultValue valore di default cha ha il figlio
     * @return valore di default 
     */
    public <T> T getChildElementValue(Element parentElement, String childElementName, T defaultValue) {
        Element childElement = getChildElement(parentElement, childElementName);

        if (childElement != null) {
            String value = childElement.getTextContent();
            return convertStringToType(value, defaultValue);
        }

        return defaultValue;
    }

    /**
     * Converti una stringa in un altro tipo
     * @param <T> tipo valore
     * @param value valore da convertire
     * @param defaultValue valore di dafault
     * @return valore convertito
     */
    public <T> T convertStringToType(String value, T defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }

        Class<?> type = defaultValue.getClass();

        if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == Character.class) {
            return (T) Character.valueOf(value.charAt(0));
        } else if (type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        }else if (type == Long.class) {
            return (T) Long.valueOf(value);
        } else {
            return (T) value;
        }
    }

    /**
     * Converti campo in una stringa XML
     * @param field oggetto da convertire
     * @return stringa XML che contiene il campo
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
     public String fieldToXML(Field field) throws TransformerException, ParserConfigurationException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("field");
            doc.appendChild(rootElement);

            rootElement.appendChild(createPlayerElement(doc, "playerOne", field.getPlayerOne()));
            rootElement.appendChild(createPlayerElement(doc, "playerTwo", field.getPlayerTwo()));
            rootElement.appendChild(createBallElement(doc, "ball", field.getBall()));

            Element powerUpsElement = doc.createElement("powerUps");
            rootElement.appendChild(powerUpsElement);

            for (PowerUp powerUp : field.getListPowerUp()) {
                powerUpsElement.appendChild(createPowerUpElement(doc, "powerUp", powerUp));
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creo un elemento giocatore per poi inserirlo nella stringa XML
     * @param doc document per creare l'element
     * @param elementName nome che assume l'elemento
     * @param player oggetto da cui creare l'element
     * @return element XML
     */
    public Element createPlayerElement(Document doc, String elementName, Player player) {
        Element playerElement = doc.createElement(elementName);

        Element paddleElement = doc.createElement("paddle");
        paddleElement.appendChild(createPaddleElement(doc, "paddle", player.getPaddle()));
        playerElement.appendChild(paddleElement);

        Element scoreElement = doc.createElement("score");
        scoreElement.appendChild(doc.createTextNode(String.valueOf(player.getScore())));
        playerElement.appendChild(scoreElement);

        Element setsElement = doc.createElement("sets");
        setsElement.appendChild(doc.createTextNode(String.valueOf(player.getSets())));
        playerElement.appendChild(setsElement);

        Element currentPowerUpElement = doc.createElement("currentPowerUp");
        // Aggiungi i sottoelementi di PowerUp a currentPowerUpElement
        playerElement.appendChild(currentPowerUpElement);

        return playerElement;
    }

    /**
     * Creo un elemento paddle per poi inserirlo nella stringa XML
     * @param doc document per creare l'element
     * @param elementName nome che assume l'elemento
     * @param paddle oggetto da cui creare l'element
     * @return element XML
     */
    public Element createPaddleElement(Document doc, String elementName, Paddle paddle) {
        Element paddleElement = doc.createElement(elementName);

        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(String.valueOf(paddle.getX())));
        paddleElement.appendChild(xElement);

        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(String.valueOf(paddle.getY())));
        paddleElement.appendChild(yElement);

        Element widthElement = doc.createElement("width");
        widthElement.appendChild(doc.createTextNode(String.valueOf(paddle.getWidth())));
        paddleElement.appendChild(widthElement);

        Element heightElement = doc.createElement("height");
        heightElement.appendChild(doc.createTextNode(String.valueOf(paddle.getHeight())));
        paddleElement.appendChild(heightElement);

        return paddleElement;
    }

    /**
     * Creo un elemento ball per poi inserirlo nella stringa XML
     * @param doc document per creare l'element
     * @param elementName nome che assume l'elemento
     * @param ball oggetto da cui creare l'element
     * @return element XML
     */
    public Element createBallElement(Document doc, String elementName, Ball ball) {

        Element ballElement = doc.createElement(elementName);
    
        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(String.valueOf(ball.getX())));
        ballElement.appendChild(xElement);
    
        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(String.valueOf(ball.getY())));
        ballElement.appendChild(yElement);
    
        Element radiusElement = doc.createElement("radius");
        radiusElement.appendChild(doc.createTextNode(String.valueOf(ball.getRadius())));
        ballElement.appendChild(radiusElement);
    
        Element directionXElement = doc.createElement("directionX");
        directionXElement.appendChild(doc.createTextNode(String.valueOf(ball.getDirectionX())));
        ballElement.appendChild(directionXElement);
    
        Element directionYElement = doc.createElement("directionY");
        directionYElement.appendChild(doc.createTextNode(String.valueOf(ball.getDirectionY())));
        ballElement.appendChild(directionYElement);
    
        Element angleElement = doc.createElement("angle");
        angleElement.appendChild(doc.createTextNode(String.valueOf(ball.getAngle())));
        ballElement.appendChild(angleElement);
    
        Element lastTouchElement = doc.createElement("lastTouch");
        lastTouchElement.appendChild(doc.createTextNode(String.valueOf(ball.getLastTouch())));
        ballElement.appendChild(lastTouchElement);
    
        return ballElement;
    }

    /**
     * Creo un elemento powerUp per poi inserirlo nella stringa XML
     * @param doc document per creare l'element
     * @param elementName nome che assume l'elemento
     * @param powerUp oggetto da cui creare l'element
     * @return element XML
     */
    public Element createPowerUpElement(Document doc, String elementName, PowerUp powerUp) {
        Element powerUpElement = doc.createElement(elementName);
    
        Element xElement = doc.createElement("x");
        xElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getX())));
        powerUpElement.appendChild(xElement);
    
        Element yElement = doc.createElement("y");
        yElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getY())));
        powerUpElement.appendChild(yElement);
    
        Element widthElement = doc.createElement("width");
        widthElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getWidth())));
        powerUpElement.appendChild(widthElement);
    
        Element typeElement = doc.createElement("type");
        typeElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getType())));
        powerUpElement.appendChild(typeElement);

        Element timeElement = doc.createElement("timeCreate");
        timeElement.appendChild(doc.createTextNode(String.valueOf(powerUp.getTimeCreate())));
        powerUpElement.appendChild(timeElement);
    
        return powerUpElement;
    }
}
