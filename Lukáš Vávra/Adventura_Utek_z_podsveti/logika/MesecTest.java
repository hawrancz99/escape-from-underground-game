package logika;

import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Testovací třída {@code MesecTest} slouží ke komplexnímu otestování
 * třídy {@link MesecTest}.
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class MesecTest
{

    /**
     * Test kapacity měšce(max. kapacita je 4)
     */
    @Test
    public void testKapacityMesce()
    {
        Mesec sako = new Mesec();

        Vec vec1 = new Vec ("mince", true);
        Vec vec2 = new Vec ("medový_koláč", true);
        Vec vec3 = new Vec ("meč_Olympu", true);
        Vec vec4 = new Vec ("Achillovo_brnění", true);
        Vec vec5 = new Vec ("luk", true);
        Vec vec6 = new Vec ("oštěp", true);



        assertEquals (true, sako.vejdeSeVec());
        sako.vlozVec(vec1);

        assertEquals (true, sako.vejdeSeVec());
        sako.vlozVec(vec2);

        assertEquals (true, sako.vejdeSeVec());
        sako.vlozVec(vec3);

        assertEquals (false, sako.vejdeSeVec());
        sako.vlozVec(vec4);

        assertEquals (false, sako.vejdeSeVec());
        sako.vlozVec(vec5);

        assertEquals (false, sako.vejdeSeVec());
        sako.vlozVec(vec6);



    }


}
