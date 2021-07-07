package logika;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková a Lukáš Vávra
 * @version   ZS 2019
 */
public class ProstorTest
{
    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {
        Prostor rekaStryx = new Prostor("řeka_Stryx", " Na břehu řeky Stryx, je tu slyšet nářek tisíců duší nebožtíků, které směřují na člun převozníka Charóna. Charón převáží mrtvé do podsvětí. Je odpudivě špinavý, v očích mu žhnou plameny, tvář mu halí zanedbaný vous a kolem ramen má plášť. Můžeš si s ním promluvit");
        Prostor vstupDoPodsveti = new Prostor("vstup_do_podsvětí","U vstupu do podsvětí. Duše převezných zemřelích zde čekají na soud. Soudcové podsvětí rozhodují, kam duše zemřelých poputují, na Asfodelová pole, trestná pole nebo do Elysia.\n"+
                "Avšak Kratos nemá v úmyslu se nechat rozsoudit. Pokud se někomu povede porazit samotného pána podsvětí Hádese, otevře se mu vstup zpět do světa živých.\n"+
                "Než se vydáš k branám podsvětí, pořádně bych se připravil a vše důkladně prozkoumal, aby ti potom něco nechybělo. Od bran do podsvětí již není cesty zpět.");
        Prostor kovarna = new Prostor("kovárna", "V Héfaistově kovárně. Héfaistos zrovna něco vyrábí, je slyšet řinčení kladiva a kovadliny. Tolik zbraní a brnění, třeba ti pomůže správně vybrat...");
        Prostor tajemnaJeskyne = new Prostor("tajemná_jeskyně","V tajemné jeskyni. Slyšíš, že tě někdo zdraví a zná tvé jméno. Otočíš se a zjistíš, že je za tebou duch bohyně Athény.");
        Prostor branyPodsveti = new Prostor("brány_podsvětí","U bran do podsvětí. Bránu do podsvětí, kde se nachází samotný bůh podsvětí Hádes, střeží trojhlavý pes Kerberos! Musíš najít způsob jak ho porazit.");
        Prostor tartaros = new Prostor("tartaros","V propasti Tartaros. Tartaros leží tak hluboko pod zemí, jak vysoko je nebe nad ním. Zeus zde uvěznil Titány poté, co s ním prohráli válku\n"+
                "Možná budou k něčemu užiteční....");
        Prostor podsveti = new Prostor("podsvětí","V podsvětí. Konečne jsi se dostal k Hádesovi. Všude je tma, ale slyšíš jak na tebe mluví. O tomto legendárním souboji se bude zpívat a psát navěky!");
        Prostor svet = new Prostor("svět", "Na světě. Konečně se můžeš pořádně nadechnout a vyrazit směr Olymp.");
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

        assertEquals (null, rekaStryx.vratSousedniProstor("les"));
        assertEquals (vstupDoPodsveti, rekaStryx.vratSousedniProstor("vstup_do_podsvětí"));
        assertEquals (tajemnaJeskyne, vstupDoPodsveti.vratSousedniProstor("tajemná_jeskyně"));
        assertEquals (kovarna, vstupDoPodsveti.vratSousedniProstor("kovárna"));
        assertEquals (branyPodsveti, vstupDoPodsveti.vratSousedniProstor("brány_podsvětí"));
        assertEquals (tartaros, branyPodsveti.vratSousedniProstor("tartaros"));
        assertEquals (podsveti, branyPodsveti.vratSousedniProstor("podsvětí"));
        assertEquals (svet, podsveti.vratSousedniProstor("svět"));
    }

}
