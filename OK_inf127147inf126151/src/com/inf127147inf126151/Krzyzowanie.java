package com.inf127147inf126151;

import java.util.ArrayList;

public class Krzyzowanie {

    private Rozwiazanie dziecko;

    public static void krzyzuj(Rozwiazanie mama, Rozwiazanie tata) {

        ArrayList<ObiektNaMaszynie> maintenance = new ArrayList<>();
        for(int i=0; i<tata.getMaszynaB().size(); i++){
            if(tata.getMaszynaB().get(i).getClass() == Maintenance.class){
                maintenance.add(tata.getMaszynaB().get(i));
            }
        }

        Rozwiazanie dziecko = new Rozwiazanie(maintenance);

        int i;
        for(i=0; i<(1+ mama.getMaszynaA().size())/2; i++){                //przepisanie polowy kodu matki z maszyny A
            dziecko.dodajOperacje((Operacja) mama.getMaszynaA().get(i));
            //dziecko.addSinglyMaszynaA(mama.getMaszynaA().get(i));
            //System.out.println(dziecko.getMaszynaA());
        }

        int j;
        for(j=0 ; j<(1+mama.getMaszynaB().size())/2; j++){                 //przepisanie polowy kodu matki z maszyny B
            if(mama.getMaszynaB().get(j).getClass() != Maintenance.class){
                dziecko.dodajOperacje((Operacja) mama.getMaszynaB().get(j));
            }

            //dziecko.addSinglyMaszynaB(mama.getMaszynaB().get(j));
        }

        boolean inList;
        for(int k=0; k<tata.getMaszynaA().size(); k++) {                    //dopisanie do maszyny A elementow maszyny A ojca

            inList = false;
            for(int b=0; b<dziecko.getMaszynaA().size(); b++){
                if(tata.getMaszynaA().get(k).getIndex() == dziecko.getMaszynaA().get(b).getIndex()){
                    inList = true;
                    //break;
                }
            }
            if(!inList){
                dziecko.dodajOperacje((Operacja) tata.getMaszynaA().get(k));
            }
        }

        System.out.println("========================");
        for(int l=0; l<tata.getMaszynaB().size(); l++){
            inList = false;
            if(tata.getMaszynaB().get(l).getClass() == Maintenance.class){
                continue;
            }
            for(int b=0; b<dziecko.getMaszynaB().size(); b++){
                if(tata.getMaszynaB().get(l).getIndex() == dziecko.getMaszynaB().get(b).getIndex()){
                    inList = true;
                    //break;
                }
            }
            if(!inList){
                dziecko.dodajOperacje((Operacja) tata.getMaszynaB().get(l));
            }

        }

        System.out.println("=============DZIECKO===========");
        dziecko.WyswietlRozwiazanie();
    }
}
