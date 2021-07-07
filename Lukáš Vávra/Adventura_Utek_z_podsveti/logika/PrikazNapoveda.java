package logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz nápověda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček a Lukáš Vávra
 *@version    ZS 2019
 *  
 */
class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "nápověda";
    private SeznamPrikazu platnePrikazy;
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    /**
     *  Vrací základní nápovědu po zadání příkazu "nápověda".
     *  @return nápověda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "\n"+
                "  Vaším úkolem je uniknout z podsvětí.\n\n"+
                "- Pro překonání řeky Stryx je třeba interakce s Charónem (sebrání a zaplacení mincí).\n\n"+
                "- Dále je třeba u vstupu do podsvětí sebrat medový koláč, který se bude hodit později pro překonání Kerbera.\n\n"+
                "- V místnosti tajemná jeskyně je potřeba dát slib Athéně, jinak se nelze dostat do kovárny, kde jsou předměty potřebné k dokončení hry.\n\n"+
                "- V kovárně Héfaistos napoví, jaké předměty se hodí pro finální boj s Hádésem (meč Olympu a Achillovo brnění). Bez nich na Hádése nemůžete zaútočit.\n\n"+
                "- Postupte k branám podsvětí. Kerberos miluje medový koláč. Dejte mu ho, seberte klíč od brány a postupte do podsvětí. \n"+
                "- Pokud vlezete do Tartaru, je 90% šance, že už z něj nevylezete a hra skončí. Pokud zaútočíte na Kerbera (pouze pokud máte meč Olympu) zabije Vás a hra také končí. \n\n "+
                "- V podsvětí můžete zaútočit na Hádése, pokud máte správné předměty (meč Olympu a Achillovo brnění).\n"+
                "- Pokud jste se některého z těchto předmětů v průběhu hry zbavil(a), je třeba začít odznovu.\n\n"+
                "- Po zabití Hádése seberte Hádovu přilbu neviditelnosti a pokračujte na Svět. (pro vstup je třeba Hádova přilba neviditelnosti). \n\n"+

                "Můžete zadat tyto příkazy: \n"
        + platnePrikazy.vratNazvyPrikazu();
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
