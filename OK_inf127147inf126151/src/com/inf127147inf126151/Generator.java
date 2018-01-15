package com.inf127147inf126151;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public abstract class Generator {
    public static ArrayList<Zadanie> generuj() {

        ArrayList<Zadanie> listaZadan = new ArrayList<Zadanie>();

        int sumaCzasowWszystkichOperacji = 0;  //mierzymy sume wszystkich czasow trwania gdyz czas gotowosci wszystkich operacji nr 1 nie moze byc wiekszy niz 1/4 sumy wszystkich czasow trwania
        for (int i = 0; i < Stale.liczbaZadan; i++) { //tworzenie zadan, i czasow trwania operacji
            Zadanie temp = new Zadanie(i);
            listaZadan.add(temp);
            sumaCzasowWszystkichOperacji += temp.getDwieOperacje().get(0).getCzasTrwania();
            sumaCzasowWszystkichOperacji += temp.getDwieOperacje().get(1).getCzasTrwania();
        }
        for (int i=0; i<Stale.liczbaZadan; i++){ //tworzenie czasow gotowosci na podstawie wczesniejszych czasow trwania
            listaZadan.get(i).getDwieOperacje().get(0).generujCzasGotowosci(sumaCzasowWszystkichOperacji);
        }

        return listaZadan;

//        ArrayList<ObiektNaMaszynie> listaMaintenance = generujMaintenance();
//        for (int i=0;i<listaMaintenance.size();i++){ //test czy dziala. dziala.
//            if(listaMaintenance.get(i).getClass()==Maintenance.class) System.out.println(listaMaintenance.get(i).getCzasStartu());;
//        }


        //TODO: to powinno byc w petli for (int i=0; i<Stale.domyslnaWielkoscPopulacji; i++) ale zostawiłem bez na razie, dla wygody przegladu.
//        Rozwiazanie rozwiazanie1 = generujRozwiazanie(listaZadan,listaMaintenance);
//        rozwiazanie1.WyswietlRozwiazanie();
//        return rozwiazanie1;
    }

    public static ArrayList<ObiektNaMaszynie> generujMaintenance(){  //generuje zadana w stalych liczbe maintenance. maintenance sa posortowane i nie nachodza na siebie
        int startPoprzedniego = 0;
        int czasTrwaniaPoprzedniego=0;

        ArrayList<ObiektNaMaszynie> listaMaintenance = new ArrayList<>();

        for (int i=0; i<Stale.liczbaMaintenance; i++){
            int start = startPoprzedniego+czasTrwaniaPoprzedniego + (int)(Math.random()*Stale.maxCzasStartuMaintenance);
            int czasTrwania = (int)(Math.random()*Stale.maxCzasTrwaniaMaintenance);
            listaMaintenance.add(new Maintenance(start,czasTrwania));
            startPoprzedniego=start;
            czasTrwaniaPoprzedniego=czasTrwania;
        }

        return listaMaintenance;
    }

    //TODO: PAMIETAC o dodaniu kopiowania listy zadan bo inaczej to troche chujnia z grzybnia. wtedy mozna by zostawic listeZadan statyczna, jako jakis punkt odniesienia, a pracowac przy tworzeniu jednego z rozwiazan na kopii.
    public static Rozwiazanie generujRozwiazanie(ArrayList<Zadanie> listaZadan2, ArrayList<ObiektNaMaszynie> listaMaintenance){
        Rozwiazanie rozwiazanie = new Rozwiazanie(listaMaintenance);
        ArrayList<Zadanie> listaZadan = new ArrayList<>(listaZadan2);

        ArrayList<ArrayList<Integer>> pomocnicza = new ArrayList<>();
        for (int i=0; i<listaZadan.size();i++){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(0);
            temp.add(1);
            temp.add(i);
            pomocnicza.add(temp);
        }

//        for (int i=0; i<50;i++){
//            System.out.println(pomocnicza.get(i));
//        }

        while(pomocnicza.size()!=0) {
            int i = (int) (Math.random()*pomocnicza.size());
            int j = pomocnicza.get(i).get(0);
            Zadanie tempOperacja = listaZadan.get(pomocnicza.get(i).get(pomocnicza.get(i).size()-1));
            rozwiazanie.dodajOperacje(tempOperacja.getDwieOperacje().get(j));
            pomocnicza.get(i).remove(0);

            if (pomocnicza.get(i).size() == 1) pomocnicza.remove(i);
        }
        return rozwiazanie;
    }
}
