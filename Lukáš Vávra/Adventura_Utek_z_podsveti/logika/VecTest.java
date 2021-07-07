package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testovací třída VecTest slouží ke komplexnímu otestování třídy Vec
 *
 * @author    Lukáš Vávra
 * @version   ZS 2019
 */
public class VecTest
{
    Vec vec1;
    Vec vec2;
    Vec vec3;
    Vec vec4;

    /**
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1 = new Vec("mince", true);
        vec2 = new Vec("Charónův_člun", false);
        vec3 = new Vec("jablečný_koláč", true);
        vec4 = new Vec("Héliův_vůz", false);
    }

    /**
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown() {
    }
    /**
     * Testuje, zda věc vrací správný název a přenositelnost.
     *
     */
    @Test
    public void testVec()
    {
        assertEquals(true, vec1.isPrenositelnost());
        assertEquals(false, vec2.isPrenositelnost());
        assertEquals(true, vec3.isPrenositelnost());
        assertEquals(false, vec4.isPrenositelnost());
    }
}
