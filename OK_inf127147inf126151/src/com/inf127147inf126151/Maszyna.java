package com.inf127147inf126151;

import java.util.ArrayList;


//!!nieuzywana, do wyrzucenia!! EWENTUALNIE WRZUCIC MECHANIZMY Z KLASY ROZWIAZANIE, DLA CZYSTOSCI ALE NIECO TRUDNIESJZEGO ZAPISU

public class Maszyna {
    private int aktualnaPozycja = 0;

    private ArrayList<ObiektNaMaszynie> listaWszystkichObiektowNaMaszynie = new ArrayList<>();

    public Maszyna (){}

    public Maszyna(ArrayList<ObiektNaMaszynie> listaWszystkichObiektowNaMaszynie) {
        this.listaWszystkichObiektowNaMaszynie = listaWszystkichObiektowNaMaszynie;
    }













    //    private ArrayList<Zadanie> listaOperacji = new ArrayList<>();
//    private ArrayList<Maintenance> listaMaintenance = new ArrayList<Maintenance>();
//
//
//
//    public void wyswietlMaintenance(){
//        for( int i=0; i<listaMaintenance.size();i++){
//            System.out.println("maintenance nr "+i);
//            int tempCzasStartu = listaMaintenance.get(i).getCzasStartu();
//            System.out.println("czas startu " + tempCzasStartu);
//            System.out.println("czas zakonczenia " + (tempCzasStartu + listaMaintenance.get(i).getCzasTrwania()));
//            System.out.println("-------------");
//        }
//    }
//
//    public void dodajOperacje(Operacja operacja){
//
//    }
}
