package com.inf127147inf126151;

public class Operacja extends ObiektNaMaszynie{
    private int czasGotowosci;
    private int czasTrwania;
    private boolean maszynaA;
    private int index;

    public Operacja(boolean czyMaszynaA, int index) {
        generujCzasTrwania();
        this.maszynaA=czyMaszynaA;
        this.index=index;
    }

    private void generujCzasTrwania(){
        this.czasTrwania=(int)(Math.random()*Stale.maxCzasTrwaniaOperacji);
    }

    public void generujCzasGotowosci(int sumaCzasowWszystkichOperacji){
        this.czasGotowosci=(int)(Math.random()*sumaCzasowWszystkichOperacji/4);
    }

    @Override
    public int getCzasStartu() {
        return this.czasGotowosci;
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
}
