package com.inf127147inf126151;

public abstract class Stale {
    static int liczbaZadan = 50;
    static int maxCzasTrwaniaOperacji = 50;
    static int maxCzasStartuMaintenance = 300; //maksymalny czas rozpoczęcia maintenance po poprzednim maintenance (w przypadku pierwszego, od poczatku maszyny)
    static int maxCzasTrwaniaMaintenance = 40;
    static int liczbaMaintenance = 12; //k >= n/8 wrzucilem tutaj zeby moc zmieniac recznie jezeli chcemy. zeby miec parametry do zabawy
    static int domyslnaWielkoscPopulacji = 50;
}
