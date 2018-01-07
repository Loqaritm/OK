package com.inf127147inf126151;

import java.util.ArrayList;

public class Rozwiazanie {
    private ArrayList<ObiektNaMaszynie> maszynaA = new ArrayList<>();
    private ArrayList<ObiektNaMaszynie> maszynaB = new ArrayList<>();
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
                else{                                                                                   //jezeli nie zmiesci sie przed maintenance
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
        System.out.println("Maszyna A | B ");
        for (int i=0; i<maszynaB.size();i++){
            String mB = (maszynaB.get(i).getClass()==Maintenance.class) ? "MAINTENANCE" : Integer.toString(maszynaB.get(i).getIndex());
            String mA;
            if(maszynaA.size()> i){
                mA = Integer.toString(maszynaA.get(i).getIndex());
                if(maszynaA.get(i).getIndex() >=10){
                    System.out.println(" Zadanie nr: "+ mA + " | " + mB);
                }
                else{
                    System.out.println(" Zadanie nr: "+ mA + "  | " + mB);        //dadana spacja zeby sie rowno wyswietlalo :)
                }
            }
            else{
                mA = " ";
                System.out.println(" Zadanie nr: "+ mA + "  | " + mB);
            }
        }
//        System.out.println("==========");
//        System.out.println("Maszyna B: ");
//        for (int i=0; i<maszynaB.size();i++){
//            if(maszynaB.get(i).getClass()==Maintenance.class) System.out.println("MAINTENANCE");
//            else{
//                System.out.println("Zadanie nr: "+maszynaB.get(i).getIndex());
//            }
//        }
    }

    public ArrayList<ObiektNaMaszynie> getMaszynaA() {
        return maszynaA;
    }

    public void setMaszynaA(ArrayList<ObiektNaMaszynie> maszynaA) {
        this.maszynaA = maszynaA;
    }

    public ArrayList<ObiektNaMaszynie> getMaszynaB() {
        return maszynaB;
    }

    public void setMaszynaB(ArrayList<ObiektNaMaszynie> maszynaB) {
        this.maszynaB = maszynaB;
    }

    public void addSinglyMaszynaA(ObiektNaMaszynie o){
        this.maszynaA.add(o);
    }
    public void addSinglyMaszynaB(ObiektNaMaszynie o){
        this.maszynaB.add(o);
    }
}
