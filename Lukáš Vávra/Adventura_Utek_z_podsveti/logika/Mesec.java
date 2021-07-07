package logika;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * Instance třídy {@code Mesec} představuje inventář(měšec) hráče, který má po dobu hraní k dispozici a do
 * kterého může ukládat sebrané věci
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class Mesec {
    private static final int KAPACITA = 4;
    private Map<String, Vec> seznamVeci;
    /**
     * Implementuje samotný inventář(měšec) pomocí HashMapy
     * na počátku je vložena věc slib, potřebná pro splění hry.
     */
    public Mesec()
    {
        seznamVeci = new HashMap<String, Vec>();
        vlozVec(new Vec ("slib", true));
    }
    /**
     * Metoda vrací hodnotu zda-li se může věc přidat do saka.
     * Kapacita je max. 4 věcí
     */
   public boolean vejdeSeVec(){
       if( seznamVeci.size() < KAPACITA){
           return true;
       }
       return false;
   }
    /**
     * Vrací true kdyz vlozi vec
     * Vrací false kdyz nevlozi vec
     * @param  vec
     * @return true, false
     */
   public boolean vlozVec(Vec vec){
       if (vejdeSeVec()) {
           seznamVeci.put(vec.getNazev(), vec);
           return true;
       } else {
           return false;
       }
   }
    /**
     * Metoda pro ziskani zadane veci,
     * @param nazev nazev hledane veci
     * @return vec
     */
    public Vec getVec(String nazev) {
       return seznamVeci.get(nazev);
    }
    /**
     * Metoda pro vypsání všech věcí nacházející se v Kratově měšci
     * Metoda prochází všemi položkami seznamu
     * @return obsah měšce
     */
    public String nazvyVeci() {
        int zbyva = 4 - this.seznamVeci.size();
        String nazvy = "Kratosi, ve tvém měšci se nyní nachází : ";
        if (this.seznamVeci.isEmpty()) {
            return nazvy + "bohužel nic..";
        } else {
            String jmenoVeci;
            for(Iterator var3 = this.seznamVeci.keySet().iterator(); var3.hasNext(); nazvy = nazvy + jmenoVeci + ", ") {
                jmenoVeci = (String)var3.next();
            }

            return zbyva > 0 ? nazvy + " ---> Můžeš sebrat ještě " + zbyva + " věcí." : nazvy + "---> Jsi sice silák, ale více věcí už neuneseš, pokud chceš něco sebrat,\n"+
                    "musíš se něčeho zbavit, ale dobře si rozmysli čeho.";
        }
    }
    /**
     * metoda vybere věc ze seznamu a odstraní ji z něj
     * zkotroluje zda-li seznam obsahuje věc
     * @param jmeno veci pro odstraneni
     */
    public Vec vyberVec(String jmeno) {
        if (this.seznamVeci.containsKey(jmeno)) {
            Vec vyhozenaVec = (Vec)this.seznamVeci.get(jmeno);
            this.seznamVeci.remove(jmeno);
            return vyhozenaVec;
        } else {
            return null;
        }
    }
    /**
     * Vrací true kdyz se v saku nachází vec
     * Vrací false kdyz se v saku vec nenachází
     * @param jmeno veci
     * @return true, false
     */
    public boolean jeVecVMesci(String jmeno) {
        return seznamVeci.containsKey(jmeno);
    }
}
