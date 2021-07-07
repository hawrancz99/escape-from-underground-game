package logika;
/**
 * Instance třídy Vec představují názvy a možnost přenášení věcí
 *
 * @author    Lukáš Vávra
 * @version   ZS 2019
 */
public class Vec {
private String nazev;
private boolean prenositelnost;
    /**
     *  Konstruktor implementuje nazev a prenositelnost dane veci
     */
    public Vec(String nazev, boolean prenositelnost)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
    }

    /***
     *  Metoda vrací název věci
     */
    public String getNazev(){
        return nazev;
    }

    /***
     *  Metoda vrací zda-li je věc přenositelná či nikoliv
     */
    public boolean isPrenositelnost(){
        return prenositelnost;
    }

}
