package logika;
/**
 * Instance třídy {@code PrikazProhledat} implementuje pro hru příkaz prohledat, pomocí něhož hráč najde
 * v místnosti předměty pro hraní hry
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */

public class PrikazProhledat implements IPrikaz {
    private static final String NAZEV = "prohledat";
    private HerniPlan plan;
    /**
     *  Konstruktor vrací odkaz na herní plán, který vytváří podobu hry
     */
    public PrikazProhledat(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * Tato metoda zahajuje provádění příkazu prohledat, pomocí níž se hráč porozhlédne po herním prostoru
     *
     * @param   - vola se bez parametru
     * @return  - vraci vypis veci v místnosti
     */
    public String provedPrikaz(String... parametry) {
        return this.plan.getAktualniProstor().nazvyVeci();
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}