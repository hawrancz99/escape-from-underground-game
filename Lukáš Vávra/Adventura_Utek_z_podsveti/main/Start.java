package main;

import logika.*;
import uiText.TextoveRozhrani;
import java.io.File;

/**
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková a Lukáš Vávra
 * @version   ZS 2019
 */
public class Start
{
    /**
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        if(args.length == 0){
        ui.hraj();
    }
    else{
        ui.hrajZeSouboru(new File(args[0]));
    }
    }
    
    public Start(){

    }
}
