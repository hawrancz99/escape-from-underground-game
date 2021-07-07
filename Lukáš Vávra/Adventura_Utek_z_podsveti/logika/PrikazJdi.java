package logika;
/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček a Lukáš Vávra
 *@version    ZS 2019
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
       this.hra = hra;
    }

    /**
     *  Metoda vrací text, chceme-li vejít do zamčeného prostoru, aniž bychom splnili požadavek k odemčení
     *  @return Stringový text
     */
    public String zamcenoVypis(){
        if (plan.getAktualniProstor().getNazev().equals("řeka_Stryx"))
        {
            return "Musíte najít způsob jak se dostat přes řeku. Zkusil bych se rozhlédnout kolem...";
        }
        if (plan.getAktualniProstor().getNazev().equals("vstup_do_podsvětí"))
        {
            return "Do Héfaistovi kovárny nemůže jít jen tak někdo. Musíš být bůh nebo mít od nějakého boha požehnání...";
        }
        if (plan.getAktualniProstor().getNazev().equals("brány_podsvětí"))
        {
            return "Od Kerbera neutečeš, musíš ho porazit a otevřít bránu podsvětí!";
        }
        if (plan.getAktualniProstor().getNazev().equals("podsvětí"))
        {
            return "Musíš porazit Hádése a vzít si jeho přilbu!";
        }
        return "Prostor musíte nějak odemknout";
    }
    /**
     * Rozhoduje, zdali jsou splněny podmínky pro odemčení místnosti hráčem
     */
    public boolean odemProstor (){

        if (plan.getAktualniProstor().getNazev().equals("řeka_Stryx") && plan.getMesec().jeVecVMesci("palubní_lístek") ||
                plan.getAktualniProstor().getNazev().equals("vstup_do_podsvětí") && plan.getMesec().jeVecVMesci("požehnání_bohyně_Athény") ||
                plan.getAktualniProstor().getNazev().equals("tajemná_jeskyně") || plan.getAktualniProstor().getNazev().equals("kovárna")||
                plan.getAktualniProstor().getNazev().equals("brány_podsvětí") && plan.getMesec().jeVecVMesci("klíč_od_brány") ||
                plan.getAktualniProstor().getNazev().equals("podsvětí") && plan.getMesec().jeVecVMesci("Hádova_přilba_neviditelnosti")
        )
        {
            return true;
        }
        return false;
    }
    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0)
        {
            return "Očekávám tvoje rozkazy Kratosi!";
        }

        String smer = parametry[0];

        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        odemProstor();

        if (sousedniProstor == null)
        {
            return "Kratosi, tam jít nemůžeš!";
        }
        if (plan.getAktualniProstor().getNazev().equals(smer))
        {
            return "Však už tu jsi!! Kam bys chodil??";
        }

        if (sousedniProstor.jeZamceno() && !odemProstor())
        {
            return "Tam nelze jít \n" + zamcenoVypis();
        }
        //pokud se postava nachází v prostoru tartaros a pokusí se jít do brány podsvětí, je 90% šance, že hra skončí
       if(plan.getAktualniProstor().getNazev().equals("tartaros") && smer.equals("brány_podsvětí")){
            double sanceSpadnuti =  Math.random();
               if(sanceSpadnuti < 0.1){
                   //úspěšně se dostane zpět do brány_podsvětí
                   plan.setAktualniProstor(sousedniProstor);
                   return "Máš víc štěstí, než rozumu! Povedlo se ti dostat se z Tartaru zpět!" + sousedniProstor.dlouhyPopis() ;
               }
                       hra.setKonecHry(true);
               return "Kronos měl pravdu. Spadl jsi do propasti a zemřel jsi. Příště si dobře rozmysli, kam půjdeš.";
        }

        if (odemProstor())
        {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();
        }

        plan.setAktualniProstor(sousedniProstor);
        return sousedniProstor.dlouhyPopis();

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
