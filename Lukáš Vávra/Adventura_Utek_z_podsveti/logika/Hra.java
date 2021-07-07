package logika;
/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova a Lukáš Vávra
 *@version    ZS 2019
 */
public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    public Mesec mesec;
    private Postava postava;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazJeTuNekdo(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazDat(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProhledat(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan, postava));
        platnePrikazy.vlozPrikaz(new PrikazMesec(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZautoc(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazVyhodit(mesec,herniPlan));
        mesec = new Mesec();
    }
    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "                 ,#####,\n" +
                "                 #_   _#\n" +
                "                 |a` `a|\n" +
                "                 |  u  |\n" +
                "                 \\  =  /\n" +
                "                 |\\___/|\n" +
                "        ___ ____/:     :\\____ ___\n" +
                "      .'   `.-===-\\   /-===-.`   '.\n" +
                "     /      .-\"\"\"\"\"-.-\"\"\"\"\"-.      \\\n" +
                "    /'             =:=             '\\\n" +
                "  .'  ' .:    o   -=:=-   o    :. '  `.\n" +
                "  (.'   /'. '-.....-'-.....-' .'\\   '.)\n" +
                "  /' ._/   \".     --:--     .\"   \\_. '\\\n" +
                " |  .'|      \".  ---:---  .\"      |'.  |\n" +
                " |  : |       |  ---:---  |       | :  |\n" +
                "  \\ : |       |_____._____|       | : /\n" +
                "  /   (       |----|------|       )   \\\n" +
                " /... .|      |    |      |      |. ...\\\n" +
                "|::::/''     /     |       \\     ''\\::::|\n" +
                "'\"\"\"\"       /'    .L_      `\\       \"\"\"\"'\n" +
                "           /'-.,__/` `\\__..-'\\\n" +
                "          ;      /     \\      ;\n" +
                "          :     /       \\     |\n" +
                "          |    /         \\.   |\n" +
                "          |`../           |  ,/\n" +
                "          ( _ )           |  _)\n" +
                "          |   |           |   |\n" +
                "          |___|           \\___|\n" +
                "          :===|            |==|\n" +
                "           \\  /            |__|\n" +
                "           /\\/\\           /\"\"\"`8.__\n" +
                "           |oo|           \\__.//___)\n" +
                "           |==|\n" +
                "           \\__/\n"+

                "V této hře jste Kratos (ten fešák nahoře). Kratos se narodil ve Spartě, kde ho do splodila jeho smrtlená matka Callisto a vládce bohů Zeus. Kratos se vypracoval až na generála spartské armády.\n" +
                "Na bitevním poli se mu dařilo výborně, nicméně i jeho potkal pád. V bitvě proti barbarským kmenům se zdálo být již vše ztracené, proto ze zoufalství požádal Arese,\n"+
                "aby mu pomohl zničit všechny nepřátele, výmeňou za jeho věrnost. Ares mu pomohl a Kratos na oplátku nějakou dobu vykonával vše, co si Ares zamanul, ať už šlo o zabíjení nevinných či cokoliv jiného.\n"+
                "Avšak jednou mu Ares nařídil vyplenit vesnici, ve které zrovna přebývali jeho žena s dcerou a nevědomky je zabil.\n"+
                "Kratos našel pandořinu skříňku, otevřel ji a pomocí síly získané z pandořiny skříňky přemohl Arese a stal se novým bohem války. Kratos dál hnán nočními můrami a běsy ze své minulosti,\n"+
                "začal velet Spartské armádě. Další bůh, který ho podvedl byl samotný Zeus, když mu nepomohl v bitvě, zbavil ho božské moci a uvrhl do podsvětí.\n"+
                "Kratos, nyní plný nenávisti vůči bohuům, má jediný cíl- jednou provždy zničit Dia. Avšak nejdříve se musí dostat ven z podsvětí. Jestli se z něho dostane záleží už jen na Vás.\n" +
                "Vaším inventářem je měšec, který unese maximálně 4 předmětů, hmotných či nehmotných. Věci, které zahodíte lze opět sebrat z prostoru. Z některých prostorů se dostanete zpět, z některých ne.\n"+
                "Hodně štěstí!\n"+
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n\n"
                +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Konec hry! Moc děkuji, že jste si zahrál(a)";
    }
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            if(herniPlan.jeVyhra()){
                konecHry= true;
                textKVypsani += "\n Unikl jsi z podsvětí a v dáli vidíš Olymp. Pomsta čeká!...\n" +
                        "                 \n"+
                        "          /\\\n" +
                        "         /**\\\n" +
                        "        /****\\   /\\\n" +
                        "       /      \\ /**\\\n" +
                        "      /  /\\    /    \\        /\\    /\\  /\\      /\\            /\\/\\/\\  /\\\n" +
                        "     /  /  \\  /      \\      /  \\/\\/  \\/  \\  /\\/  \\/\\  /\\  /\\/ / /  \\/  \\\n" +
                        "    /  /    \\/ /\\     \\    /    \\ \\  /    \\/ /   /  \\/  \\/  \\  /    \\   \\\n" +
                        "   /  /      \\/  \\/\\   \\  /      \\    /   /    \\\n" +
                        "__/__/_______/___/__\\___\\__________________________________________________";
        }
    }
        else {
            textKVypsani="Kratosi, tvoje příkazy jsou nesrozumitelné, oprav se. ";
        }
        return textKVypsani;
    
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

