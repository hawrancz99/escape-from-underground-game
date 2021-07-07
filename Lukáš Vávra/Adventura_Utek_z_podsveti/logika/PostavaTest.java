package logika;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Testovací třída {@code PostavaTest} slouží ke komplexnímu otestování
 * třídy {@link PostavaTest}.
 *
 * @author  Lukáš Vávra
 * @version ZS 2019
 */
public class PostavaTest
{

    /**
     * Metoda testuje zda se po provedení příkazu vlozPostavu(), opravdu postava vloží do prostoru.
     */
    @Test
    public void testPostavy()
    {
        HerniPlan plan = new HerniPlan ();

        Postava postava1 = new Postava("převozník_Charón", "Kratos, bůh války, v podsvětí?? To je mi ale překvapení. Musel si svého tatíčka pořádně naštvat, haha!\n"+
                "Pardon, kde jé mé vychování. Jsem přeovzník Charón. Na svém člunu převážím pouze duše řádně pohřbených mrtvých, kteří mají peníze na palubní lístek. Pokud mi nemáš čím zaplatit, budiž odsouzen na věčné bloudění na břehu Stryx.","Už jsi se mnou mluvil!" );

        plan.getAktualniProstor().vlozPostavu(postava1);

        assertEquals (true, plan.getAktualniProstor().jeVProstoruPostava("převozník_Charón"));
    }

}
