package com.inf127147inf126151;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Maszyna maszyna = new Maszyna();
//        maszyna.wyswietlMaintenance();
        int size = 2;
        List<Rozwiazanie> listaRozwiazan = new ArrayList<>();
        for(int i=0; i<size; i++){
            listaRozwiazan.add(Generator.generuj());
        }

        for (int i=0; i<size-1; i++){
            Krzyzowanie.krzyzuj(listaRozwiazan.get(i),listaRozwiazan.get(i+1));
        }

    }

}
