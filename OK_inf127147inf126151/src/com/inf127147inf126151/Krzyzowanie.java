package com.inf127147inf126151;

import java.util.ArrayList;
import java.util.List;

public class Krzyzowanie {

    private Rozwiazanie dziecko;

    public void krzyzuj(Rozwiazanie mama, Rozwiazanie tata) {
        Rozwiazanie dziecko = new Rozwiazanie();

        int i;
        for(i=0; i<(mama.getMaszynaA().size())/2; i++){
            dziecko.dodajOperacje((Operacja)mama.getMaszynaA().get(i));
            System.out.println(dziecko.getMaszynaA()
        }

        for(int j=i; i<(mama.getMaszynaB().size())/2; i++){
            dziecko.dodajOperacje((Operacja)mama.getMaszynaB().get(i));
        }


    }
}
