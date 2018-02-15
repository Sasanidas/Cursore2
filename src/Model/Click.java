package Model;

import java.awt.Point;

/**
 *
 * @author FerminMunozF
 */
public class Click extends Point {

    public Click() {
    }

    private int duracionClick, randomuno, randomdos;
    private boolean doble, random, sihayTexto, esCuadrado = false;
    private int[] coordenadasClick;
    private Point coordenadas;
    

    private String teclasaEscribir, alias;

    public Click(int x, int y, int duracionClick, boolean doble, boolean random, boolean sihayTexto) {
        super(x, y);
        this.random = random;
        this.duracionClick = duracionClick;
        this.doble = doble;
        this.sihayTexto = sihayTexto;
    }

    /**
     * @return the duracionClick
     */
    public int getDuracionClick() {
        return duracionClick;
    }

    public boolean isEsCuadrado() {
        return esCuadrado;
    }

    public Point getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Point coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setEsCuadrado(boolean esCuadrado) {
        this.esCuadrado = esCuadrado;
    }

    /**
     * @param duracionClick the duracionClick to set
     */
    public void setDuracionClick(int duracionClick) {
        this.duracionClick = duracionClick;
    }

    public int[] getCoordenadasClick() {
        return coordenadasClick;
    }

    public void setCoordenadasClick(int[] coordenadasClick) {
        this.coordenadasClick = coordenadasClick;
    }

    /**
     * @return the doble
     */
    public boolean isDoble() {
        return doble;
    }

    /**
     * @param doble the doble to set
     */
    public void setDoble(boolean doble) {
        this.doble = doble;
    }

    /**
     * @return the random
     */
    public boolean isRandom() {
        return random;
    }

    /**
     * @param random the random to set
     */
    public void setRandom(boolean random) {
        this.random = random;
    }

    /**
     * @return the randomuno
     */
    public int getRandomuno() {
        return randomuno;
    }

    /**
     * @param randomuno the randomuno to set
     */
    public void setRandomuno(int randomuno) {
        this.randomuno = randomuno;
    }

    /**
     * @return the randomdos
     */
    public int getRandomdos() {
        return randomdos;
    }

    /**
     * @param randomdos the randomdos to set
     */
    public void setRandomdos(int randomdos) {
        this.randomdos = randomdos;
    }

    /**
     * @return the teclasaEscribir
     */
    public String getTeclasaEscribir() {
        return teclasaEscribir;
    }

    /**
     * @param teclasaEscribir the teclasaEscribir to set
     */
    public void setTeclasaEscribir(String teclasaEscribir) {
        this.teclasaEscribir = teclasaEscribir;
    }

    /**
     * @return the Alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param Alias the Alias to set
     */
    public void setAlias(String Alias) {
        this.alias = Alias;
    }

    /**
     * @return the sihayTexto
     */
    public boolean isSihayTexto() {
        return sihayTexto;
    }

    /**
     * @param sihayTexto the sihayTexto to set
     */
    public void setSihayTexto(boolean sihayTexto) {
        this.sihayTexto = sihayTexto;
    }
}
