package ru.spbau.group202.sharkova.hw2;

public class Main {

    public static void main(String[] args) {
        SpiralMatrix sp = new SpiralMatrix(3);
        sp.printElementsInSpiralOrder(System.out);
        System.out.println("");
        sp.sortColumnsByFirstElements();
        sp.printElementsInSpiralOrder(System.out);

        System.out.println("");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sp.setValue(i, j, i + j * 3);
            }
        }

        sp.printElementsInSpiralOrder(System.out);
        System.out.println("");
        sp.sortColumnsByFirstElements();
        sp.printElementsInSpiralOrder(System.out);
    }

}
