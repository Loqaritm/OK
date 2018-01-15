package com.inf127147inf126151;

import java.util.ArrayList;

public class Rozwiazanie {
    private ArrayList<ObiektNaMaszynie> maszynaA = new ArrayList<>(0);
    private ArrayList<ObiektNaMaszynie> maszynaB = new ArrayList<>(0);
    private int aktualnyCzasA=0;
    private int aktualnyCzasB=0;
    private int pozycjaNaMaszynieB=0;
    private int iloscMaintenanceLeft;


    //TODO: tu mozna za pomoca roznych konstruktorow zrobic sposoby generowania (juz zrobiony) i wkladania elementow przy mutacji itp. nie wiem. moze.
    public Rozwiazanie(){}

    public Rozwiazanie(ArrayList<ObiektNaMaszynie> listaMaintenance) {
        this.maszynaA = new ArrayList<>();
        this.maszynaB = new ArrayList<ObiektNaMaszynie>(listaMaintenance);
        iloscMaintenanceLeft=listaMaintenance.size();
    }

    public void dodajOperacje(Operacja operacja){
        if (operacja.isMaszynaA()) {
            this.maszynaA.add(operacja);
            if (operacja.getCzasGotowosci()>aktualnyCzasA){
                operacja.setCzasStartu(aktualnyCzasA+operacja.getCzasGotowosci());
                aktualnyCzasA+=operacja.getCzasGotowosci()+operacja.getCzasTrwania();
                operacja.setCzasZakonczenia(aktualnyCzasA);
                operacja.przypiszCzasGotowosciDrugiejOperacji();
                return;
            }
            else{
                operacja.setCzasStartu(aktualnyCzasA);
                aktualnyCzasA+=operacja.getCzasTrwania();
                operacja.setCzasZakonczenia(aktualnyCzasA);
                operacja.przypiszCzasGotowosciDrugiejOperacji();
                return;
            }
        }

        else{                                           //jezeli operacja na maszynie B
            if (iloscMaintenanceLeft==0){               //jezeli nie ma przed nami zadnych maintenance to NIE MOZEMY sprobowac dostac obiektu na nastepnej pozycji
                maszynaB.add(operacja);
                operacja.setCzasStartu(aktualnyCzasB);
                aktualnyCzasB+=operacja.getCzasTrwania();
                operacja.przypiszCzasGotowosciDrugiejOperacji();
                return;
            }

            else {                                              //jezeli obiekt "nastepny" to maintenance
                ObiektNaMaszynie obiektNaMaszynie=maszynaB.get(pozycjaNaMaszynieB);

                if(operacja.getCzasStartu()<aktualnyCzasB && (aktualnyCzasB+operacja.getCzasTrwania())<obiektNaMaszynie.getCzasStartu()){ //jezeli operacja zmiesci sie przed maintenance
                    maszynaB.add(pozycjaNaMaszynieB,operacja);
                    operacja.setCzasStartu(aktualnyCzasB);
                    pozycjaNaMaszynieB++;
                    aktualnyCzasB+=operacja.getCzasTrwania();
                    operacja.setCzasZakonczenia(aktualnyCzasB);
                    operacja.przypiszCzasGotowosciDrugiejOperacji();
                    return;
                }
                else if(operacja.getCzasStartu()>aktualnyCzasB && (operacja.getCzasStartu()+operacja.getCzasTrwania())<obiektNaMaszynie.getCzasStartu()){
                    maszynaB.add(pozycjaNaMaszynieB,operacja);
                    pozycjaNaMaszynieB++;
                    aktualnyCzasB=operacja.getCzasStartu()+operacja.getCzasTrwania();
                    operacja.setCzasStartu(aktualnyCzasB);
                    operacja.setCzasZakonczenia(aktualnyCzasB);
                    operacja.przypiszCzasGotowosciDrugiejOperacji();
                    return;
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
            String mB = (maszynaB.get(i).getClass()==Maintenance.class) ? "MAINTENANCE " + maszynaB.get(i).getCzasStartu()
                    + ":" + maszynaB.get(i).getCzasTrwania():
                    Integer.toString(maszynaB.get(i).getIndex()) + "_"+
                            Integer.toString(maszynaB.get(i).getNumerOperacjiWZadaniu()) + " " + maszynaB.get(i).getCzasStartu2()
                    + ":" + maszynaB.get(i).getCzasTrwania();
            String mA;
            if(maszynaA.size()> i){
                mA = Integer.toString(maszynaA.get(i).getIndex()) + "_"+
                        Integer.toString(maszynaA.get(i).getNumerOperacjiWZadaniu()) + " " + maszynaA.get(i).getCzasStartu2()
                            + ":" + maszynaA.get(i).getCzasTrwania();
                if(maszynaA.get(i).getIndex() >=10){
                    System.out.println( i +" Zadanie nr: "+ mA + " | " + mB);
                }
                else{
                    System.out.println( i +" Zadanie nr: "+ mA + "  | " + mB);        //dadana spacja zeby sie rowno wyswietlalo :)
                }
            }
            else{
                mA = " ";
                System.out.println( i +" Zadanie nr: "+ mA + "  | " + mB);
            }
        }
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

}
