package com.inf127147inf126151;

import java.util.ArrayList;
import java.util.List;

public class Krzyzowanie {

    private Rozwiazanie dziecko;

    public static void krzyzuj(Rozwiazanie mama, Rozwiazanie tata) {
        Rozwiazanie dziecko = new Rozwiazanie();

        int i;
        for(i=0; i<(mama.getMaszynaA().size())/2; i++){                //przepisanie polowy kodu matki z maszyny A
            //dziecko.dodajOperacje((Operacja) mama.getMaszynaA().get(i));
            dziecko.addSinglyMaszynaA(mama.getMaszynaA().get(i));
            //System.out.println(dziecko.getMaszynaA());
        }

        int j;
        for(j=0 ; j<(mama.getMaszynaB().size())/2; j++){                 //przepisanie polowy kodu matki z maszyny B
            //dziecko.dodajOperacje((Operacja) mama.getMaszynaB().get(i));
            dziecko.addSinglyMaszynaB(mama.getMaszynaB().get(j));
        }

        boolean inList;
        for(int k=i; k<tata.getMaszynaA().size(); k++) {                    //dopisanie do maszyny A elementow maszyny A ojca
            inList = false;
            for(int b=0; b<dziecko.getMaszynaA().size(); b++){
                if(tata.getMaszynaA().get(k).getIndex() == dziecko.getMaszynaA().get(b).getIndex()){
                    inList = true;
                    break;
                }
            }
            if(!inList){
                dziecko.getMaszynaA().add(tata.getMaszynaA().get(k));
            }
        }
        System.out.println("=============DZIECKO===========");
        dziecko.WyswietlRozwiazanie();
    }
}
