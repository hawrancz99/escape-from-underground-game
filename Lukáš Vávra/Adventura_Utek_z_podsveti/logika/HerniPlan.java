package logika;


/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Lukáš Vávra
 *@version    ZS 2019
 */
public class HerniPlan {
    
    private Prostor aktualniProstor;
    private Prostor vyherniProstor;
    private Postava postava;
    private Mesec mesec;

    /**
     *  Konstruktor který volá metodu, která vytváří jednotlivé prostory a propojuje je pomocí východů a vytváří inventář(měšec).
     */
    public HerniPlan() {
        zalozProstoryHry();
        mesec = new Mesec();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů. Jako výchozí aktuální prostor nastaví řeku Stryx.
     */
    private void zalozProstoryHry() {
        //prostory
        Prostor rekaStryx = new Prostor("řeka_Stryx", " Na břehu řeky Stryx, je tu slyšet nářek tisíců duší nebožtíků, které směřují na člun převozníka Charóna. Charón převáží mrtvé do podsvětí.\n"+
                "Je odpudivě špinavý, v očích mu žhnou plameny, tvář mu halí zanedbaný vous a kolem ramen má plášť. Můžeš si s ním promluvit.\n"+
                "                  _.._\n" +
                "   _________....-~    ~-.______\n" +
                "~~~                            ~~~~-----...___________..--------\n" +
                "                                           |   |     |\n" +
                "                                           | |   |  ||\n" +
                "                                           |  |  |   |\n" +
                "                                           |'. .' .`.|\n" +
                "___________________________________________|0oOO0oO0o|____________\n" +
                " -          -         -       -      -    / '  '. ` ` \\    -    -\n" +
                "      --                  --       --   /    '  . `   ` \\    --\n" +
                "---            ---          ---       /  '                \\ ---\n" +
                "     ----               ----        /       ' ' .    ` `    \\  ----\n" +
                "-----         -----         ----- /   '   '        `      `   \\\n" +
                "     ------          ------     /          '    . `     `    `  \\\n" +
                "            -------           /  '    '      '      `\n" +
                "                    --------/     '     '   '");
        Prostor vstupDoPodsveti = new Prostor("vstup_do_podsvětí","U vstupu do podsvětí. Duše převezných zemřelích zde čekají na soud. Soudcové podsvětí rozhodují, kam duše zemřelých poputují, na Asfodelová pole,\n"+
                "trestná pole nebo do Elysia. Avšak Kratos nemá v úmyslu se nechat rozsoudit. Pokud se někomu povede porazit samotného pána podsvětí Hádese, otevře se mu vstup zpět do světa živých.\n"+
                "Než se vydáš k branám podsvětí, pořádně bych se připravil, zkusil si promluvit s kým to půjde a vše pořádně prohledal, aby ti potom něco nechybělo.");
        Prostor kovarna = new Prostor("kovárna", "V Héfaistově kovárně. Héfaistos zrovna něco vyrábí, je slyšet řinčení kladiva a kovadliny. Tolik zbraní a brnění, třeba ti pomůže správně vybrat...\n"+
                " .-------..___ \n" +
                "  '-._     :_.-' \n" +
                "   .- ) _ ( --. \n" +
                "  :  '-' '-'  ;. \n" +
                " /'-.._____.-' | \n" +
                " |   |     \\   | \n" +
                " \\   |     /   \\ \n" +
                " |   \\     )_.-' \n" +
                " '-._/__..-'");
        Prostor tajemnaJeskyne = new Prostor("tajemná_jeskyně","V tajemné jeskyni. Slyšíš, že tě někdo zdraví a zná tvé jméno. Otočíš se a zjistíš, že je za tebou duch bohyně Athény.");
        Prostor branyPodsveti = new Prostor("brány_podsvětí","U bran do podsvětí. Bránu do podsvětí, kde se nachází samotný bůh podsvětí Hádes, střeží trojhlavý pes Kerberos! Pokud máš v měšci nějakou zbraň,\n"+
                "můžeš na něj zkusit zaútočit, nebo možná...\n"+
                "   / \\__\n" +
                "  (    @\\___\n" +
                "  /          O\n" +
                " /   (_____/\n" +
                "/_____/   U");
        Prostor tartaros = new Prostor("tartaros","V propasti Tartaros. Tartaros leží tak hluboko pod zemí, jak vysoko je nebe nad ním. Zeus zde uvěznil Titány poté, co s ním prohráli válku\n"+
                "Možná budou k něčemu užiteční....");
        Prostor podsveti = new Prostor("podsvětí","V podsvětí. Konečne jsi se dostal k Hádesovi. Všude je tma, ale slyšíš jak na tebe mluví. O tomto legendárním souboji se bude zpívat a psát navěky!\n"+
                "                            ,-.\n" +
                "       ___,---.__          /'|`\\          __,---,___\n" +
                "    ,-'    \\`    `-.____,-'  |  `-.____,-'    //    `-.\n" +
                "  ,'        |           ~'\\     /`~           |        `.\n" +
                " /      ___//              `. ,'          ,  , \\___      \\\n" +
                "|    ,-'   `-.__   _         |        ,    __,-'   `-.    |\n" +
                "|   /          /\\_  `   .    |    ,      _/\\          \\   |\n" +
                "\\  |           \\ \\`-.___ \\   |   / ___,-'/ /           |  /\n" +
                " \\  \\           | `._   `\\\\  |  //'   _,' |           /  /\n" +
                "  `-.\\         /'  _ `---'' , . ``---' _  `\\         /,-'\n" +
                "     ``       /     \\    ,='/ \\`=.    /     \\       ''\n" +
                "             |__   /|\\_,--.,-.--,--._/|\\   __|\n" +
                "             /  `./  \\\\`\\ |  |  | /,//' \\,'  \\\n" +
                "            /   /     ||--+--|--+-/-|     \\   \\\n" +
                "           |   |     /'\\_\\_\\ | /_/_/`\\     |   |\n" +
                "            \\   \\__, \\_     `~'     _/ .__/   /\n" +
                "             `-._,-'   `-._______,-'   `-._,-'");
        Prostor svet = new Prostor("svět", "Na světě. Konečně se můžeš pořádně nadechnout.");
        // průchody mezi prostory
        rekaStryx.setVychod(vstupDoPodsveti);
        vstupDoPodsveti.setVychod(kovarna);
        vstupDoPodsveti.setVychod(tajemnaJeskyne);
        vstupDoPodsveti.setVychod(branyPodsveti);
        tajemnaJeskyne.setVychod(vstupDoPodsveti);
        kovarna.setVychod(vstupDoPodsveti);
        branyPodsveti.setVychod(vstupDoPodsveti);
        branyPodsveti.setVychod(tartaros);
        branyPodsveti.setVychod(podsveti);
        tartaros.setVychod(branyPodsveti);
        podsveti.setVychod(branyPodsveti);
        podsveti.setVychod(svet);
        //zamčené prostory
        vstupDoPodsveti.zamknout(true);
        kovarna.zamknout(true);
        podsveti.zamknout(true);
        svet.zamknout(true);
        //věci
        rekaStryx.vlozVec(new Vec("mince", true));
        rekaStryx.vlozVec(new Vec("Charónův_člun", false));
        vstupDoPodsveti.vlozVec(new Vec("medový_koláč", true));
        vstupDoPodsveti.vlozVec(new Vec("jablečný_koláč", true));
        kovarna.vlozVec(new Vec("meč_Olympu",true));
        kovarna.vlozVec(new Vec("Achillovo_brnění", true));
        kovarna.vlozVec(new Vec("Héliův_vůz", false));
        kovarna.vlozVec(new Vec("okřídlená_Hermova_helma", true));
        kovarna.vlozVec(new Vec("okřídlené_Hermovi_sandály", true));
        kovarna.vlozVec(new Vec("šípy", true));
        tajemnaJeskyne.vlozVec(new Vec("kámen", true));
        branyPodsveti.vlozVec(new Vec("oštěp", true));
        branyPodsveti.vlozVec(new Vec("luk", true));
        //postavy
        rekaStryx.vlozPostavu(new Postava("převozník_Charón", "Kratos, bůh války, v podsvětí?? To je mi ale překvapení. Musel si svého tatíčka pořádně naštvat, haha!\n"+
                "Pardon, kde jé mé vychování. Jsem převozník Charón. Na svém člunu převážím pouze duše řádně pohřbených mrtvých, kteří mají peníze na palubní lístek. Pokud mi nemáš čím zaplatit,\n"+
                "budiž odsouzen na věčné bloudění na břehu Stryx.","Už jsi se mnou mluvil!"));
        vstupDoPodsveti.vlozPostavu(new Postava("stařenka_pekařka","Buď zdráv, mladý muži. Mé jméno je Elena, tedy bylo... Bývala jsem tam nejlepší pekařka široko daleko.\n"+
                "Jednoho dne jsem vezla čerstvě upečené medové koláče do vedlejší vesnice na trh. Najednou se na můj vozík s koláči vrhla smečka divokých psů a moje staré a slabé srdíčko to nevydrželo.\n"+
                "Tak jsem zde, čekám na soud.....Vypadáš hladově.Nemáš hlad? Měla bych tu mít někde ještě 2 koláče. Á tady jsou, vem si jaký chceš.","Už jsi se mnou mluvil!"));
        kovarna.vlozPostavu(new Postava("Héfaistos","Rád tě vidím Kratosi. Slyšel jsem, že chceš zničit Dia. V tom ti rád pomůžu. Zeus mě srazil z Olympu a od té doby prahnu po pomstě.\n"+
                "Nejdřív však musíš porazit Hádese, což nebude vůbec jednoduché. Myslím, že se správným brněním a mečem by se ti to mohlo podařit. Mám tu spoustu výrobků, té nejlepší kvality.\n"+
                "Vem si vše, co budeš potřebovat.","Už jsi se mnou mluvil!"));
        tajemnaJeskyne.vlozPostavu(new Postava("Athéna","Zdravím Kratosi. Vím, proč jsi tu a chci ti pomoci. Když jsi otevřel pandořinu skříňku, aby jsi přemohl Árese,\n"+
                "vypustil jsi do světa i zlo, které zatemnělo mysl všem bohům. Zeus musí padnout jinak lidstvo zanikne. Dej mi slib, že Dia zastavíš a já ti dám mé požehnání.","Už jsi se mnou mluvil!"));
        branyPodsveti.vlozPostavu(new Postava("Kerberos", "Vrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", "Už jsi se mnou mluvil!"));
        podsveti.vlozPostavu(new Postava("Hádés", "Kratosi, jsem rád, že jsi přišel. Tolik jsem toho o tobě slyšel. Smrtelník, který přemohl Árese a stal se novým bohem války.\n"+
                "Už dlouho si s tebou chci poměřit síly. Myslíš si, že máš na to porazit mě, natož Dia? HAHA! Pojďme se přesvědčit, tvá duše je má!!!! ","Už jsi se mnou mluvil!"));
        tartaros.vlozPostavu(new Postava("Kronos", "Jestli mě jdeš požádat o pomoc pro boj s bohy, tak tě zklamu. Po válce s nimy nemám dost sil ani na to dostat se z téhle zatracené propasti.\n"+
                "Jsi na to sám. A být tebou, při cestě zpět si dávám velký pozor, jeden krůček vedle a je po tobě....", "Už jsi se mnou mluvil!"));

        aktualniProstor = rekaStryx;
        vyherniProstor = svet;
    }
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    /**
     *  Metoda rozhoduje o tom, zda-li je momentální prostor ten vítězný (poslední)
     */
    public boolean jeVyhra (){
    return aktualniProstor.equals(vyherniProstor);
    }
    /**
     *  Metoda vrací odkaz na postavu
     *  @return  postava
     */
    public Postava getPostava(){
        return postava;
    }
    /**
     *  Metoda vrací odkaz na obsah saka
     *
     *@return  obsah Saka
     */
    public Mesec getMesec() {
        return mesec;
    }
    /**
     *  Metoda vrací odkaz na vitezny prostor,.
     *
     *@return     vitezny prostor
     */
    public Prostor getVyherniProstor() {
        return vyherniProstor;
    }
}
