package com.inf127147inf126151;

public abstract class ObiektNaMaszynie{
    protected int czasStartu;
    protected int czasTrwania;
    protected int czasZakonczenia;
    private int index;

    public int getCzasStartu() {
        return czasStartu;
    }

    public void setCzasStartu(int czasStartu) {
        this.czasStartu = czasStartu;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public int getIndex(){
        return index;
    }

    public int getNumerOperacjiWZadaniu(){
        return -1;
    }

    public int getCzasStartu2(){
        return -1;
    }

    public abstract Object toMaszynaA();

    public abstract Object toMaszynaB();

    public void setIndex(int id) {
        this.index = id;
    }

    public Zadanie getZadanieRodzic(){
        return null;
    }

    public int getCzasGotowosci(){
        return -1;
    }

    public int getCzasZakonczenia() {
        return czasZakonczenia;
    }

    public void setCzasZakonczenia(int czasZakonczenia) {
        this.czasZakonczenia = czasZakonczenia;
    }

    public void przypiszCzasGotowosciDrugiejOperacji(){
        System.out.println("zjebalem sie bo to maintenance i nie dzialam");
    }

    public abstract void setCzasGotowosci(int czasGotowosci);
}
