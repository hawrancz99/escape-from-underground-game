package logika;
/**
 * Instance třídy {@code PrikazMluv} představují příkaz "mluv" pomocí něhož hráč může komunikovat s ostatními postavami
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    public Postava postava;

    /**
     *  Konstruktor využívající herní plán a postavu
     */

    public PrikazMluv(HerniPlan plan, Postava postava) {
        this.plan = plan;
        this.postava = postava;
    }

    /**
     * Po zavolání tohoto příkazu promluvíte se zadanou osobou (vrátí se její proslov)
     * Také opraví hráče při špatném zadáním parametrů
     *
     * @param   - jmeno postavy se kterou chce hráč mluvit
     * @return  - vraci vypis proslovu nebo opravu hráče
     */
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0)
        {
            return "S kým chceš mluvit? Zadej jméno postavy.";
        }

        if (parametry.length >= 2)
        {
            return "Můžeš mluvit jen s jednou postavou.";
        }

        String jmenoPostavy = parametry[0];
        Prostor aktProstor = plan.getAktualniProstor();
        Postava postava = aktProstor.vyberPostavu(jmenoPostavy);

        if (aktProstor.jeVProstoruPostava(jmenoPostavy) && !postava.promluveno)
        {
            postava.promluveno = true;
            return postava.getProslov();

        }
        else if(aktProstor.jeVProstoruPostava(jmenoPostavy) && postava.promluveno) {
            return postava.getProslov2();
        }

        return "Nikdo takový zde není. ";
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
