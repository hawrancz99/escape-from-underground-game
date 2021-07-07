package logika;
/**
 * Instance třídy {@code PrikazJeTuNekdo} představují příkaz, který zjišťuje, zda-li se v místnosti nachází, nějaká další potava
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PrikazJeTuNekdo implements IPrikaz {
    private static final String NAZEV = "je_tu_někdo";
    private HerniPlan plan;
    /**
     * Konstruktor využívající herní plán
     */
    public PrikazJeTuNekdo(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * po zavolání této metody se vypíší postavy v prostoru kolem
     */
    public String provedPrikaz(String... parametry) {
        return this.plan.getAktualniProstor().jmenoPostav();
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}
