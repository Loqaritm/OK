package com.inf127147inf126151;

import java.util.ArrayList;

public class Rozwiazanie {
    private ArrayList<ObiektNaMaszynie> maszynaA;
    private ArrayList<ObiektNaMaszynie> maszynaB;
    private int aktualnyCzasB=0;
    private int pozycjaNaMaszynieB=0;
    private int iloscMaintenanceLeft;


    //TODO: tu mozna za pomoca roznych konstruktorow zrobic sposoby generowania (juz zrobiony) i wkladania elementow przy mutacji itp. nie wiem. moze.
    public Rozwiazanie(){}

    public Rozwiazanie(ArrayList<ObiektNaMaszynie> listaMaintenance) {
        this.maszynaA = new ArrayList<>();
        this.maszynaB = listaMaintenance;
        iloscMaintenanceLeft=listaMaintenance.size();
    }

    public void dodajOperacje(Operacja operacja){
        if (operacja.isMaszynaA()) this.maszynaA.add(operacja);

        else{                                           //jezeli operacja na maszynie B
            if (iloscMaintenanceLeft==0){               //jezeli nie ma przed nami zadnych maintenance to NIE MOZEMY sprobowac dostac obiektu na nastepnej pozycji
                maszynaB.add(operacja);
                return;
            }

//            if(obiektNaMaszynie.getClass()!=Maintenance.class){  //jezeli obiekt "nastepny" nie jest maintenance to:
//                if(operacja.getCzasStartu()<aktualnyCzasB) { // sprawdz czy element mozna wlozyc zaraz po poprzednim elemencie
//                    pozycjaNaMaszynieB++;
//                    maszynaB.add(pozycjaNaMaszynieB,operacja);
//                    aktualnyCzasB+=operacja.getCzasTrwania();
//                }
//                else{                                               //jezeli nie to poczekaj i wloz go gdy bedzie gotowy
//                    pozycjaNaMaszynieB++;
//                    maszynaB.add(pozycjaNaMaszynieB,operacja);
//                    aktualnyCzasB=operacja.getCzasStartu()+operacja.getCzasTrwania();
//                }
//            }
            else {                                              //jezeli obiekt "nastepny" to maintenance
                ObiektNaMaszynie obiektNaMaszynie=maszynaB.get(pozycjaNaMaszynieB);

                if(operacja.getCzasStartu()<aktualnyCzasB && (aktualnyCzasB+operacja.getCzasTrwania())<obiektNaMaszynie.getCzasStartu()){ //jezeli operacja zmiesci sie przed maintenance
                    maszynaB.add(pozycjaNaMaszynieB,operacja);
                    pozycjaNaMaszynieB++;
                    aktualnyCzasB+=operacja.getCzasTrwania();
                }
                else if(operacja.getCzasStartu()>aktualnyCzasB && (operacja.getCzasStartu()+operacja.getCzasTrwania())<obiektNaMaszynie.getCzasStartu()){
                    maszynaB.add(pozycjaNaMaszynieB,operacja);
                    pozycjaNaMaszynieB++;
                    aktualnyCzasB=operacja.getCzasStartu()+operacja.getCzasTrwania();
                }
                else{                                           //jezeli nie zmiesci sie przed maintenance
                    pozycjaNaMaszynieB++;                                                               //przesun sie element dalej
                    aktualnyCzasB=obiektNaMaszynie.getCzasStartu()+obiektNaMaszynie.getCzasTrwania();   //zmien czas na ten po maintenance
                    iloscMaintenanceLeft--;                                                             //wazne! zmniejsza ilosc maintenance przed nami
                    dodajOperacje(operacja);                                                            //sprobuj wlozyc element po maintenance
                }
            }
        }
    }


    //TODO: Poprawic jakosc wyswietlania zeby to mialo wiecej sensu dla czlowieka i mialo wiecej informacji.
    public void WyswietlRozwiazanie(){
        System.out.println("Maszyna A: ");
        for (int i=0; i<maszynaA.size();i++){
            System.out.println("Zadanie nr: "+maszynaA.get(i).getIndex());
        }
        System.out.println("==========");
        System.out.println("Maszyna B: ");
        for (int i=0; i<maszynaB.size();i++){
            if(maszynaB.get(i).getClass()==Maintenance.class) System.out.println("MAINTENANCE");
            else{
                System.out.println("Zadanie nr: "+maszynaB.get(i).getIndex());
            }
        }
    }
}
