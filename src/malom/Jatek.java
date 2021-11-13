package malom;

import java.util.Scanner;
import static malom.Pozicio.of;

enum Valaszto{
    gepEllen ,
    ketJatekosMod
}

public class Jatek {

    public static void main(String[] args) {
        Palya palya = new Palya();
        palya.print();
//        System.out.println();
//        palya.mozgat(of(2, 4), of(2, 3));
//        palya.print();
        //palya.szabadCellak(of(4, 5)).forEach(System.out::println);
        System.out.println(palya.malomE(of(2, 3)));
        //palya.futtato();
        palya.nyertes(palya);

        int valasztottOpcio = 0;
        System.out.println("Válassz játékmódot! \n" +
                "(A játkmód kiválasztásához egyetlen számot elég beírnod) \n" +
                "Gép ellen (1) //PILLANATNYILAG NEM ÜZEMEL \n" +
                "Két játékos mód (2) \n");
        Scanner in = new Scanner(System.in);
        String[] sorResz = in.nextLine().split(" ");
         valasztottOpcio += Integer.parseInt(sorResz[0]);

         switch (valasztottOpcio){
             case 1 :
                 System.exit(1);//TODO lerakjuk a babukat felváltva, utána rákattintunk egy bábura
                 break;
             case 2 :
                while(true){
                    System.out.println("Fehér kezd.");
                    palya.print();
                }
         }
    }
}
