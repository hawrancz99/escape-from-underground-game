package logika;

/**
 * Instance třídy {@code PrikazZautoc} představují příkaz "zaútoč" pomocí něhož lze zautocit na nepřátelské postavy
 * Pro úspěšný útok u sebe musí mít hráč správný předmět nebo jejich kombinaci
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PrikazZautoc implements IPrikaz {
    private static final String NAZEV = "zaútoč";
    private HerniPlan plan;
    private Hra hra;

    /**
     * konstruktor využívající herni plan a hru
     */
    public PrikazZautoc(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * Příkaz zautoc interaguje s nepřátelskou postavou
     * opravuje špatně zadaný příkaz
     *
     * @param parametry -  parametr je jméno postavy,
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Na koho chcete zaútočit? Zadejte název postavy.\n";
        }
        if (parametry.length >= 2) {
            return "Nemužete útočit na více postav najednou.\n";
        }
        if (plan.getAktualniProstor().getNazev().equals("brány_podsvětí") && parametry[0].equals("Kerberos") && plan.getMesec().jeVecVMesci("meč_Olympu")
                || plan.getMesec().jeVecVMesci("luk") || plan.getMesec().jeVecVMesci("oštěp") || plan.getMesec().jeVecVMesci("šípy")
                || plan.getMesec().jeVecVMesci("kámen"))
        {
            hra.setKonecHry(true);
            return "Zaútočil jsi na Kerbera, ale to ho ještě více rozzuřilo. Hodil tě do propasti Tartaros a zemřel jsi. Budeš ho muset příště přelstít jinak.";
        }
        if (plan.getAktualniProstor().getNazev().equals("podsvětí") && plan.getMesec().jeVecVMesci("meč_Olympu") &&
                plan.getMesec().jeVecVMesci("Achillovo_brnění") && parametry[0].equals("Hádés"))
        {
            plan.getMesec().vyberVec("meč_Olympu");
            plan.getMesec().vyberVec("Achillovo_brnění");
            plan.getAktualniProstor().vlozVec(new Vec ("Hádova_přilba_neviditelnosti", true));
            plan.getAktualniProstor().odeberPostavu("Hádés");

            return "Byl to těžký boj, ale Hádés padl! Výstup  na Svět je otevřený.\n"
                    +"Navíc jsi získal i Hádovu přilbu neviditelnosti. Ta se určitě bude hodit pro boj s Diem!\n"
                    +"Stačí ji sebrat a hurá na čerstvý vzduch!\n";
        }


        return "Nic se nestalo, na nikoho jsi nazaútočil! Zadal js buď špatné jméno postavy, nebo nemáš dostatečné předměty pro útok na danou postavu.\n" +
                " Hurá do útoku!\n";
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}