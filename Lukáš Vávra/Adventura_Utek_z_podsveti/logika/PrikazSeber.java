package logika;
/**
 * Instance třídy {@code PrikazSeber} představují příkaz seber, pomocí něhož hráč sbírá předměty z prostoru a ukládá je do svého měšce (inventáře)
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Mesec mesec;

    /**Konstruktor třídy využívá herní plán, získává odkaz na měšec
     */
    public PrikazSeber(HerniPlan plan) {
        this.plan = plan;
        this.mesec = plan.getMesec();
    }
    /**
     * Provede příkaz "seber". Pokud jsou hráčem zadány špatně parametry, bude opraven.
     *
     *@param parametry - název věci, který má být sebrán
     *@return zpráva, která se vypíše hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo(co mám sebrat) tak ....
            return "Co mám sebrat? Musíš zadat název věci.";
        }

        String nazevSbiraneVeci = parametry[0];
        
        Prostor aktualniProstor = plan.getAktualniProstor();
        Vec sbiranaVec = aktualniProstor.odeberVec(nazevSbiraneVeci);

        if (sbiranaVec == null) {
            return "To tu není!";
        }
        else {
            if(sbiranaVec.isPrenositelnost()){
                if(mesec.vejdeSeVec()){
                    mesec.vlozVec(sbiranaVec)   ;
                    return "Věc " + nazevSbiraneVeci + " jsi úspěšně vložil do svého měšce.";
                }
                else{
                    aktualniProstor.vlozVec(sbiranaVec);
                    return "Tvůj měšec je už plný, budeš muset něco vyhodit!";
                }
            }

            else{
                aktualniProstor.vlozVec(sbiranaVec);
                return "To neuneseš.";
            }
        }
    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }





}
