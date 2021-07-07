package logika;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova a Lukáš Vávra
 * @version ZS 2019
 */
public class Prostor {
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;
    private Map<String, Vec> veci;
    private Map<String, Postava> seznamPostav;
    private boolean zamcena = false;
    /**
     * Vytvoření prostoru se zadaným popisem
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        veci = new HashMap<>();
        seznamPostav = new HashMap<>();
    }

    /**
     * Metoda zamkne místnost
     */
    public void zamknout (boolean zamceno) {
        this.zamcena = zamceno;
    }

    /**
     * Metoda vrací true nebo false podle toho pokud je místnost zamčená nebo ne
     */
    public boolean jeZamceno(){
        return zamcena;
    }
    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }
    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prostor)) {
            return false;
        }
            Prostor druhy = (Prostor)o;
            return java.util.Objects.equals(this.nazev, druhy.nazev);

    }
    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
        @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;
    }
    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jste v mistnosti/prostoru " + popis + ".\n" + popisVychodu() + "\n";
    }
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "";
        if(!vychody.isEmpty()){
            vracenyText = "východy: ";
            for (Prostor sousedni : vychody) {
                vracenyText += " " + sousedni.getNazev();
                if(sousedni.jeZamceno()){
                    vracenyText += "(zamknuto)";
                }
            }
        }
        return vracenyText;
    }
    /**
     * Vrací nazvy věcí nacházející se v místnosti
     */
    public String nazvyVeci() {
        String vracenyText = "věci v místnosti:";
        if (veci.isEmpty()) {
            vracenyText += "nikde nic, hledejte někde jinde" ;
        }
        for (String nazevVeci : veci.keySet()) {
            vracenyText += " ; " + nazevVeci;
        }
        return vracenyText;
    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                        .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }
    /**
     * Metoda vkládající věc do prostoru
     *
     */
    public boolean vlozVec(Vec neco) {
        if(veci.containsKey(neco.getNazev())){
            return false;
        }

        veci.put(neco.getNazev(),neco);
        return true;
    }

    /**
     * Metoda odebírající věc z prostoru
     *
     */
    public Vec odeberVec (String nazevVeci){
        return veci.remove(nazevVeci);
    }

    /**
     * metoda vloží libovolnou postavu do prostoru
     * @param postava má 2 parametry: String jmeno, String proslov
     * @return typ void - bez návratové hodnoty
     */
    public void vlozPostavu (Postava postava){
        seznamPostav.put(postava.getJmeno(),postava );
    }
    /**
     * Metoda odebírající postavu z prostoru
     *
     */
    public void odeberPostavu (String jmeno){
        seznamPostav.remove(jmeno);
    }


    /**
     * metoda vrátí text se jmény postav v daném prostoru
     *
     * @return jména postav
     */
    public String jmenoPostav() {
        String vysledek = "\n Po rozhlídnutí jsi zjistil, že jsou kolem tyto postavy: ";
        if (seznamPostav.isEmpty()){
            vysledek += "Nikde nikdo :(";
            return vysledek + "\n";
        }

        for (Postava postava : seznamPostav.values()){

            vysledek += postava.getJmeno() + ", ";
        }
        return vysledek;
    }
    /**
     * metoda vybere postavu ze seznamu
     * zkotroluje zda-li se daná postava nachází v prostoru
     * narozdíl od metody jeVProstoruPostava() vrací celé jméno postavy a ne jen hodnotu boolean
     * @param //postava má 2 parametry: String jmeno, String proslov
     * @return proslov
     */
    public Postava vyberPostavu (String jmeno){
        Postava proslov;
        if (seznamPostav.containsKey(jmeno))
        {
            proslov = seznamPostav.get(jmeno);
            return proslov;
        }
        return null;
    }
    /**
     * Vrací true kdyz je v prostoru vec
     * Vrací false kdyz neni v prostoru vec
     * @param //nazev veci
     * @return true, false
     */
    public boolean jeVProstoruVec (String jmeno) {
        return veci.containsKey(jmeno);

    }
    /**
     * Vrací true kdyz je v prostoru postava
     * Vrací false kdyz neni v prostoru postava
     * @param jmeno postava
     * @return true, false
     */
    public boolean jeVProstoruPostava (String jmeno){
        return seznamPostav.containsKey(jmeno);
    }
}
