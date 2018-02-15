/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controler.MyError;
import Vista.MainFrame;
import java.awt.Point;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.JTable;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fer
 */
public class GestorJSON {

    public static void escribirJson(String rutaArchivo) throws MyError {
        ArrayList<Click> clicks = MouseControl.getConjuntoClicks();
        ListIterator clicksiterator = clicks.listIterator();
        JSONObject cliky = null;
        JSONArray secuencia = new JSONArray();
        while (clicksiterator.hasNext()) {
            Click v = (Click) clicksiterator.next();
            cliky = new JSONObject();
            ArrayList randoms = new ArrayList();
            ArrayList coordenadas = new ArrayList();
            ArrayList<Integer> intList = null;
            if (v.isEsCuadrado()) {
                intList = new ArrayList<Integer>();
                intList.add((int) v.getCoordenadasClick()[1] - v.getCoordenadas().y);
                intList.add((int) v.getCoordenadasClick()[1]);
                intList.add((int) v.getCoordenadasClick()[3] - v.getCoordenadas().x);
                intList.add((int) v.getCoordenadasClick()[3]);
                coordenadas.add(v.getCoordenadas().x);
                coordenadas.add(v.getCoordenadas().y);
                cliky.put("coordenadas", coordenadas);

            } else {
                coordenadas.add(v.x);
                coordenadas.add(v.y);
                cliky.put("coordenadas", coordenadas);
            }

            randoms.add(v.getRandomuno());
            randoms.add(v.getRandomdos());
            cliky.put("alias", v.getAlias());
            cliky.put("duracionClick", v.getDuracionClick());
            cliky.put("Randoms", randoms);
            cliky.put("random", v.isRandom());
            cliky.put("doble", v.isDoble());
            cliky.put("sihayTexto", v.isSihayTexto());
            cliky.put("esCuadrado", v.isEsCuadrado());
            cliky.put("coordenadasClick", intList);
            cliky.put("teclasaEscribir", v.getTeclasaEscribir());
            secuencia.add(cliky);
        }
        try (FileWriter file = new FileWriter(rutaArchivo + ".json")) {
            file.write(secuencia.toJSONString());
        } catch (IOException ex) {
            throw new MyError("Error con el guardar JSON");
        } catch (Exception e) {
            throw new MyError("Error al guardado.");
        }

    }

    public static void leerJSON(String rutaArchivo, MainFrame j) throws MyError {
        JSONParser parser = new JSONParser();
        MouseControl.setConjuntoClicks(new ArrayList<>());
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(rutaArchivo));
            for (int i = 0; i < a.size(); i++) {
                JSONObject un = (JSONObject) a.get(i);
                Click e = new Click();
                e.setAlias(un.get("alias") + "");
                Long k = (Long) un.get("duracionClick");
                e.setDuracionClick(k.intValue());
                ArrayList l = (ArrayList) un.get("Randoms");
                ArrayList ll = (ArrayList) un.get("coordenadas");
                ArrayList llg = (ArrayList) un.get("coordenadasClick");
                k = (Long) l.get(0);
                e.setRandomuno(k.intValue());
                k = (Long) l.get(1);
                e.setRandomdos(k.intValue());
                e.setRandom((boolean) un.get("random"));
                e.setDoble((boolean) un.get("doble"));
                e.setSihayTexto((boolean) un.get("sihayTexto"));
                e.setEsCuadrado((boolean) un.get("esCuadrado"));
                k = (Long) ll.get(0);
                e.x = k.intValue();
                k = (Long) ll.get(1);
                e.y = k.intValue();
                e.setCoordenadas(new Point(e.x, e.y));
                if (e.isEsCuadrado()) {
                    int[] coordenadas = new int[4];
                    k = (Long) llg.get(0);
                    coordenadas[0] = k.intValue();
                    k = (Long) llg.get(1);
                    coordenadas[1] = k.intValue();
                    k = (Long) llg.get(2);
                    coordenadas[2] = k.intValue();
                    k = (Long) llg.get(3);
                    coordenadas[3] = k.intValue();
                    e.setCoordenadasClick(coordenadas);
                } else {
                    e.setCoordenadasClick(null);
                }
                e.setTeclasaEscribir(un.get("teclasaEscribir") + "");
                StringWriter out = new StringWriter();
                un.writeJSONString(out);

                String jsonText = out.toString();
                System.out.println(jsonText);
                MouseControl.getConjuntoClicks().add(e);
                j.anadirAlaTabla(e);
            }

        } catch (IOException ex) {
            throw new MyError("Error al leer el archivo");
        } catch (ParseException ex) {
            throw new MyError("Error al leer el JSON");
        }

    }

}
