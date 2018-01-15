package com.inf127147inf126151;

public class Maintenance extends ObiektNaMaszynie implements Comparable<Maintenance>{

//    public Maintenance() {
//        this.czasStartu=(int)(Math.random()*Stale.maxCzasStartuMaintenance);
//        this.czasTrwania=(int)(Math.random()*Stale.maxCzasTrwaniaMaintenance);
//    }

    public Maintenance(int czasStartu, int czasTrwania){
        this.czasStartu=czasStartu;
        this.czasTrwania=czasTrwania;
    }

    @Override
    public int compareTo(Maintenance o) {
        return Integer.compare(this.czasStartu, o.czasStartu);
    }

    @Override
    public Object toMaszynaA() {
        return null;
    }

    @Override
    public Object toMaszynaB() {
        return null;
    }
}
