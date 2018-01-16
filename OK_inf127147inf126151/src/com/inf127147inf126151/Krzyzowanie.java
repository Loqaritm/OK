package com.inf127147inf126151;

import java.util.ArrayList;

public class Krzyzowanie{

    private Rozwiazanie dziecko;
    private boolean flagA = false;
    private boolean flagB = false;
    private int indexFlagA;
    private int indexFlagB;

    private Rozwiazanie mama = new Rozwiazanie();
    private Rozwiazanie tata = new Rozwiazanie();



    private ArrayList<ObiektNaMaszynie> samotniA = new ArrayList<>();
    private ArrayList<ObiektNaMaszynie> samotniB = new ArrayList<>();

    public Rozwiazanie krzyzuj(Rozwiazanie mama, Rozwiazanie tata) {

        this.mama = mama;
        this.tata = tata;

        ArrayList<ObiektNaMaszynie> maintenance = new ArrayList<>();
        for(int i=0; i<tata.getMaszynaB().size(); i++){
            if(tata.getMaszynaB().get(i).getClass() == Maintenance.class){
                maintenance.add(tata.getMaszynaB().get(i));
            }
        }

        Rozwiazanie dziecko = new Rozwiazanie(maintenance);

        for(int i=0; i<(1+ mama.getMaszynaA().size())/2; i++){                      //przepisanie polowy kodu matki z maszyny A
//            dziecko.dodajOperacje((Operacja) mama.getMaszynaA().get(i));
            dziecko.getMaszynaA().add(mama.getMaszynaA().get(i));
            ObiektNaMaszynie tempObiekt = mama.getMaszynaA().get(mama.getMaszynaA().size() - i -1);

            if (tempObiekt.getNumerOperacjiWZadaniu()==1){
                tempObiekt.getZadanieRodzic().getDwieOperacje().get(1).setCzasGotowosci(-1);
            }
            samotniA.add(tempObiekt);   //stworzenie samotnychA
        }

        int j;
        int mamaSizeB = mama.getMaszynaB().size();
        for(j=0 ; j<(1+mamaSizeB)/2; j++){                                              //przepisanie polowy kodu matki z maszyny B
            if(mama.getMaszynaB().get(j).getClass() != Maintenance.class) {
                dziecko.dodajOperacje((Operacja) mama.getMaszynaB().get(j));
            }
            if(!(mama.getMaszynaB().get(mamaSizeB -j -1) instanceof Maintenance)){
                mama.getMaszynaB().get(mamaSizeB -j -1).getZadanieRodzic().getDwieOperacje().get(1).setCzasGotowosci(-1);
                samotniB.add(mama.getMaszynaB().get(mamaSizeB -j -1));              //stworzenie samotnychB
            }
        }

        ////////////////////////dodanie ogona maszynom :////////////////////



        ////////////////////////////////////////dopisanie drugiej polowy kodu////////////////////////////////
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int k=0; k<tata.getMaszynaA().size(); k++) {                    //dopisanie do maszyny A elementow maszyny A ojca ktore sa w samotnychA
//                    for(int i=0; i<samotniA.size(); i++){
//                        if((tata.getMaszynaA().get(k).getIndex() == samotniA.get(i).getIndex())
//                                && tata.getMaszynaA().get(k).getNumerOperacjiWZadaniu() == samotniA.get(i).getNumerOperacjiWZadaniu()){
//                            boolean zmiana = false;
//                            while (tata.getMaszynaA().get(k).getCzasStartu() == -1){
//                                System.out.println("czekam AAAAA" + tata.getMaszynaA().get(k).getIndex());
//                                zmiana = true;
//
//                            }
//                            if(zmiana){
//                                System.out.println(tata.getMaszynaA().get(k).getIndex()+" zmienilem sie na : "+tata.getMaszynaA().get(k).getCzasStartu());
//                                zmiana = false;
//                            }
//                            dziecko.dodajOperacje((Operacja) tata.getMaszynaA().get(k));
//                            samotniA.remove(i);
//                            break;
//                        }
//                    }
//                }
//            }
//        });
//        t1.setDaemon(true);
//        t1.start();
//
//        for(ObiektNaMaszynie o: tata.getMaszynaB()){        //przylacznie do maszyny B elementow z maszyny B ojca
//            if(o instanceof Maintenance) continue;
//            for(ObiektNaMaszynie e: samotniB){
//                if((o.getIndex() == e.getIndex()) && (o.getNumerOperacjiWZadaniu() == e.getNumerOperacjiWZadaniu())){
//                    boolean zm = false;
//                    while (o.getCzasStartu() == -1){
//                        System.out.println("czekam BBBB"+ o.getIndex());
//                        zm = true;
//                    }
//                    if(zm){
//                        System.out.println(e.getIndex()+" zmienilem sie na : "+e.getCzasStartu());
//                        zm = false;
//                    }
//
//                    dziecko.dodajOperacje((Operacja) o);
//                    samotniB.remove(e);
//                    break;
//                }
//            }
//        }
//
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("==============DZIECKO==============");
        dziecko.WyswietlRozwiazanie();
        return dziecko;

    }
}
