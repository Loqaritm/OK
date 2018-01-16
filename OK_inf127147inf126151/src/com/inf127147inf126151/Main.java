package com.inf127147inf126151;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public final static ArrayList<Zadanie> listaZadan =Generator.generuj();
    public final static ArrayList<ObiektNaMaszynie> listaMaintenance = Generator.generujMaintenance();

    public static void main(String[] args) {
//        Maszyna maszyna = new Maszyna();
//        maszyna.wyswietlMaintenance();

//        ArrayList<Zadanie> listaZadan = new ArrayList<>();
//        ArrayList<ObiektNaMaszynie> listaMaintenance = new ArrayList<>();
//        listaZadan = Generator.generuj();
//        listaMaintenance = Generator.generujMaintenance();

        ArrayList<Rozwiazanie> listaRozwiazan = new ArrayList<>();

        int size = 2;
        for(int i=0; i<size; i++){
            listaRozwiazan.add(Generator.generujRozwiazanie(listaZadan,listaMaintenance));
            listaRozwiazan.get(i).WyswietlRozwiazanie();
        }


        Krzyzowanie k = new Krzyzowanie();
        Rozwiazanie dziecko = new Rozwiazanie();
        for (int i=0; i<size-1; i++){
            dziecko = k.krzyzuj(listaRozwiazan.get(i),listaRozwiazan.get(i+1));
        }

        sprawdz(dziecko);

    }

    private static void sprawdz(Rozwiazanie rozwiazanie){
        for (ObiektNaMaszynie o: rozwiazanie.getMaszynaA()){
            for(ObiektNaMaszynie e: rozwiazanie.getMaszynaB()){
                if(o.getIndex() == e.getIndex()){
                    if(o.getNumerOperacjiWZadaniu() == 1){
                        if(o.getCzasStartu2()>e.getCzasStartu2()){
                            System.out.println("WYPIERDOLILEM BLAD");
                            System.out.println(o.getIndex());
                        }
                    }else{
                        if(o.getCzasStartu2()<e.getCzasStartu2()){
                            System.out.println("WYPIERDOLILEM BLAD");
                            System.out.println(o.getIndex());
                        }
                    }
                }
            }
        }
    }

}
