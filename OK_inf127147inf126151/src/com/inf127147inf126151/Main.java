package com.inf127147inf126151;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Maszyna maszyna = new Maszyna();
//        maszyna.wyswietlMaintenance();

        ArrayList<Zadanie> listaZadan = new ArrayList<>();
        ArrayList<ObiektNaMaszynie> listaMaintenance = new ArrayList<>();
        listaZadan = Generator.generuj();
        listaMaintenance = Generator.generujMaintenance();

        List<Rozwiazanie> listaRozwiazan = new ArrayList<>();
        int size = 2;
        for(int i=0; i<size; i++){
            listaRozwiazan.add(Generator.generujRozwiazanie(listaZadan,listaMaintenance));
            listaRozwiazan.get(i).WyswietlRozwiazanie();
        }

        for (int i=0; i<size-1; i++){
            Krzyzowanie.krzyzuj(listaRozwiazan.get(i),listaRozwiazan.get(i+1));
        }

    }

}
