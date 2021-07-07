package logika;
/**
 * Instance třídy {@code PrikazMesec} představují výpis  informací na obrazovku o hráčově měšci (inventáři)
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PrikazMesec implements IPrikaz {
    private static final String NAZEV = "měšec";
    private HerniPlan plan;
    /**
     * Konstruktor využívající herní plán
     */
    public PrikazMesec(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * Po zavolání ptohoto příkazu se vypíše obsah měšce
     * @param   - volá se bez parametrů
     * @return  - vrací výpis věcí, které jsou v měšci
     */
    public String provedPrikaz(String... parametry) {
        return this.plan.getMesec().nazvyVeci();
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  @return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}