package logika;
/**
 * Instance třídy {@code PrikazDat} umožňuje zadat příkaz "dát", kterým věnujete některé z postav něco ze svého měšce
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PrikazDat implements IPrikaz
{
    private static final String NAZEV = "dát";
    private HerniPlan plan;

    /**
     * Konstruktor, využívá třídy HerniPlan, ve které se nachází věci a postavy
     *
     */
    public PrikazDat(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Příkaz Dej interaguje s postavou. Postava musí být v aktuální místnosti
     *
     *@param parametry - 1. parametr je jméno postavy,
     *                   2. parametr je jméno věci,
     *
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Copak chcete někomu dát?? Zadejte 2 parametry - jméno postavy a název věci";
        }
        else if (parametry.length == 1) {
            return "Zadejte jméno postavy a název věci - tento příkaz vyžaduje dva parametry";
        }
        else if (parametry.length >= 3) {
            return "Nemužete rozdávat více věcí";
        }
        else if (plan.getAktualniProstor().getNazev().equals("řeka_Stryx") && plan.getMesec().jeVecVMesci("mince")
                && parametry[0].equals("převozník_Charón") && parametry[1].equals("mince") && plan.getMesec().vejdeSeVec()) {
            plan.getMesec().vyberVec("mince");
            plan.getMesec().vlozVec(new Vec("palubní_lístek", true));

            return "Právě jsi dal Charónovi minci, ten ti dal palubní lístek a převezl tě na druhý břeh. Vstup do podsvětí je ti nyní otevřený.\n";
        }
        else if (plan.getAktualniProstor().getNazev().equals("tajemná_jeskyně") && plan.getMesec().jeVecVMesci("slib")
                && parametry[0].equals("Athéna") && parametry[1].equals("slib")) {
            plan.getMesec().vyberVec("slib");
            plan.getMesec().vlozVec(new Vec("požehnání_bohyně_Athény", true));

            return "Dal jsi Athéně svůj slib, že zastavíš Dia. Na oplátku jsi dostal požehnání bohyně Athény\n";
        }
        else if (plan.getAktualniProstor().getNazev().equals("brány_podsvětí") && plan.getMesec().jeVecVMesci("medový_koláč")
                && parametry[0].equals("Kerberos") && parametry[1].equals("medový_koláč")) {
            plan.getMesec().vyberVec("medový_koláč");
            plan.getAktualniProstor().vlozVec(new Vec("klíč_od_brány", true));

            return "Nalákal jsi Kerbera na medový koláč. Spokojeně si na něm hoduje a ty vidíš jak se něco malého leskne na zemi před bránou. Možná se to bude hodit..\n";
        }
        return "Nikomu jste nic nedal. Někde je chyba";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    @Override
    public String getNazev()
    {
        return NAZEV;
    }

}
