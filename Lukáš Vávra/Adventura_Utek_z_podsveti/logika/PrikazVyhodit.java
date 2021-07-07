package logika;
/**
 * Instance třídy {@code PrikazVyhodit} představují příkaz umožňující hráči vyhodit předměty ze svého měšce (inventáře)
 * Tyto předměty se vloží do místnosti a jdou opět sebrat
 *
 * @author  Lukáš Vávra
 * @version LS 2019
 */
public class PrikazVyhodit implements IPrikaz {
    private static final String NAZEV = "vyhodit";
    public Hra hra;
    public Mesec mesec;
    public HerniPlan plan;
    /**
     * Konstruktor využívající třídy mesec a herní plán
     */
    public PrikazVyhodit(Mesec mesec, HerniPlan plan) {
        this.mesec = mesec;
        this.plan = plan;
    }
    /**
     *  Provádí příkaz "vyhodit". Daná věc musí být v měšci
     *
     *@param parametry - jako  parametr obsahuje jméno věci
     *
     *@return zpráva, kterou hra vypíše hráči
     */
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Co chceš zahodit?\n";
        } else if (parametry.length >= 2) {
            return "Můžeš vyhodit jen 1 věc v daný moment.\n";
        } else {
            String vyhozenaVec = parametry[0];
            Mesec mesec = this.plan.getMesec();
            if (mesec.jeVecVMesci(vyhozenaVec)) {
                mesec.vyberVec(vyhozenaVec);
                this.plan.getAktualniProstor().vlozVec(new Vec(vyhozenaVec, true));
                return "Zahodil jsi: " + vyhozenaVec + "\n";
            } else {
                return "Tuto věc v měšci nemáš!! \n";
            }
        }
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
}