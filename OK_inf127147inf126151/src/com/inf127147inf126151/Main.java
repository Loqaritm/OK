package com.inf127147inf126151;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public final static ArrayList<Zadanie> listaZadan =Generator.generuj();
    public final static ArrayList<ObiektNaMaszynie> listaMaintenance = Generator.generujMaintenance();

    public static void main(String[] args) {

        ArrayList<Rozwiazanie> listaRozwiazan = new ArrayList<>();

        int size = 50;
        for(int i=0; i<size; i++){
            listaRozwiazan.add(Generator.generujRozwiazanie(listaZadan,listaMaintenance));
            //listaRozwiazan.get(i).WyswietlRozwiazanie();
        }


        CrossOver c = new CrossOver();
        for (int i=0; i<size-1; i++){
            listaRozwiazan.add(c.krzyzuj(listaRozwiazan.get(i),listaRozwiazan.get(i+1)));
        }

        System.out.println(listaRozwiazan.size()+ " " + listaRozwiazan);

    }
}

//    private static void sprawdz(Rozwiazanie rozwiazanie){
//        for (ObiektNaMaszynie o: rozwiazanie.getMaszynaA()){
//            for(ObiektNaMaszynie e: rozwiazanie.getMaszynaB()){
//                if(o.getIndex() == e.getIndex()){
//                    if(o.getNumerOperacjiWZadaniu() == 1){
//                        if(o.getCzasStartu2()>e.getCzasStartu2()){
//                            System.out.println("WYPIERDOLILEM BLAD");
//                            System.out.println(o.getIndex());
//                        }
//                    }else{
//                        if(o.getCzasStartu2()<e.getCzasStartu2()){
//                            System.out.println("WYPIERDOLILEM BLAD");
//                            System.out.println(o.getIndex());
//                        }
//                    }
//                }
//            }
//        }
//    }


