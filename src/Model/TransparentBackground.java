package Model;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

public class TransparentBackground extends JComponent {

    private JFrame frame;
    private static int anchura, altura, anchuraRango, alturaRango;
    private static int[] coordenadasPasar;
    private static boolean teste = true;

    private Image background;
    public static Point coordenadas;

    public TransparentBackground(JFrame frame) {
        this.frame = frame;
        updateBackground();
    }

    public static void iniciarCaptura() {
        JFrame frame = new JFrame("Transparent Window");
        TransparentBackground bg = new TransparentBackground(frame);
        bg.setLayout(new BorderLayout());
        Button button = new Button("Aceptar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coordenadas = frame.getLocationOnScreen();
                altura = frame.getHeight();
                anchura = frame.getWidth();
                anchuraRango = (int) (coordenadas.getX() + anchura);
                alturaRango = (int) (coordenadas.getY() + altura);
                int[] coordenadasPasasr = {altura, alturaRango, anchura, anchuraRango};
                coordenadasPasar = coordenadasPasasr;
                teste = false;
                frame.dispose();
            }
        });
        frame.getContentPane().add("Center", bg);
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD,16 ));
        bg.add("South", button);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

    public void updateBackground() {
        try {
            Robot rbt = new Robot();
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension dim = tk.getScreenSize();
            background = rbt.createScreenCapture(
                    new Rectangle(0, 0, (int) dim.getWidth(),
                            (int) dim.getHeight()));
        } catch (Exception ex) {

        }
    }

    public void paintComponent(Graphics g) {
        Point pos = this.getLocationOnScreen();
        Point offset = new Point(-pos.x, -pos.y);
        g.drawImage(background, offset.x, offset.y, null);
    }

    public Point getCoordenadas() {
        return coordenadas;
    }

    public static boolean isTeste() {
        return teste;
    }

    public static void setTeste(boolean teste) {
        TransparentBackground.teste = teste;
    }

    public static int[] getCoordenadasPasar() {
        return coordenadasPasar;
    }

    public static void setCoordenadasPasar(int[] coordenadasPasar) {
        TransparentBackground.coordenadasPasar = coordenadasPasar;
    }

    public static int getAnchuraRango() {
        return anchuraRango;
    }

    public static void setAnchuraRango(int anchuraRango) {
        TransparentBackground.anchuraRango = anchuraRango;
    }

    public static int getAlturaRango() {
        return alturaRango;
    }

    public static void setAlturaRango(int alturaRango) {
        TransparentBackground.alturaRango = alturaRango;
    }

    public void setCoordenadas(Point coordenadas) {
        this.coordenadas = coordenadas;
    }

    public static int getAnchura() {
        return anchura;
    }

    public static void setAnchura(int anchura) {
        TransparentBackground.anchura = anchura;
    }

    public static int getAltura() {
        return altura;
    }

    public static void setAltura(int altura) {
        TransparentBackground.altura = altura;
    }

}
