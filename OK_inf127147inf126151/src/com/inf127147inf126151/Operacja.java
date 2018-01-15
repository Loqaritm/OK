package com.inf127147inf126151;

public class Operacja extends ObiektNaMaszynie{
    private int czasGotowosci;
    private int czasZakonczenia;
    private boolean maszynaA;
    private int index;
    private Zadanie zadanieRodzic;
    private int numerOperacjiWZadaniu;

    public Operacja(boolean czyMaszynaA, int index, int numerOperacjiWZadaniu, Zadanie zadanieRodzic) { //numerOperacjiWZadaniu - czy 1-wsza czy 2-ga
        generujCzasTrwania();
        this.maszynaA=czyMaszynaA;
        this.index=index;
        this.numerOperacjiWZadaniu=numerOperacjiWZadaniu;
        this.zadanieRodzic = zadanieRodzic;
    }

    private void generujCzasTrwania(){
        this.czasTrwania=(int)(Math.random()*Stale.maxCzasTrwaniaOperacji);
    }

    public void generujCzasGotowosci(int sumaCzasowWszystkichOperacji){
        this.czasGotowosci=(int)(Math.random()*sumaCzasowWszystkichOperacji/4);
    }

    public void przypiszCzasGotowosciDrugiejOperacji(){  //wykonujemy na operacji ktora dodajemy do rozwiazania
        if (this.getNumerOperacjiWZadaniu()==1){            //jezeli ta operacja jest wlasnie pierwsza z zadania to
            this.getZadanieRodzic().getDwieOperacje().get(1).setCzasGotowosci(this.czasZakonczenia); //tworzymy czas gotowosci dla drugiej operacji
        }
    }

    @Override
    public int getCzasStartu() {
        return this.czasGotowosci;
    }  //!!wazne!! bo to moze troche gmatwac kod w klasie Rozwiazanie

    @Override
    public int getCzasStartu2(){
        return this.czasStartu;
    }

    @Override
    public int getCzasTrwania() {
        return this.czasTrwania;
    }

//    public int getCzasTrwaniaOperacji() {
//        return czasTrwania;
//    }

    public boolean isMaszynaA() {
        return maszynaA;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int id){
        this.index = id;
    }

    @Override
    public int getNumerOperacjiWZadaniu() {
        return numerOperacjiWZadaniu;
    }

    public Zadanie getZadanieRodzic() {
        return zadanieRodzic;
    }

    public int getCzasGotowosci() {
        return czasGotowosci;
    }

    public int getCzasZakonczenia() {
        return czasZakonczenia;
    }

    public void setCzasZakonczenia(int czasZakonczenia) {
        this.czasZakonczenia = czasZakonczenia;
    }

    public void setCzasGotowosci(int czasGotowosci) {
        this.czasGotowosci = czasGotowosci;
    }

    @Override
    public Operacja toMaszynaA(){
        this.maszynaA = true;
        return this;
    }

    @Override
    public Operacja toMaszynaB(){
        this.maszynaA = false;
        return this;
    }


}
