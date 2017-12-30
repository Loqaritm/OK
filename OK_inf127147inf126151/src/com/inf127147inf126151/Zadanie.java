package com.inf127147inf126151;

import java.util.ArrayList;

public class Zadanie {
    private final int index;
    private ArrayList<Operacja> dwieOperacje= new ArrayList<>(); //lista tylko dwoch operacji, jednej dla maszyny A, drugiej dla B

    public Zadanie(int index) {
        this.index = index;
        boolean maszynaA=generujMaszyne(); //maszyna A czy maszyna B
        this.dwieOperacje.add(new Operacja(maszynaA,index));
        this.dwieOperacje.add(new Operacja(!maszynaA,index));
    }

    private boolean generujMaszyne(){
        int a = (int)(Math.random()*2);
        if (a==0){
            return true;
        }
            return false;
    }

    public ArrayList<Operacja> getDwieOperacje() {
        return dwieOperacje;
    }


    public int getIndex() {
        return index;
    }
}
