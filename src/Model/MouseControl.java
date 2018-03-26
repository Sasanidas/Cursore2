/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controler.MyError;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author FerminMunozF
 */
public class MouseControl {

    static char[] teclas = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static int[] teclas_codeKey = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};

    private static ArrayList<Click> conjuntoClicks = new ArrayList<>();

    private Thread copiaThread;

    public MouseControl(Thread copiaThread) {
        this.copiaThread = copiaThread;
    }

    public MouseControl() {
    }

    public Click establecerPropiedadesClick(String tiempo, boolean doble, boolean aleatorio, String numeroUno, String numeroDos) throws MyError {
        try {

            Click z = new Click();
            if (aleatorio) {
                if (Integer.parseInt(numeroUno) >= Integer.parseInt(numeroDos) || Integer.parseInt(numeroUno) < 0) {
                    throw new MyError("Eror al establecer el rango aleatorio");
                }
                z.setRandom(true);
                z.setRandomuno(Integer.parseInt(numeroUno) * 1000);
                z.setRandomdos(Integer.parseInt(numeroDos) * 1000);
            } else if (!tiempo.isEmpty()) {
                int tiempoEnte = Integer.parseInt(tiempo) * 1000;
                z.setDuracionClick(tiempoEnte);
            } else {
                z.setDuracionClick(1600);
            }
            if (doble) {
                z.setDoble(true);
            } else {
                z.setDoble(false);
            }
            return z;

        } catch (NumberFormatException ex) {
            throw new MyError("El caracter introducido no es un numero");
        }
    }

    public void movimientoRaton(Point puntoRaton, int delay) throws MyError, InterruptedException {
        try {
            Robot e = new Robot();
            copiaThread.sleep(delay);
            e.mouseMove(puntoRaton.x, puntoRaton.y);
        } catch (AWTException ex) {
            throw new MyError("Error con posicionar el raton");
        }
    }

    public static boolean aliasRepetido(String alias) {
        for (int i = 0; i < MouseControl.getConjuntoClicks().size(); i++) {
            if (MouseControl.getConjuntoClicks().get(i).getAlias().equalsIgnoreCase(alias)) {
                return true;
            }
        }
        return false;
    }

    public void clickerSinAl(boolean doble) throws MyError, InterruptedException {
        try {
            Robot e = new Robot();
            if (doble) {
                e.mousePress(InputEvent.BUTTON1_MASK);
                e.mouseRelease(InputEvent.BUTTON1_MASK);
                copiaThread.sleep(300);
                e.mousePress(InputEvent.BUTTON1_MASK);
                e.mouseRelease(InputEvent.BUTTON1_MASK);
            } else {

            }
        } catch (AWTException ex) {
            throw new MyError("Error al realizar el click");
        }

    }

    public Point devolverPosicion() throws InterruptedException {
        copiaThread.sleep(2000);
        Point e = new Point(MouseInfo.getPointerInfo().getLocation().x,
                MouseInfo.getPointerInfo().getLocation().y);
        return e;
    }

    public void realizarSecuencia(ArrayList<Click> e, String retardoSecuencia) throws MyError, InterruptedException {
        Robot z;
        try {
            z = new Robot();
            if (!e.isEmpty()) {
                if (!retardoSecuencia.isEmpty()) {
                    copiaThread.sleep(Integer.parseInt(retardoSecuencia) * 1000);
                }
                for (int i = 0; i < e.size(); i++) {
                    if (e.get(i).isRandom()) {
                        copiaThread.sleep(devolverAleatorio(e.get(i).getRandomuno(), e.get(i).getRandomdos()));
                    } else if (e.get(i).getDuracionClick() != 1600) {
                        copiaThread.sleep(e.get(i).getDuracionClick());
                    } else {
                        copiaThread.sleep(1600);
                    }
                    if (e.get(i).isDoble()) {
                        if (e.get(i).isEsCuadrado()) {
//                            Point h = cliclarArea(e.get(i));
                            cliclarArea(e.get(i), z);
//                            z.mouseMove((int) h.getX(), (int) h.getY());
                            z.mousePress(InputEvent.BUTTON1_MASK);
                            z.mouseRelease(InputEvent.BUTTON1_MASK);
                            copiaThread.sleep(250);
                            z.mousePress(InputEvent.BUTTON1_MASK);
                            z.mouseRelease(InputEvent.BUTTON1_MASK);

                        } else {
                            z.mouseMove((int) e.get(i).getX(), (int) e.get(i).getY());
                            z.mousePress(InputEvent.BUTTON1_MASK);
                            z.mouseRelease(InputEvent.BUTTON1_MASK);
                            copiaThread.sleep(250);
                            z.mousePress(InputEvent.BUTTON1_MASK);
                            z.mouseRelease(InputEvent.BUTTON1_MASK);
                        }

                    } else if (e.get(i).isEsCuadrado()) {
                        cliclarArea(e.get(i), z);
//                            z.mouseMove((int) h.getX(), (int) h.getY());
                        z.mousePress(InputEvent.BUTTON1_MASK);
                        z.mouseRelease(InputEvent.BUTTON1_MASK);
                    } else {
                        z.mouseMove((int) e.get(i).getX(), (int) e.get(i).getY());
                        z.mousePress(InputEvent.BUTTON1_MASK);
                        z.mouseRelease(InputEvent.BUTTON1_MASK);
                    }
                    if (e.get(i).isSihayTexto()) {
                        escribir(e.get(i).getTeclasaEscribir());
                    }

                }
            } else {
                throw new MyError("Secuencia vacia, no es posible realizarla");
            }

        } catch (AWTException ex) {
            throw new MyError("Error en realizar la secuencia");
        } catch (Exception es) {
            throw new MyError("Error inesperado en realizar la secuencia");
        }

    }

    public static int devolverAleatorio(int numerouno, int numerodos) {
        int numeroGenerado;
        int numero_multi = numerouno * numerodos;
        do {
            numeroGenerado = (int) ((int) Math.round(Math.random() * (numero_multi)));
            if (numeroGenerado > numerouno & numeroGenerado < numerodos) {
                return numeroGenerado;
            }
        } while (true);
    }

    public static void escribir(String e) {
        Robot r;
        try {
            char[] aqui = e.toCharArray();
            r = new Robot();
            r.delay(1000);
            for (int i = 0; i < aqui.length; i++) {
                r.delay(500);
                r.keyPress(devolver_keycode(Character.toLowerCase(aqui[i])));

            }
        } catch (AWTException ex) {
            Logger.getLogger(MouseControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static int devolver_keycode(char a) {
        for (int i = 0; i < teclas.length; i++) {
            if (teclas[i] == a) {
                return teclas_codeKey[i];
            }
        }
        return 65;
    }

    public void cliclarArea(Click z, Robot h) throws AWTException {
        int coordenadas1 = z.getCoordenadasClick()[3] - z.getCoordenadasClick()[2];
        int coordenadas2 = z.getCoordenadasClick()[1] - z.getCoordenadasClick()[0];
        int puntox = devolverAleatorio((int) z.getCoordenadas().getX(), z.getCoordenadasClick()[3]);
        int puntoy = devolverAleatorio((int) z.getCoordenadas().getY(), z.getCoordenadasClick()[1]);
        h.mouseMove(puntox, puntoy);
    }

    public static void actualizarTabla(JTable g) {
        DefaultTableModel l = (DefaultTableModel) g.getModel();
        l.setNumRows(0);
        for (int i = 0; i < MouseControl.getConjuntoClicks().size(); i++) {
            Click e = MouseControl.getConjuntoClicks().get(i);
            String doble = null;
            String aleatorio = null;
            String sihayText = null;
            if (e.isDoble()) {
                doble = "Si";
            } else if (!e.isDoble()) {
                doble = "No";
            }
            if (e.isRandom()) {
                aleatorio = "Si";
            } else if (!e.isRandom()) {
                aleatorio = "No";
            }
            if (e.isSihayTexto()) {
                sihayText = "Si";
            } else if (!e.isSihayTexto()) {
                sihayText = "No";
            }
            if (e.isRandom()) {
                if (e.isEsCuadrado()) {
                    DefaultTableModel z = (DefaultTableModel) g.getModel();
                    String[] stri = {(e.getCoordenadasClick()[3] - e.getCoordenadasClick()[2]) + "-" + e.getCoordenadasClick()[3],
                        (e.getCoordenadasClick()[1] - e.getCoordenadasClick()[0]) + "-" + e.getCoordenadasClick()[1],
                        doble, e.getRandomuno() / 1000 + "-" + e.getRandomdos() / 1000, aleatorio, e.getAlias(), sihayText};
                    z.addRow(stri);
                } else {
                    DefaultTableModel z = (DefaultTableModel) g.getModel();
                    String[] stri = {e.getLocation().getX() + "", e.getLocation().getY() + "", doble, e.getRandomuno() / 1000 + "-" + e.getRandomdos() / 1000, aleatorio, e.getAlias(), sihayText};
                    z.addRow(stri);
                }

            } else if (!e.isRandom()) {
                if (e.isEsCuadrado()) {
                    DefaultTableModel z = (DefaultTableModel) g.getModel();
                    String[] stri = {(e.getCoordenadasClick()[3] - e.getCoordenadasClick()[2]) + "-" + e.getCoordenadasClick()[3],
                        (e.getCoordenadasClick()[1] - e.getCoordenadasClick()[0]) + "-" + e.getCoordenadasClick()[1],
                        doble, e.getDuracionClick() / 1000 + "", aleatorio, e.getAlias(), sihayText};
                    z.addRow(stri);
                } else {
                    DefaultTableModel z = (DefaultTableModel) g.getModel();

                    String[] stri = {e.getLocation().getX() + "", e.getLocation().getY() + "", doble, e.getDuracionClick() / 1000 + "", aleatorio, e.getAlias(), sihayText};
                    z.addRow(stri);
                }

            }
        }
    }

    public static int tiempoTotal() {
        int tiempoTotal = 0;
        ListIterator<Click> clicks = conjuntoClicks.listIterator();
        while (clicks.hasNext()) {
            Click uno = clicks.next();
            if (uno.isRandom()) {
                tiempoTotal += uno.getRandomdos();
            } else {
                tiempoTotal += uno.getDuracionClick();
            }
        }
        return tiempoTotal;
    }

    public static void realizarProgreso(int tiempoTotal, JProgressBar e) {
        System.out.println();
        int numAum = 100 / (tiempoTotal / 1000);
        System.out.println(numAum);
        Thread delayThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Robot e = new Robot();
                    e.delay(1100);
                } catch (AWTException ex) {

                } catch (Exception e) {
                    System.out.println(e.getCause());
                }

            }
        }, "Tiempo de espera");
        Thread realizar = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < (tiempoTotal / 1000); i++) {
                        e.setValue(e.getValue() + numAum);
                        delayThread.run();
                        System.out.println("Prueba + " + numAum + " " + i);
                    }
                    e.setValue(100);
                } catch (Exception e) {
                    System.out.println(e.getCause());
                }

            }
        }, "Hacer");
        realizar.start();
    }

    public Thread getCopiaThread() {
        return copiaThread;
    }

    public void setCopiaThread(Thread copiaThread) {
        this.copiaThread = copiaThread;
    }

    public static ArrayList<Click> getConjuntoClicks() {
        return conjuntoClicks;
    }

    public static void setConjuntoClicks(ArrayList<Click> conjuntoClicks) {
        MouseControl.conjuntoClicks = conjuntoClicks;
    }

}
