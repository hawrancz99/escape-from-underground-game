package logika;

/**
 * Class Postava - popisuje obecné chování všech postav
 * @author Lukáš Vávra
 * @version ZS 2019
 */
public class Postava {
    String jmeno;
    String proslov;
    String proslov2;
    boolean promluveno = false;
    /**
     *  Konstruktor vytvářející jméno a proslov postavy
     */
    public Postava(String jmeno, String proslov, String proslov2)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.proslov2 = proslov2;
    }
    /**
     *  Tato metoda vrací jméno postavy
     *  @return jméno postavy
     */
    public String getJmeno(){
        return jmeno;
    }
    /**
     *  Tato metoda vrací proslov postavy
     *  @return proslov postavy
     */
    public String getProslov(){
        return proslov;
    }

    public boolean getPromluveno(){
        return promluveno;
    }
    public String getProslov2(){
        return proslov2;
    }
}

