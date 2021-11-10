package malom;

import static malom.Pozicio.of;

public class Jatek {

    public static void main(String[] args) {
        Palya palya = new Palya();
        palya.print();
//        System.out.println();
//        palya.mozgat(of(2, 4), of(2, 3));
//        palya.print();
        //palya.szabadCellak(of(4, 5)).forEach(System.out::println);
        System.out.println(palya.malomE(of(2, 3)));

    }

}
