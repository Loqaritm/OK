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
        ArrayList<ObiektNaMaszynie> samotniA = new ArrayList<>();
        ArrayList<ObiektNaMaszynie> samotniB = new ArrayList<>();

        for(int i=0; i<(1+ mama.getMaszynaA().size())/2; i++){                      //przepisanie polowy kodu matki z maszyny A
            dziecko.dodajOperacje((Operacja) mama.getMaszynaA().get(i));
            samotniA.add(mama.getMaszynaA().get(mama.getMaszynaA().size() - i -1));   //stworzenie samotnychA
        }

        int j;
        int mamaSizeB = mama.getMaszynaB().size();
        for(j=0 ; j<(1+mamaSizeB)/2; j++){                                              //przepisanie polowy kodu matki z maszyny B
            if(mama.getMaszynaB().get(j).getClass() != Maintenance.class) {
                dziecko.dodajOperacje((Operacja) mama.getMaszynaB().get(j));
            }
            if(!(mama.getMaszynaB().get(mamaSizeB -j -1) instanceof Maintenance)){
                samotniB.add(mama.getMaszynaB().get(mamaSizeB -j -1));              //stworzenie samotnychB
            }
        }

        for(int k=0; k<tata.getMaszynaA().size(); k++) {                    //dopisanie do maszyny A elementow maszyny A ojca ktore sa w samotnychA
            for(int i=0; i<samotniA.size(); i++){
                if((tata.getMaszynaA().get(k).getIndex() == samotniA.get(i).getIndex())
                        && tata.getMaszynaA().get(k).getNumerOperacjiWZadaniu() == samotniA.get(i).getNumerOperacjiWZadaniu()){
                    dziecko.dodajOperacje((Operacja) tata.getMaszynaA().get(k));
                    samotniA.get(i).setIndex(100);
                }
            }
        }

        for(int k=0; k<tata.getMaszynaB().size(); k++) {                    //dopisanie do maszyny A elementow maszyny B ojca którzy zostali w samotnychA od razu usuwamy te zadania z maszynyB ojca
            for(int i=0; i<samotniA.size(); i++){
                if((tata.getMaszynaB().get(k).getIndex() == samotniA.get(i).getIndex())
                        && tata.getMaszynaB().get(k).getNumerOperacjiWZadaniu() == samotniA.get(i).getNumerOperacjiWZadaniu()){
                    dziecko.dodajOperacje((Operacja) tata.getMaszynaB().get(k).toMaszynaA());
                    samotniA.get(i).setIndex(100);
                }
            }
        }

        for(ObiektNaMaszynie o: tata.getMaszynaB()){        //przylacznie do maszyny B elementow z maszyny B ojca
            if(o instanceof Maintenance) continue;
            for(ObiektNaMaszynie e: samotniB){
                if((o.getIndex() == e.getIndex()) && (o.getNumerOperacjiWZadaniu() == e.getNumerOperacjiWZadaniu())){
                    dziecko.dodajOperacje((Operacja) o);
                    e.setIndex(100);
                }
            }
        }

        for(ObiektNaMaszynie o: tata.getMaszynaA()) { //dolozenie brakujacych elementow z A ojca do B dziecka;
            for(ObiektNaMaszynie e: samotniB){
                if((o.getIndex() == e.getIndex()) && (o.getNumerOperacjiWZadaniu() == e.getNumerOperacjiWZadaniu())){
                    dziecko.dodajOperacje((Operacja) o.toMaszynaB());
                    e.setIndex(100);
                }
            }
        }



        System.out.println("=============DZIECKO===========");
        dziecko.WyswietlRozwiazanie();
    }
}
