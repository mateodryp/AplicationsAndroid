package com.example.beer_advisor;

public class BeerExpert {
    static String [] brand = new String[3];
    public static String[] getBrands(String color){
        switch (color){
            case "Light":
                brand[0]="Light 1";
                brand[1]="Light 2";
                brand[2]="Light 3";
                break;
            case "Amber":
                brand[0]="Amber 1";
                brand[1]="Amber 2";
                brand[2]="Amber 3";
                break;
            case "Brown":
                brand[0]="Brown 1";
                brand[1]="Brown 2";
                brand[2]="Brown 3";
                break;
            case "Dark":
                brand[0]="Dark 1";
                brand[1]="Dark 2";
                brand[2]="Dark 3";
                break;

        }

        return brand;
    }




}
