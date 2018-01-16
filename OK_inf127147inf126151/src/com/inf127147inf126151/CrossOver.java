package com.inf127147inf126151;

import javafx.beans.binding.ObjectBinding;

import java.util.ArrayList;

public class CrossOver {
    private Rozwiazanie dziecko;

    private ArrayList<ObiektNaMaszynie> samotniA = new ArrayList<>();
    private ArrayList<ObiektNaMaszynie> samotniB = new ArrayList<>();

    public Rozwiazanie krzyzuj(Rozwiazanie mama, Rozwiazanie tata){

        System.out.println(sprawdz(mama));
        System.out.println(sprawdz(tata));

        ArrayList<ObiektNaMaszynie> maintenance = new ArrayList<>();
        for(int i=0; i<tata.getMaszynaB().size(); i++){
            if(tata.getMaszynaB().get(i).getClass() == Maintenance.class){
                maintenance.add(tata.getMaszynaB().get(i));
            }
        }

        Rozwiazanie dziecko = new Rozwiazanie(maintenance);

        ArrayList<ObiektNaMaszynie> temp = mama.getMaszynaA();
        for(int i=0; i<(1+ temp.size())/2; i++){                        //przepisanie połowy kodu A matki
            dziecko.dodajOperacje((Operacja) temp.get(i));
            samotniA.add(temp.get(temp.size() - i -1));                //tworzenie samotnych A
        }

        temp = mama.getMaszynaB();
        for(int i=0; i<(1+temp.size())/2; i++){                        //przepisanie poowy kodu B matki
            if(!(temp.get(i) instanceof Maintenance)){
                dziecko.dodajOperacje((Operacja) temp.get(i));
            }
            if(!(temp.get(temp.size() -1 -i) instanceof Maintenance)){
                samotniB.add(temp.get(temp.size() -1 -i));              //stworzenie samotnych B
            }
        }

        for(ObiektNaMaszynie o: tata.getMaszynaA()){
            for(ObiektNaMaszynie s: samotniA){
                if((o.getIndex() == s.getIndex()) && (o.getNumerOperacjiWZadaniu() == s.getNumerOperacjiWZadaniu())){
                    dziecko.dodajOperacje((Operacja) o);
                    samotniA.remove(s);
                    break;
                }

            }
        }

        for(ObiektNaMaszynie o: tata.getMaszynaB()){
            if(o instanceof Maintenance) continue;
            for(ObiektNaMaszynie s: samotniB){
                if((o.getIndex() == s.getIndex()) && (o.getNumerOperacjiWZadaniu() == s.getNumerOperacjiWZadaniu())){
                    dziecko.dodajOperacje((Operacja) o);
                    samotniB.remove(s);
                    break;
                }
            }
        }

        this.dziecko = dziecko;
//        System.out.println("==============DZIECKO==============");
//        dziecko.WyswietlRozwiazanie();

        //sprawdz(dziecko);

        return funkcjaNaprawcza(maintenance);
    }

    public Rozwiazanie funkcjaNaprawcza(ArrayList<ObiektNaMaszynie> maintenance) {

        Rozwiazanie zdroweDziecko = new Rozwiazanie(maintenance);
        Rozwiazanie mutant = new Rozwiazanie(maintenance);

        boolean flagA;
        boolean flagB;


        mutant = dziecko;
        zdroweDziecko = new Rozwiazanie(maintenance);

        int k=0;

        while(!sprawdz(mutant)){

            if(k>50) return null;
            k++;

            for(ObiektNaMaszynie o: mutant.getMaszynaA()){
                if(o.getNumerOperacjiWZadaniu() == 1){
                    zdroweDziecko.dodajOperacje((Operacja) o);
                    for(ObiektNaMaszynie e: mutant.getMaszynaB()){
                        if(e instanceof Maintenance) continue;
                        if(o.getIndex() == e.getIndex())e.setCzasGotowosci(o.getCzasZakonczenia());
                    }
                }
                else{
                    //System.out.println(o.getIndex());
                    for(ObiektNaMaszynie e:mutant.getMaszynaB()){
                        if(e instanceof Maintenance) continue;
                        if(o.getIndex() == e.getIndex()){
                            o.setCzasGotowosci(e.getCzasZakonczenia());
                            zdroweDziecko.dodajOperacje((Operacja) o);
                            break;
                        }

                    }
                }
            }

            for(ObiektNaMaszynie o: mutant.getMaszynaB()){
                int a =1;
                if(o instanceof Maintenance) continue;
                if(o.getNumerOperacjiWZadaniu() == 1){
                    zdroweDziecko.dodajOperacje((Operacja) o);
                    for(ObiektNaMaszynie e: mutant.getMaszynaA()){
                        if(o.getIndex() == e.getIndex())e.setCzasGotowosci(o.getCzasZakonczenia());
                    }
                }
                else{
                    for(ObiektNaMaszynie e:mutant.getMaszynaA()){
                        if(o.getIndex() == e.getIndex()){
                            o.setCzasGotowosci(e.getCzasZakonczenia());
                            zdroweDziecko.dodajOperacje((Operacja) o);
                            break;
                        }

                    }
                }
            }
            mutant = zdroweDziecko;
            zdroweDziecko = new Rozwiazanie(maintenance);
        }

//        System.out.println("===========zdrowe dziecko==============");
//        mutant.WyswietlRozwiazanie();
//
//        System.out.println(sprawdz(mutant));


        return mutant;
    }

    private static boolean sprawdz(Rozwiazanie rozwiazanie){
        for (ObiektNaMaszynie o: rozwiazanie.getMaszynaA()){
            for(ObiektNaMaszynie e: rozwiazanie.getMaszynaB()){
                if(e instanceof Maintenance) continue;
                if(o.getIndex() == e.getIndex()){
                    if(o.getNumerOperacjiWZadaniu() == 1){
                        if(o.getCzasStartu2()>e.getCzasStartu2()){
                            return false;
                        }
                    }else{
                        if(o.getCzasStartu2()<e.getCzasStartu2()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
