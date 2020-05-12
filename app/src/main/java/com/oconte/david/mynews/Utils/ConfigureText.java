package com.oconte.david.mynews.Utils;

public class ConfigureText {

    public static String convertSectionNameForDisplay(String name){
        if (name != null){
            return name.replaceAll(";", " >");
        } else {
            return "No section";
        }
    }
}
