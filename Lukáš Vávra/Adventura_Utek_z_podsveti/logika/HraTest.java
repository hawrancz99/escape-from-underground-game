package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída HraTest slouží ke komplexnímu otestování třídy Hra
 *
 * @author    Jarmila Pavlíčková a Lukáš Vávra
 * @version  ZS 2019
 */
public class HraTest {
    private Hra hra1;

    /**
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /**
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /**
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("řeka_Stryx", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber mince");
        hra1.zpracujPrikaz("dát převozník_Charón mince");
        hra1.zpracujPrikaz("jdi vstup_do_podsvětí");
        assertEquals(false, hra1.konecHry());

        assertEquals("vstup_do_podsvětí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber medový_koláč");
        hra1.zpracujPrikaz("jdi tajemná_jeskyně");
        assertEquals(false, hra1.konecHry());

        assertEquals("tajemná_jeskyně", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dát Athéna slib");
        hra1.zpracujPrikaz("jdi vstup_do_podsvětí");
        assertEquals(false, hra1.konecHry());

        assertEquals("vstup_do_podsvětí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi kovárna");
        assertEquals(false, hra1.konecHry());

        assertEquals("kovárna", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber meč_Olympu");
        hra1.zpracujPrikaz("vyhodit palubní_lístek");
        hra1.zpracujPrikaz("seber Achillovo_brnění");
        hra1.zpracujPrikaz("jdi vstup_do_podsvětí");
        assertEquals(false, hra1.konecHry());

        assertEquals("vstup_do_podsvětí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi brány_podsvětí");
        assertEquals(false, hra1.konecHry());

        assertEquals("brány_podsvětí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dát Kerberos medový_koláč");
        hra1.zpracujPrikaz("seber klíč_od_brány");
        hra1.zpracujPrikaz("jdi podsvětí");
        assertEquals(false, hra1.konecHry());

        assertEquals("podsvětí", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("zaútoč Hádés");
        hra1.zpracujPrikaz("seber Hádova_přilba_neviditelnosti");
        hra1.zpracujPrikaz("jdi svět");
        assertEquals(true, hra1.konecHry());

        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
}
