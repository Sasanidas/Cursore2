/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controler.MyError;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.DOMImplementation;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class GestorXML {

    /**
     * Escribe todos los datos en un fichero XML
     *
     * @param nombreArchivo Nombre del archivo XML
     * @throws Controlador.MyError
     */
    public static void escribirDocumento(String rutaArchivo) throws MyError {
        DocumentBuilderFactory documentBuildF = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuildF.newDocumentBuilder();
            DOMImplementation imple = documentBuilder.getDOMImplementation();
            Document documento = imple.createDocument(null, "Secuencia", null);
            documento.setXmlVersion("1.0");
            Element raiz = documento.getDocumentElement();

            ArrayList<Click> clicks = MouseControl.getConjuntoClicks();
            ListIterator clicksiterator = clicks.listIterator();
            while (clicksiterator.hasNext()) {
                Click v = (Click) clicksiterator.next();
                //Creamos los elementos
                Element click = documento.createElement("Click");
                Element duracionClick = documento.createElement("DuracionClick");
                Element randomUno = documento.createElement("Randomuno");
                Element randomDos = documento.createElement("Randomdos");
                Element doble = documento.createElement("Doble");
                Element random = documento.createElement("Random");
                Element esCuadrado = documento.createElement("Cuadrado");
                Element coordenadasClick = documento.createElement("CoordenadasClick");
                Element coordenadas = documento.createElement("Coordenadas");
                Element x = documento.createElement("x");
                Element y = documento.createElement("y");
                //Creamos los nodos de texto
                Text trandomUno = documento.createTextNode(v.getRandomuno() + "");
                Text trandomDos = documento.createTextNode(v.getRandomdos() + "");
                Text tduracionClick = documento.createTextNode(v.getDuracionClick() + "");
                Text tdoble = documento.createTextNode(v.isDoble() + "");
                Text tesCuadrado = documento.createTextNode(v.isEsCuadrado() + "");
                Text tx = documento.createTextNode(v.getX() + "");
                Text ty = documento.createTextNode(v.getY() + "");
                ArrayList<Text> coordenadasArray = new ArrayList<>();
                if (!(v.getCoordenadasClick() == null)) {
                coordenadasArray.add(documento.createTextNode(v.getCoordenadas().getX() + ","));
                coordenadasArray.add(documento.createTextNode(v.getCoordenadasClick()[3] + ","));
                coordenadasArray.add(documento.createTextNode(v.getCoordenadas().getY() + ","));
                coordenadasArray.add(documento.createTextNode(v.getCoordenadasClick()[1] + ""));
                }

                x.appendChild(tx);
                y.appendChild(ty);
                duracionClick.appendChild(tduracionClick);
                randomUno.appendChild(trandomUno);
                randomDos.appendChild(trandomDos);
                random.appendChild(randomUno);
                random.appendChild(randomDos);
                random.setAttribute("radom", v.isRandom() + "");
                doble.appendChild(tdoble);
                esCuadrado.appendChild(tesCuadrado);
                coordenadas.appendChild(x);
                coordenadas.appendChild(y);
                for (int i = 0; i < coordenadasArray.size(); i++) {
                    coordenadasClick.appendChild(coordenadasArray.get(i));
                }

                click.setAttribute("alias", v.getAlias());
                click.appendChild(duracionClick);
                click.appendChild(random);
                click.appendChild(doble);
                click.appendChild(esCuadrado);
                click.appendChild(coordenadas);
                click.appendChild(coordenadasClick);
                // Y finalmente añadimos al raiz
                raiz.appendChild(click);

            }
            Source source = new DOMSource(documento);
            Result destino = new StreamResult(new File(rutaArchivo + ".xml"));
            Transformer optimus = TransformerFactory.newInstance().newTransformer();
            optimus.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            optimus.transform(source, destino);

        } catch (ParserConfigurationException ex) {
            throw new MyError("Error con la configuracion");
        } catch (TransformerConfigurationException ex) {
            throw new MyError("Error con el guardar XML");
        } catch (TransformerException ex) {
            throw new MyError("Error al transformar el archivo");
        }

    }

    public static void leerDocumento(String ruta) {

    }

}
