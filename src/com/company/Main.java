package com.company;

public class Main {
    public static void main(final String[] args) {
        MyCollection<Integer> arr = new MyCollection<>();

        arr.add(1);
        arr.add(2);

        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        arr.remove(2);

        for (Integer i : arr) {
            System.out.print(i + " ");
        }
    }
}